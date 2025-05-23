package com.finalproject.gamestop.websocket;

import com.finalproject.gamestop.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class OrderNotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyOrderStatusChanged(OrderDTO orderDTO) {
        System.out.println(
                "Sending WebSocket notification for order: " + orderDTO.getId() + " status: " + orderDTO.getStatus());
        messagingTemplate.convertAndSend("/topic/orders", orderDTO);
    }
}
