package app.Modele;

import app.Modele.Entites.Animaux.Animaux;
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
    private BooleanProperty theEnd;

    private AlliesManager allies;
    private EnnemyManager ennemis;

    private int[][] map;

    public GameWorld(){

        map = Terrain.genererMap();
        allies= new AlliesManager();
        ennemis = new EnnemyManager();
        animauxList = FXCollections.observableArrayList();
        animauxList.addAll(ennemis.getAnimaux());
        theEnd= new SimpleBooleanProperty(false);
    }

    public void updateGW(double dt) {
        for (Animaux animaux : allies.getAnimaux()) {
            animaux.update(dt);
        }

        for (Animaux animaux : ennemis.getAnimaux()) {
            animaux.update(dt);
        }

        supprimerAnimauxMorts();
    }


    public void ajouterAllie(Animaux a){

        allies.getAnimaux().add(a);
        animauxList.add(a);
    }

    public void ajouterEnnemi(Animaux a){

        ennemis.getAnimaux().add(a);
        animauxList.add(a);
    }

    public void supprimerAnimal(Animaux a){

        allies.getAnimaux().remove(a);
        ennemis.getAnimaux().remove(a);
        animauxList.remove(a);
    }

    public void supprimerAnimauxMorts() {
        for(int i=animauxList.size()-1;i>=0;i--)
            if (!animauxList.get(i).isAlive())
                supprimerAnimal(animauxList.get(i));
    }

    public List<Animaux> getAllies() {
        return allies.getAnimaux();
    }

    public List<Animaux> getEnnemis() {
        return ennemis.getAnimaux();
    }

    public void setAllies(AlliesManager allies) {
        this.allies = allies;
    }

    public void setEnnemis(EnnemyManager ennemis) {
        this.ennemis = ennemis;
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
}
