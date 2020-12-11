package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.SpriteSheet;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sword extends StaticEntity {
    private static final String[] SPRITE_PATH_WOODEN = {"images/woodenSword.png", "images/woodenSwordRight.png", "images/woodenSwordLeft.png", "images/woodenSwordDown.png"};
    private static final String SPRITE_PATH_IRON = "images/ironSword.png";
    private static final String SPRITE_PATH_DIAMOND = "images/diamondSword.png";
    private BufferedImage[] image;
    int i;

    private int width;
    private int height;
    private double damage;
    private String name;
    private boolean selected = false;

    public Sword() {
        selected = true;
        image = new BufferedImage[4];
        load();

    }

    public void unselectedSword() {
        selected = false;
    }

    public void draw(Buffer buffer, int x, int y, Direction direction) {
        if (direction == Direction.UP) {
            buffer.drawImage(image[0], x + 8, y - 35);
        } else if (direction == Direction.DOWN) {
            buffer.drawImage(image[3], x + 8, y + 15);
        } else if (direction == Direction.RIGHT) {
            buffer.drawImage(image[1], x + 5, y + 12);
        } else {
            buffer.drawImage(image[2], x - 35, y + 12);
        }
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {

    }

    private void load() {
        try {
            for (int i = 0; i < 4; i++) {
                image[i] = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH_WOODEN[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Factory {

        public static Sword woodenSword() {
            Sword woodenSword = new Sword();
            woodenSword.name = "woodenSword";
            woodenSword.damage = 5;
            woodenSword.width = 15;
            woodenSword.height = 10;
            return woodenSword;
        }

        public static Sword ironSword() {
            Sword ironSword = new Sword();
            ironSword.name = "ironSword";
            ironSword.damage = 5;
            ironSword.width = 15;
            ironSword.height = 10;
            return ironSword;
        }

        public static Sword diamondSword() {
            Sword diamondSword = new Sword();
            diamondSword.name = "diamondSword";
            diamondSword.damage = 5;
            diamondSword.width = 15;
            diamondSword.height = 10;
            return diamondSword;
        }
    }

    public boolean isSelected() {
        return selected;
    }
}
