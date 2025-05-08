package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private String idiomaOrigen;
    private String idiomaDesti;

    private final List<Idioma> diccionaris;

    public Model() {
        diccionaris = new ArrayList<>();
    }

    public void setIdiomes(String iOrigen, String iDesti) {
        idiomaOrigen = iOrigen;
        idiomaDesti = iDesti;
    }

    public ResultatComparacio compararDos() {
        carregarDiccionaris(Arrays.asList(idiomaOrigen, idiomaDesti));

        Idioma origen = diccionaris.get(0);
        Idioma desti = diccionaris.size() > 1 ? diccionaris.get(1) : origen;

        ResultatComparacio resultat = ComparadorIdiomes.comparar(origen, desti);

        System.out.println(resultat);

        return resultat;
    }

    public List<ResultatComparacio> compararTots() {
        System.out.println("Comparant " + idiomaOrigen + " amb tots els altres idiomes.");

        List<String> noms = Arrays.asList("ale", "cat", "eng", "esp", "eus", "fra", "hol", "ita", "nor", "por", "swe");
        carregarDiccionaris(noms);

        Idioma origen = diccionaris.stream()
            .filter(id -> id.getNom().equals(idiomaOrigen))
            .findFirst()
            .orElse(null);

        List<ResultatComparacio> resultats = new ArrayList<>();

        for (Idioma altre : diccionaris) {
            if (!altre.getNom().equals(idiomaOrigen)) {
                ResultatComparacio resultat = ComparadorIdiomes.comparar(origen, altre);
                resultats.add(resultat);
                System.out.println(resultat);
            }
        }

        return resultats;
    }

    public void carregarDiccionaris(List<String> idiomes) {
        diccionaris.clear();

        File carpeta = new File("diccionaris");

        File[] fitxers = carpeta.listFiles((dir, name) ->
            idiomes.contains(name.replace(".txt", ""))
        );

        for (File fitxer : fitxers) {
            try {
                System.out.println("Carregant el fixter " + fitxer.getName());


                List<String> paraules = Files.readAllLines(fitxer.toPath());
                String nom = fitxer.getName().substring(0, 3);
                diccionaris.add(new Idioma(nom, paraules));

                System.out.println("Carregat!");
            } catch (IOException e) {
                System.out.println("Error llegint el fitxer: " + fitxer.getName());
            }
        }
    }
}
