package app.Modele;

import app.Modele.Entites.Animaux.Animal;

import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private ObservableList<Animal> animauxList;
    private ObservableList<Barrage> barrageList;
    private BooleanProperty theEnd;
    private final int tailleTile=32;

    private int[][] map;

    private IntegerProperty totalCoin;

    public GameWorld(){

        map = Terrain.genererMap();
        animauxList = FXCollections.observableArrayList();
        barrageList = FXCollections.observableArrayList();
        theEnd= new SimpleBooleanProperty(false);

        totalCoin = new SimpleIntegerProperty(0);
    }


    public void updateGW(double dt)  {

        for (Entite entite : animauxList) {
            entite.update(dt);
        }

        for (Barrage barrage : barrageList) {
            barrage.update(dt);
        }

        supprimerAnimauxMorts();
    }

    public void ajouterAnimal(Animal a) {
        animauxList.add(a);
        System.out.println(a.getClass().getName());
    }

    public void supprimerAnimal(Animal a){
        animauxList.remove(a);
    }

    public void supprimerAnimauxMorts() {
        for(int i=animauxList.size()-1;i>=0;i--)
            if (!animauxList.get(i).isAlive())
                supprimerAnimal(animauxList.get(i));
    }

    public ObservableList<Animal> getAnimaux() {
        return animauxList;
    }

    public List<Animal> getAllies() {

        List<Animal> allies = new ArrayList<>();

        for (Animal animal : animauxList)
            if (animal.isAllie())
                allies.add(animal);

        return allies;
    }

    public List<Animal> getEnnemis() {

        List<Animal> ennemis = new ArrayList<>();

        for (Animal animal : animauxList)
            if (!animal.isAllie())
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

    public IntegerProperty getTotalCoin() {
        return totalCoin;
    }
    public void setTotalCoin(int coin) {
        this.totalCoin.set(coin);
    }
}
