package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<MembershipEntity, Long> {
    @Modifying
    @Query(value = "insert in to memberships(fk_user, fk_group, active) values(:student, :group, :active)", nativeQuery = true)
    boolean insertMemberships(@Param("student") long userId, @Param("group") long groupId, @Param("active") boolean active);
}
