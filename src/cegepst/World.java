package cegepst;

import cegepst.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private static final String sword = "images/diamondSword.png";
    private Image background;
    private ArrayList<Blockade> worldBorder;

    public World() {
        loadBackground();
        setBlockade();
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, -64);
        for (Blockade blockade : worldBorder) {
            blockade.draw(buffer);
        }
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBlockade() {
        worldBorder = new ArrayList<>();
        Blockade topBorder = new Blockade();
        topBorder.setDimension(400,48);
        topBorder.teleport(0,0);
        Blockade topBorder2 = new Blockade();
        topBorder2.setDimension(400,48);
        topBorder2.teleport(464,0);
        Blockade leftBorder = new Blockade();
        leftBorder.setDimension(1,800);
        leftBorder.teleport(0,0);
        Blockade rightBorder = new Blockade();
        rightBorder.setDimension(8,700);
        rightBorder.teleport(800,0);
        Blockade bottomBorder = new Blockade();
        bottomBorder.setDimension(800,8);
        bottomBorder.teleport(0,600 - 40);
        Blockade topBorder3 = new Blockade();
        topBorder3.setDimension(800,8);
        topBorder3.teleport(0,0);

        worldBorder.add(topBorder3);
        worldBorder.add(topBorder);
        worldBorder.add(topBorder2);
        worldBorder.add(leftBorder);
        worldBorder.add(rightBorder);
        worldBorder.add(bottomBorder);
    }
}
