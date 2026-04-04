package dev.gracco.ui.screen;

import dev.gracco.Main;
import dev.gracco.ui.Theme;
import dev.gracco.ui.element.JRoundedButton;
import dev.gracco.ui.element.JRoundedPanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Theme.getWhite());

        JPanel card = new JRoundedPanel(20, 1, Theme.getSecondary());
        card.setBorder(new EmptyBorder(20, 30, 30, 30));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Theme.getHighlight());
        card.setPreferredSize(new Dimension(300, 380));

        Dimension fieldSize = new Dimension(240, 42);

        JLabel title = new JLabel(Main.getName());
        title.setFont(Theme.getFont(Theme.FontType.SEMI_BOLD, 26));
        title.setForeground(Theme.getBlack());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Log in to your account");
        subtitle.setFont(Theme.getFont(Theme.FontType.REGULAR, 16));
        subtitle.setForeground(Theme.getBlack());
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(Theme.getFont(Theme.FontType.MEDIUM, 14));
        usernameLabel.setForeground(Theme.getBlack());
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(fieldSize);
        usernameField.setPreferredSize(fieldSize);
        usernameField.setFont(Theme.getFont(Theme.FontType.REGULAR, 14));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.getSecondary(), 1),
                new EmptyBorder(10, 12, 10, 12)
        ));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Theme.getFont(Theme.FontType.MEDIUM, 14));
        passwordLabel.setForeground(Theme.getBlack());
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        passwordField.setFont(Theme.getFont(Theme.FontType.MEDIUM, 14));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.getSecondary(), 1),
                new EmptyBorder(10, 12, 10, 12)
        ));

        JButton loginButton = new JRoundedButton("Log In", 10);
        loginButton.setMaximumSize(fieldSize);
        loginButton.setPreferredSize(fieldSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Theme.getPrimary());
        loginButton.setForeground(Theme.getWhite());
        loginButton.setFocusPainted(false);
        loginButton.setFont(Theme.getFont(Theme.FontType.SEMI_BOLD, 15));
        loginButton.setBorder(new EmptyBorder(12, 20, 12, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Theme.getPrimaryHover());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(Theme.getPrimary());
            }
        });

        JLabel footer = new JLabel("Forgot password?");
        footer.setFont(Theme.getFont(Theme.FontType.LIGHT, 13));
        footer.setForeground(Theme.getBlack());
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // How to add button action listener
        // button.addActionListener(e -> {
        //    new Thread(() -> {
        //        doHeavyWork();
        //
        //        javax.swing.SwingUtilities.invokeLater(() -> {
        //            Alert.success("Done", this);
        //        });
        //    }).start();
        //});

        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));
        card.add(usernameLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(usernameField);
        card.add(Box.createVerticalStrut(18));
        card.add(passwordLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(18));
        card.add(loginButton);
        card.add(Box.createVerticalStrut(8));
        card.add(footer);

        root.add(card);
        setContentPane(root);

        setVisible(true);
    }
}
