package com.alldata.javacourse.surveys.websockets;

public record Message(MessageType type, Integer questionId, Integer userId, Integer optionId) {
    public enum MessageType {
        JOIN,
        LEAVE,
        VOTE,
    }
}
