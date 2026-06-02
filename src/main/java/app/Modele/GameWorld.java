package app.Modele;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Chemins.DeplacementMethodes;
import app.Modele.Entites.Animaux.Animal;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.Utilitaires.Noeud;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameWorld {

    private ObservableList<Animal> animauxList;
    private ObservableList<Barrage> barrageList;
    private BooleanProperty theEnd;
    private final int tailleTile=32;

    private int[][] map;
    //private Map<Double, Noeud> dijkRacoutou;
    private Map<Noeud, Noeud> dijkRacoutou2;


    public GameWorld(){

        map = Terrain.genererMap();
        animauxList = FXCollections.observableArrayList();
        animauxList.add(new Racoutou(this));
        barrageList = FXCollections.observableArrayList();
        theEnd= new SimpleBooleanProperty(false);

        //dijkRacoutou= new DeplacementDijkstra(tailleTile, map).calculerDistances(getTileRacoutou());
        dijkRacoutou2= new DeplacementDijkstra(tailleTile, map).testDijkstra(this.getRacoutou().getCoord());

        /*System.out.println(map[0].length);
        for(int i=0; i< map.length; i++){
            for(int j=0; j<map[i].length; j++){
                System.out.print(DeplacementMethodes.getCout(map, j, i)+ "  ");
            }
            System.out.println();
        }

        for(Map.Entry<Noeud, Noeud> entry: dijkRacoutou2.entrySet()){
            System.out.print("x: "+ entry.getKey().getI()+"  y: "+ entry.getKey().getJ());
            if(entry.getValue() == null){
                System.out.println(" null");
            }
            else{
                System.out.println("  PARENT x: "+ entry.getValue().getI()+"  y: "+ entry.getValue().getJ());
            }
        }

         */
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

    public Entite getRacoutou(){
        int i=0;
        while(i<animauxList.size() && !(animauxList.get(i) instanceof Racoutou))
            i++;
        if(i==animauxList.size()) return null;
        return animauxList.get(i);
    }

    public int[] getTileRacoutou(){
        Entite r = this.getRacoutou();
        System.out.println("RACOUTOU: "+ r.getTile()[0] + " "+ r.getTile()[1]);
        return r.getTile();
    }

    /*public Map<Double, Noeud> getDijkRacoutou() {
        return dijkRacoutou;
    }

    public void setDijkRacoutou(Map<Double, Noeud> dijkRacoutou) {
        this.dijkRacoutou = dijkRacoutou;
    }

     */

    public Map<Noeud, Noeud> getDijkRacoutou2() {
        return dijkRacoutou2;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
