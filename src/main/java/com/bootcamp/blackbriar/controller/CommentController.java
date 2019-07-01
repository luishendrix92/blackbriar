package com.bootcamp.blackbriar.controller;

import java.security.Principal;
import java.util.List;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;
import com.bootcamp.blackbriar.model.comments.AnswerResponse;
import com.bootcamp.blackbriar.model.comments.CommentRequest;
import com.bootcamp.blackbriar.model.comments.FeedbackEntity;
import com.bootcamp.blackbriar.model.comments.FeedbackResponse;
import com.bootcamp.blackbriar.model.comments.ReviewRequest;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.service.forum.CommentService;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CommentService commentService;

  @Autowired
  private InboxService inboxService;

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
    AnswerEntity answer = commentService.insertAnswer(forumId, answerData, auth.getName());
    ForumEntity forum = answer.getForum();
    String studentName = answer.getStudent().getMember().getStudent().getFirstName() + " " +
      answer.getStudent().getMember().getStudent().getLastName();

    inboxService.sendMessage(
      forum.getGroup().getOwner().getUserId(),
      forum.getId(),
      "Your forum '" + forum.getTitle() + "' has a new answer sent by '" + studentName  + ".'",
      "FMNWA"
    );

    return modelMapper.map(answer, AnswerResponse.class);
  }

  @PutMapping(value = "api/answers/{anserId}/edit")
  public AnswerResponse editAnswer(
    @PathVariable long answerId,
    @RequestBody CommentRequest answerData,
    Principal auth
  ){
    AnswerEntity answer = commentService.editAnswer(answerId, answerData, auth.getName());
    ForumEntity forum = answer.getForum();
    String studentName = answer.getStudent().getMember().getStudent().getFirstName() + " " +
      answer.getStudent().getMember().getStudent().getLastName();

    inboxService.sendMessage(
      forum.getGroup().getOwner().getUserId(),
      forum.getId(),
      "Your forum '" + forum.getTitle() + "' has a new answer sent by '" + studentName  + ".'",
      "FMNWA"
    );

    return modelMapper.map(answer, AnswerResponse.class);

  }

  @PutMapping(value = "api/answers/{answerId}/review")
  public AnswerResponse reviewAnswer(
    @PathVariable long answerId,
    @RequestBody ReviewRequest review,
    Principal auth
  ) {
    boolean approved = review.isApproved();
    String instructorMessage = review.getReason();
    AnswerEntity reviewed = commentService.reviewAnswer(answerId, approved, auth.getName());
    ForumEntity forum = reviewed.getForum();
    
    inboxService.sendMessage(
      auth.getName(),
      forum.getId(),
      "Your answer in forum '" + forum.getTitle() + "' has been " + (approved ? "approved" : "rejected") + (instructorMessage != null ? " for the following reason: '" + instructorMessage + ".'" : "."),
      "FMARW"
    );

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
    @RequestBody ReviewRequest review,
    Principal auth
  ) {
    boolean approved = review.isApproved();
    String instructorMessage = review.getReason();
    FeedbackEntity reviewed = commentService.reviewFeedback(feedbackId, approved, auth.getName());
    ForumEntity forum = reviewed.getParent().getForum();

    inboxService.sendMessage(
      auth.getName(),
      forum.getId(),
      "Your reply in forum '" + forum.getTitle() + "' has been " + (approved ? "approved" : "rejected") + (instructorMessage != null ? " for the following reason: '" + instructorMessage + ".'" : "."),
      "FMFRW"
    );
    
    return modelMapper.map(reviewed, FeedbackResponse.class);
  }

  @DeleteMapping(value = "api/feedback/{feedbackId}")
  public void removeFeedback(@PathVariable long feedbackId, Principal auth) {
    commentService.deleteFeedback(feedbackId, auth.getName());
  }
}
