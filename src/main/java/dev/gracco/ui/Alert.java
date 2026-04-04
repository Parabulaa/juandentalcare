package dev.gracco.ui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class Alert {
    private Alert() {
    }

    public static void success(String m, JFrame currentWindow) {
        show(m, currentWindow, "Success", new Color(34, 139, 34), new Color(232, 245, 233));
    }

    public static void error(String m, JFrame currentWindow) {
        show(m, currentWindow, "Error", new Color(198, 40, 40), new Color(255, 235, 238));
    }

    public static void warning(String m, JFrame currentWindow) {
        show(m, currentWindow, "Warning", new Color(237, 108, 2), new Color(255, 243, 224));
    }

    private static void show(String m, JFrame currentWindow, String titleText, Color accent, Color background) {
        JFrame alertFrame = new JFrame();
        alertFrame.setUndecorated(true);
        alertFrame.setAlwaysOnTop(true);
        alertFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        alertFrame.setResizable(false);

        JPanel outer = new JPanel(new BorderLayout());
        outer.setBorder(BorderFactory.createLineBorder(accent, 2));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(18, 22, 18, 22));

        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(accent);

        JLabel message = new JLabel(
                "<html><div style='text-align:center; width:260px;'>" + escapeHtml(m) + "</div></html>",
                SwingConstants.CENTER
        );
        message.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        message.setFont(new Font("SansSerif", Font.PLAIN, 14));
        message.setForeground(new Color(30, 30, 30));

        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(message);

        outer.add(content, BorderLayout.CENTER);

        alertFrame.setContentPane(outer);
        alertFrame.pack();

        int x;
        int y;

        if (currentWindow != null) {
            x = currentWindow.getX() + (currentWindow.getWidth() - alertFrame.getWidth()) / 2;
            y = currentWindow.getY() + 30;
        } else {
            alertFrame.setLocationRelativeTo(null);
            x = alertFrame.getX();
            y = alertFrame.getY();
        }

        alertFrame.setLocation(x, y);
        alertFrame.setVisible(true);
        alertFrame.toFront();

        Timer timer = new Timer(2500, e -> alertFrame.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    private static String escapeHtml(String text) {
        if (text == null) return "";
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}