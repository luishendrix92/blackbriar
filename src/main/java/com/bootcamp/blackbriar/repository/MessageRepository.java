package com.bootcamp.blackbriar.repository;

import java.util.List;


import com.bootcamp.blackbriar.model.inbox.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
  List<MessageEntity> findByInboxSubjectUserIdAndArchivedOrderByCreatedDesc(
    String subjectId,
    boolean isArchived
  );

  List<MessageEntity> findByInboxSubjectUserIdOrderByCreatedDesc(String subjectId);
}
