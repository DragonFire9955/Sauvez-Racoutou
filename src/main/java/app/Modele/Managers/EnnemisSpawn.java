package app.Modele.Managers;

import app.Modele.GameWorld;
import javafx.scene.layout.TilePane;

public class EnnemisSpawn {


    public static double[] randomCoord(GameWorld w){
        double y, x;
        if (Math.random()<0.5){
            y = Math.random()*w.getMap().length*w.getTailleTile();
            if (Math.random()<0.5)
                x=0;
            else
                x=(w.getMap()[0].length-1)*w.getTailleTile();
        }
        else{
            x = Math.random()*w.getMap()[0].length*w.getTailleTile();
            if (Math.random()<0.5)
                y=0;
            else
                y=(w.getMap().length-1)*w.getTailleTile();
        }
        return new double[]{x, y};
    }
}
