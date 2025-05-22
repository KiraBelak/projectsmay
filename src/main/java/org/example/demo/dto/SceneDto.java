package org.example.demo.dto;

import org.example.demo.model.Scene;

import java.util.List;
import java.util.stream.Collectors;

public record SceneDto(int width, int height, List<BlockDto> blocks) {

    public record BlockDto(int x, int y, int width, int height) { }

    public static SceneDto fromEntity(Scene s) {
        return new SceneDto(
            s.getWidth(),
            s.getHeight(),
            s.getBlocks().stream()
                .map(b -> new BlockDto(b.x, b.y, b.width, b.height))
                .collect(Collectors.toList()));
    }
}
