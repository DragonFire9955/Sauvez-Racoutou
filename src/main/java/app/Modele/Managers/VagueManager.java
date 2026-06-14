package app.Modele.Managers;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.GameWorld;

import java.util.*;

public class VagueManager {

    private static TreeMap<Integer, List<Animal>> v1(GameWorld w) {
        TreeMap<Integer, List<Animal>> v1 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletRoller", 3);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletClassique", 4);
        vv2.put("pouletBouclier",2);

        v1.put(0, creerVague(vv1, w));
        v1.put(10, creerVague(vv2, w));

        return v1;
    }

    private static TreeMap<Integer, List<Animal>> v2(GameWorld w) {
        TreeMap<Integer, List<Animal>> v2 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 3);
        vv1.put("pouletBouclier", 1);


        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletClassique", 4);
        vv2.put("pouletBouclier",2);
        vv2.put("pouletRoller",2);

        Map<String , Integer> vv3 = new HashMap<>();
        vv3.put("pouletBouclier", 3);
        vv3.put("pouletMenottes", 1);

        v2.put(0, creerVague(vv1, w));
        v2.put(5, creerVague(vv2, w));
        v2.put(10, creerVague(vv3, w));

        return v2;
    }

    private static TreeMap<Integer, List<Animal>> v3(GameWorld w) {
        TreeMap<Integer, List<Animal>> v3 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 5);
        vv1.put("pouletRoller", 3);
        vv1.put("pouletBouclier", 1);


        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletRoller", 3);
        vv2.put("pouletBouclier", 5);
        vv2.put("pouletMenottes", 1);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("pouletProjectible", 7);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("pouletClassique", 9);
        vv4.put("pouletMenottes", 3);
        vv4.put("pouletProjectible", 2);

        v3.put(0, creerVague(vv1, w));
        v3.put(5, creerVague(vv2, w));
        v3.put(15, creerVague(vv3, w));
        v3.put(25, creerVague(vv4, w));

        return v3;
    }

    private static TreeMap<Integer,List<Animal>> v4(GameWorld w) {
        TreeMap<Integer, List<Animal>> v4 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 5);
        vv1.put("pouletRoller", 4);
        vv1.put("pouletBouclier", 3);
        vv1.put("pouletMenottes", 2);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletRoller", 15);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("pouletMenottes", 7);
        vv3.put("pouletProjectible", 3);
        vv3.put("pouletConservateur", 3);

        Map<String, Integer> vv4 = new HashMap<>();
        vv3.put("pouletBouclier", 9);
        vv3.put("pouletMenottes", 2);
        vv3.put("pouletConservateur", 3);

        v4.put(0, creerVague(vv1, w));
        v4.put(10, creerVague(vv2, w));
        v4.put(20, creerVague(vv3, w));
        v4.put(30, creerVague(vv4, w));

        return v4;
    }

    private static TreeMap<Integer, List<Animal>> v5(GameWorld w) {
        TreeMap<Integer, List<Animal>> v5 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 5);
        vv1.put("pouletRoller", 3);
        vv1.put("pouletBouclier", 1);
        vv1.put("pouletMenottes", 1);
        vv1.put("pouletConservateur", 1);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletRoller", 3);
        vv2.put("pouletMenottes", 1);
        vv2.put("pouletConservateur", 5);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("pouletClassique", 10);
        vv3.put("pouletBouclier", 2);
        vv3.put("pouletMenottes", 2);
        vv3.put("pouletConservateur", 2);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("pouletClassique", 3);
        vv4.put("pouletRoller", 12);
        vv4.put("pouletBouclier", 2);
        vv4.put("pouletMenottes", 5);
        vv4.put("pouletConservateur", 2);


        v5.put(0, creerVague(vv1, w));
        v5.put(10, creerVague(vv2, w));
        v5.put(20, creerVague(vv3, w));
        v5.put(35, creerVague(vv4, w));

        return v5;
    }

    private static TreeMap<Integer, List<Animal>> v6(GameWorld w) {
        TreeMap<Integer, List<Animal>> v6 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 3);
        vv1.put("pouletRoller", 3);
        vv1.put("pouletBouclier", 10);
        vv1.put("pouletMenottes", 3);
        vv1.put("pouletConservateur", 2);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletClassique", 8);
        vv2.put("pouletRoller", 4);
        vv1.put("pouletBouclier", 5);
        vv2.put("pouletMenottes", 7);
        vv1.put("pouletConservateur", 3);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("pouletMenottes", 15);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("pouletClassique", 10);
        vv4.put("pouletRoller", 3);
        vv4.put("pouletConservateur", 10);

        Map<String, Integer> vv5 = new HashMap<>();
        vv5.put("pouletRoller", 25);

        v6.put(0, creerVague(vv1, w));
        v6.put(10, creerVague(vv2, w));
        v6.put(25, creerVague(vv3, w));
        v6.put(35, creerVague(vv4, w));
        v6.put(50, creerVague(vv5, w));

        return v6;
    }

    private static TreeMap<Integer, List<Animal>> v7(GameWorld w) {
        TreeMap<Integer, List<Animal>> v7 = new TreeMap<>();

        Map<String, Integer> vv1 = new HashMap<>();
        vv1.put("pouletClassique", 6);
        vv1.put("pouletRoller", 3);
        vv1.put("pouletConservateur", 6);

        Map<String, Integer> vv2 = new HashMap<>();
        vv2.put("pouletRoller", 2);
        vv2.put("pouletMenottes", 4);
        vv2.put("pouletConservateur", 9);

        Map<String, Integer> vv3 = new HashMap<>();
        vv3.put("pouletClassique", 10);
        vv3.put("pouletRoller", 10);
        vv3.put("pouletBouclier", 5);
        vv3.put("pouletConservateur", 3);

        Map<String, Integer> vv4 = new HashMap<>();
        vv4.put("pouletBouclier", 15);
        vv4.put("pouletMenottes", 5);
        vv4.put("pouletConservateur", 5);

        Map<String, Integer> vv5 = new HashMap<>();
        vv5.put("pouletClassique", 8);
        vv5.put("pouletRoller", 4);
        vv5.put("pouletBouclier", 15);
        vv5.put("pouletMenottes", 4);
        vv5.put("pouletConservateur",5);

        v7.put(0, creerVague(vv1, w));
        v7.put(15, creerVague(vv2, w));
        v7.put(30, creerVague(vv3, w));
        v7.put(45, creerVague(vv4, w));
        v7.put(60, creerVague(vv5, w));

        return v7;
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
        v.add(v2(w));    // ( +10 = 30) + 20 = 50
        v.add(v3(w));    // ( +10 = 60) + 15 = 75
        v.add(v4(w));    // ( +10 = 85) + 15 = 100
        v.add(v5(w));   // ( +10 = 110) + 20 = 130
        v.add(v6(w));


        return v;
    }

    public static Animal creerAnimal(String classe, GameWorld w) {

        Animal a;

        switch (classe) {
            case "pouletClassique":
                a = EntitesManager.creerPouletClassique(w);
                break;
            case "pouletBouclier":
                a = new PouletBouclier(w);
                break;
            case "pouletRoller":
                a = EntitesManager.creerPouletRolleur(w);
                break;
            case "pouletProjectible":
                a = new PouletBouclier(w);
                break;
            case "pouletMenottes":
                a = EntitesManager.creerPouletMenotte(w);
                break;
            case "pouletConservateur":
                a = EntitesManager.creerPouletConservateur(w);
                break;
            default:
                System.out.println("ATTENTION POULET INCONNU DANS LE SPAWN DES VAGUES");
                System.out.println(classe);
                a = null;
                break;
        }
        return a;
    }
}
