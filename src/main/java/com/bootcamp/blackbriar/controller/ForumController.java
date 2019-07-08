package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.forum.ForumService;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import java.lang.reflect.Type;
import java.security.Principal;

import com.bootcamp.blackbriar.model.forum.ForumRequest;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEditRequest;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumResponse;
import com.bootcamp.blackbriar.model.forum.ForumRest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ForumController {
  @Autowired
  ForumService forumService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  InboxService inboxService;

  @GetMapping(value = "/api/forums/{forumId}")
  public ForumResponse getForumDetails(@RequestParam(required = false) Boolean scoreboard, @PathVariable long forumId,
      Principal auth) {
    ForumEntity forum = forumService.fetchForum(forumId);
    ForumResponse response = modelMapper.map(forum, ForumResponse.class);
    boolean userIsStudent = !forum.getGroup().getOwner().getUserId().equals(auth.getName());
    boolean forumHasEnded = new Date().getTime() >= forum.getSettings().getEndDate().getTime();
    boolean hideScoreboard = scoreboard != null && !scoreboard;

    if (userIsStudent && !forumHasEnded || hideScoreboard) {
      response.setScoreboard(new ArrayList<>());
    }

    return response;
  }

  @GetMapping(value = "/api/groups/{groupId}/forums")
  public List<ForumRest> listGroupForums(@PathVariable long groupId, Principal auth) {
    List<ForumEntity> groupForums = forumService.getForumsByGroup(groupId, auth.getName());
    Type forumList = new TypeToken<List<ForumRest>>() {}.getType();

    return modelMapper.map(groupForums, forumList);
  }

  @PostMapping(value = "/api/groups/{groupId}/forums")
  public ForumResponse createForum(
    @PathVariable long groupId,
    @RequestBody ForumRequest forumDetails,
    Principal auth
  ) {
    ForumEntity forum = forumService.createForum(groupId, forumDetails, auth.getName());

    return modelMapper.map(forum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}")
  public ForumResponse editForum(
    @PathVariable long forumId,
    @RequestBody ForumEditRequest data,
    Principal auth
  ) {
    ForumEntity editedForum = forumService.updateForum(forumId, data, auth.getName());
    List<FMembershipEntity> members = editedForum.getScoreboard();

    for (FMembershipEntity member : members) {
      inboxService.sendMessage(
        member.getMember().getStudent().getUserId(),
        editedForum.getId(),
        "Forum details for '" + editedForum.getTitle() + "' were updated by the instructor.",
        "FMUPD"
      );
    }

    return modelMapper.map(editedForum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}/publish")
  public ForumResponse startActivity(@PathVariable long forumId, Principal auth) {
    ForumEntity startedForum = forumService.publishForum(forumId, auth.getName());

    return modelMapper.map(startedForum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}/finish")
  public ForumResponse finishActivity(@PathVariable long forumId, Principal auth) {
    ForumEntity finishedForum = forumService.endForum(forumId, auth.getName());
    List<FMembershipEntity> members = finishedForum.getScoreboard();

    for (FMembershipEntity member : members) {
      inboxService.sendMessage(
        member.getMember().getStudent().getUserId(),
        finishedForum.getId(),
        "Forum activity '" + finishedForum.getTitle() + "' was finalized by the instructor, you are now able to see your final score and roles.",
        "FORUM_SCORES_POPUP"
      );
    }

    return modelMapper.map(finishedForum, ForumResponse.class);
  }

  @DeleteMapping(value = "/api/forums/{forumId}")
  public void delete(@PathVariable long forumId, Principal auth) {
    forumService.removeForum(forumId, auth.getName());
  }
}
