package com.bootcamp.blackbriar.controller;

import java.security.Principal;
import java.util.List;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;
import com.bootcamp.blackbriar.model.comments.AnswerResponse;
import com.bootcamp.blackbriar.model.comments.CommentRequest;
import com.bootcamp.blackbriar.model.comments.FeedbackEntity;
import com.bootcamp.blackbriar.model.comments.FeedbackResponse;
import com.bootcamp.blackbriar.service.forum.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CommentService commentService;

  @GetMapping(value = "api/forums/{forumId}/answers")
  public List<AnswerResponse> getForumComments(@PathVariable long forumId, Principal auth) {
    return commentService.getAnswers(forumId, auth.getName());
  }

  @PostMapping(value = "api/forums/{forumId}/answers")
  public AnswerResponse answerForum(
    @PathVariable long forumId,
    @RequestBody CommentRequest answerData,
    Principal auth
  ) {
    return commentService.insertAnswer(forumId, answerData, auth.getName());
  }

  @PutMapping(value = "api/answers/{answerId}/review")
  public AnswerResponse reviewAnswer(
    @PathVariable long answerId,
    @RequestParam(defaultValue = "true") boolean approve,
    Principal auth
  ) {
    AnswerEntity reviewed = commentService.reviewAnswer(answerId, approve, auth.getName());

    return modelMapper.map(reviewed, AnswerResponse.class);
  }

  @PostMapping(value = "api/answers/{answerId}/replies")
  public FeedbackResponse replyToAnswer(
    @PathVariable long answerId,
    @RequestBody CommentRequest replyData,
    Principal auth
  ) {
    FeedbackEntity commentMade = commentService.createFeedback(replyData, answerId, auth.getName());
    
    return modelMapper.map(commentMade, FeedbackResponse.class);
  }

  @PutMapping(value = "api/feedback/{feedbackId}/review")
  public FeedbackResponse reviewReply(
    @PathVariable long feedbackId,
    @RequestParam(defaultValue = "true") boolean approve,
    Principal auth
  ) {
    FeedbackEntity reviewed = commentService.reviewFeedback(feedbackId, approve, auth.getName());
    
    return modelMapper.map(reviewed, FeedbackResponse.class);
  }

  @DeleteMapping(value = "api/feedback/{feedbackId}")
  public void removeFeedback(@PathVariable long feedbackId, Principal auth) {
    commentService.deleteFeedback(feedbackId, auth.getName());
  }
}
