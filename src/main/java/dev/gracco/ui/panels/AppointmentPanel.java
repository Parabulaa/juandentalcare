package dev.gracco.ui.panels;

import dev.gracco.ui.Theme;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class AppointmentPanel extends JPanel {
    public AppointmentPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.WHITE);

        JLabel label = new JLabel("Two");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Theme.BLACK);

        add(label, BorderLayout.CENTER);
    }
}