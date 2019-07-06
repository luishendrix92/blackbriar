package com.bootcamp.blackbriar.repository;

import java.util.List;


import com.bootcamp.blackbriar.model.inbox.MessageEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
  List<MessageEntity> findByInboxSubjectUserIdAndArchivedOrderByCreatedDesc(
    String subjectId,
    boolean isArchived
  );

  List<MessageEntity> findByInboxSubjectUserIdOrderByCreatedDesc(String subjectId);

  @Modifying
  @Query(value = "UPDATE Message M SET archived = true WHERE M.inbox.subject.userId = ?1")
  void markAllAsRead(String subjectId);
}
