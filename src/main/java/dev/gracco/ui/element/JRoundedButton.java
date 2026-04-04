package dev.gracco.ui.element;

import javax.swing.JButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class JRoundedButton extends JButton {
    private final int radius;
    private final int borderThickness;
    private Color backgroundColor;
    private Color foregroundColor;
    private Color borderColor;

    public JRoundedButton(String text, int radius) {
        this(text, radius, 0, null);
    }

    public JRoundedButton(String text, int radius, Color borderColor) {
        this(text, radius, 2, borderColor);
    }

    public JRoundedButton(String text, int radius, int borderThickness, Color borderColor) {
        super(text);
        this.radius = radius;
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
        this.backgroundColor = super.getBackground();
        this.foregroundColor = super.getForeground();

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void setBackground(Color bg) {
        this.backgroundColor = bg;
        super.setBackground(bg);
        repaint();
    }

    @Override
    public void setForeground(Color fg) {
        this.foregroundColor = fg;
        super.setForeground(fg);
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(backgroundColor.darker());
        } else {
            g2.setColor(backgroundColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        if (borderColor != null && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            int offset = borderThickness / 2;
            g2.drawRoundRect(
                    offset,
                    offset,
                    getWidth() - borderThickness - 1,
                    getHeight() - borderThickness - 1,
                    radius,
                    radius
            );
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
