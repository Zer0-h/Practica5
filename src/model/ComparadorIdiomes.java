package model;

import java.util.List;

/**
 * Classe per comparar dues llistes de paraules d'idiomes diferents.
 */
public class ComparadorIdiomes {

    public static ResultatComparacio comparar(Idioma a, Idioma b) {
        double sumaDistAB = a.getParaules().parallelStream()
            .mapToInt(paraulaA -> b.getParaules().stream()
                .mapToInt(paraulaB -> distanciaLevenshtein(paraulaA, paraulaB))
                .min()
                .orElse(Integer.MAX_VALUE))
            .sum();

        System.out.println("Finalitzada la primera part");

        double sumaDistBA = b.getParaules().parallelStream()
            .mapToInt(paraulaB -> a.getParaules().stream()
                .mapToInt(paraulaA -> distanciaLevenshtein(paraulaB, paraulaA))
                .min()
                .orElse(Integer.MAX_VALUE))
            .sum();

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
