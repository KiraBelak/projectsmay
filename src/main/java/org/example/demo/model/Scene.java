package org.example.demo.model;

import lombok.Data;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

@Data
public class Scene {
    List<Rectangle> blocks = new ArrayList<>();
    int width = 800;
    int height = 450;

    public static Scene createTestScene() {
        Scene scene = new Scene();
        //floor, 50px tall
        scene.blocks.add(new Rectangle(50, 400, 700, 50));
        // platforms
        scene.blocks.add(new Rectangle(100, 350, 100, 20));
        scene.blocks.add(new Rectangle(250, 300, 60, 20));
        scene.blocks.add(new Rectangle(400, 320, 100, 20));
        // right wall
        scene.blocks.add(new Rectangle(700, 200, 25, 130));
        return scene;
    }
}
