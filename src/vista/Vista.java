package vista;

import controlador.Controlador;
import controlador.Notificacio;
import controlador.Notificar;
import java.awt.BorderLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import model.ResultatComparacio;

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

        //panellGraf = new PanellGraf(new ArrayList<>());
        List<ResultatComparacio> resultatsDos = List.of(
            new ResultatComparacio("cat", "esp", 4.23)
        );

        List<ResultatComparacio> resultatsTots = List.of(
            new ResultatComparacio("cat", "esp", 4.23),
            new ResultatComparacio("cat", "fra", 5.78),
            new ResultatComparacio("cat", "ita", 6.12),
            new ResultatComparacio("cat", "por", 7.03),
            new ResultatComparacio("cat", "eng", 8.41),
            new ResultatComparacio("cat", "ale", 8.90),
            new ResultatComparacio("cat", "eus", 9.76),
            new ResultatComparacio("cat", "nor", 7.88),
            new ResultatComparacio("cat", "hol", 8.25),
            new ResultatComparacio("cat", "swe", 9.34)
        );

        panellGraf = new PanellGraf();

        panellGraf.pintarResultats(resultatsTots, "cat");
        add(panellGraf, BorderLayout.CENTER);

        setSize(1400, 1200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void notificar(Notificacio notificacio) {
        switch (notificacio) {
            case MOSTRA_GRAF_TOTS -> {
                System.out.println("MOSTRAR GRAF TOTS");
                panellGraf.pintarResultats(controlador.getModel().getResultatsMultiples(), controlador.getModel().getIdiomaOrigen());
                panellGraf.repaint();
            }
            case MOSTRA_GRAF_DOS -> {
                System.out.println("MOSTRAR GRAF DOS");
                ResultatComparacio r = controlador.getModel().getResultat();
                panellGraf.pintarResultats(List.of(r), controlador.getModel().getIdiomaOrigen()); // Envoltat com a llista
                panellGraf.repaint();
            }
        }
    }
}
