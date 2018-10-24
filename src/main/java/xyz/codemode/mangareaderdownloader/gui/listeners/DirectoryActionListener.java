package xyz.codemode.mangareaderdownloader.gui.listeners;

import xyz.codemode.mangareaderdownloader.core.MRD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DirectoryActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String directoryPath = MRD.getWindow().getCentralPanel().getDirectoryPath();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setApproveButtonToolTipText("Select root directory for your manga");
        fileChooser.setSelectedFile(new File(directoryPath));

        fileChooser.showDialog(MRD.getWindow(), "Select");
        try {
            directoryPath = fileChooser.getSelectedFile().toString();
            MRD.getWindow().getCentralPanel().setDirectoryPath(directoryPath);
        } catch (NullPointerException ex) {}

    }
}
