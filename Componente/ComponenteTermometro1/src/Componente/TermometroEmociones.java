/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;
import javax.swing.*;

public class TermometroEmociones extends JPanel {

    private static final String[] EMOCIONES = {
        "Triste", "Melancólico", "Serio", "Calmado", "Neutral",
        "Feliz", "Emocionado", "Eufórico", "Sorprendido", "Enamorado"
    };

    private static final Color[] COLORES = {
        new Color(30, 144, 255),  // Triste
        new Color(112, 128, 144), // Melancólico
        new Color(47, 79, 79),    // Serio
        new Color(32, 178, 170),  // Calmado
        new Color(211, 211, 211), // Neutral
        new Color(255, 255, 102), // Feliz
        new Color(255, 165, 0),   // Emocionado
        new Color(255, 69, 0),    // Eufórico
        new Color(147, 112, 219), // Sorprendido
        new Color(255, 105, 180)  // Enamorado
    };

    private JSlider slider;
    private JLabel etiquetaValor;
    private JLabel etiquetaTitulo;
    private JLabel emojiLabel;

    private String titulo = "Estado Emocional";
    private boolean mostrarEmoji = true;
    private boolean mostrarNombres = true;

    public TermometroEmociones() {
        setLayout(null);
        setPreferredSize(new Dimension(300, 360));
        setBackground(new Color(255, 228, 225));

        etiquetaTitulo = new JLabel(titulo, SwingConstants.CENTER);
        etiquetaTitulo.setBounds(10, 10, 280, 30);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(etiquetaTitulo);

        slider = new JSlider(JSlider.VERTICAL, 0, 9, 4);
        slider.setBounds(30, 50, 80, 250);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(false); 
        slider.setFocusable(true);
        add(slider);

        etiquetaValor = new JLabel(EMOCIONES[slider.getValue()], SwingConstants.CENTER);
        etiquetaValor.setBounds(140, 120, 120, 40);
        etiquetaValor.setOpaque(true);
        etiquetaValor.setFont(new Font("Arial", Font.BOLD, 16));
        add(etiquetaValor);

        emojiLabel = new JLabel("", SwingConstants.CENTER);
        emojiLabel.setBounds(150, 190, 80, 80);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        add(emojiLabel);

        slider.addChangeListener(e -> actualizar());

        actualizarEtiquetasSlider();
        actualizar();
    }

    private void actualizar() {
        int index = slider.getValue();

        Color color = COLORES[index];
        setBackground(color);
        etiquetaValor.setBackground(color);
        etiquetaValor.setForeground(getContraste(color));
        etiquetaTitulo.setForeground(getContraste(color));

        // ✅ Muestra el nombre de la emoción
        etiquetaValor.setText(EMOCIONES[index]);

        if (mostrarEmoji) {
            emojiLabel.setText(obtenerEmoji(index));
            emojiLabel.setVisible(true);
        } else {
            emojiLabel.setVisible(false);
        }
    }

    private void actualizarEtiquetasSlider() {
        Hashtable<Integer, JLabel> etiquetas = new Hashtable<>();
        for (int i = 0; i < EMOCIONES.length; i++) {
            String texto = mostrarNombres ? EMOCIONES[i] : String.valueOf(i);
            etiquetas.put(i, new JLabel(texto));
        }
        slider.setLabelTable(etiquetas);
        slider.repaint();
    }

    private String obtenerEmoji(int index) {
        switch (index) {
            case 0: return "😢";
            case 1: return "😔";
            case 2: return "😐";
            case 3: return "😌";
            case 4: return "😶";
            case 5: return "😊";
            case 6: return "😁";
            case 7: return "🤩";
            case 8: return "😲";
            case 9: return "😍";
            default: return "🙂";
        }
    }

    private Color getContraste(Color colorFondo) {
        double brillo = (0.299 * colorFondo.getRed() + 0.587 * colorFondo.getGreen() + 0.114 * colorFondo.getBlue()) / 255;
        return (brillo > 0.5) ? Color.BLACK : Color.WHITE;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        if (etiquetaTitulo != null) {
            etiquetaTitulo.setText(titulo);
            repaint();
        }
    }

    public boolean isMostrarEmoji() {
        return mostrarEmoji;
    }

    public void setMostrarEmoji(boolean mostrarEmoji) {
        this.mostrarEmoji = mostrarEmoji;
        actualizar();
    }

    public boolean isMostrarNombres() {
        return mostrarNombres;
    }

    public void setMostrarNombres(boolean mostrarNombres) {
        this.mostrarNombres = mostrarNombres;
        actualizarEtiquetasSlider();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if (etiquetaTitulo != null) {
            etiquetaTitulo.setFont(font);
        }
    }

    @Override
    public Font getFont() {
        return (etiquetaTitulo != null) ? etiquetaTitulo.getFont() : super.getFont();
    }
}
