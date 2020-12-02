package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombies extends MovableEntity {

    private static final String SPRITE_PATH = "images/player.png";
    private static int ANIMATION_SPEED = 8;
    private int healthPoint = 10;
    private int soundCooldown;
    private BufferedImage spriteSheet;
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;

    public Zombies() {
        setSpeed(4);
        setDimension(32,32);
        loadSpriteSheet();
        loadFrame();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        }
    }

    private void loadFrame() {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 128, width, height);
        downFrames[1] = spriteSheet.getSubimage(32, 128, width, height);
        downFrames[2] = spriteSheet.getSubimage(64, 128, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 160, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 160, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 160, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 192, width, height);
        rightFrames[1] = spriteSheet.getSubimage(32, 192, width, height);
        rightFrames[2] = spriteSheet.getSubimage(64, 192, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 224, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 224, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 224, width, height);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
