package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.model.forum.ForumRequest;
import com.bootcamp.blackbriar.service.forum.AnswerService;
import com.bootcamp.blackbriar.service.forum.ForumService;

import java.lang.reflect.Type;
import java.security.Principal;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;
import com.bootcamp.blackbriar.model.comments.AnswerRequest;
import com.bootcamp.blackbriar.model.comments.AnswerResponse;
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
  private AnswerService answerService;

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

  @DeleteMapping(value = "/api/forums/{forumId}")
  public void delete(@PathVariable long forumId, Principal auth) {
    forumService.removeForum(forumId, auth.getName());
  }

  @GetMapping(value = "api/forums/{forumId}/answers")
  public List<AnswerResponse> getForumComments(@PathVariable long forumId, Principal auth) {
    return answerService.getAnswers(forumId, auth.getName());
  }

  @PostMapping(value = "api/forums/{forumId}/answers")
  public AnswerResponse answerForum(
    @PathVariable long forumId,
    @RequestBody AnswerRequest answerData,
    Principal auth
  ) {
    return answerService.insertAnswer(forumId, answerData, auth.getName());
  }

  @PutMapping(value = "api/answers/{answerId}/review")
  public AnswerResponse reviewAnswer(
    @PathVariable long answerId,
    @RequestParam(defaultValue = "true") boolean approve,
    Principal auth
  ) {
    AnswerEntity reviewed = answerService.reviewAnswer(answerId, approve, auth.getName());

    return modelMapper.map(reviewed, AnswerResponse.class);
  }

  @DeleteMapping(value = "api/answers/{answerId}")
  public void removeAnswer(@PathVariable long answerId) {
    answerService.deleteAnswer(answerId);
  }
}
