package com.bootcamp.blackbriar.service.inbox;

// import java.util.List;

import com.bootcamp.blackbriar.model.inbox.MessageEntity;

public interface InboxService {
  //List<MessageEntity> getUserInbox(String userId);

  MessageEntity getLatestMessage(String userId);

  MessageEntity sendMessage(String subjectId, Long actionRef, String message, String type);
}
