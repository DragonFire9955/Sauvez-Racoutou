package app.Controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;


public class DragAndDrop {

    public void drag(Button bouton) {
        bouton.setOnDragDetected(e -> { //réagit qaund la souris clique, maintien et bouge
            Dragboard db = bouton.startDragAndDrop(TransferMode.COPY); //carton invisible qui transporte qlq chose
            //initialise le mode Drag&Drop de JavaFX

            ClipboardContent contenu = new ClipboardContent(); //pour ajouter du contenu au carton
            contenu.putString(bouton.getId()); //on ajout l'id (le nom) du bouton
            db.setContent(contenu); //on ajoute le contenu au carton
            db.setDragView(new Image("app/images/"+ bouton.getId() +"/niv0/img.png", 32, 32, true, true));
            //met une image visuellement (mais stocke une id)

            e.consume(); //fin de l'évènement
        });
    }

    public void survole(TilePane tileMap){
        tileMap.setOnDragOver(e -> { //réagit quand la souris survole la map en maintenant quelque chose
            e.acceptTransferModes(TransferMode.COPY); //accpete que la souris survole la Tilemap sous forme de copy
            e.consume(); //fin de l'événement
        });
    }

}