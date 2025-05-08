package vista;

import controlador.Controlador;
import controlador.Notificacio;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import model.Model;

public class PanellBotons extends JPanel {

    private final JComboBox<String> comboOrigen;
    private final JComboBox<String> comboDesti;
    private final JButton botoUnaComparacio;
    private final JButton botoCompararTots;

    public PanellBotons(Controlador controlador) {
        setLayout(new BorderLayout());

        List<String> idiomesDisponibles = Arrays.asList("ale", "cat", "eus", "fra", "hol", "eng", "ita", "nor", "por", "esp", "swe");

        // ComboBox per seleccionar idioma origen
        comboOrigen = new JComboBox<>(idiomesDisponibles.toArray(new String[0]));
        comboOrigen.setBorder(BorderFactory.createTitledBorder("Idioma Origen"));

        // ComboBox per seleccionar idioma destí
        comboDesti = new JComboBox<>(idiomesDisponibles.toArray(new String[0]));
        comboDesti.setBorder(BorderFactory.createTitledBorder("Idioma Destí"));

        JPanel panellSeleccio = new JPanel(new GridLayout(2, 1));
        panellSeleccio.add(comboOrigen);
        panellSeleccio.add(comboDesti);
        add(panellSeleccio, BorderLayout.NORTH);

        // Panell de botons
        JPanel panellBotons = new JPanel(new FlowLayout());

        botoUnaComparacio = new JButton("Comparar amb un altre idioma");
        botoUnaComparacio.addActionListener(e -> {
            Model model = controlador.getModel();

            model.setIdiomes((String) comboOrigen.getSelectedItem(), (String) comboDesti.getSelectedItem());
            controlador.notificar(Notificacio.COMPARAR_DOS);
        });

        botoCompararTots = new JButton("Comparar amb tots els altres");
        botoCompararTots.addActionListener(e -> {
            Model model = controlador.getModel();

            model.setIdiomes((String) comboOrigen.getSelectedItem(), (String) comboDesti.getSelectedItem());
            controlador.notificar(Notificacio.COMPARAR_TOTS);
        });

        panellBotons.add(botoUnaComparacio);
        panellBotons.add(botoCompararTots);
        add(panellBotons, BorderLayout.SOUTH);
    }
}
