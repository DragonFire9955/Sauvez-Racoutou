package app.Modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioManager {

    private static AudioManager instance;
    private Clip clip;
    private BooleanProperty sonActive;

    private AudioManager() {
        this.sonActive = new SimpleBooleanProperty(true);

        this.sonActive.addListener((obs, ancien, estActive) -> {
            if (clip != null) {
                if (estActive) {
                    clip.start();
                } else {
                    clip.stop();
                }
            }
        });
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void jouerMusique(String url) {

        if (clip != null) {
            clip.stop();
            clip.close();
        }

        try { //essaye
            InputStream input = getClass().getResourceAsStream(url);

            //on met le fichier dans un buffer pour qu'il soit lisible par le clip
            InputStream buffer = new BufferedInputStream(input);
            AudioInputStream audio = AudioSystem.getAudioInputStream(buffer);

            clip = AudioSystem.getClip(); //nouveau clip vide
            clip.open(audio); //on met notre audio dedans

            clip.loop(Clip.LOOP_CONTINUOUSLY); //pour que la musique se relance quand elle finie

            if (sonActive.get()) {
                clip.start();
            }

        } catch (Exception e) { //si erreur
            System.out.println(e.getMessage());
        }
    }

    public BooleanProperty sonActiveProperty() {
        return sonActive;
    }
}