package com.bootcamp.blackbriar.service.forum;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;
import com.bootcamp.blackbriar.model.comments.FeedbackEntity;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumRequest;
import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.repository.AnswerRepository;
import com.bootcamp.blackbriar.repository.FMembershipRepository;
import com.bootcamp.blackbriar.repository.FeedbackRepository;
import com.bootcamp.blackbriar.repository.ForumRepository;
import com.bootcamp.blackbriar.repository.ForumSettingsRepository;
import com.bootcamp.blackbriar.repository.GroupRepository;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;

@Service
public class ForumService {
  @Autowired
  private ForumRepository forumRepository;

  @Autowired
  private ForumSettingsRepository settingsRepository;

  @Autowired
  private FMembershipRepository fmRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private FeedbackRepository feedbackRepository;

  @Autowired
  private ForumRoleService roleService;

  @Autowired
  private InboxService inboxService;

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private ModelMapper modelMapper;

  public ForumEntity fetchForum(long forumId) {
    return forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
  }

  public ForumEntity createForum(long groupId, ForumRequest forumDetails, String instructorId) {
    GroupEntity group = groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException("This group does not exist."));
    
    if (!group.getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("Only group owners can add forum activities to them.");
    } else if (forumDetails.getEndDate().getTime() <= new Date().getTime()) {
      throw new RuntimeException("End date should take place in the future.");
    } else if (forumDetails.isPublished() && group.getMembers().size() == 0) {
      throw new RuntimeException("Your group has no members yet.");
    }

    ForumEntity forum = modelMapper.map(forumDetails, ForumEntity.class);
    ForumSettingsEntity settings;

    forum.setGroup(group);

    forum = forumRepository.save(forum);
    settings = modelMapper.map(forumDetails, ForumSettingsEntity.class);

    settings.setForum(forum);
    settings.setStartDate(new Date());
    settingsRepository.save(settings);
    forum.setSettings(settings);

    if (forum.isPublished()) {
      forum.setScoreboard(init(forum));
    }

    return forum;
  }

  public void removeForum(long forumId, String instructorId) {
    ForumEntity toRemove = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
    
    if (!toRemove.getGroup().getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("You are not allowed to delete forums that you didn't create.");
    }

    forumRepository.delete(toRemove);
  }

  public List<ForumEntity> getForumsByGroup(long groupId, String userId) {
    GroupEntity group = groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException("Group not found."));
    
    if (group.getOwner().getUserId().equals(userId)) {
      return forumRepository.findByGroupId(groupId);
    } else {
      return forumRepository.findByGroupIdAndPublished(groupId, true);
    }
  }

  public ForumEntity publishForum(long forumId, String instructorId) {
    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("Forum not found."));
    ForumSettingsEntity settings = forum.getSettings();
    
    // TODO: Test if else/if works instead of separate ifs
    if (forum.isPublished()) {
      throw new RuntimeException("This forum has already started.");
    }
    
    if (!forum.getGroup().getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("Only forum owners can publish them.");
    }
    
    if (settings.getEndDate().getTime() <= new Date().getTime()) {
      throw new RuntimeException("This forum has already ended.");
    }

    if (forum.getGroup().getMembers().size() == 0) {
      throw new RuntimeException("The group has no members yet.");
    }

    init(forum);
    forum.setPublished(true);
    settings.setStartDate(new Date());
    settingsRepository.save(settings);

    return forumRepository.save(forum);
  }

  public ForumEntity endForum(long forumId, String instructorId) {
    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new RuntimeException("Forum not found."));
    List<FMembershipEntity> members = forum.getScoreboard();
    int healerPoints = forum.getSettings().getHealerPoints();
    int warlockPoints = forum.getSettings().getWarlockPoints();

    if (!forum.getGroup().getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("Only the instructor can finish this forum.");
    } else if (new Date().getTime() < forum.getSettings().getEndDate().getTime()) {
      throw new RuntimeException("You can't finish this forum yet, try once the end date has been reached.");
    }

    // TODO: Add new validation: forum has no unreviewed answers

    int healerCount = answerRepository.validHealerAmount(forumId).size();
    int warlockCount = fmRepository.warlockAmount(forumId).intValue();

    for (FMembershipEntity member : members) {
      member.setScore(
        member.getScore() +
        (healerCount * healerPoints) -
        (warlockCount * warlockPoints)
      );

      inboxService.sendMessage(
        member.getMember().getStudent().getUserId(),
        forum.getId(),
        "The forum '" + forum.getTitle() + "' has been finished by the instructor! Your final score is " + member.getScore() + " points." + (member.isHealer() ? " You were a healer." : "") + (member.isWarlock() ? " You were a warlock!" : "") + (member.isWarrior() ? " Congratulations, warrior!" : ""),
        "FMSCR"
      );
    }

    fmRepository.saveAll(members);
    forum.setScoreboard(members);

    return forum;
  }

  private List<FMembershipEntity> init(ForumEntity forum) {
    List<FMembershipEntity> members = roleService.initMembership(forum);
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    long forumEndDelay = forum.getSettings().getEndDate().getTime() -
      TimeUnit.HOURS.toMillis(24) -
      new Date().getTime();

    ses.schedule(oneDayLeft(members), forumEndDelay, TimeUnit.MILLISECONDS);
    ses.shutdown();

    for (FMembershipEntity member : members) {
      inboxService.sendMessage(
        member.getMember().getStudent().getUserId(),
        forum.getId(),
        "A forum activity '" + forum.getTitle() + "' started in group '" + forum.getGroup().getTitle() + ".'",
        "FMSTR"
      );
    }

    return members;
  }

  private Runnable oneDayLeft(List<FMembershipEntity> forumMembers) {
    return () -> {
      System.out.println(forumMembers.size() + " members");
      
      for (FMembershipEntity member : forumMembers) {
        AnswerEntity studentAnswer = answerRepository
          .findByStudentId(member.getId()).orElse(null);
        boolean hasValidResponse = studentAnswer != null
          && studentAnswer.getApproved() == Boolean.TRUE;
        String alertCategory = null;
        String message = null;

        // TODO: Check for missing replies
        if (studentAnswer == null) {
          alertCategory = "FMWLK";
          message = "The forum '" + member.getForum().getTitle() + "' is about to end and you haven't responded yet. You don't want to be the Warlock, do you?";
        } else {
          List<FeedbackEntity> replies = feedbackRepository.findByStudentId(member.getId());

          if (hasValidResponse && replies.size() <= 0) {
            alertCategory = "FMHLA";
            message = "The forum '" + member.getForum().getTitle() + "' is about to end and you haven't given any feedback yet. Don't miss the chance to be a Healer!";
          } else if (replies.stream().anyMatch(reply -> reply.isApproved())) {
            alertCategory = "FMHLI";
            message = "The forum '" + member.getForum().getTitle() + "' is about to end. Remember, only the first healer to reply to an answer will be taken in to consideration to increase points. However, not everyone is a healer!";
          }
        }

        if (alertCategory != null) {
          inboxService.sendMessage(
            member.getMember().getStudent().getUserId(),
            member.getForum().getId(),
            message,
            alertCategory
          );
        }
      }
    };
  }
}
