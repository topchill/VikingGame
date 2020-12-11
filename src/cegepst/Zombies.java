package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.Sound;
import cegepst.engine.SpriteSheet;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombies extends MovableEntity {

    private int healthPoint = 20;
    private int soundCooldown;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private static int ANIMATION_SPEED = 8;


    public Zombies() {
        setSpeed(1);
        setDimension(32,32);
        CollidableRepository.getInstance().registerEntity(this);
        Sound.play("sounds/best.wav");
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        spriteSheet.loadFrame(96, 128, width, height);
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
