package app.Controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;


public class DragAndDrop {

    public void drag(Button bouton) {
        bouton.setOnDragDetected(e -> { //réagit qaund la souris clique et maintien
            Dragboard db = bouton.startDragAndDrop(TransferMode.COPY); //copie virtuelle du bouton
            //initialise le mode Drag&Drop de JavaFX

            ClipboardContent contenu = new ClipboardContent(); //pour ajouter du contenu
            //contenu.putString(String.valueOf(id)); //on ajout l'id de l'image
            db.setContent(contenu); //on ajoute le contenu au drag&drop
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