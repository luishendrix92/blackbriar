package com.bootcamp.blackbriar.repository;

import java.util.Optional;

import com.bootcamp.blackbriar.model.inbox.MessageEntity;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
  Optional<MessageEntity> findFirstByInboxSubjectUserIdAndArchivedOrderByCreatedDesc(String subjectId, boolean isArchived);
}
