package com.bootcamp.blackbriar.repository;


import com.bootcamp.blackbriar.model.membershipForum.MembershipForumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipForumRepository extends CrudRepository<MembershipForumEntity, Long> {
   /**
    @Query("SELECT m FROM MembershipForum m WHERE m.member.id = ?1 AND m.forum.id = ?2")
    MembershipForumEntity getByCompositeKey(long memberId, long forumId);**/
}
