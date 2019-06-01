package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumSettingsRepository extends CrudRepository<ForumSettingsEntity, Long> {

}
