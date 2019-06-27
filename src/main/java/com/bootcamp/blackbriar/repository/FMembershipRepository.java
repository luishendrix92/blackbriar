package com.bootcamp.blackbriar.repository;

import java.util.List;
import java.util.Optional;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FMembershipRepository extends CrudRepository<FMembershipEntity, Long> {
  Optional<FMembershipEntity> findByMemberStudentUserId(String userId);
  List<FMembershipEntity> findByForumId(long forumId);
  Optional<FMembershipEntity> findByMemberStudentUserIdAndForumId(String userId, long forumId);
}
