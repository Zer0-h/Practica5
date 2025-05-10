package vista;

import controlador.Controlador;
import controlador.Notificacio;
import controlador.Notificar;
import java.awt.BorderLayout;

import javax.swing.*;

public class Vista extends JFrame implements Notificar {

    private final Controlador controlador;
    private final PanellBotons panellBotons;
    private final PanellGraf panellGraf;

    public Vista(Controlador controlador) {
        super("Comparador Lèxic d'Idiomes");
        this.controlador = controlador;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panellBotons = new PanellBotons(controlador);
        add(panellBotons, BorderLayout.NORTH);

        panellGraf = new PanellGraf();
        add(panellGraf, BorderLayout.CENTER);

        setSize(1400, 1200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void notificar(Notificacio notificacio) {
        switch (notificacio) {
            case PINTAR_GRAF -> {
                panellGraf.pintarResultats(controlador.getModel().getResultatsMultiples(), controlador.getModel().getIdiomaOrigen());
            }
        }
    }
}
