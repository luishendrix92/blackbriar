package com.bootcamp.blackbriar.repository;

import java.util.List;
import java.util.Optional;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FMembershipRepository extends CrudRepository<FMembershipEntity, Long> {
  Optional<FMembershipEntity> findByMemberStudentUserId(String userId);

  List<FMembershipEntity> findByForumId(long forumId);

  Optional<FMembershipEntity> findByMemberStudentUserIdAndForumId(String userId, long forumId);

  @Query("SELECT COUNT(*) FROM ForumMembership M LEFT JOIN M.feedback F WHERE M.forum.id = ?1 AND M.warlock = true AND F IS NULL")
  Long warlockAmount(long forumId);
}
