package model;

public class ComparadorIdiomes {

    private boolean verbose;

    public ComparadorIdiomes(boolean verbose) {
        this.verbose = verbose;
    }

    public ResultatComparacio comparar(Idioma a, Idioma b) {
        long start = System.nanoTime();

        if (verbose) {
            System.out.println("Comparant " + a.getNom() + " amb " + b.getNom());
        }

        Levenshtein lev = new Levenshtein();



        double sumaDistAB = a.getParaules().parallelStream()
            .mapToInt(pa -> b.getParaules().stream()
                .mapToInt(pb -> lev.distancia(pa, pb))
                .min()
                .orElse(Integer.MAX_VALUE))
            .sum();

        double sumaDistBA = b.getParaules().parallelStream()
            .mapToInt(pb -> a.getParaules().stream()
                .mapToInt(pa -> lev.distancia(pb, pa))
                .min()
                .orElse(Integer.MAX_VALUE))
            .sum();

        double mitjaA = sumaDistAB / a.getParaules().size();
        double mitjaB = sumaDistBA / b.getParaules().size();
        double dist = Math.sqrt(mitjaA * mitjaA + mitjaB * mitjaB);

        if (verbose) {
            long end = System.nanoTime();
            System.out.println("Temps: " + ((end - start) / 1_000_000) + " ms");
        }

        return new ResultatComparacio(a.getNom(), b.getNom(), dist);
    }
}
