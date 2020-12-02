package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.RenderingEngine;
import cegepst.engine.Sound;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private Tree tree;
    private Zombies zombie;
    private Rat rat;


    public VikingGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        zombie = new Zombies();
        rat = new Rat();
        player.teleport(200, 200);
        zombie.teleport(400, 300);
        rat.teleport(50, 300);
        world = new World();
        tree = new Tree(100, 100);
    }

    @Override
    public void update() {
        player.update();
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
        zombie.draw(buffer);
        rat.draw(buffer);
        if (player.getY() < tree.getY() + 52) {
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
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
