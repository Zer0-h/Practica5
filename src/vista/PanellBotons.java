package vista;

import controlador.Controlador;
import controlador.Notificacio;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Model;

public class PanellBotons extends JPanel {

    private final JComboBox<String> comboOrigen;
    private final JComboBox<String> comboDesti;
    private final JButton botoUnaComparacio;
    private final JButton botoCompararTots;

    private final Map<String, String> mapaIdiomes; // codi -> nom complet

    public PanellBotons(Controlador controlador) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        mapaIdiomes = new LinkedHashMap<>();
        mapaIdiomes.put("ale", "Alemany");
        mapaIdiomes.put("cat", "Català");
        mapaIdiomes.put("eus", "Euskera");
        mapaIdiomes.put("fra", "Francès");
        mapaIdiomes.put("hol", "Holandès");
        mapaIdiomes.put("eng", "Anglès");
        mapaIdiomes.put("ita", "Italià");
        mapaIdiomes.put("nor", "Noruec");
        mapaIdiomes.put("por", "Portuguès");
        mapaIdiomes.put("esp", "Espanyol");
        mapaIdiomes.put("swe", "Suec");

        DefaultComboBoxModel<String> modelOrigen = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelDesti = new DefaultComboBoxModel<>();

        for (String codi : mapaIdiomes.keySet()) {
            modelOrigen.addElement(mapaIdiomes.get(codi));
            modelDesti.addElement(mapaIdiomes.get(codi));
        }

        comboOrigen = new JComboBox<>(modelOrigen);
        comboOrigen.setPreferredSize(new Dimension(150, 30));

        comboDesti = new JComboBox<>(modelDesti);
        comboDesti.setPreferredSize(new Dimension(150, 30));

        // Botons
        botoUnaComparacio = new JButton("Comparar amb un altre idioma");
        botoUnaComparacio.addActionListener(e -> {
            Model model = controlador.getModel();
            String origenCodi = obtenirCodi((String) comboOrigen.getSelectedItem());
            String destiCodi = obtenirCodi((String) comboDesti.getSelectedItem());
            model.setIdiomes(origenCodi, destiCodi);
            controlador.notificar(Notificacio.COMPARAR_DOS);
        });

        botoCompararTots = new JButton("Comparar amb tots els altres");
        botoCompararTots.addActionListener(e -> {
            Model model = controlador.getModel();
            String origenCodi = obtenirCodi((String) comboOrigen.getSelectedItem());
            String destiCodi = obtenirCodi((String) comboDesti.getSelectedItem());
            model.setIdiomes(origenCodi, destiCodi);
            controlador.notificar(Notificacio.COMPARAR_TOTS);
        });

        // Afegim tots els components en línia
        add(new JLabel("Idioma Origen:"));
        add(comboOrigen);
        add(Box.createHorizontalStrut(10));
        add(new JLabel("Idioma Destí:"));
        add(comboDesti);
        add(Box.createHorizontalStrut(10));
        add(botoUnaComparacio);
        add(botoCompararTots);
    }

    private String obtenirCodi(String nomComplet) {
        for (Map.Entry<String, String> entry : mapaIdiomes.entrySet()) {
            if (entry.getValue().equals(nomComplet)) {
                return entry.getKey();
            }
        }
        return nomComplet; // fallback per seguretat
    }
}
