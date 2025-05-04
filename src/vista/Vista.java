package vista;

import controlador.Controlador;
import controlador.Notificacio;
import controlador.Notificar;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame implements Notificar {

    private final Controlador controlador;
    private final PanellBotons panellBotons;

    public Vista(Controlador controlador) {
        super("Comparador Lèxic d'Idiomes");
        this.controlador = controlador;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panellBotons = new PanellBotons(controlador);
        add(panellBotons, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void notificar(Notificacio notificacio) {
        // No hi ha cap notificació visual de moment
    }
}
