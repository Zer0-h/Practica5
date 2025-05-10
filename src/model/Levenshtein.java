package model;

public class Levenshtein {

    public int distancia(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        if (m == 0) return n;
        if (n == 0) return m;

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        // Inicialitzar la primera fila
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                curr[j] = Math.min(
                        Math.min(curr[j - 1] + 1,        // Inserció
                                 prev[j] + 1),           // Supressió
                        prev[j - 1] + cost);             // Substitució
            }

            // Intercanviar files
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

}
