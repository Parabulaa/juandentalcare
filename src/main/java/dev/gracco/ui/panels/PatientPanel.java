package dev.gracco.ui.panels;

import dev.gracco.ui.Theme;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class PatientPanel extends JPanel {
    public PatientPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.WHITE);

        JLabel label = new JLabel("Three");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Theme.BLACK);

        add(label, BorderLayout.CENTER);
    }
}