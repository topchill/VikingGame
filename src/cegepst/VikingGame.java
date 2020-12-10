package cegepst;

import cegepst.engine.*;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private Tree tree;
    private Zombies zombie;
    private Rat rat;
    private SpriteSheet spriteSheet;


    public VikingGame() {
        gamePad = new GamePad();
        spriteSheet = new SpriteSheet();
        spriteSheet.loadSpriteSheet();
        player = new Player(gamePad, spriteSheet);
        zombie = new Zombies(spriteSheet);
        rat = new Rat(spriteSheet);
        player.teleport(200, 200);
        zombie.teleport(400, 300);
        rat.teleport(50, 300);
        world = new World();
        tree = new Tree(100, 100);
    }

    @Override
    public void update() {
        player.update();
        zombie.update(player.getX(), player.getY());
        if (gamePad.isFirePressed()) {
            player.cooldownAttack();
        }
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        zombie.drawSprite(buffer, spriteSheet);
        rat.drawSprite(buffer, spriteSheet);
        if (player.getY() < tree.getY() + 52) {
            player.drawSprite(buffer, spriteSheet);
            tree.draw(buffer);
        } else {
            tree.drawSprite(buffer, spriteSheet);
            player.draw(buffer);
        }
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        //RenderingEngine.getInstance().getScreen().fullScreen();
        Sound.playMP3("musics/Glorious-Morning-2.mp3", true);
        //Sound.play("musics/map.wav");
    }

    @Override
    public void conclude() {

    }
}
