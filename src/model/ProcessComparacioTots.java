package model;

import controlador.Controlador;
import controlador.Notificacio;
import java.util.List;

public class ProcessComparacioTots extends Thread {

    private final Controlador controlador;

    public ProcessComparacioTots(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        Model model = controlador.getModel();

        List<Idioma> diccionaris = model.carregarDiccionaris(List.of("ale", "cat", "eng", "esp", "eus", "fra", "hol", "ita", "nor", "por", "swe"));

        Idioma origen = diccionaris.stream()
            .filter(id -> id.getNom().equals(model.getIdiomaOrigen()))
            .findFirst()
            .orElse(null);

        if (origen == null) {
            System.out.println("No s'ha trobat l'idioma origen: " + model.getIdiomaOrigen());
            return;
        }

        ComparadorIdiomes comparador = new ComparadorIdiomes(true);

        for (Idioma altre : diccionaris) {
            if (!altre.getNom().equals(model.getIdiomaOrigen())) {
                ResultatComparacio resultat = comparador.comparar(origen, altre);
                model.addResultatsMultiples(resultat);
                controlador.notificar(Notificacio.PINTAR_GRAF);
            }
        }
    }
}
