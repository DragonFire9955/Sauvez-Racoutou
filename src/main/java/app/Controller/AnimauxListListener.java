package app.Controller;

import app.Modele.Entites.Animaux;
import app.Vue.AnimalVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class AnimauxListListener implements ListChangeListener<Animaux> {

    //J'ai enlevé la liste des Animaux car elle n'était pas utilisée.

    @FXML
    private Pane carte;

    public AnimauxListListener(Pane carte){
        this.carte = carte;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Animaux> c){

        while(c.next()) {
            if (c.wasRemoved()) {
                /*Iterator<? extends Animaux> iSupp = c.getRemoved().iterator();
                while (iSupp.hasNext())  {
                    a = iSupp.next();*/
                for (Animaux a: c.getRemoved()) {
                    /*for(int j=0;j<carte.getChildren().size();j++){
                        if(carte.getChildren().get(j).getId()==a.getId()) {
                            carte.getChildren().remove(j);
                            System.out.println("ajout dans list");


                    }*/
                    carte.getChildren().removeIf(node -> a.getId().equals(node.getId()));
                }

            }
            if (c.wasAdded()) {
                /*Iterator<? extends Animaux> iAdd = c.getAddedSubList().iterator();
                while (iAdd.hasNext()) {
                    a= iAdd.next();*/
                for (Animaux a: c.getAddedSubList()) {
                    System.out.println("ajout dans list");
                    carte.getChildren().add(AnimalVue.appliquerBonneImage(a));
                }
            }
        }
    }
}
