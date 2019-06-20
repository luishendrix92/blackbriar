package com.bootcamp.blackbriar.repository;

import java.util.Optional;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FMembershipRepository extends CrudRepository<FMembershipEntity, Long> {
  Optional<FMembershipEntity> findByMemberStudentUserId(String userId);
  Optional<FMembershipEntity> findByMemberStudentUserIdAndForumId(String userId, long forumId);
}
