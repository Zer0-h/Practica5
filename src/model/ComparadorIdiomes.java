package model;

import java.util.List;

/**
 * Classe per comparar dues llistes de paraules d'idiomes diferents.
 */
public class ComparadorIdiomes {

public static ResultatComparacio comparar(Idioma a, Idioma b) {
        double sumaDistAB = 0;
        int comptadorAB = 0;

        System.out.println("Tamany idioma A: " + a.getParaules().size() + " Tamany idioma B: " + b.getParaules().size());

        for (String paraulaA : a.getParaules()) {
            int minDist = Integer.MAX_VALUE;
            for (String paraulaB : b.getParaules()) {
                int dist = distanciaLevenshtein(paraulaA, paraulaB);
                if (dist < minDist) minDist = dist;

                comptadorAB++;
                if (comptadorAB % 1000 == 0) {
                    System.out.println("Comparant " + a.getNom() + " vs " + b.getNom() + " → " + comptadorAB + " comparacions");
                }
            }
            sumaDistAB += minDist;
        }

        double sumaDistBA = 0;
        int comptadorBA = 0;

        for (String paraulaB : b.getParaules()) {
            int minDist = Integer.MAX_VALUE;
            for (String paraulaA : a.getParaules()) {
                int dist = distanciaLevenshtein(paraulaB, paraulaA);
                if (dist < minDist) minDist = dist;

                comptadorBA++;
                if (comptadorBA % 1000 == 0) {
                    System.out.println("Comparant " + b.getNom() + " vs " + a.getNom() + " → " + comptadorBA + " comparacions");
                }
            }
            sumaDistBA += minDist;
        }

        double mitjaA = sumaDistAB / a.getParaules().size();
        double mitjaB = sumaDistBA / b.getParaules().size();
        double distanciaFinal = Math.sqrt(Math.pow(mitjaA, 2) + Math.pow(mitjaB, 2));

        return new ResultatComparacio(a.getNom(), b.getNom(), distanciaFinal);
    }

    private static int distanciaLevenshtein(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] d = new int[m + 1][n + 1];

        // Inicialitzar primera fila i primera columna
        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        // Omplir la matriu amb els costos mínims
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;

                d[i][j] = Math.min(
                    Math.min(d[i - 1][j] + 1,      // Esborrar
                             d[i][j - 1] + 1),     // Inserir
                    d[i - 1][j - 1] + cost         // Substituir
                );
            }
        }

        return d[m][n];
    }
}
