package app.Modele;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Animaux.Animal;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.Managers.VagueManager;
import app.Modele.Projectile.ProjectileSimple;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class GameWorld {

    private ObservableList<Animal> animauxList;
    private ObservableList<Barrage> barrageList;
    private IntegerProperty theEnd;
    private final int tailleTile=64;

    private int[][] map;
    private Map<Noeud, Noeud> dijkRacoutou2;



    private List<TreeMap<Integer, List<Animal>>> ensemblesVagues;
    private IntegerProperty durreeVague;
    private  int debutVague;
    private IntegerProperty numeroVague;
    private IntegerProperty tempsActuelVague;
    private int difficulte;

    ObservableList<ProjectileSimple> projectiles;

    private IntegerProperty totalCoin;

    public GameWorld(int[][] map, int difficulte){

        this.animauxList = FXCollections.observableList(new ArrayList<>());
        this.animauxList.add(new Racoutou(this, StatsEntiteInitialiser.getStatsLevels("racoutou")));
        barrageList = FXCollections.observableArrayList();
        theEnd = new SimpleIntegerProperty(0);

        dijkRacoutou2 = new DeplacementDijkstra(tailleTile, map).dijkstra(this.getRacoutou().getCoord());

        projectiles = FXCollections.observableArrayList();

        totalCoin = new SimpleIntegerProperty(1000);

        this.difficulte = difficulte;

        ensemblesVagues = VagueManager.ensembleVagues(this, difficulte);
        durreeVague = new SimpleIntegerProperty(0);
        debutVague=0;
        numeroVague = new SimpleIntegerProperty(0);

        tempsActuelVague = new SimpleIntegerProperty(0);

    }


    public void updateGW(double dt)  {

        if(!perdue() && !gagne()){
            vagueManager(dt);

            for (Entite entite : animauxList) {
                entite.update(dt);
            }
            for (Barrage barrage : barrageList) {
                barrage.update(dt);
            }
            for (ProjectileSimple p : projectiles) {
                p.update(dt);
            }

            supprimerEntitesMorts();
            supprimerProjectilesMorts();
        }
    }

    private void vagueManager(double dt) {

        double tempsVague = dt - debutVague;

        if(tempsVague>=(durreeVague.get()) && numeroVague.get()<ensemblesVagues.size()){
            numeroVague.set(numeroVague.get()+1);
            debutVague = (int) dt;
            durreeVague.set(ensemblesVagues.get(numeroVague.get()-1).lastKey() +10);
            tempsVague = 0;
        }

        tempsActuelVague.set((int) tempsVague);

        if(numeroVague.get()!=0 && ensemblesVagues.get(numeroVague.get()-1).containsKey(tempsActuelVague.get())){
            animauxList.addAll(ensemblesVagues.get(numeroVague.get()-1).pollFirstEntry().getValue());
        }
    }

    public void ajouterAnimal(Animal a) {
        animauxList.add(a);
    }

    public void supprimerAnimal(Animal a){
        animauxList.remove(a);
    }


    public void supprimerEntitesMorts(){
        for(int i=animauxList.size()-1;i>=0;i--)
            if (!animauxList.get(i).isAlive())
                supprimerAnimal(animauxList.get(i));

        for(int i= barrageList.size()-1; i>=0; i--)
            if(!barrageList.get(i).isAlive())
                supprimerBarrage(barrageList.get(i));
    }

    public void addProjectile(ProjectileSimple p) {
        projectiles.add(p);
    }
    public void supprimerProjectilesMorts() {

        for (int i = projectiles.size() - 1; i >= 0; i--) {
            if (projectiles.get(i).isDead()) {
                projectiles.remove(i);
            }
        }
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
        int taille = b.getTaille();
        for(int i= 0; i<taille; i++){
            for(int j = 0; j<taille; j++) {
                map[b.getTuileOrigine()[0] - i][b.getTuileOrigine()[1] - j] = b.getIdPoids();
            }
        }
        resetDijkstraRacoutou();
    }
    public void supprimerBarrage(Barrage b) {
        int taille = b.getTaille();
        for(int i= 0; i<taille; i++){
            for(int j = 0; j<taille; j++) {
                map[b.getTuileOrigine()[0] - i][b.getTuileOrigine()[1] - j] = 1;
            }
        }
        barrageList.remove(b);
        resetDijkstraRacoutou();
    }
    public ObservableList<Barrage> getBarrage() {
        return barrageList;
    }

    public IntegerProperty getTheEnd(){
        return theEnd;
    }

    public int[][] getMap() {
        return map;
    }
    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getTailleTile(){
        return tailleTile;
    }

    public ObservableList<ProjectileSimple> getProjectiles() {
        return projectiles;
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

    public void resetDijkstraRacoutou() {
        this.dijkRacoutou2 = new DeplacementDijkstra(tailleTile, map).dijkstra(this.getRacoutou().getCoord());
    }

    public Map<Noeud, Noeud> getDijkRacoutou2() {
        return dijkRacoutou2;
    }

    public IntegerProperty getNumeroVagueProperty() {
        return numeroVague;
    }

    public int getDurreeVague() {
        return durreeVague.get();
    }

    public IntegerProperty getDurreeVagueProperty() {
        return durreeVague;
    }

    public IntegerProperty getTempsActuelVagueProperty(){
        return tempsActuelVague;
    }

    public boolean perdue(){
        if(this.getRacoutou() == null){
            theEnd.set(-1);
            return true;
        }
        return false;
    }

    public boolean gagne(){
        if(numeroVague.get()>= ensemblesVagues.size() && this.getEnnemis().isEmpty()){
            theEnd.set(1);
            return true;
        }

        return  false;
    }

}
