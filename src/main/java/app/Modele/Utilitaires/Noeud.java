package app.Modele.Utilitaires;

public class Noeud {

    private int i;
    private int j;

    private int coutDepuisDeprt; //La totalité des coûts depuis le ptn de départ
    private int coutEstimArr; // estimation jusqu'à arrivée
    private int coutEstimDepArr; //coutDepuisDeprt + coutEstimArr

    private Noeud parent;

    public Noeud(int i, int j) {
        this.i = i;
        this.j = j;
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


        //Ok si tableau de 2 entiers
        //if(obj instanceof int[] && ((int[]) obj).length==2 && (int[]) obj[0] == x && obj[1]==y) return true;

        //On vérif si c bien un Noeud, je te préshot Blandine NON
        if (!(obj instanceof Noeud)) return false;

        return i ==((Noeud) obj).getI() && j ==((Noeud) obj).getJ();
    }

    public boolean equals(int[] coord){
        System.out.println("youhou");
        return (coord.length==2 && coord[0]== i && coord[1]== j);
    }

    /*Le hashCode doit être aussi redéf car hashCode contient HashSet et HashMap
    Il est use pour créer la liste des Noeuds vu de Deplacement.trouverChemin(), java fait ses calculs interne :
    En gros c un peu comme les IA (présenté par RU et Pomme) il attribue à l'ensemble (le hash) un numéro qu'il met dans
    une boite et va attribuer des numéros proches pour les noeuds proches du noeud en question.
    https://hejoseph.com/dev/fr/docs/java/Core/OOP/object-equality/ <-- (J'explique mal js donc voilà un truc qui explique)
     */
    @Override
    public int hashCode() {
        return i * 31 + j;
    }

    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }
    public void setJ(int j) {
        this.j = j;
    }

    public int[] getCoord(){
        return new int[]{i, j};
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

