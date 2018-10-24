package xyz.codemode.mangareaderdownloader.gui.listeners;

import xyz.codemode.mangareaderdownloader.core.Config;
import xyz.codemode.mangareaderdownloader.core.MRD;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;

public class MainWindowAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent we) {
        int x = MRD.getWindow().getX();
        int y = MRD.getWindow().getY();
        String directory = MRD.getWindow().getCentralPanel().getDirectoryPath();

        ExecutorService exec = MRD.getManager().getExecutorService();
        exec.execute(() -> {
            int configX = Config.getWindowX();
            int configY = Config.getWindowY();
            String configDirectory = Config.getDirectory();

            boolean condition = (configX!=x) || (configY!=y) || (!configDirectory.equals(directory));

            if(condition) {
                Config.setWindowX(x);
                Config.setWindowY(y);
                Config.setDirectory(directory);
                Config.saveConfig();
            }
        });

        MRD.getWindow().dispose();
        exec.shutdown();
    }
}
