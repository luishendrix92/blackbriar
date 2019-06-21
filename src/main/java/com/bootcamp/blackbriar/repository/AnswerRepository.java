package com.bootcamp.blackbriar.repository;

import java.util.List;
import java.util.Optional;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
  AnswerEntity findByStudentMemberStudentUserId(String studentId);
  List<AnswerEntity> findByStudentMemberStudentUserIdAndForumId(String userId, long forumId);
  Optional<AnswerEntity> findByStudentMemberStudentUserIdAndApproved(String userId, Boolean approved);
}
