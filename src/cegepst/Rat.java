package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheet;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rat extends MovableEntity {

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

    public Rat() {
        setSpeed(1);
        setDimension(32,32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        spriteSheet.loadFrame(96, 0, width, height);
        spriteSheet.directionDraw(getDirection(), x, y, buffer, currentAnimationFrame);
        if (super.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= 3) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }

    public void update(int playerX, int playerY) {
        super.update();
        AIPathing(playerX, playerY);
    }

    public void AIPathing(int playerX, int playerY) {
        if (x > playerX) {
            moveLeft();
        }
        if (x < playerX) {
            moveRight();
        }
        if (y < playerY) {
            moveDown();
        }
        if (y > playerY) {
            moveUp();
        }
    }
}
