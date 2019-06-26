package com.bootcamp.blackbriar.repository;

import java.util.Optional;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FMembershipRepository extends CrudRepository<FMembershipEntity, Long> {
  Optional<FMembershipEntity> findByMemberStudentUserId(String userId);
  Optional<FMembershipEntity> findByMemberStudentUserIdAndForumId(String userId, long forumId);
  

  @Query("SELECT COUNT(*) FROM ForumMembership fm WHERE fm.warrior = true AND fm.forum.id =?1")
  int getWarriorCount(long forumId);
}
