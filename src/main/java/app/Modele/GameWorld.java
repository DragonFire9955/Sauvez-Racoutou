package app.Modele;

import app.Modele.Entites.Animaux;
import app.Modele.Managers.AlliesManager;
import app.Modele.Managers.EnnemyManager;
import app.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private Terrain terrain;
    private ObservableList<Animaux> animauxList;

    private AlliesManager allies;
    private EnnemyManager ennemis;

    public GameWorld(){
        terrain=new Terrain();
        allies= new AlliesManager();
        ennemis = new EnnemyManager();
        animauxList = FXCollections.observableArrayList();
        animauxList.addAll(ennemis.getAnimaux());

    }

    public void updateGW(double dt) {
        for (Animaux animaux : allies.getAnimaux()) {
            animaux.update(dt);
        }

        for (Animaux animaux : ennemis.getAnimaux()) {
            animaux.update(dt);
        }
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

        List<Animaux> morts = new ArrayList<>();
        for(int i=animauxList.size()-1;i>=0;i--) {
            if (!animauxList.get(i).isAlive())
                animauxList.remove(i);
        }
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
}
