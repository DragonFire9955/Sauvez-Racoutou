package app.Modele;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Animaux.Animal;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameWorld {

    private ObservableList<Animal> animauxList;
    private ObservableList<Barrage> barrageList;
    private BooleanProperty theEnd;
    private final int tailleTile=32;

    private int[][] map;
    //private Map<Double, Noeud> dijkRacoutou;
    private Map<Noeud, Noeud> dijkRacoutou2;


    private List<Map<Integer, Map<Animal, Integer>>> ensemblesVagues;
    private List<Integer> debutVagues;
    private IntegerProperty numeroVague;
    private double tempsVague;
    private int tempsTotalVague;



    private IntegerProperty totalCoin;

    public GameWorld(){

        map = Terrain.genererMap();
        animauxList = FXCollections.observableArrayList();
        animauxList.add(new Racoutou(this));
        barrageList = FXCollections.observableArrayList();
        theEnd= new SimpleBooleanProperty(false);

        //dijkRacoutou= new DeplacementDijkstra(tailleTile, map).calculerDistances(getTileRacoutou());
        dijkRacoutou2= new DeplacementDijkstra(tailleTile, map).testDijkstra(this.getRacoutou().getCoord());

        totalCoin = new SimpleIntegerProperty(0);

        ensemblesVagues = Vague.ensembleVagues(this);
        debutVagues = Vague.debutVagues();
        numeroVague = new SimpleIntegerProperty(0);
        tempsVague=0;
        tempsTotalVague=0;
        //vagueActuelle = Vague.creerVague1(this);
        //tempsVague = 0;
    }


    public void updateGW(double dt)  {

        vagueManager(dt);

        for (Entite entite : animauxList) {
            entite.update(dt);
        }

        for (Barrage barrage : barrageList) {
            barrage.update(dt);
        }

        supprimerAnimauxMorts();
    }
/*
    private void vagueManager(double dt) {

        tempsVague = dt;
        int tempsActuel = (int) tempsVague;

        if (vagueActuelle.containsKey(tempsActuel)) {
            animauxList.addAll(vagueActuelle.get(tempsActuel));
            vagueActuelle.remove(tempsActuel);
        }
    }

 */

    private void vagueManager(double dt) {

        tempsVague = dt;
        int tempsActuel = (int) tempsVague;

        if(debutVagues.contains(tempsActuel)
        && Utilitaire.getIndex(debutVagues, tempsActuel) < debutVagues.size()-1
        && Utilitaire.getIndex(debutVagues, tempsActuel)+1 != numeroVague.get()){
            numeroVague.set(Utilitaire.getIndex(debutVagues, tempsActuel)+1);
            tempsTotalVague = debutVagues.get(numeroVague.get()) - debutVagues.get(numeroVague.get() -1);
        }

        if(numeroVague.get()!=0 && ensemblesVagues.get(numeroVague.get()-1).containsKey(tempsActuel)){
            if(ensemblesVagues.get(numeroVague.get()-1).containsKey(tempsActuel))
                animauxList.addAll(Objects.requireNonNull(Vague.creerVague(ensemblesVagues.get(numeroVague.get() - 1).get(tempsActuel))));
        }
    }

    public void ajouterAnimal(Animal a) {
        animauxList.add(a);
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

    public List<Animal> getAlliesAnimaux() {

        List<Animal> allies = new ArrayList<>();

        for (Animal animal : animauxList)
            if (animal.isAllie())
                allies.add(animal);

        return allies;
    }

    public List<Entite> getAlliesAnimauxBarrages() {
        List<Entite> entites = new ArrayList<>(getAlliesAnimaux());
        entites.addAll(getBarrage());
        return entites;
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

    public Entite getRacoutou(){
        int i=0;
        while(i<animauxList.size() && !(animauxList.get(i) instanceof Racoutou))
            i++;
        if(i==animauxList.size()) return null;
        return animauxList.get(i);
    }

    public int[] getTileRacoutou(){
        Entite r = this.getRacoutou();
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

    public IntegerProperty getNumeroVagueProperty() {
        return numeroVague;
    }

    public int getTempsTotalVague() {
        return tempsTotalVague;
    }
}
