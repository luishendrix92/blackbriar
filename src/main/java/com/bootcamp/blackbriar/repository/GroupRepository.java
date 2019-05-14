package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
  List<GroupEntity> findByOwnerUserId(String fk_user);
}

