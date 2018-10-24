package xyz.codemode.mangareaderdownloader.gui;

import xyz.codemode.mangareaderdownloader.gui.listeners.DirectoryActionListener;
import xyz.codemode.mangareaderdownloader.gui.listeners.DownloadActionListener;
import xyz.codemode.mangareaderdownloader.gui.listeners.UrlApplyActionListener;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(150,200));

        directory = new JButton("Choose directory");
        directory.setPreferredSize(new Dimension(130,30));
        //directory.setFont(new Font("LetItBeDefault", Font.PLAIN, 14));
        directory.addActionListener(new DirectoryActionListener());
        add(directory);

        urlApply = new JButton("Apply URL");
        urlApply.setPreferredSize(new Dimension(130,30));
        //urlApply.setFont(new Font("LetItBeDefault", Font.PLAIN, 14));
        urlApply.addActionListener(new UrlApplyActionListener());
        add(urlApply);

        download = new JButton("DOWNLOAD");
        download.setPreferredSize(new Dimension(130,30));
        //download.setFont(new Font("LetItBeDefault", Font.PLAIN, 14));
        download.addActionListener(new DownloadActionListener());
        download.setEnabled(false);
        add(download);
    }

    public void enableDirectory(boolean arg) { directory.setEnabled(arg); }
    public void enableUrlApply(boolean arg) { urlApply.setEnabled(arg); }
    public void enableDownload(boolean arg) { download.setEnabled(arg); }

    private JButton directory, urlApply, download;

}
