package app.Controller.Listener;

import app.Modele.Projectile.ProjectileSimple;
import app.Vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ProjectileListener implements ListChangeListener<ProjectileSimple> {

    @FXML
    private Pane carte;

    public ProjectileListener(Pane carte) {

        this.carte = carte;
    }

    @Override
    public void onChanged(Change<? extends ProjectileSimple> c) {

        while(c.next()) {
            if (c.wasRemoved()) {
                for (ProjectileSimple e: c.getRemoved()) {

                    //On suppr du visuel
                    carte.getChildren().removeIf(node -> ("cercle"+e.getId()).equals(node.getId()));
                }

            }
            if (c.wasAdded()) {
                //parcours les entités ajoutés
                for (ProjectileSimple p: c.getAddedSubList()) {

                    //affiche l'image de l'entite sur la carte
                    ProjectileVue projectileVue = new ProjectileVue(p);
                    carte.getChildren().add(projectileVue.getProjectileImage());
                }
            }
        }
    }
}
