package com.bootcamp.blackbriar.service.forum;

import com.bootcamp.blackbriar.model.comments.*;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.repository.AnswerRepository;
import com.bootcamp.blackbriar.repository.FMembershipRepository;
import com.bootcamp.blackbriar.repository.ForumRepository;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class AnswerService {
  @Autowired
  private FMembershipRepository fmembershipRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private ForumRepository forumRepository;

  @Autowired
  private ModelMapper modelMapper;

  // TODO: Separate by update and insert
  public AnswerResponse insertAnswer(long forumId, AnswerRequest answerData, String userId) {
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

  public void deleteAnswer(long answerId) {
    answerRepository.deleteById(answerId);
  }
}
