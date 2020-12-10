package cegepst.engine;

import cegepst.engine.controls.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private BufferedImage spriteSheet;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private static final String SPRITE_PATH = "images/player.png";
    private static int ANIMATION_SPEED = 8;



    public void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void animation() {
        --nextFrame;
        if (nextFrame == 0) {
            ++currentAnimationFrame;
            if (currentAnimationFrame >= leftFrames.length) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    } else {
        currentAnimationFrame = 1;
    }
    }

    public void loadFrame(int x, int y, int width, int height) {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(x, y, width, height);
        downFrames[1] = spriteSheet.getSubimage(x + 32, y, width, height);
        downFrames[2] = spriteSheet.getSubimage(x + 64, y, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(x, y + 32, width, height);
        leftFrames[1] = spriteSheet.getSubimage(x + 32, y + 32, width, height);
        leftFrames[2] = spriteSheet.getSubimage(x + 64, y + 32, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(x, y + 64, width, height);
        rightFrames[1] = spriteSheet.getSubimage(x + 32, y + 64, width, height);
        rightFrames[2] = spriteSheet.getSubimage(x + 64, y + 64, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(x, y + 96, width, height);
        upFrames[1] = spriteSheet.getSubimage(x + 32, y + 96, width, height);
        upFrames[2] = spriteSheet.getSubimage(x + 64, y + 96, width, height);
    }

    public void directionDraw() {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        }
        drawHealth(buffer);
    }
}
