package xyz.codemode.mangareaderdownloader.gui.listeners;

import xyz.codemode.mangareaderdownloader.core.MRD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;

public class DownloadActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        exec = MRD.getManager().getExecutorService();
        exec.execute(() -> {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    //Lock GUI
                    MRD.getWindow().getCentralPanel().enableUrlTextField(false);
                    MRD.getWindow().getCentralPanel().enableSpinners(false);
                    MRD.getWindow().getButtonPanel().enableDirectory(false);
                    MRD.getWindow().getButtonPanel().enableUrlApply(false);
                    MRD.getWindow().getButtonPanel().enableDownload(false);

                    MRD.getWindow().getStatusBar().setText("Downloading...");

                    //Get chapter values and manga directory
                    startChapter = MRD.getWindow().getCentralPanel().getStartChapter();
                    endChapter = MRD.getWindow().getCentralPanel().getEndChapter();
                    directory = MRD.getWindow().getCentralPanel().getDirectoryPath();
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                ex.printStackTrace();
            }

            MRD.getManager().setMangaDirectory(directory);
            MRD.getManager().downloadChapters(startChapter,endChapter);

            SwingUtilities.invokeLater(() -> {
                //Unlock GUI
                MRD.getWindow().getCentralPanel().enableUrlTextField(true);
                MRD.getWindow().getButtonPanel().enableDirectory(true);
                MRD.getWindow().getButtonPanel().enableUrlApply(true);

                MRD.getWindow().getStatusBar().setText("Finished downloading");
            });
        });
    }

    private ExecutorService exec;
    private int startChapter, endChapter;
    private String directory;
}
