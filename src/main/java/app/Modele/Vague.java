package app.Modele;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.Managers.AnimauxManager;

import java.util.*;

public class Vague {

    private static TreeMap<Integer, List<Animal>> v1(GameWorld w) {
        TreeMap<Integer, List<Animal>> v1 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 10);
        vv1.put("PouletMenotte", 1);


        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 1);
        vv2.put("PouletMenotte",1);

        Map<String , Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 1);
        vv3.put("PouletMenotte", 2);

        v1.put(0, creerVague(vv1, w));
        v1.put(5, creerVague(vv2, w));
        v1.put(10, creerVague(vv3, w));

        return v1;
    }

    private static TreeMap<Integer, List<Animal>> v2(GameWorld w) {
        TreeMap<Integer, List<Animal>> v2 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 5);
        vv1.put("PouletMenotte", 3);
        vv1.put("PouletBouclier", 1);


        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 3);
        vv2.put("PouletMenotte", 1);
        vv2.put("PouletBouclier", 5);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 7);
        vv3.put("PouletMenotte", 2);
        vv3.put("PouletBouclier", 7);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("PouletClassique", 9);
        vv4.put("PouletMenotte", 3);
        vv4.put("PouletBouclier", 2);

        Map<String, Integer> vv5 = new HashMap<>();
        vv5.put("PouletBouclier", 9);

        v2.put(0, creerVague(vv1, w));
        v2.put(5, creerVague(vv2, w));
        v2.put(10, creerVague(vv3, w));
        v2.put(15, creerVague(vv4, w));
        v2.put(20, creerVague(vv5, w));

        return v2;
    }

    private static TreeMap<Integer,List<Animal>> v3(GameWorld w) {
        TreeMap<Integer, List<Animal>> v3 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 5);
        vv1.put("PouletMenotte", 3);
        vv1.put("PouletBouclier", 1);
        vv1.put("PouletVolant", 1);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 7);
        vv2.put("PouletMenotte", 3);
        vv2.put("PouletBouclier", 3);
        vv2.put("PouletVolant", 2);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 7);
        vv3.put("PouletMenotte", 3);
        vv3.put("PouletBouclier", 3);
        vv3.put("PouletVolant", 3);

        Map<String, Integer> vv4 = new HashMap<>();
        vv3.put("PouletClassique", 9);
        vv3.put("PouletMenotte", 3);
        vv3.put("PouletBouclier", 2);
        vv3.put("PouletVolant", 4);

        v3.put(0, creerVague(vv1, w));
        v3.put(5, creerVague(vv2, w));
        v3.put(10, creerVague(vv3, w));
        v3.put(15, creerVague(vv4, w));

        return v3;
    }

    private static TreeMap<Integer, List<Animal>> v4(GameWorld w) {
        TreeMap<Integer, List<Animal>> v4 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 5);
        vv1.put("PouletMenotte", 3);
        vv1.put("PouletBouclier", 1);
        vv1.put("PouletVolant", 1);
        vv1.put("PouletConservateur", 1);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 3);
        vv2.put("PouletMenotte", 1);
        vv2.put("PouletBouclier", 5);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 10);
        vv3.put("PouletMenotte", 2);
        vv3.put("PouletBouclier", 2);
        vv3.put("PouletVolant", 2);
        vv3.put("PouletConservateur", 2);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("PouletClassique", 12);
        vv4.put("PouletMenotte", 3);
        vv4.put("PouletBouclier", 2);
        vv4.put("PouletVolant", 5);
        vv4.put("PouletConservateur", 2);


        v4.put(0, creerVague(vv1, w));
        v4.put(5, creerVague(vv2, w));
        v4.put(10, creerVague(vv3, w));
        v4.put(15, creerVague(vv4, w));

        return v4;
    }

    private static TreeMap<Integer, List<Animal>> v5(GameWorld w) {
        TreeMap<Integer, List<Animal>> v5 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 10);
        vv1.put("PouletMenotte", 3);
        vv1.put("PouletBouclier", 3);
        vv1.put("PouletVolant", 3);
        vv1.put("PouletConservateur", 2);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 8);
        vv2.put("PouletMenotte", 4);
        vv2.put("PouletBouclier", 7);
        vv1.put("PouletVolant", 5);
        vv1.put("PouletConservateur", 3);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 15);
        vv3.put("PouletMenotte", 5);
        vv3.put("PouletBouclier", 5);
        vv3.put("PouletVolant", 2);
        vv3.put("PouletConservateur", 1);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("PouletClassique", 10);
        vv4.put("PouletMenotte", 3);
        vv4.put("PouletBouclier", 10);
        vv4.put("PouletVolant", 4);
        vv4.put("PouletConservateur", 5);

        Map<String, Integer> vv5 = new HashMap<>();
        vv5.put("PouletClassique", 12);
        vv5.put("PouletMenotte", 5);
        vv5.put("PouletBouclier", 8);
        vv5.put("PouletVolant", 10);
        vv5.put("PouletConservateur", 8);

        v5.put(0, creerVague(vv1, w));
        v5.put(5, creerVague(vv2, w));
        v5.put(10, creerVague(vv3, w));
        v5.put(15, creerVague(vv4, w));
        v5.put(20, creerVague(vv5, w));

        return v5;
    }

    private static TreeMap<Integer, List<Animal>> v6(GameWorld w) {
        TreeMap<Integer, List<Animal>> v6 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("PouletClassique", 6);
        vv1.put("PouletMenotte", 3);
        vv1.put("PouletBouclier", 6);
        vv1.put("PouletVolant", 1);
        vv1.put("PouletConservateur", 2);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("PouletClassique", 9);
        vv2.put("PouletMenotte", 2);
        vv2.put("PouletBouclier", 4);
        vv1.put("PouletVolant", 2);
        vv2.put("PouletConservateur", 3);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("PouletClassique", 10);
        vv3.put("PouletMenotte", 5);
        vv3.put("PouletBouclier", 10);
        vv3.put("PouletVolant", 3);
        vv3.put("PouletConservateur", 5);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("PouletClassique", 15);
        vv4.put("PouletMenotte", 5);
        vv4.put("PouletBouclier", 5);
        vv4.put("PouletVolant", 3);
        vv4.put("PouletConservateur", 2);

        Map<String, Integer> vv5 = new HashMap<>();
        vv5.put("PouletClassique", 8);
        vv5.put("PouletMenotte", 4);
        vv5.put("PouletBouclier", 15);
        vv5.put("PouletVolant", 4);
        vv5.put("PouletConservateur",5);

        v6.put(0, creerVague(vv1, w));
        v6.put(5, creerVague(vv2, w));
        v6.put(10, creerVague(vv3, w));
        v6.put(20, creerVague(vv4, w));
        v6.put(30, creerVague(vv5, w));

        return v6;
    }

    public static List<Animal> creerVague(Map<String, Integer> m, GameWorld w){
        List<Animal> animaux = new ArrayList<>();
        if(m.isEmpty())
            return null;
        for(Map.Entry entry: m.entrySet()){
            for(int i =0; i < (int) entry.getValue(); i++){
                animaux.add(creerAnimal( (String) entry.getKey(), w));
            }
        }

        return animaux;
    }

    public static List<TreeMap<Integer, List<Animal>>> ensembleVagues (GameWorld w){
        List<TreeMap<Integer, List<Animal>>> v= new ArrayList<>();

        v.add(v1(w));     // 0+20 =20
        /*
        v.add(v2(w));    // ( +10 = 30) + 20 = 50
        v.add(v3(w));    // ( +10 = 60) + 15 = 75
        v.add(v4(w));    // ( +10 = 85) + 15 = 100
        v.add(v5(w));   // ( +10 = 110) + 20 = 130
        v.add(v6(w));
          // ( +10 = 140) + 30 = 170
         */


        return v;
    }


    public static Animal creerAnimal(String classe, GameWorld w) {

        Animal a;

        switch (classe) {
            case "pouletClassique":
                a = AnimauxManager.creerPouletClassique(w);
                break;
            case "pouletMenotte":
                a = AnimauxManager.creerPouletMenotte(w);
                break;

            case "pouletBouclier":
                a = new PouletBouclier(w);
                break;
            case "pouletConservateur":
                a = AnimauxManager.creerPouletConservateur(w);
                break;
            case "pouletVolant":
                a = new PouletVolant(w);
                break;
            default:
                a = null;
                break;
        }
        return a;
    }
}
