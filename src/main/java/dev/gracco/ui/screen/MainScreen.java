package dev.gracco.ui.screen;

import dev.gracco.Main;
import dev.gracco.ui.Theme;
import dev.gracco.ui.panels.FirstPanel;
import dev.gracco.ui.panels.SecondPanel;
import dev.gracco.ui.panels.ThirdPanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {
    private static final int EXPANDED_SIDEBAR_WIDTH = 320;
    private static final int COLLAPSED_SIDEBAR_WIDTH = 100;
    private static final int MIN_WINDOW_WIDTH = 1280;
    private static final int MIN_WINDOW_HEIGHT = 720;

    private final JPanel sidebar;
    private final JPanel contentPanel;
    private final JLabel toggleButton;
    private final JLabel titleLabel;
    private final CardLayout cardLayout;

    private final JButton oneButton;
    private final JButton twoButton;
    private final JButton threeButton;

    private boolean sidebarExpanded = true;
    private String selectedPanel = "One";

    private static final int SIDEBAR_ANIMATION_DURATION = 220;
    private static final int SIDEBAR_ANIMATION_STEP_DELAY = 10;

    private boolean sidebarAnimating = false;

    public MainScreen() {
        setTitle(Main.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Theme.WHITE);

        sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(Theme.BACKGROUND_GREEN);
        sidebar.setPreferredSize(new Dimension(EXPANDED_SIDEBAR_WIDTH, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel sidebarTop = new JPanel(new BorderLayout());
        sidebarTop.setBackground(Theme.BACKGROUND_GREEN);
        sidebarTop.setBorder(BorderFactory.createEmptyBorder(12, 12, 16, 12));

        titleLabel = new JLabel(Main.getName());
        titleLabel.setForeground(Theme.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setFont(Theme.getFont(Theme.FontType.SEMI_BOLD, 24));

        toggleButton = new JLabel(Theme.getBurger());
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.setHorizontalAlignment(SwingConstants.CENTER);
        toggleButton.setVerticalAlignment(SwingConstants.CENTER);
        toggleButton.setPreferredSize(new Dimension(48, 48));

        toggleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSidebar();
            }
        });

        sidebarTop.add(titleLabel, BorderLayout.WEST);
        sidebarTop.add(toggleButton, BorderLayout.EAST);

        JPanel sidebarCenter = new JPanel();
        sidebarCenter.setBackground(Theme.BACKGROUND_GREEN);
        sidebarCenter.setLayout(new BoxLayout(sidebarCenter, BoxLayout.Y_AXIS));
        sidebarCenter.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

        oneButton = createSidebarButton("One");
        twoButton = createSidebarButton("Two");
        threeButton = createSidebarButton("Three");

        oneButton.addActionListener(e -> showPanel("One"));
        twoButton.addActionListener(e -> showPanel("Two"));
        threeButton.addActionListener(e -> showPanel("Three"));

        sidebarCenter.add(oneButton);
        sidebarCenter.add(Box.createVerticalStrut(10));
        sidebarCenter.add(twoButton);
        sidebarCenter.add(Box.createVerticalStrut(10));
        sidebarCenter.add(threeButton);

        sidebar.add(sidebarTop, BorderLayout.NORTH);
        sidebar.add(sidebarCenter, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Theme.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contentPanel.add(new FirstPanel(), "One");
        contentPanel.add(new SecondPanel(), "Two");
        contentPanel.add(new ThirdPanel(), "Three");

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        showPanel("One");

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        button.setPreferredSize(new Dimension(0, 48));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(12);

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setForeground(Theme.BLACK);
        button.setBackground(Theme.WHITE);
        button.setFont(Theme.getFont(Theme.FontType.MEDIUM, 14));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, Theme.SECONDARY),
                BorderFactory.createEmptyBorder(0, 14, 0, 14)
        ));

        button.setBorderPainted(true);

        addHoverColor(button);

        return button;
    }

    private void showPanel(String panelName) {
        selectedPanel = panelName;
        cardLayout.show(contentPanel, panelName);
        updateSidebarSelection();
    }

    private void updateSidebarSelection() {
        styleSidebarButton(oneButton, selectedPanel.equals("One"), "One", Theme.getBurger());
        styleSidebarButton(twoButton, selectedPanel.equals("Two"), "Two", Theme.getBurger());
        styleSidebarButton(threeButton, selectedPanel.equals("Three"), "Three", Theme.getBurger());
    }

    private void styleSidebarButton(JButton button, boolean selected, String expandedText, Icon collapsedIcon) {
        if (sidebarExpanded) {
            button.setText(expandedText);
            button.setIcon(null);
            button.setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            button.setText("");
            button.setIcon(collapsedIcon);
            button.setHorizontalAlignment(SwingConstants.CENTER);
        }

        button.putClientProperty("selected", selected);

        if (selected) {
            button.setBackground(Theme.SECONDARY);
            button.setForeground(Theme.WHITE);
        } else {
            button.setBackground(Theme.WHITE);
            button.setForeground(Theme.BLACK);
        }
    }

    private void toggleSidebar() {
        if (sidebarAnimating) return;

        sidebarAnimating = true;
        toggleButton.setEnabled(false);

        int startWidth = sidebar.getPreferredSize().width;
        int targetWidth = sidebarExpanded ? COLLAPSED_SIDEBAR_WIDTH : EXPANDED_SIDEBAR_WIDTH;

        if (!sidebarExpanded) {
            titleLabel.setText(Main.getName());
        }

        int distance = targetWidth - startWidth;
        int steps = Math.max(1, SIDEBAR_ANIMATION_DURATION / SIDEBAR_ANIMATION_STEP_DELAY);

        Timer timer = new Timer(SIDEBAR_ANIMATION_STEP_DELAY, null);
        final int[] currentStep = {0};

        timer.addActionListener(e -> {
            currentStep[0]++;
            float progress = Math.min((float) currentStep[0] / steps, 1f);

            int newWidth = startWidth + Math.round(distance * progress);

            sidebar.setPreferredSize(new Dimension(newWidth, 0));
            sidebar.revalidate();
            revalidate();
            repaint();

            if (progress >= 1f) {
                timer.stop();

                sidebarExpanded = !sidebarExpanded;

                if (!sidebarExpanded) titleLabel.setText("");

                updateSidebarSelection();
                sidebarAnimating = false;
                toggleButton.setEnabled(true);
            }
        });

        timer.setRepeats(true);
        timer.start();
    }

    private void addHoverColor(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boolean selected = Boolean.TRUE.equals(button.getClientProperty("selected"));

                if (!selected) {
                    button.setBackground(Theme.HIGHLIGHT);
                    button.setForeground(Theme.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boolean selected = Boolean.TRUE.equals(button.getClientProperty("selected"));

                if (!selected) {
                    button.setBackground(Theme.WHITE);
                    button.setForeground(Theme.BLACK);
                }
            }
        });
    }
}