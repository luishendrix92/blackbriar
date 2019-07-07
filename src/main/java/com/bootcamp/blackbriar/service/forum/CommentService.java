package com.bootcamp.blackbriar.service.forum;

import com.bootcamp.blackbriar.model.comments.*;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.repository.AnswerRepository;
import com.bootcamp.blackbriar.repository.FMembershipRepository;
import com.bootcamp.blackbriar.repository.FeedbackRepository;
import com.bootcamp.blackbriar.repository.ForumRepository;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class CommentService {
  @Autowired
  private InboxService inboxService;

  @Autowired
  private FMembershipRepository fmembershipRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private FeedbackRepository feedbackRepository;

  @Autowired
  private ForumRepository forumRepository;

  @Autowired
  private ModelMapper modelMapper;

  public AnswerEntity insertAnswer(long forumId, CommentRequest answerData, String userId) {
    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
    FMembershipEntity student = fmembershipRepository.findByMemberStudentUserIdAndForumId(userId, forumId)
      .orElse(null);

    if (student == null || forum.getGroup().getOwner().getUserId().equals(userId)) {
      throw new EntityNotFoundException("Instructors and non-members can't answer forums.");
    }

    answerRepository.findByStudentId(student.getId()).ifPresent(
      (_answer) -> { throw new RuntimeException("You already answered this forum."); }
    );

    AnswerEntity created = new AnswerEntity();

    created.setForum(forum);
    created.setStudent(student);
    created.setContent(answerData.getContent());
    created.setFiles(answerData.getFiles());
    
    return answerRepository.save(created);
  }

  public List<AnswerResponse> getAnswers(long forumId, String userId) {
    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
    boolean isInstructor = forum.getGroup().getOwner().getUserId().equals(userId);
    FMembershipEntity student = fmembershipRepository.findByMemberStudentUserIdAndForumId(userId, forumId)
      .orElse(null);

    if (!isInstructor && student == null) {
      throw new RuntimeException("Only instructors and members can view forum comments.");
    }

    List<AnswerEntity> answers = new ArrayList<AnswerEntity>();

    if (isInstructor) {
      answers = forum.getAnswers();
    } else {
      AnswerEntity existingAnswer = answerRepository.findByStudentId(student.getId()).orElse(null);

      if (existingAnswer != null && existingAnswer.getApproved() == Boolean.TRUE) {
        answers = answerRepository.findByForumIdAndApproved(forumId, true);

        for (AnswerEntity answer : answers) {
          answer.setReplies(
            answer.getReplies().stream()
              .filter(feedback -> feedback.isApproved())
              .collect(Collectors.toList())
          );
        }
      } else if (existingAnswer != null) {
        answers.add(existingAnswer);
      }
    }

    Type AnswerRest = new TypeToken<List<AnswerResponse>>() {}.getType();

    return modelMapper.map(answers, AnswerRest);
  }

  public AnswerEntity reviewAnswer(long answerId, boolean willApprove, String userId) {
    AnswerEntity toReview = answerRepository.findById(answerId)
      .orElseThrow(() -> new EntityNotFoundException("This answer does not exist."));
    ForumEntity forum = toReview.getForum();
    
    if (!forum.getGroup().getOwner().getUserId().equals(userId)) {
      throw new RuntimeException("Only the forum owner can review answers.");
    } else if (toReview.getApproved() == Boolean.TRUE) {
      throw new RuntimeException("Approved answers can't be rejected or re-approved.");
    } else if (toReview.getApproved() == Boolean.FALSE && !willApprove) {
      throw new RuntimeException("You already rejected this answer.");
    }

    if (willApprove) {
      ForumSettingsEntity settings = forum.getSettings();
      FMembershipEntity forumMember = toReview.getStudent();

      if (fmembershipRepository.warriorAmount(forum.getId()) < settings.getWarriorLimit()) {
        forumMember.setWarrior(true);
        forumMember.setScore(
          forumMember.getScore() +
          computeWarriorPoints(
            forumMember.getScore(),
            settings.getWarriorPoints(),
            toReview.getAttempts()
          )
        );
      }

      forumMember.setScore(forumMember.getScore() + settings.getValidResponsePoints());
      fmembershipRepository.save(forumMember);
    }

    toReview.setApproved(willApprove);
    
    return answerRepository.save(toReview);
  }

  public FeedbackEntity createFeedback(CommentRequest data, long answerId, String userId) {
    AnswerEntity answer = answerRepository.findById(answerId)
      .orElseThrow(() -> new EntityNotFoundException("Answer not found."));
    FMembershipEntity member = fmembershipRepository
      .findByMemberStudentUserIdAndForumId(userId, answer.getForum().getId())
      .orElse(null);
    FeedbackEntity feedback = new FeedbackEntity();
    
    if (member != null) {
      answerRepository.findByStudentIdAndApproved(member.getId(), Boolean.TRUE)
        .orElseThrow(() -> new RuntimeException("You must have your answer approved."));

      feedback.setStudent(member);
    } else if (answer.getForum().getGroup().getOwner().getUserId().equals(userId)) {
      feedback.setInstructor(answer.getForum().getGroup().getOwner());
      feedback.setApproved(true);
    } else {
      throw new RuntimeException("Only forum owners and members can reply to answers.");
    }

    feedback.setContent(data.getContent());
    feedback.setParent(answer);
    feedback.setFiles(data.getFiles());

    return feedbackRepository.save(feedback);
  }

  public FeedbackEntity reviewFeedback(long feedbackId, boolean approve, String userId) {
    FeedbackEntity feedback = feedbackRepository.findById(feedbackId)
      .orElseThrow(() -> new EntityNotFoundException("Feedback not found."));
    boolean isForumOwner = feedback.getParent()
      .getForum().getGroup().getOwner().getUserId().equals(userId);
    
    if (!isForumOwner) {
      throw new RuntimeException("Only the forum owner can review this feedback.");
    } else if (feedback.getInstructor() != null) {
      throw new RuntimeException("Instructors can't review their own feedback.");
    } else if (feedback.isApproved() == approve) {
      throw new RuntimeException("You already approved or rejected this feedback.");
    }

    feedback.setApproved(approve);

    return feedbackRepository.save(feedback);
  }

  public void deleteFeedback(long feedbackId, String userId) {
    FeedbackEntity feedback = feedbackRepository.findById(feedbackId)
      .orElseThrow(() -> new EntityNotFoundException("Feedback not found."));

    if (!feedback.getParent().getForum().getGroup().getOwner().getUserId().equals(userId)) {
      throw new RuntimeException("Only the forum owner can delete feedback.");
    }

    feedbackRepository.deleteById(feedbackId);

    FMembershipEntity student = feedback.getStudent();

    if (student != null) {
      inboxService.sendMessage(
        student.getMember().getStudent().getUserId(),
        null,
        "One of your replies in forum '" + student.getForum().getTitle() + "' has been deleted by the instructor.",
        "GNRIC"
      );
    }
  }

  public AnswerEntity editAnswer(long answerId, CommentRequest data, String userId) {
    AnswerEntity answer = answerRepository.findById(answerId)
      .orElseThrow(() -> new EntityNotFoundException("This answer does not exist."));
    
    if (!answer.getStudent().getMember().getStudent().getUserId().equals(userId)) {
      throw new RuntimeException("Only the author of the answer can edit it.");
    } else if (answer.getApproved() == Boolean.TRUE) {
      throw new RuntimeException("You can't edit an approved answer.");
    }

    answer.setAttempts(
      answer.getAttempts() +
      (answer.getApproved() == Boolean.FALSE ? 1 : 0)
    );
    answer.setApproved(null);
    answer.setFiles(data.getFiles());
    answer.setContent(data.getContent());

    return answerRepository.save(answer);
  }

  private int computeWarriorPoints(int score, int points, int attempts) {
    int rejections = attempts - 1;
    double warriorPoints = 0;

    if (rejections == 0) {
      warriorPoints = points;
    } else if (rejections < 3) {
      warriorPoints = points - (points * (rejections / 10));
    } else if (rejections > 2 && rejections < 5) {
      warriorPoints = points * 0.4;
    } else {
      warriorPoints = points * 0.1;
    }

    return (int) Math.ceil(warriorPoints);
  }
}
