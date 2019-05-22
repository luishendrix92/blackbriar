package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<MembershipEntity, Long> {
  @Query("SELECT m FROM Membership m WHERE m.student.userId = ?1 AND m.group.id = ?2")
  MembershipEntity getByCompositeKey(String studentId, long groupId);
}
