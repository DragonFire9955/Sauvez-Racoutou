package app.Vue;

import javafx.scene.image.Image;

public class ImageSetter {


// SON
    public static Image sonOff = new Image(ImageSetter.class.getResourceAsStream("/app/images/son_off.png"));
    public static Image sonOn = new Image(ImageSetter.class.getResourceAsStream("/app/images/son_on.png"));


// TUILES
    public static Image sol = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/vide.png"), 64, 64, true, true);

    // BATIMENTS
    public static Image batCoinG = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coinG.png"),64, 64, false, true);
    public static Image batCoinD = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coinD.png"), 64, 64, false, true);

    public static Image batCoteB = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coteB.png"), 64, 64, false, true);
    public static Image batCoteBBus = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coteBBus.png"), 64, 64, false, true);
    public static Image batCoteBArbre = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coteBArbre.png"), 64, 64, false, true);

    public static Image batCoteG = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coteG.png"), 64, 64, false, true);
    public static Image batCoteD = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/coteD.png"), 64, 64, false, true);
    public static Image batCentre = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/batiment/centre.png"), 64, 64, false, true);

    // PLACE
    public static Image placeCoinHG = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coinHG.png"), 64, 64, false, true);
    public static Image placeCoinHD = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coinHD.png"), 64, 64, false, true);
    public static Image placeCoinBG = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coinBG.png"), 64, 64, false, true);
    public static Image placeCoinBD = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coinBD.png"), 64, 64, false, true);
    public static Image placeCoteB = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coteB.png"), 64, 64, false, true);
    public static Image placeCoteH = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coteH.png"), 64, 64, false, true);
    public static Image placeCoteG = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coteG.png"), 64, 64, false, true);
    public static Image placeCoteD = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/coteD.png"), 64, 64, false, true);
    public static Image placeCentre = new Image(ImageSetter.class.getResourceAsStream("/app/tuile/place/centre.png"), 64, 64, false, true);

}
