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

import java.util.Date;
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
    String message = "";
    int count = 0;

    ForumEntity forum = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));

    // TODO: Change to database query (composite key)
    if ((forum.getScoreboard().stream().filter(s -> userId.equals(s.getMember().getStudent().getUserId()))
        .findFirst()) == null || forum.getGroup().getOwner().getUserId().equals(userId)) {
      throw new EntityNotFoundException("Instructors and non-members can't answer forums.");
    }

    AnswerEntity answerCreated = modelMapper.map(answerData, AnswerEntity.class);
    AnswerEntity answerObtained = answerRepository.findByStudentMemberStudentUserId(userId);

    if (answerObtained != null) {
      count = answerObtained.getAttempts();

      if (answerObtained.getApproved()) {
        message = "You have already responded to this forum, and your answer has already been accepted.";
        throw new RuntimeException(message);
      } else if (!answerObtained.getApproved()) {
        count++;
        message = "Your answer has been updated, wait for the teacher to rate your answer.";
        answerCreated.setAttempts(count);
      }
    } else {
      message = "Your answer has been saved successfully";
      answerCreated.setAttempts(1);
    }

    // TODO: Fix this with composite key query
    FMembershipEntity student = fmembershipRepository.findByMemberStudentUserId(userId)
      .orElseThrow(() -> new EntityNotFoundException("This student is not subscribed in this forum."));

    answerCreated.setCreated(new Date());
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

    // TODO: Change to query and add approved answer validation
    if (!isInstructor && fmembershipRepository.findByMemberStudentUserIdAndForumId(userId, forumId) == null) {
      throw new RuntimeException("Only instructors and members can view forum comments.");
    }

    List<AnswerEntity> answersObtained = answerRepository.findByStudentMemberStudentUserIdAndForumId(userId, forumId);

    if (isInstructor || (answersObtained.size() > 0 && answersObtained.get(0).getApproved() == Boolean.TRUE)) {
      answersObtained = forum.getAnswers();
    }

    Type AnswerRest = new TypeToken<List<AnswerResponse>>() {}.getType();

    return modelMapper.map(answersObtained, AnswerRest);
  }
}
