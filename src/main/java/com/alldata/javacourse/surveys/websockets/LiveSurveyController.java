package com.alldata.javacourse.surveys.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class LiveSurveyController {
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public LiveSurveyController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/survey/{surveyId}")
    @SendTo("/survey/{surveyId}")
    public Message redirect(@DestinationVariable String surveyId, @Payload Message message) {
        // TODO save vote to database
        return message;
    }

//    @MessageMapping("/survey/{surveyId}/addUser")
//    @SendTo("/survey-results/{surveyId}")
//    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
//        final String userId = UUID.randomUUID().toString();
//        headerAccessor.getSessionAttributes().put("userId", userId);
//        return new Message(Message.MessageType.JOIN, userId, message.userId());
//    }
//
//    @MessageMapping("/survey-management/{surveyId}/pushQuestion")
//    @SendTo("/survey-results/{surveyId}")
//    public Message pushQuestion(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
//        final String userId = UUID.randomUUID().toString();
//        headerAccessor.getSessionAttributes().put("userId", userId);
//        return new Message(Message.MessageType.JOIN, userId, message.display());
//    }

}
