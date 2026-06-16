package app.Modele.Managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapManager {

    private final List<int[][]> maps = new ArrayList<>();

    public MapManager() {
        chargerToutesLesMaps();
    }

    public List<int[][]> getMaps() {
        return maps;
    }

    /**
     * cherche pas à savoir
     * */
    private void chargerToutesLesMaps() {

        Path path = Paths.get("src/main/resources/app/maps");

        try (Stream<Path> txtFiles = Files.list(path)) {
            txtFiles.filter(p -> p.toString().endsWith(".txt")).sorted().forEach(p -> {
                try {
                    maps.add(chargerMap(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    Un BufferedReader ouvre le fichier en question et lis ligne par ligne (comme on a vu en système au S1)
    String ligne contient la ligne actuelle et est lue avec br.readLine()
    \\s c'est un espace, et + c pr dire 1 ou plusieurs (tjr le cours de S1)
    Le string est converti en int (parseInt lis un string et le met en entier)
    Un stream c un flux de données (aller voir sur internet hein), avec toArray transforme le stream en int[]
    Et on ajoute le résultat à la liste
    On return lignes qui contient tout les tableaux de int, à préciser :
    on retourne un tableau double de int car le map est un tableau en 2D, et on retourne ligne par ligne, donc on
    se fiche de la 1ere valeur du double tableau car ce qui nous intéresse c tout ce qu'il contient, donc ce qui
    est souvent fait est de mettre un 0 apparemment car c plus compréhensible.
     */
    private int[][] chargerMap(Path fichier) throws IOException {

        List<int[]> lignes = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(fichier)) {

            String ligne;

            while ((ligne = br.readLine()) != null)
                lignes.add(Arrays.stream(ligne.split("\\s+")).mapToInt(Integer::parseInt).toArray());

        }

        return lignes.toArray(new int[0][]);
    }

}