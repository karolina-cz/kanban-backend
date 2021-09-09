package com.pw.kanban.api.task;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TestRes {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    String testRes() {
        return "Hello, friend!";
    }
}
