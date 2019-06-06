package com.bootcamp.blackbriar.controller;

import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.bootcamp.blackbriar.model.inbox.SocketMessage;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
  //@Autowired
  //private ModelMapper modelMapper;

  @Autowired
  private SimpMessagingTemplate sockets;

  @GetMapping(value = "/api/ping/timer")
  public String startTimer() {
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    Runnable task = () -> {
      SocketMessage socketMessage = new SocketMessage();

      socketMessage.setActionRef(null);
      socketMessage.setCategory("GNRIC");
      socketMessage.setContent("Pong.");

      sockets.convertAndSend("/topic/" + "timer", socketMessage);
    };

    ses.schedule(task, 5, TimeUnit.SECONDS);
    ses.shutdown();

    return "Timer started!";
  }
}
