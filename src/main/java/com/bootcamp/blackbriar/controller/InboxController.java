package com.bootcamp.blackbriar.controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import com.bootcamp.blackbriar.model.inbox.MessageEntity;
import com.bootcamp.blackbriar.model.inbox.MessageResponse;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class InboxController {
  @Autowired
  InboxService inboxService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping(value="/api/messages")
  public List<MessageResponse> getInboxMessages(
    @RequestParam(required = false, defaultValue = "all") String filter,
    Principal auth
  ) {
    List<MessageEntity> messages = inboxService.fetchMessages(auth.getName(), filter);
    Type inboxMessages = new TypeToken<List<MessageResponse>>() {}.getType();

    return modelMapper.map(messages, inboxMessages);
  }

  @PutMapping(value = "/api/messages/read")
  public void readMessages(Principal auth) {
    inboxService.readAll(auth.getName());
  }

  @PutMapping(value = "/api/messages/{messageId}/mark")
  public MessageResponse markAsReadOrUnread(
    @RequestParam(required = false, defaultValue = "true") boolean read,
    @PathVariable long messageId,
    Principal auth
  ) {
    MessageEntity markedMessage = inboxService.mark(messageId, read, auth.getName());

    return modelMapper.map(markedMessage, MessageResponse.class);
  }
}
