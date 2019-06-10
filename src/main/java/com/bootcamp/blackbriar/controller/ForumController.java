package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.model.forum.ForumRequest;
import com.bootcamp.blackbriar.service.forum.ForumService;

import java.lang.reflect.Type;
import java.security.Principal;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForumController {
  @Autowired
  ForumService forumService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping(value = "/api/forums/{forumId}")
  public ForumResponse getForumDetails(@PathVariable long forumId) {
    return modelMapper.map(forumService.fetchForum(forumId), ForumResponse.class);
  }

  @GetMapping(value = "/api/groups/{groupId}/forums")
  public List<ForumResponse> listGroupForums(@PathVariable long groupId, Principal auth) {
    List<ForumEntity> groupForums = forumService.getForumsByGroup(groupId, auth.getName());
    Type forumList = new TypeToken<List<ForumResponse>>(){}.getType();
    
    return modelMapper.map(groupForums, forumList);
  }

  @PostMapping(value = "/api/groups/{groupId}/forums")
  public ForumResponse postMethodName(
    @PathVariable long groupId,
    @RequestBody ForumRequest forumDetails,
    Principal auth
  ) {
    ForumEntity forum = forumService.createForum(groupId, forumDetails, auth.getName());

    return modelMapper.map(forum, ForumResponse.class);
  }

  @PutMapping(value = "/api/forums/{forumId}/publish")
  public ForumResponse startActivity(@PathVariable long forumId, Principal auth) {
    ForumEntity startedForum = forumService.publishForum(forumId, auth.getName());
    startedForum = forumService.fetchForum(forumId);

    return modelMapper.map(startedForum, ForumResponse.class);
  }

  @DeleteMapping(value = "/api/forums/{forumId}")
  public void delete(@PathVariable long forumId, Principal auth) {
    forumService.removeForum(forumId, auth.getName());
  }
}
