package app.Vue;

import javafx.scene.image.Image;

public class ImageSetter {

    public static Image chat = new Image(ImageSetter.class.getResourceAsStream("/app/images/chat.png"));

    public static Image base = new Image(ImageSetter.class.getResourceAsStream("/app/images/herbe.png"), 32, 32, true, true);
    public static Image sol = new Image(ImageSetter.class.getResourceAsStream("/app/images/sol.png"), 32, 32, true, true);
    public static Image tonneau = new Image(ImageSetter.class.getResourceAsStream("/app/images/tonneau4.png"), 32, 32, true, true);
    public static Image hor = new Image(ImageSetter.class.getResourceAsStream("/app/images/horizontal.png"), 32, 32, false, true);
    public static Image ver = new Image(ImageSetter.class.getResourceAsStream("/app/images/vertical2.png"), 32, 32, false, true);

    public static Image poubelle = new Image(ImageSetter.class.getResourceAsStream("/app/images/poubelle.png"), 32, 32, false, true);
    public static Image classique = new Image(ImageSetter.class.getResourceAsStream("/app/images/chat.png"), 32, 32, false, true);
    public static Image projectiles = new Image(ImageSetter.class.getResourceAsStream("/app/images/projectiles.png"), 32, 32, false, true);
    public static Image journaliste = new Image(ImageSetter.class.getResourceAsStream("/app/images/journaliste.png"), 32, 32, false, true);

}
