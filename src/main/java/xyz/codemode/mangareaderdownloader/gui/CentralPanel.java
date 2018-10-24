package xyz.codemode.mangareaderdownloader.gui;

import xyz.codemode.mangareaderdownloader.core.Config;

import javax.swing.*;

public class CentralPanel extends JPanel {
    public CentralPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(null);

        JLabel mangaDirectoryLabel = new JLabel("Manga directory:");
        mangaDirectoryLabel.setBounds(10,7,100,25);
        add(mangaDirectoryLabel);

        JLabel urlLabel = new JLabel("URL:");
        urlLabel.setBounds(10,42,100,25);
        add(urlLabel);

        JLabel startChapterLabel = new JLabel("Start chapter:");
        startChapterLabel.setBounds(10,77,100,25);
        add(startChapterLabel);

        JLabel endChapterLabel = new JLabel("End chapter:");
        endChapterLabel.setBounds(290,77,100,25);
        add(endChapterLabel);

        directoryPath = new JTextField();
        directoryPath.setBounds(110,7,330,25);
        directoryPath.setEditable(false);
        directoryPath.setText(Config.getDirectory());
        add(directoryPath);

        url = new JTextField();
        url.setBounds(110,42,330,25);
        add(url);

        startChapterSpinner = new JSpinner(createSpinnerNumberModel(1));
        startChapterSpinner.setBounds(110,77,50,25);
        startChapterSpinner.setEnabled(false);
        add(startChapterSpinner);

        endChapterSpinner = new JSpinner(createSpinnerNumberModel(1));
        endChapterSpinner.setBounds(390,77,50,25);
        endChapterSpinner.setEnabled(false);
        add(endChapterSpinner);
    }

    private SpinnerNumberModel createSpinnerNumberModel(int max) { return new SpinnerNumberModel(1,1,max,1); }

    public void setSpinnerModels(int maxValue) {
        startChapterSpinner.setModel(createSpinnerNumberModel(maxValue));
        endChapterSpinner.setModel(createSpinnerNumberModel(maxValue));
    }

    public void setDirectoryPath(String path) { directoryPath.setText(path); }

    public void enableSpinners(boolean arg) {
        startChapterSpinner.setEnabled(arg);
        endChapterSpinner.setEnabled(arg);
    }

    public void enableUrlTextField(boolean arg) { url.setEditable(arg); }

    public String getDirectoryPath() { return directoryPath.getText(); }
    public String getUrl() { return url.getText(); }
    public int getStartChapter() { return (int)startChapterSpinner.getValue(); }
    public int getEndChapter() { return (int)endChapterSpinner.getValue(); }

    private JTextField directoryPath;
    private JTextField url;
    private JSpinner startChapterSpinner, endChapterSpinner;
}
