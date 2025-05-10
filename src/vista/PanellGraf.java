package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import model.ResultatComparacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class PanellGraf extends JPanel {

    private List<ResultatComparacio> resultats;
    private double escala = 1.0;
    private Point arrossegantDesDe = null;
    private int offsetX = 0, offsetY = 0;
    private String idiomaOrigen;

    public PanellGraf() {
        this.resultats = new ArrayList<>();
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);

        // Zoom amb scroll
        addMouseWheelListener(e -> {
            escala *= (e.getWheelRotation() < 0) ? 1.1 : 0.9;
            escala = Math.max(0.2, Math.min(escala, 5.0));
            repaint();
        });

        // Arrossegament
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                arrossegantDesDe = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (arrossegantDesDe != null) {
                    int dx = e.getX() - arrossegantDesDe.x;
                    int dy = e.getY() - arrossegantDesDe.y;
                    offsetX += dx;
                    offsetY += dy;
                    arrossegantDesDe = e.getPoint();
                    repaint();
                }
            }
        });
    }

    public void pintarResultats(List<ResultatComparacio> resultats, String idiomaOrigen) {
        this.resultats = resultats;
        this.idiomaOrigen = idiomaOrigen;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (resultats == null || resultats.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.translate(offsetX, offsetY);
        g2.scale(escala, escala);

        Set<String> noms = new HashSet<>();
        for (ResultatComparacio r : resultats) {
            noms.add(r.getIdiomaA());
            noms.add(r.getIdiomaB());
        }

        int centreX = getWidth() / 2;
        int centreY = getHeight() / 2;
        int radi = Math.min(centreX, centreY) - 100;
        radi = Math.max(radi, 120);

        Map<String, Point> posicionsMap = new HashMap<>();

        // Centrar idioma origen
        posicionsMap.put(idiomaOrigen, new Point(centreX, centreY));

        // Posicionar la resta
        List<String> restants = noms.stream()
            .filter(n -> !n.equals(idiomaOrigen))
            .toList();

        for (int i = 0; i < restants.size(); i++) {
            double angle = 2 * Math.PI * i / restants.size();
            int x = centreX + (int) (radi * Math.cos(angle));
            int y = centreY + (int) (radi * Math.sin(angle));
            posicionsMap.put(restants.get(i), new Point(x, y));
        }

        // Dibuixar arestes
        for (ResultatComparacio r : resultats) {
            Point p1 = posicionsMap.get(r.getIdiomaA());
            Point p2 = posicionsMap.get(r.getIdiomaB());

            if (p1 != null && p2 != null) {
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);

                int mx = (p1.x + p2.x) / 2;
                int my = (p1.y + p2.y) / 2;

                String text = String.format("%.1f", r.getDistancia());
                int w = g2.getFontMetrics().stringWidth(text);
                g2.setColor(Color.BLACK);
                g2.drawString(text, mx - w / 2, my - 5); // lleugerament per sobre de la línia
            }
        }

        // Dibuixar nodes
        for (Map.Entry<String, Point> entry : posicionsMap.entrySet()) {
            Point p = entry.getValue();
            String nom = entry.getKey();
            int rNode = 25;

            if (nom.equals(idiomaOrigen)) {
                g2.setColor(new Color(0, 180, 255)); // color especial per a l'origen
            } else {
                g2.setColor(Color.LIGHT_GRAY);
            }

            g2.fillOval(p.x - rNode, p.y - rNode, 2 * rNode, 2 * rNode);
            g2.setColor(Color.BLACK);
            g2.drawOval(p.x - rNode, p.y - rNode, 2 * rNode, 2 * rNode);

            // Centrat del text dins el node
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(nom);
            int textHeight = fm.getAscent();
            g2.drawString(nom, p.x - textWidth / 2, p.y + textHeight / 2 - 2);
        }
    }
}