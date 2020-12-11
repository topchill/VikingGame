package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheet;
import cegepst.engine.entity.MovableEntity;

public class Reaper extends MovableEntity {
    private static int ANIMATION_SPEED = 8;
    private int healthPoint = 10;
    private int soundCooldown;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;

    public Reaper() {
        setSpeed(1);
        setDimension(32,32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        spriteSheet.loadFrame(288, 0, width, height);
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
