package com.bootcamp.blackbriar.repository;

import java.util.List;
import java.util.Optional;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
  AnswerEntity findByStudentMemberStudentUserId(String studentId);

  Optional<AnswerEntity> findByStudentMemberStudentUserIdAndForumId(String userId, long forumId);

  Optional<AnswerEntity> findByStudentIdAndApproved(long fMembershipId, Boolean approved);

  Optional<AnswerEntity> findByStudentId(long fMembershipId);

  List<AnswerEntity> findByForumIdAndApproved(long forumId, boolean approved);

  @Query("SELECT COUNT(A.id) FROM Answer A JOIN A.replies F WHERE A.forum.id = ?1 AND F.student.healer = true AND F.approved = true GROUP BY A.id")
  List<Long> validHealerAmount(long forumId);
}
