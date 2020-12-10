package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheet;
import cegepst.engine.entity.StaticEntity;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSetting.DEBUG_COLLISION) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {

    }
}
