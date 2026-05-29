package app.Controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;


public class DragAndDrop {

    public void drag(Button bouton, int id, String url) {
        bouton.setOnDragDetected(e -> { //réagit qaund la souris maintien sur un bouton
            Dragboard db = bouton.startDragAndDrop(TransferMode.COPY); //se met en mode drag&drop (l'image est dédoublé)

            ClipboardContent contenu = new ClipboardContent(); //pour ajouter du contenu
            contenu.putString(String.valueOf(id)); //on ajout l'id de l'image
            db.setContent(contenu); //on ajoute le contenu au drag&drop
            db.setDragView(new Image(getClass().getResourceAsStream(url), 32, 32, true, true));
            //met une image visuellement (mais stocke une id)

            e.consume(); //fin de l'évènement
        });
    }

    public void survole(TilePane tileMap){
        tileMap.setOnDragOver(e -> { //réagit quand la souris passe au dessus de la map en mode drag&drop

            e.acceptTransferModes(TransferMode.COPY); //met l'id que si il survole la map
            e.consume(); //fin de l'événement
        });
    }

}