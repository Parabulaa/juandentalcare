package dev.gracco.ui.element;

import javax.swing.JPanel;
import java.awt.Color;

public class JRoundedPanel extends JPanel {
    private final int radius;
    private final int borderThickness;
    private final java.awt.Color borderColor;

    public JRoundedPanel(int radius) {
        this(radius, 0, null);
    }

    public JRoundedPanel(int radius, Color borderColor) {
        this(radius, 2, borderColor);
    }

    public JRoundedPanel(int radius, int borderThickness, Color borderColor) {
        this.radius = radius;
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        if (borderColor != null && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new java.awt.BasicStroke(borderThickness));
            int offset = borderThickness / 2;
            g2.drawRoundRect(offset, offset,
                    getWidth() - borderThickness,
                    getHeight() - borderThickness,
                    radius, radius);
        }

        g2.dispose();
    }
}
