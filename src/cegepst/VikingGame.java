package cegepst;

import cegepst.engine.*;

import java.util.ArrayList;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private ArrayList<Tree> trees;
    private Zombies zombie;
    private Rat rat;
    private SpriteSheet spriteSheet;
    private Sword sword;


    public VikingGame() {
        gamePad = new GamePad();
        spriteSheet = new SpriteSheet();
        spriteSheet.loadSpriteSheet();
        player = new Player(gamePad);
        zombie = new Zombies();
        rat = new Rat();
        trees = new ArrayList<>();
        player.teleport(200, 200);
        zombie.teleport(400, 300);
        rat.teleport(50, 300);
        world = new World();
        trees.add(new Tree(125, 100));
        trees.add(new Tree(155, 150));
        trees.add(new Tree(10, 100));
        trees.add(new Tree(250, 100));
        trees.add(new Tree(300, 200));
        trees.add(new Tree(400, 250));
        trees.add(new Tree(180, 400));
        trees.add(new Tree(700, 450));
        trees.add(new Tree(500, 300));
    }

    @Override
    public void update() {
        player.update();
        zombie.update(player.getX(), player.getY());
        rat.update(player.getX(), player.getY());
        if (gamePad.isFirePressed()) {
            player.cooldownAttack();
        }
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
        for (Tree tree: trees) {
            if (player.getY() < tree.getY() + 52) {
                tree.blockadeFromTop();
            } else {
                tree.blockadeFromBottom();
            }
        }

    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        player.drawHealth(buffer);
    }

    @Override
    public void drawSprite(Buffer buffer, SpriteSheet spriteSheet) {
        zombie.drawSprite(buffer, spriteSheet);
        rat.drawSprite(buffer, spriteSheet);
        for (Tree tree: trees) {
            if (player.getY() < tree.getY() + 52) {
                player.drawSprite(buffer, spriteSheet);
                tree.draw(buffer);
            } else {
                tree.draw(buffer);
                player.drawSprite(buffer, spriteSheet);
            }
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
