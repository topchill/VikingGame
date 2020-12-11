package cegepst.engine;

import cegepst.engine.controls.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class SpriteSheet {
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private BufferedImage spriteSheet;
    private static final String SPRITE_PATH = "images/player.png";




    public void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
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

    public void directionDraw(Direction direction, int x, int y, Buffer buffer, int currentAnimationFrame) {
        if (direction == Direction.UP) {
            buffer.drawImage(this.upFrames[currentAnimationFrame], x, y);
        } else if (direction == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (direction == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        }
    }
}
