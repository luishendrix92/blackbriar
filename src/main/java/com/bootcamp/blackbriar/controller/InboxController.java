package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class InboxController {
  @Autowired
  InboxService inboxService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;
}
