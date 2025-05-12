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
    private List<ResultatComparacio> resultats;

    public Model() {
    }

    public void setIdiomes(String iOrigen, String iDesti) {
        idiomaOrigen = iOrigen;
        idiomaDesti = iDesti;
    }

    public String getIdiomaOrigen() {
        return idiomaOrigen;
    }

    public String getIdiomaDesti() {
        return idiomaDesti;
    }

    public List<ResultatComparacio> getResultatsMultiples() { return resultats; }

    public void resetResultats() { resultats = new ArrayList<>(); }

    public void setResultats(List<ResultatComparacio> r) { resultats = r; }

    public void afegirResultats(ResultatComparacio r) { resultats.add(r); }


    public List<Idioma> carregarDiccionaris(List<String> idiomes) {
        List<Idioma> diccionaris = new ArrayList<>();

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

        return diccionaris;
    }
}
