package org.example.demo.dto;

import java.util.List;

public record SceneDto(
        int width,
        int height,
        List<BlockDto> blocks) {

    public record BlockDto(int x, int y, int width, int height) { }
}
