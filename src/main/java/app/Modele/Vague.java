package app.Modele;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.PouletConservateur;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.Managers.AnimauxManager;

import java.util.*;

public class Vague {

    /*
    private IntegerProperty currentWave;
    private int waveDuration;
    private GameWorld w;
     */

    /*
    public Vague(GameWorld w) {

        currentWave = new SimpleIntegerProperty(0);
        waveDuration=0;

        this.w=w;
    }
     */

    private static Map<Integer, Map<Animal, Integer>> v1(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v1 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 3);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);


        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 1);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 5);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 7);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 2);

        v1.put(0, vv1);
        v1.put(5, vv2);
        v1.put(10, vv3);

        return v1;
    }

    private static Map<Integer, Map<Animal, Integer>> v2(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v2 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 5);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv1.put(new PouletBouclier(w), 1);


        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 3);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 1);
        vv2.put(new PouletBouclier(w), 5);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 7);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 2);
        vv3.put(new PouletBouclier(w), 7);

        Map<Animal, Integer> vv4 = new HashMap<>();
        vv4.put(AnimauxManager.creerChatClassique(w), 9);
        vv4.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv4.put(new PouletBouclier(w), 2);

        Map<Animal, Integer> vv5 = new HashMap<>();
        vv5.put(new PouletBouclier(w), 9);

        v2.put(0, vv1);
        v2.put(5, vv2);
        v2.put(10, vv3);
        v2.put(15, vv4);
        v2.put(20, vv5);

        return v2;
    }

    private static Map<Integer, Map<Animal, Integer>> v3(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v3 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 5);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv1.put(new PouletBouclier(w), 1);
        vv1.put(new PouletVolant(w), 1);

        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 7);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv2.put(new PouletBouclier(w), 3);
        vv2.put(new PouletVolant(w), 2);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 7);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv3.put(new PouletBouclier(w), 3);
        vv3.put(new PouletVolant(w), 3);

        Map<Animal, Integer> vv4 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 9);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv3.put(new PouletBouclier(w), 2);
        vv3.put(new PouletVolant(w), 4);

        v3.put(0, vv1);
        v3.put(5, vv2);
        v3.put(10, vv3);
        v3.put(15, vv4);

        return v3;
    }

    private static Map<Integer, Map<Animal, Integer>> v4(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v4 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 5);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv1.put(new PouletBouclier(w), 1);
        vv1.put(new PouletVolant(w), 1);
        vv1.put(new PouletConservateur(w), 1);

        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 3);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 1);
        vv2.put(new PouletBouclier(w), 5);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 10);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 2);
        vv3.put(new PouletBouclier(w), 2);
        vv3.put(new PouletVolant(w), 2);
        vv3.put(new PouletConservateur(w), 2);

        Map<Animal, Integer> vv4 = new HashMap<>();
        vv4.put(AnimauxManager.creerChatClassique(w), 12);
        vv4.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv4.put(new PouletBouclier(w), 2);
        vv4.put(new PouletVolant(w), 5);
        vv4.put(new PouletConservateur(w), 2);


        v4.put(0, vv1);
        v4.put(5, vv2);
        v4.put(10, vv3);
        v4.put(15, vv4);

        return v4;
    }

    private static Map<Integer, Map<Animal, Integer>> v5(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v5 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 10);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv1.put(new PouletBouclier(w), 3);
        vv1.put(new PouletVolant(w), 3);
        vv1.put(new PouletConservateur(w), 2);

        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 8);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 4);
        vv2.put(new PouletBouclier(w), 7);
        vv1.put(new PouletVolant(w), 5);
        vv1.put(new PouletConservateur(w), 3);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 15);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 5);
        vv3.put(new PouletBouclier(w), 5);
        vv3.put(new PouletVolant(w), 2);
        vv3.put(new PouletConservateur(w), 1);

        Map<Animal, Integer> vv4 = new HashMap<>();
        vv4.put(AnimauxManager.creerChatClassique(w), 10);
        vv4.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv4.put(new PouletBouclier(w), 10);
        vv4.put(new PouletVolant(w), 4);
        vv4.put(new PouletConservateur(w), 5);

        Map<Animal, Integer> vv5 = new HashMap<>();
        vv5.put(AnimauxManager.creerChatClassique(w), 12);
        vv5.put(AnimauxManager.creerPouletMenotte(w), 5);
        vv5.put(new PouletBouclier(w), 8);
        vv5.put(new PouletVolant(w), 10);
        vv5.put(new PouletConservateur(w), 8);

        v5.put(0, vv1);
        v5.put(5, vv2);
        v5.put(10, vv3);
        v5.put(15, vv4);
        v5.put(20, vv5);

        return v5;
    }

    private static Map<Integer, Map<Animal, Integer>> v6(GameWorld w) {
        Map<Integer, Map<Animal, Integer>> v6 = new HashMap<>();

        Map<Animal, Integer> vv1 = new HashMap<>();
        vv1.put(AnimauxManager.creerChatClassique(w), 6);
        vv1.put(AnimauxManager.creerPouletMenotte(w), 3);
        vv1.put(new PouletBouclier(w), 6);
        vv1.put(new PouletVolant(w), 1);
        vv1.put(new PouletConservateur(w), 2);

        Map<Animal, Integer> vv2 = new HashMap<>();
        vv2.put(AnimauxManager.creerChatClassique(w), 9);
        vv2.put(AnimauxManager.creerPouletMenotte(w), 2);
        vv2.put(new PouletBouclier(w), 4);
        vv1.put(new PouletVolant(w), 2);
        vv2.put(new PouletConservateur(w), 3);

        Map<Animal, Integer> vv3 = new HashMap<>();
        vv3.put(AnimauxManager.creerChatClassique(w), 10);
        vv3.put(AnimauxManager.creerPouletMenotte(w), 5);
        vv3.put(new PouletBouclier(w), 10);
        vv3.put(new PouletVolant(w), 3);
        vv3.put(new PouletConservateur(w), 5);

        Map<Animal, Integer> vv4 = new HashMap<>();
        vv4.put(AnimauxManager.creerChatClassique(w), 15);
        vv4.put(AnimauxManager.creerPouletMenotte(w), 5);
        vv4.put(new PouletBouclier(w), 5);
        vv4.put(new PouletVolant(w), 3);
        vv4.put(new PouletConservateur(w), 2);

        Map<Animal, Integer> vv5 = new HashMap<>();
        vv5.put(AnimauxManager.creerChatClassique(w), 8);
        vv5.put(AnimauxManager.creerPouletMenotte(w), 4);
        vv5.put(new PouletBouclier(w), 15);
        vv5.put(new PouletVolant(w), 4);
        vv5.put(new PouletConservateur(w),5);

        v6.put(0, vv1);
        v6.put(5, vv2);
        v6.put(10, vv3);
        v6.put(20, vv4);
        v6.put(30, vv5);

        return v6;
    }

    public static List<Animal> creerVague(Map<Animal, Integer> m){
        List<Animal> animaux = new ArrayList<>();
        if(m.isEmpty())
            return null;
        for(Map.Entry entry: m.entrySet()){
            for(int i =0; i < (int) entry.getValue(); i++)
                animaux.add((Animal) entry.getKey());
        }

        return animaux;
    }



    /*
    public static Map<Integer, Map<Integer, Map<Animal, Integer>>> ensembleVagues(GameWorld w){
        Map<Integer, Map<Integer, Map<Animal, Integer>>> v = new HashMap<>();

        v.put(0, v1(w));     // 0+20 =20
        v.put(25, v2(w));    // ( +5 = 25) + 20 = 45
        v.put(55, v3(w));    // ( +10 = 55) + 15 = 70
        v.put(80, v4(w));    // ( +10 = 90) + 15 = 105
        v.put(115, v5(w));   // ( +10 = 115) + 20 = 135
        v.put(145, v6(w));   // ( +10 = 145) + 30 = 165


        return v;
    }

     */

/*
    public static SequencedMap<Integer, Map<Integer, Map<Animal, Integer>>> ensembleVagues2(GameWorld w) {
        Map<Integer, Map<Integer, Map<Animal, Integer>>> v = new HashMap<>();
        SequencedMap<List<Map<Integer, Map<Animal, Integer>>>> v = new LinkedHashMap();
        v.put(0, v1(w));     // 0+20 =20
        v.put(25, v2(w));    // ( +5 = 25) + 20 = 45
        v.put(55, v3(w));    // ( +10 = 55) + 15 = 70
        v.put(80, v4(w));    // ( +10 = 90) + 15 = 105
        v.put(115, v5(w));   // ( +10 = 115) + 20 = 135
        v.put(145, v6(w));   // ( +10 = 145) + 30 = 165


        return v;
    }

 */


    public static List<Integer> debutVagues(){

        List<Integer> t = new ArrayList<>();
        t.add(0);
        t.add(30);
        t.add(60);
        t.add(85);
        t.add(110);
        t.add(140);
        t.add(165);
        /*
              0+20 =20
   ( +5 = 25) + 20 = 45
   (+10 = 55) + 15 = 70
   ( +10 = 90) + 15 = 105
  ( +10 = 115) + 20 = 135
  ( +10 = 145) + 30 = 165
        */

        return t;
    }

    public static List<Map<Integer, Map<Animal, Integer>>> ensembleVagues (GameWorld w){
        List<Map<Integer, Map<Animal, Integer>>> v= new ArrayList<>();

        v.add(v1(w));     // 0+20 =20
        v.add(v2(w));    // ( +10 = 30) + 20 = 50
        v.add(v3(w));    // ( +10 = 60) + 15 = 75
        v.add(v4(w));    // ( +10 = 85) + 15 = 100
        v.add(v5(w));   // ( +10 = 110) + 20 = 130
        v.add(v6(w));   // ( +10 = 140) + 30 = 170


        return v;
    }

    public static ArrayList<Animal> lAnimaux(int[] nb, GameWorld w){

        ArrayList<Animal> l = new ArrayList<>();
        for(int i=0; i<nb[0]; i++)
            l.add(AnimauxManager.creerPouletClassique(w));

        for(int i=0; i<nb[1]; i++)
            l.add(AnimauxManager.creerPouletMenotte(w));

        return l;
    }

    /* public static HashMap<Integer, ArrayList<Animal>> creerVague1(GameWorld w){

         currentWave.setValue(1);
         waveDuration = 20;

         HashMap<Integer, ArrayList<Animal>> v= new HashMap<>();

         v.put(0, lAnimaux(new int[]{3, 3}, w));
         v.put(5, lAnimaux(new int[]{1, 5}, w));
         v.put(10, lAnimaux(new int[]{7, 2}, w));

         return v;
     }


    public HashMap<Integer, ArrayList<Animal>> creerVague1(GameWorld w){
        return creerVague(v1, 1, 20, w);
    }

    public HashMap<Integer, ArrayList<Animal>> creerVague2(GameWorld w){
        return creerVague(v2, 2, 30, w);
    }

    public HashMap<Integer, ArrayList<Animal>> creerVague3(GameWorld w){
        return creerVague(v3, 3, 25, w);
    }

    public HashMap<Integer, ArrayList<Animal>> creerVague4(GameWorld w){
        return creerVague(v4, 4, 25, w);
    }

    public HashMap<Integer, ArrayList<Animal>> creerVague(int[][] tab, int nbWave, int durree, GameWorld w){
        HashMap<Integer, ArrayList<Animal>> v= new HashMap<>();

        currentWave.setValue(nbWave);
        waveDuration =durree;
        int temps;

        for(int i=0; i < tab.length; i++){
            temps= tab[i][0];
            int[] nb = new int[tab[0].length -1];
            System.arraycopy(tab[i], 1, nb, 0, tab[0].length - 1);
            v.put(temps, lAnimaux(nb, w));
        }

        return v;
    }

    */

    /*
       TEMPS, simple, menotte, bouclier, conservateur, volant

    v1
        {0 , 3, 3, 0, 0, 0},
        {5 , 1, 5, 0, 0, 0},
        {10, 7, 2, 0, 0, 0}

    v2
        {0,  5, 3, 1, 0, 0},
        {5,  3, 1, 5, 0, 0},
        {10, 7, 2, 7, 0, 0},
        {15, 9, 3, 2, 0, 0},
        {20, 0, 0, 9, 0, 0},

    v3
        {0,  5, 3, 1, 0, 1},
        {5,  7, 1, 2, 0, 2},
        {10, 7, 3, 3, 0, 3},
        {15, 9, 3, 2, 0, 4}

    v4
        {0,  5,  3, 1, 1, 1},
        {5,  3,  1, 5, 0, 0},
        {10, 10, 2, 2, 2, 2},
        {15, 12, 3, 2, 3, 5}

     v5
        {0,  10, 3, 3,  2,  3},
        {5,  8,  4, 7,  3,  5},
        {10, 15, 5, 5,  1,  2},
        {15, 10, 3, 10, 5,  4}
        {20, 12, 5, 8,  10, 8}

        v6
        {0,  6,  3, 6,   2,  1},
        {5,  9,  2, 4,   3,  2},
        {10, 10, 5, 10,  5,  3},
        {20, 15, 5, 5,   3,  2}
        {30, 8,  4, 15,  5,  4}
     */








}
