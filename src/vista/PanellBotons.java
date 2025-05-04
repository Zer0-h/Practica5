package vista;

import controlador.Controlador;
import controlador.Notificacio;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import model.Model;

public class PanellBotons extends JPanel {

    private final JList<String> llistaIdiomes;
    private final JButton botoComparar;

    public PanellBotons(Controlador controlador) {
        setLayout(new BorderLayout());

        // Els 11 idiomes definits
        List<String> idiomes = Arrays.asList("ale", "cat", "eus", "fra", "hol", "eng", "ita", "nor", "por", "esp", "swe");

        llistaIdiomes = new JList<>(idiomes.toArray(new String[0]));
        llistaIdiomes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(llistaIdiomes), BorderLayout.CENTER);

        botoComparar = new JButton("Comparar Idiomes Seleccionats");
        botoComparar.addActionListener(e -> {
            Model model = controlador.getModel();
            model.setIdiomes(llistaIdiomes.getSelectedValuesList());

            controlador.notificar(Notificacio.COMPARAR_IDIOMES);
        });
        add(botoComparar, BorderLayout.SOUTH);
    }
}
