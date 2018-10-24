package xyz.codemode.mangareaderdownloader.gui.listeners;

import xyz.codemode.mangareaderdownloader.core.MRD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;

public class UrlApplyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        exec = MRD.getManager().getExecutorService();

        exec.execute(() -> {

            try {
                SwingUtilities.invokeAndWait(() -> {
                    MRD.getWindow().getButtonPanel().enableUrlApply(false);
                    MRD.getWindow().getStatusBar().setText("Loading page...");
                    url = MRD.getWindow().getCentralPanel().getUrl();
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                ex.printStackTrace();
            }

            //It may take a while
            boolean loaded = MRD.getManager().loadPage(url);

            SwingUtilities.invokeLater(() -> {
                if (loaded) {
                    lastChapter = MRD.getManager().getLastChapter();
                    MRD.getWindow().getCentralPanel().setSpinnerModels(lastChapter);

                    //Enable download button and spinners
                    MRD.getWindow().getButtonPanel().enableDownload(true);
                    MRD.getWindow().getCentralPanel().enableSpinners(true);
                    MRD.getWindow().getStatusBar().setText("Successfully loaded page");
                } else {
                    MRD.getWindow().getStatusBar().setText("Incorrect URL");
                }

                MRD.getWindow().getButtonPanel().enableUrlApply(true);
            });
        });
    }

    private String url;
    private int lastChapter;
    private ExecutorService exec;
}
