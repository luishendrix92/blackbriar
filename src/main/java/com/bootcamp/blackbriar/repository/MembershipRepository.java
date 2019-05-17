package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<MembershipEntity, Long> {
  
}
