package app.Modele.Utilitaires;

import java.util.Objects;

public class Noeud {

    private int x;
    private int y;

    private int coutDepuisDeprt; //La totalité des coûts depuis le ptn de départ
    private int coutEstimArr; // estimation jusqu'à arrivée
    private int coutEstimDepArr; //coutDepuisDeprt + coutEstimArr

    private Noeud parent;

    public Noeud(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /*Ces fonctions servent à éviter le crash de l'utilisation d'un Stream dans la recherche des voisins
    if (listNoeudsVu.contains(voisin))
    continue;
    Grâce à ça on prends en compte le fait que les cases identiques en x ET y sont égales et donc les mêmes
    Alors que le Stream les considèrent comme différentes et donc boucle infinie
     */
    @Override
    public boolean equals(Object obj) {

        //Corrige le crash principal ici
        if (this == obj) return true;

        //On vérif si c bien un Noeud, je te préshot Blandine
        if (!(obj instanceof Noeud)) return false;

        return x==((Noeud) obj).getX() && y==((Noeud) obj).getY();
    }

    /*Le hashCode doit être aussi redéf car hashCode contient HashSet et HashMap
    Il est use pour créer la liste des Noeuds vu de Deplacement.trouverChemin(), java fait ses calculs interne :
    En gros c un peu comme les IA (présenté par RU et Pomme) il attribue à l'ensemble (le hash) un numéro qu'il met dans
    une boite et va attribuer des numéros proches pour les noeuds proches du noeud en question.
    https://hejoseph.com/dev/fr/docs/java/Core/OOP/object-equality/ <-- (J'explique mal js donc voilà un truc qui explique)
     */
    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getCoutDepuisDeprt() {
        return coutDepuisDeprt;
    }
    public void setCoutDepuisDeprt(int coutDepuisDeprt) {
        this.coutDepuisDeprt = coutDepuisDeprt;
    }

    public int getCoutEstimArr() {
        return coutEstimArr;
    }
    public void setCoutEstimArr(int coutEstimArr) {
        this.coutEstimArr = coutEstimArr;
    }

    public int getCoutEstimDepArr() {
        return coutEstimDepArr;
    }
    public void setCoutEstimDepArr(int coutEstimDepArr) {
        this.coutEstimDepArr = coutEstimDepArr;
    }

    public Noeud getParent() {
        return parent;
    }
    public void setParent(Noeud parent) {
        this.parent = parent;
    }
}