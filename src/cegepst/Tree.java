package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private Image image;
    private Blockade blockade;

    public Tree(int x, int y) {
        teleport(x, y);
        load();
        blockade = new Blockade();
        blockade.setDimension(20,8);
        blockadeFromTop();
    }

    public void blockadeFromTop() {
        blockade.teleport(x + 22, y + 64);
    }

    public void blockadeFromBottom() {
        blockade.teleport(x + 22, y + 48);
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        blockade.draw(buffer);
    }
}
