package com.bootcamp.blackbriar.service.answer;

import com.amazonaws.services.apigateway.model.Model;
import com.bootcamp.blackbriar.model.answer.AnswerEntity;
import com.bootcamp.blackbriar.model.answer.AnswerRequest;
import com.bootcamp.blackbriar.model.answer.AnswerResponse;
import com.bootcamp.blackbriar.model.feedback.FeedbackEntity;
import com.bootcamp.blackbriar.model.feedback.FeedbackResponse;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.model.user.UserRest;
import com.bootcamp.blackbriar.repository.AnswerRepository;
import com.bootcamp.blackbriar.repository.FMembershipRepository;
import com.bootcamp.blackbriar.repository.ForumRepository;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import aj.org.objectweb.asm.TypePath;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

  public AnswerResponse insertAnswer(long forumId, AnswerRequest answerData, String userId) {
    String message = "";
    int count = 0;

    ForumEntity forum = forumRepository.findById(forumId)
        .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));

    if ((forum.getRoles().stream().filter(s -> userId.equals(s.getMember().getStudent().getUserId()))
        .findFirst()) == null || (forum.getGroup().getOwner().getUserId().equals(userId))) {
      throw new EntityNotFoundException("Unauthorized access.");
    }

    AnswerEntity answerCreated = modelMapper.map(answerData, AnswerEntity.class);
    AnswerEntity answerObtained = answerRepository.findByStudentId(answerData.getStudentId());

    if (answerObtained != null) {
      count = answerObtained.getCounter();

      if (answerObtained.getValidate()) {
        message = "You have already responded to this forum, and your answer has already been accepted.";
        throw new RuntimeException(message);
      } else if (!answerObtained.getValidate()) {
        count++;
        message = "Your answer has been updated, wait for the teacher to rate your answer.";
        answerCreated.setCounter(count);
      }
    }

    else {
      message = "Your answer has been saved successfully";
      answerCreated.setCounter(1);
    }

    FMembershipEntity student = fmembershipRepository.findById(answerData.getStudentId())
        .orElseThrow(() -> new EntityNotFoundException("This student is not subscribed in this forum."));

    answerCreated.setCreated(new Date());
    answerCreated.setForum(forum);
    answerCreated.setStudent(student);

    answerCreated = answerRepository.save(answerCreated);

    AnswerResponse answerResponse = modelMapper.map(answerCreated, AnswerResponse.class);

    answerResponse.setMessage(message);

    return answerResponse;

  }

  public List<AnswerResponse> getAnswer(long forumId, String userId) {

    ForumEntity forum = forumRepository.findById(forumId)
        .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));

    if ((forum.getRoles().stream().filter(s -> userId.equals(s.getMember().getStudent().getUserId()))
        .findFirst()) == null || (forum.getGroup().getOwner().getUserId().equals(userId))) {
      throw new RuntimeException("Acces Denied.");
    }

    List<AnswerEntity> answersObtained = forum.getAnswers();
   /* List<FeedbackResponse> feedbacks = new ArrayList<FeedbackResponse>();
    AnswerResponse elementAnswer = new AnswerResponse();
    FeedbackResponse elementFeedback = new FeedbackResponse();
    List<FeedbackEntity> feedbacksAux = new ArrayList<FeedbackEntity>();
    UserEntity student = new UserEntity();
    int i = 0, j = 0;*/

    Type AnswerRest = new TypeToken<List<AnswerResponse>>() {
    }.getType();
    List<AnswerResponse> answersSerialized = modelMapper.map(answersObtained, AnswerRest);



   return answersSerialized;

  }

}