package com.bootcamp.blackbriar.service.forum;

import com.bootcamp.blackbriar.model.comments.*;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.repository.AnswerRepository;
import com.bootcamp.blackbriar.repository.FMembershipRepository;
import com.bootcamp.blackbriar.repository.FeedbackRepository;
import com.bootcamp.blackbriar.repository.ForumRepository;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class CommentService {
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

  // TODO: Separate by update and insert
  public AnswerResponse insertAnswer(long forumId, CommentRequest answerData, String userId) {
    String message;

    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));

    if (
      fmembershipRepository
        .findByMemberStudentUserIdAndForumId(userId, forumId)
        .orElse(null) == null ||
      forum.getGroup().getOwner().getUserId().equals(userId)
    ) {
      throw new EntityNotFoundException("Instructors and non-members can't answer forums.");
    }

    AnswerEntity answerCreated = modelMapper.map(answerData, AnswerEntity.class);
    AnswerEntity answerObtained = answerRepository.findByStudentMemberStudentUserId(userId);

    if (answerObtained != null) {
      if (answerObtained.getApproved()) {
        throw new RuntimeException("You have already responded to this forum, and your answer has already been accepted.");
      } else {
        message = "Your answer has been updated, wait for the teacher to rate your answer.";

        answerCreated.setAttempts(
          answerCreated.getAttempts() + 1
        );
      }
    } else {
      message = "Your answer has been successfully saved.";
    }

    FMembershipEntity student = fmembershipRepository
      .findByMemberStudentUserIdAndForumId(userId, forumId)
      .orElseThrow(() -> new EntityNotFoundException("User is not subscribed to this forum."));

    answerCreated.setForum(forum);
    answerCreated.setStudent(student);

    answerCreated = answerRepository.save(answerCreated);
    AnswerResponse answerResponse = modelMapper.map(answerCreated, AnswerResponse.class);

    answerResponse.setMessage(message);

    return answerResponse;
  }

  public List<AnswerResponse> getAnswers(long forumId, String userId) {

    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
    boolean isInstructor = forum.getGroup().getOwner().getUserId().equals(userId);

    if (
      !isInstructor &&
      fmembershipRepository
        .findByMemberStudentUserIdAndForumId(userId, forumId)
        .orElse(null) == null
    ) {
      throw new RuntimeException("Only instructors and members can view forum comments.");
    }

    List<AnswerEntity> answers = answerRepository
      .findByStudentMemberStudentUserIdAndForumId(userId, forumId);

    if (isInstructor || (answers.size() > 0 && answers.get(0).getApproved() == Boolean.TRUE)) {
      answers = forum.getAnswers();
    }

    Type AnswerRest = new TypeToken<List<AnswerResponse>>() {}.getType();

    return modelMapper.map(answers, AnswerRest);
  }

  public AnswerEntity reviewAnswer(long answerId, boolean willApprove, String userId) {
    AnswerEntity toReview = answerRepository.findById(answerId)
      .orElseThrow(() -> new EntityNotFoundException("This answer does not exist."));
    
    if (!toReview.getForum().getGroup().getOwner().getUserId().equals(userId)) {
      throw new RuntimeException("Only the forum owner can review answers.");
    } else if (
      toReview.getApproved() == Boolean.TRUE &&
      (willApprove || !willApprove)
    ) {
      throw new RuntimeException("Approved answers can't be rejected or re-approved.");
    } else if (toReview.getApproved() == Boolean.FALSE && !willApprove) {
      throw new RuntimeException("You already rejected this answer.");
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
      answerRepository.findByStudentMemberStudentUserIdAndApproved(userId, Boolean.TRUE)
        .orElseThrow(() -> new RuntimeException("You must have your answer approved."));

      feedback.setStudent(member);
    } else if (answer.getForum().getGroup().getOwner().getUserId().equals(userId)) {
      feedback.setInstructor(answer.getForum().getGroup().getOwner());
    } else {
      throw new RuntimeException("Only forum owners and members can reply to answers.");
    }

    feedback.setContent(data.getContent());
    feedback.setParent(answer);

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
  }
}
