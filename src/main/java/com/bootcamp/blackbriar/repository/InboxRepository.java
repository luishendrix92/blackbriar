package com.bootcamp.blackbriar.repository;

import java.util.Optional;

import com.bootcamp.blackbriar.model.inbox.InboxEntity;

import org.springframework.data.repository.CrudRepository;

public interface InboxRepository extends CrudRepository<InboxEntity, Long> {
  Optional<InboxEntity> findBySubjectUserId(String subjectId);
}
