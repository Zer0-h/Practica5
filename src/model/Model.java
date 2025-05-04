package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> idiomes;
    private final List<Idioma> diccionaris;

    public Model() {
        diccionaris = new ArrayList<>();
    }

    public List<String> getIdiomes() {
        return idiomes;
    }

    public void setIdiomes(List<String> idiomes) {
        this.idiomes = idiomes;
    }

    public List<ResultatComparacio> compararIdiomesSeleccionats() {
        System.out.println("Comencant la comparacio");

        carregarDiccionaris();

        List<ResultatComparacio> resultats = new ArrayList<>();

        for (int i = 0; i < diccionaris.size(); i++) {
            for (int j = i + 1; j < diccionaris.size(); j++) {
                Idioma idiomaA = diccionaris.get(i);
                Idioma idiomaB = diccionaris.get(j);

                if (idiomaA != null && idiomaB != null) {
                    ResultatComparacio resultat = ComparadorIdiomes.comparar(idiomaA, idiomaB);
                    resultats.add(resultat);
                    System.out.println(resultat);
                }
            }
        }

        return resultats;
    }

    public void carregarDiccionaris() {
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
