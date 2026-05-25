package app.Modele;

import app.Modele.Entites.Animaux.Allies.ChatClassique;
import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Animaux.Ennemis.PouletClassique;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Managers.AlliesManager;
import app.Modele.Managers.EnnemyManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private ObservableList<Animaux> animauxList;
    private ObservableList<Barrage>  barrageList;
    private BooleanProperty theEnd;

    private int[][] map;
    private final int tailleTile =32;

    public GameWorld(){
        map = Terrain.genererMap();
        animauxList = FXCollections.observableArrayList();
        barrageList = FXCollections.observableArrayList();
        theEnd= new SimpleBooleanProperty(false);
    }

    public void updateGW(double dt) {
        for (Animaux animaux : animauxList) {
            animaux.update(dt);
        }

        for (Barrage barrage : barrageList) {
            barrage.update(dt);
        }

        supprimerAnimauxMorts();
    }

    public void ajouterAnimal(Animaux a) {
        animauxList.add(a);
    }

    public void supprimerAnimal(Animaux a){
        animauxList.remove(a);
    }

    public void supprimerAnimauxMorts() {
        for(int i=animauxList.size()-1;i>=0;i--) {
            System.out.println("boucle for");
            if (!animauxList.get(i).isAlive()) {
                System.out.println("condition if");
                supprimerAnimal(animauxList.get(i));
            }
        }
    }

    public List<Animaux> getAllies() {
        List<Animaux> allies = new ArrayList<>();

        for (Animaux animal : animauxList)
            if (animal instanceof ChatClassique || animal instanceof Racoutou)
                allies.add(animal);

        return allies;
    }

    public List<Animaux> getEnnemis() {
        List<Animaux> ennemis = new ArrayList<>();

        for (Animaux animal : animauxList)
            if (animal instanceof PouletClassique)
                ennemis.add(animal);

        return ennemis;
    }

    public void ajouterBarrage(Barrage b) {
        barrageList.add(b);
    }
    public void supprimerBarrage(Barrage b) {
        barrageList.remove(b);
    }
    public ObservableList<Barrage> getBarrage() {
        return barrageList;
    }

    public ObservableList<Animaux> getAnimaux() {
        return animauxList;
    }

    public BooleanProperty getTheEnd(){
        return theEnd;
    }

    public void changeStateTheEnd(){
        theEnd.set(!theEnd.getValue());
    }

    public int[][] getMap() {
        return map;
    }

    public int getTailleTile(){
        return tailleTile;
    }
}
