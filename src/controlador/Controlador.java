package controlador;

import model.ComparadorIdiomes;
import model.Idioma;
import model.Model;
import model.ResultatComparacio;
import vista.Vista;

import javax.swing.*;
import java.io.File;
import java.util.List;

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

    private void compararIdiomes() {
        model.compararIdiomesSeleccionats();
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void notificar(Notificacio notificacio) {
        switch (notificacio) {
            case COMPARAR_IDIOMES -> compararIdiomes();
        }
    }
}
