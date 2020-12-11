package cegepst;

import cegepst.engine.*;

import java.awt.*;
import java.util.ArrayList;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private ArrayList<Tree> trees;
    private Sword swords;
    private Zombies zombie;
    private Rat rat;
    private Reaper reaper;
    private SpriteSheet spriteSheet;
    private boolean pickup = false;
    private int frameAction = 0;


    public VikingGame() {
        gamePad = new GamePad();
        spriteSheet = new SpriteSheet();
        spriteSheet.loadSpriteSheet();
        player = new Player(gamePad);
        zombie = new Zombies();
        rat = new Rat();
        reaper = new Reaper();
        trees = new ArrayList<>();
        player.teleport(200, 200);
        zombie.teleport(400, 300);
        rat.teleport(50, 300);
        reaper.teleport(550, 500);
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
        reaper.update(player.getX(), player.getY());
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
        reaper.drawSprite(buffer, spriteSheet);

        for (Tree tree: trees) {
            if (player.getY() < tree.getY() + 52) {
                player.drawSprite(buffer, spriteSheet);
                tree.draw(buffer);
            } else {
                tree.draw(buffer);
                player.drawSprite(buffer, spriteSheet);
            }
        }

        swords = new Sword();
        if (!pickup) {
            swords.draw(buffer);
        }
        if (player.intersectWith(swords) && (!pickup || frameAction < 50)) {
            buffer.drawText("Diamond sword picked up !", 260, 550, Color.WHITE);
            frameAction++;
            player.pickupDiamondSword();
            pickup = true;
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
