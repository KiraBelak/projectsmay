package com.curso.proyectofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.curso.proyectofinal.model.Notification;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/global", notification);
    }

    public void sendPrivateNotification(String username, Notification notification) {
        messagingTemplate.convertAndSendToUser(username, "/topic/private", notification);
    }

    public void notifyNewArtist(Object artist) {
        sendGlobalNotification(new Notification(
                "Nuevo artista agregado",
                "ARTIST_CREATED",
                artist));
    }

    public void notifyNewAlbum(Object album) {
        sendGlobalNotification(new Notification(
                "Nuevo álbum agregado",
                "ALBUM_CREATED",
                album));
    }

    public void notifyNewSong(Object song) {
        sendGlobalNotification(new Notification(
                "Nueva canción agregada",
                "SONG_CREATED",
                song));
    }
}
