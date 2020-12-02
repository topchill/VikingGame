package cegepst.engine;

import javax.sound.sampled.*;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;


public class Sound {

    public static synchronized void play(final String fileName) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream(fileName));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static synchronized void playMP3(final String fileName, boolean loop) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(this.getClass().getClassLoader().getResource(fileName).getFile()));
                        Player player = new Player(buffer);
                        player.play();
                    } while (loop);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}