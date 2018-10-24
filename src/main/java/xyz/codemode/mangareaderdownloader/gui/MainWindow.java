package xyz.codemode.mangareaderdownloader.gui;

import xyz.codemode.mangareaderdownloader.core.Config;
import xyz.codemode.mangareaderdownloader.gui.listeners.MainWindowAdapter;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("MangaReader Downloader");
        init();
        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new MainWindowAdapter());
        setBounds(Config.getWindowX(), Config.getWindowY(), 600, 180);
        setResizable(false);
        setLayout(new BorderLayout());

        add(new MenuBar(), BorderLayout.PAGE_START);

        centralPanel = new CentralPanel();
        add(centralPanel, BorderLayout.CENTER);

        buttonPanel = new ButtonPanel();
        add(buttonPanel, BorderLayout.LINE_END);

        statusBar = new StatusBar();
        statusBar.setText("Application is ready");
        add(statusBar, BorderLayout.PAGE_END);
    }

    public CentralPanel getCentralPanel() { return centralPanel; }
    public ButtonPanel getButtonPanel() { return buttonPanel; }
    public StatusBar getStatusBar() { return statusBar; }

    private CentralPanel centralPanel;
    private ButtonPanel buttonPanel;
    private StatusBar statusBar;
}
