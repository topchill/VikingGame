package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.Sound;
import cegepst.engine.SpriteSheet;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;
import java.util.ArrayList;

public class Player extends ControllableEntity {

    private int healthPoint = 100;
    private int soundCooldown;
    private ArrayList<Sword> swords;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private static int ANIMATION_SPEED = 50;
    private boolean isAttacking = false;
    private int frameAction = 0;
    private boolean isDiamondPickedUp;

    public Player(MovementController controller) {
        super(controller);
        setSpeed(3);
        CollidableRepository.getInstance().registerEntity(this);
        setDimension(32,32);
        swords = new ArrayList<>();
        swords.add(Sword.Factory.woodenSword());
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
    }

    public void drawHealth(Buffer buffer) {
        int heart = healthPoint / 10;
        int x= 20;
        for (int i = 0; i < heart; i++) {
            buffer.drawHeart(x, 10, 15, 15, Color.red);
            x += 20;
        }
    }


    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        spriteSheet.loadFrame(0, 128, width, height);
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

        if (isAttacking || frameAction > 0) {
            for (Sword sword: swords) {
                if (sword.isSelected()) {
                    sword.draw(buffer, x, y, getDirection());
                    frameAction--;
                }
            }
        }
    }

    public void cooldownAttack() {
        soundCooldown--;
        isAttacking = false;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }
        if (soundCooldown == 0) {
            soundCooldown = 30;
            Sound.playMP3("sounds/swordSwing.mp3", false);
            isAttacking = true;
            frameAction = 100;
        }


    }

    public void pickupIronSword() {
        for (Sword sword: swords) {
            if (sword.isSelected()) {
                sword.unselectedSword();
            }
        }
        swords.add(Sword.Factory.ironSword());
    }

    public void pickupDiamondSword() {
        isDiamondPickedUp();
        for (Sword sword: swords) {
            if (sword.isSelected()) {
                sword.unselectedSword();
            }
        }
        swords.add(Sword.Factory.diamondSword());
    }

    public boolean isDiamondPickedUp() {
        return isDiamondPickedUp = true;
    }
}
