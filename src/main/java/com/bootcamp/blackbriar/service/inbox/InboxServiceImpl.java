package com.bootcamp.blackbriar.service.inbox;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bootcamp.blackbriar.model.inbox.InboxEntity;
import com.bootcamp.blackbriar.model.inbox.MessageEntity;
import com.bootcamp.blackbriar.model.inbox.SocketMessage;
import com.bootcamp.blackbriar.repository.InboxRepository;
import com.bootcamp.blackbriar.repository.MessageRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class InboxServiceImpl implements InboxService {
  @Autowired
  private InboxRepository inboxRepository;

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SimpMessagingTemplate sockets;

  @Override
  public MessageEntity sendMessage(String subjectId, Long actionRef, String message, String type) {
    InboxEntity inbox = inboxRepository.findBySubjectUserId(subjectId)
        .orElseThrow(() -> new EntityNotFoundException("Message subject inbox not found"));
    MessageEntity msg = new MessageEntity();

    msg.setActionRef(actionRef);
    msg.setArchived(false);
    msg.setCategory(type);
    msg.setContent(message);
    msg.setInbox(inbox);

    MessageEntity response = messageRepository.save(msg);
    SocketMessage socketMessage = modelMapper.map(response, SocketMessage.class);

    sockets.convertAndSend("/topic/" + subjectId, socketMessage);

    return response;
  }

  @Override
  public List<MessageEntity> fetchMessages(String userId, String filter) {
    List<MessageEntity> messages;

    switch (filter) {
      case "all":
        messages = messageRepository
          .findByInboxSubjectUserIdOrderByCreatedDesc(userId);
        break;
      case "unread":
        messages = messageRepository
          .findByInboxSubjectUserIdAndArchivedOrderByCreatedDesc(userId, false);
        break;
      case "read":
        messages = messageRepository
          .findByInboxSubjectUserIdAndArchivedOrderByCreatedDesc(userId, true);
        break;
      default:
        throw new RuntimeException("Unrecognized filter for inbox messages.");
    }

    return messages;
  }

  @Override
  public MessageEntity mark(long messageId, boolean read, String userId) {
    MessageEntity message = messageRepository.findById(messageId)
      .orElseThrow(() -> new RuntimeException("Inbox Message not found."));
    
    if (!message.getInbox().getSubject().getUserId().equals(userId)) {
      throw new RuntimeException("Only the message subject can mark it as read or unread.");
    }
    
    message.setArchived(read);
    
    return messageRepository.save(message);
  }
}
