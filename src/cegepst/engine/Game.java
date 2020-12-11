package cegepst.engine;

import java.awt.event.KeyListener;

public abstract class Game {

    private RenderingEngine renderingEngine;
    private GameTime gameTime;
    private SpriteSheet spriteSheet;
    private boolean playing = true;

    public Game() {
        renderingEngine = RenderingEngine.getInstance();
    }

    public abstract void initialize();
    public abstract void conclude();
    public abstract void update();
    public abstract void draw(Buffer buffer);
    public abstract void drawSprite(Buffer buffer, SpriteSheet spriteSheet);

    public void start() {
        initialize();
        run();
        conclude();
    }

    public void stop() {
        playing = false;
    }

    public void addKeyListener(KeyListener listener) {
        renderingEngine.addInputListener(listener);
    }

    private void run() {
        renderingEngine.start();
        spriteSheet = new SpriteSheet();
        spriteSheet.loadSpriteSheet();
        gameTime = new GameTime();

        while (playing) {
            update();
            Buffer buffer = renderingEngine.getRenderingBuffer();
            draw(buffer);
            drawSprite(buffer, spriteSheet);
            renderingEngine.renderBufferOnScreen();
            gameTime.synchronize();
        }
        renderingEngine.stop();
    }
}
