package com.bootcamp.blackbriar.repository;


import com.bootcamp.blackbriar.model.Group.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
}

