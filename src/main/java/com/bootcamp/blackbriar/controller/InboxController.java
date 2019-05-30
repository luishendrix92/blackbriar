package com.bootcamp.blackbriar.controller;

import java.security.Principal;

import com.bootcamp.blackbriar.model.inbox.MessageEntity;
import com.bootcamp.blackbriar.model.inbox.MessageResponse;
import com.bootcamp.blackbriar.model.inbox.SocketMessage;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/inbox")
public class InboxController {
  @Autowired
  InboxService inboxService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;

  // TODO: Remove this method upon socket implementation
  @GetMapping(value = "/latest")
  public MessageResponse mockNotification(Principal auth) {
    MessageEntity message = inboxService.getLatestMessage(auth.getName());

    return modelMapper.map(message, MessageResponse.class);
  }

  // TODO: Delete this method
  @GetMapping(value = "/ping")
  public void ping() {
    SocketMessage out = new SocketMessage();
    
    out.setActionRef(null);
    out.setCategory("GNRIC");
    out.setContent("Hello all! This came from someone pinging...");

    simpMessagingTemplate.convertAndSend("/topic/all", out);
  }
}
