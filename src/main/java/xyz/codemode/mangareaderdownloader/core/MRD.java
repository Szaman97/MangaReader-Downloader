package xyz.codemode.mangareaderdownloader.core;

import xyz.codemode.mangareaderdownloader.gui.MainWindow;

import javax.swing.*;

/**
 * Entry point for the application
 */
public class MRD
{
    public static void main( String[] args ) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> window = new MainWindow());
    }

    public static Manager getManager() { return manager; }
    public static MainWindow getWindow() { return window; }

    private static Manager manager = new Manager();
    private static MainWindow window;
}
