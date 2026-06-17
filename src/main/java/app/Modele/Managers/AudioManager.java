package app.Modele.Managers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioManager {

    private static AudioManager instance;
    private Clip clip;
    private BooleanProperty sonActive;
    private DoubleProperty volume;
    private String[] playlist = {
            "/app/audio/Turbo_Drive.wav",
            "/app/audio/Cyber_Echo.wav",
            "/app/audio/Sphinx_Rush.wav",
            "/app/audio/Pixel_Storm.wav"
    };
    private int indexMusiqueActuelle = 0;

    //private pour pas que d'autre classe fasse new AudioManager() pour avoir une radio
    private AudioManager() {
        this.sonActive = new SimpleBooleanProperty(true);
        this.volume = new SimpleDoubleProperty(0.5);

        this.sonActive.addListener((obs, ancien, estActive) -> {
            if (clip != null) {
                if (estActive) {
                    clip.start();
                    appliquerVolume(this.volume.get());
                } else {
                    clip.stop();
                }
            }
        });

        this.volume.addListener((obs, ancien, nouveauVolume) -> {
            double vol = nouveauVolume.doubleValue();

            if (vol == 0.0) { //pour changer l'image du haut parleur
                if (sonActive.get()) {
                    sonActive.set(false);
                }
            } else {
                if (!sonActive.get()) {
                    sonActive.set(true);
                }
            }

            appliquerVolume(vol);
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

            //on recupere le fichier audio
            InputStream fichierAudio = getClass().getResourceAsStream(url);

            //on met le fichier audio dans un carton pour qu'il soit lisible par le clip
            InputStream carton = new BufferedInputStream(fichierAudio);
            AudioInputStream audio = AudioSystem.getAudioInputStream(carton);

            clip = AudioSystem.getClip(); //nouveau clip vide
            clip.open(audio); //on met notre audio dedans

            appliquerVolume(volume.get());

            if (sonActive.get()) {
                clip.start();
            }

        } catch (Exception e) { //si erreur (pour ne pas faire crasher le jeu)
            System.out.println(e.getMessage());
        }
    }

    private void appliquerVolume(double vol) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        //on récupère le controleur du volume de l'ordinateur (Master_gain)

        //formule pour convertir le pourcentage du slider en decibel
        float dB = (float) (Math.log(vol) / Math.log(10.0) * 20.0);

        if (dB < gainControl.getMinimum()) {
            dB = gainControl.getMinimum();
        }
        if (dB > gainControl.getMaximum()) {
            dB = gainControl.getMaximum();
        }

        gainControl.setValue(dB);

    }

    public void pisteSuivante() {
        indexMusiqueActuelle = (indexMusiqueActuelle + 1);
        if (indexMusiqueActuelle > playlist.length - 1){ //pour revenir au début si on est a la fin
            indexMusiqueActuelle = 0;
        }
        jouerMusique(playlist[indexMusiqueActuelle]);
    }

    public void pistePrecedente() {
        indexMusiqueActuelle--;
        if (indexMusiqueActuelle < 0) {
            indexMusiqueActuelle = playlist.length - 1;
        }
        jouerMusique(playlist[indexMusiqueActuelle]);
    }

    public String getNomMusiqueActuelle() {
        String chemin = getMusiqueActuelle();

        //on garde les index après le dernier "/" et avant le ".wav"
        int debut = chemin.lastIndexOf("/") + 1;
        int fin = chemin.lastIndexOf(".");

        //nom entre debut et fin
        String nom = chemin.substring(debut, fin);

        //on remplace les tirets du bas par des espaces
        return nom.replace("_", " ");

    }

    public String getMusiqueActuelle() {
        return playlist[indexMusiqueActuelle];
    }

    public BooleanProperty sonActiveProperty() {
        return sonActive;
    }

    public DoubleProperty volumeProperty() {
        return volume;
    }
}