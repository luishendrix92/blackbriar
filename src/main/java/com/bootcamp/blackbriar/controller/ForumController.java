package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.forum.ForumService;

import java.lang.reflect.Type;
import java.security.Principal;

import com.bootcamp.blackbriar.model.forum.ForumRequest;
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

  @GetMapping(value = "/api/forums/{forumId}")
  public ForumResponse getForumDetails(@RequestParam(required = false) Boolean scoreboard, @PathVariable long forumId,
      Principal auth) {
    ForumEntity forum = forumService.fetchForum(forumId);
    ForumResponse response = modelMapper.map(forum, ForumResponse.class);
    boolean userIsStudent = !forum.getGroup().getOwner().getUserId().equals(auth.getName());
    boolean forumHasNotEnded = forum.getSettings().getEndDate().getTime() > new Date().getTime();
    boolean hideScoreboard = scoreboard != null && !scoreboard;

    if (userIsStudent && forumHasNotEnded || hideScoreboard) {
      response.setScoreboard(new ArrayList<>());
    }

    return response;
  }

  @GetMapping(value = "/api/groups/{groupId}/forums")
  public List<ForumRest> listGroupForums(@PathVariable long groupId, Principal auth) {
    List<ForumEntity> groupForums = forumService.getForumsByGroup(groupId, auth.getName());
    Type forumList = new TypeToken<List<ForumRest>>() {
    }.getType();

    return modelMapper.map(groupForums, forumList);
  }

  @PostMapping(value = "/api/groups/{groupId}/forums")
  public ForumResponse createForum(@PathVariable long groupId, @RequestBody ForumRequest forumDetails, Principal auth) {
    ForumEntity forum = forumService.createForum(groupId, forumDetails, auth.getName());

    return modelMapper.map(forum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}/publish")
  public ForumResponse startActivity(@PathVariable long forumId, Principal auth) {
    ForumEntity startedForum = forumService.publishForum(forumId, auth.getName());

    return modelMapper.map(startedForum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}/finish")
  public ForumResponse finishActivity(@PathVariable long forumId, Principal auth) {
    ForumEntity finishedForum = forumService.endForum(forumId, auth.getName());

    return modelMapper.map(finishedForum, ForumResponse.class);
  }

  @DeleteMapping(value = "/api/forums/{forumId}")
  public void delete(@PathVariable long forumId, Principal auth) {
    forumService.removeForum(forumId, auth.getName());
  }
}
