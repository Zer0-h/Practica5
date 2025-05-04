package model;

import java.util.List;

/**
 * Representa un idioma amb una llista de paraules.
 */
public class Idioma {
    private final String nom;
    private final List<String> paraules;

    public Idioma(String nom, List<String> paraules) {
        this.nom = nom;
        this.paraules = paraules;
    }

    public String getNom() {
        return nom;
    }

    public List<String> getParaules() {
        return paraules;
    }
}
