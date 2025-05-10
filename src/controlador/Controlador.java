package controlador;

import model.Model;
import vista.Vista;

import model.ProcessComparacio;
import model.ProcessComparacioTots;

/**
 * Controlador principal del patró MVC.
 * Gestiona la comunicació entre Vista i Model mitjançant notificacions.
 * Ara fa servir ComparadorIdiomes per calcular les distàncies.
 *
 * @author tonitorres
 */
public class Controlador implements Notificar {

    private Model model;
    private Vista vista;

    public static void main(String[] args) {
        new Controlador().inicialitzar();
    }

    private void inicialitzar() {
        model = new Model();
        vista = new Vista(this);
    }

    private void comparaDos() {
        model.resetResultatsMultiples();
        vista.notificar(Notificacio.PINTAR_GRAF);
        ProcessComparacio process = new ProcessComparacio(this);

        process.start();
    }

    private void comparaTots() {
        model.resetResultatsMultiples();
        vista.notificar(Notificacio.PINTAR_GRAF);
       ProcessComparacioTots process = new ProcessComparacioTots(this);

       process.start();
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void notificar(Notificacio notificacio) {
        switch (notificacio) {
            case COMPARAR_DOS -> comparaDos();
            case COMPARAR_TOTS -> comparaTots();
            case PINTAR_GRAF -> vista.notificar(Notificacio.PINTAR_GRAF);
        }
    }
}
