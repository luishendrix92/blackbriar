package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FMembershipRepository extends CrudRepository<FMembershipEntity, Long> {
}
