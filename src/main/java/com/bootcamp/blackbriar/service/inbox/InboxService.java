package com.bootcamp.blackbriar.service.inbox;

import java.util.List;

import com.bootcamp.blackbriar.model.inbox.MessageEntity;

public interface InboxService {
  List<MessageEntity> fetchMessages(String userId, String filter);

  MessageEntity sendMessage(String subjectId, Long actionRef, String message, String type);

  MessageEntity mark(long messageId, boolean read, String userId);
}
