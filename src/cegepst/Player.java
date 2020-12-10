package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.Sound;
import cegepst.engine.SpriteSheet;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/player.png";
    private static int ANIMATION_SPEED = 8;
    private int healthPoint = 100;
    private int soundCooldown;
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;

    public Player(MovementController controller, SpriteSheet spriteSheet) {
        super(controller);
        setSpeed(3);
        CollidableRepository.getInstance().registerEntity(this);
        setDimension(32,32);
        loadSpriteSheet();
        loadFrame();
    }

    private void loadFrame() {
        spri
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        if (super.hasMoved()) {
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

    private void drawHealth(Buffer buffer) {
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
        spriteSheet.directionDraw();
    }

    public void cooldownAttack() {
        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }
        if (soundCooldown == 0) {
            soundCooldown = 40;
            healthPoint -= 10;
            Sound.play("sounds/best1.wav");
        }
    }
}
