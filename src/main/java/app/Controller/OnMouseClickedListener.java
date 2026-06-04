package app.Controller;

import app.Modele.GameWorld;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OnMouseClickedListener implements EventHandler<MouseEvent> {

    private final GameWorld gameWorld;

    private final Rectangle highlight;

    private boolean modePlacement;

    public OnMouseClickedListener(TilePane tileMap, Pane carte, GameWorld gameWorld) {

        this.gameWorld = gameWorld;

        //Création du rectangle jaune en overview sur la case
        highlight = new Rectangle(gameWorld.getTailleTile(), gameWorld.getTailleTile());

        highlight.setFill(Color.rgb(255, 255, 0, 0.30));
        highlight.setStroke(Color.GOLD);
        highlight.setStrokeWidth(2);

        highlight.setMouseTransparent(true);
        highlight.setVisible(false);

        carte.getChildren().add(highlight);

        modePlacement = false;
    }

    @Override
    public void handle(MouseEvent e) {

        //Si click droit alors on annule
        if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.SECONDARY) {

            setModePlacement(false);
            e.consume();
            return;
        }

        if (!modePlacement) {
            highlight.setVisible(false);
            return;
        }

        highlight.setVisible(true);
        updateHighlight(e);
    }

    private void updateHighlight(MouseEvent e) {

        int tileSize = gameWorld.getTailleTile();

        int col = (int) (e.getX() / tileSize);
        int row = (int) (e.getY() / tileSize);

        if (col < 0 || row < 0)
            return;

        highlight.setLayoutX(col * tileSize);
        highlight.setLayoutY(row * tileSize);
    }

    public void setModePlacement(boolean modePlacement) {

        this.modePlacement = modePlacement;

        if (!modePlacement)
            highlight.setVisible(false);
    }
}