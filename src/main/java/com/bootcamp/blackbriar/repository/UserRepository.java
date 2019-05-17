package com.bootcamp.blackbriar.repository;

import java.util.List;

import com.bootcamp.blackbriar.model.user.GroupMemberResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
  UserEntity findByEmail(String email);

  UserEntity findByUserId(String userId);

  @Query("SELECT new com.bootcamp.blackbriar.model.user.GroupMemberResponse(s.firstName, s.lastName, s.email, s.userId, s.photo, m) FROM Membership m JOIN m.student s WHERE m.group.id = ?1")
  List<GroupMemberResponse> findByGroupId(long groupId);
}
