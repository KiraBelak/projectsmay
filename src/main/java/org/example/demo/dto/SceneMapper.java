package org.example.demo.dto;

import org.example.demo.domain.Scene;

import java.util.stream.Collectors;

public class SceneMapper {
    public static SceneDto toDto(Scene s) {
        return new SceneDto(
                s.getWidth(),
                s.getHeight(),
                s.getBlocks().stream()
                        .map(b -> new SceneDto.BlockDto(
                                b.x, b.y, b.width, b.height))
                        .collect(Collectors.toList()));
    }

}
