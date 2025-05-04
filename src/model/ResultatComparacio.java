package model;

/**
 * Conté el resultat de la comparació entre dos idiomes.
 */
public class ResultatComparacio {
    private final String idiomaA;
    private final String idiomaB;
    private final double distancia;

    public ResultatComparacio(String a, String b, double d) {
        this.idiomaA = a;
        this.idiomaB = b;
        this.distancia = d;
    }

    public String getIdiomaA() {
        return idiomaA;
    }

    public String getIdiomaB() {
        return idiomaB;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return idiomaA + " - " + idiomaB + ": " + String.format("%.3f", distancia);
    }
}
