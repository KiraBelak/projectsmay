package org.example.demo.model;

import java.time.LocalDateTime;

public class ChatMessage {

    public enum MessageType {
        CHAT,       // Mensaje regular
        JOIN,       // Usuario se une al chat
        LEAVE,      // Usuario sale del chat
        PRIVATE     // Mensaje privado a un usuario específico
    }

    private MessageType type;
    private String content;
    private String sender;
    private String recipient;  // Solo usado para mensajes privados
    private LocalDateTime timestamp;

    // Constructor vacío necesario para JSON
    public ChatMessage() {
    }

    // Constructor completo
    public ChatMessage(MessageType type, String content, String sender, String recipient, LocalDateTime timestamp) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
    }

    // Getters y setters
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Factory methods (Builder pattern)
    // Constructor conveniente para mensajes regulares
    public static ChatMessage createChatMessage(String sender, String content) {
        return new ChatMessage(
                MessageType.CHAT,
                content,
                sender,
                null,
                LocalDateTime.now()
        );
    }

    // Constructor conveniente para mensajes de unión al chat
    public static ChatMessage createJoinMessage(String sender) {
        return new ChatMessage(
                MessageType.JOIN,
                sender + " se ha unido al chat!",
                sender,
                null,
                LocalDateTime.now()
        );
    }

    // Constructor conveniente para mensajes de salida
    public static ChatMessage createLeaveMessage(String sender) {
        return new ChatMessage(
                MessageType.LEAVE,
                sender + " ha salido del chat.",
                sender,
                null,
                LocalDateTime.now()
        );
    }

    // Constructor conveniente para mensajes privados
    public static ChatMessage createPrivateMessage(String sender, String recipient, String content) {
        return new ChatMessage(
                MessageType.PRIVATE,
                content,
                sender,
                recipient,
                LocalDateTime.now()
        );
    }
}

