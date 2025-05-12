package model;

import controlador.Controlador;
import controlador.Notificacio;
import java.util.List;

public class ProcessComparacio extends Thread {

    private final Controlador controlador;

    public ProcessComparacio(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        Model model = controlador.getModel();

        List<Idioma> diccionaris = model.carregarDiccionaris(List.of(model.getIdiomaOrigen(), model.getIdiomaDesti()));

        Idioma origen = diccionaris.get(0);
        Idioma desti = diccionaris.size() > 1 ? diccionaris.get(1) : origen;

        if (origen != null && desti != null) {
            ComparadorIdiomes comparador = new ComparadorIdiomes(true);
            ResultatComparacio resultat = comparador.comparar(origen, desti);
            model.setResultats(List.of(resultat));
            controlador.notificar(Notificacio.PINTAR_GRAF);
        } else {
            System.out.println("Error: no s'han pogut carregar els idiomes.");
        }
    }
}
