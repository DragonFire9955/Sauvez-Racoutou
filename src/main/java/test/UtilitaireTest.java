package test;

import app.Modele.Utilitaires.Utilitaire;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitaireTest {


    @Test
    public void testDistance() {
        double res = Utilitaire.distance(0, 0, 3, 4);
        assertEquals(5.0, res, "La distance entre (0,0) et (3,4) doit être 5");
    }

    @Test
    public void testValAbs() {
        assertEquals(5.5, Utilitaire.valAbs(-5.5), "La valeur absolue de -5.5 est 5.5");
        assertEquals(3.0, Utilitaire.valAbs(3.0), "La valeur absolue de 3.0 est 3.0");
        assertEquals(0.0, Utilitaire.valAbs(0.0), "La valeur absolue de 0.0 est 0.0");
    }

    @Test
    public void testDivisionEuclidienne() {
        assertEquals(3, Utilitaire.divisionEuclidienne(10.5, 3.0), "10.5 / 3.0 = 3");
        assertEquals(0, Utilitaire.divisionEuclidienne(2.0, 5.0), "2.0 / 5.0 = 0");
    }


    @Test
    public void testGetIndex() {
        List<Integer> liste = Arrays.asList(10, 20, 30, 40);

        assertEquals(2, Utilitaire.getIndex(liste, 30), "L'index de 30 est 2");
        assertEquals(-1, Utilitaire.getIndex(liste, 99), "Si la valeur n'existe pas ca doit retourner -1");
    }

}
