package xyz.codemode.mangareaderdownloader.gui;

import xyz.codemode.mangareaderdownloader.core.MRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutWindow extends JDialog {
    public AboutWindow() {
        super(MRD.getWindow());
        init();
        setVisible(true);
    }

    private void init() {
        setSize(new Dimension(350,230));
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("About");
        setResizable(false);
        setModal(true);

        JLabel title = new JLabel("MangaReader Downloader");
        title.setFont(new Font("LetItBeDefault",Font.BOLD,24));
        title.setBounds(20,10,310,30);
        add(title);

        String versionNumber = getClass().getPackage().getImplementationVersion();  //works fine with JAR
        JLabel version = new JLabel("v" + versionNumber);
        version.setFont(new Font("LetItBeDefault",Font.PLAIN,24));
        version.setBounds(140,40,100,25);
        add(version);

        JLabel author = new JLabel("Copyright © 2018 Michał Głogowski");
        JLabel github = new JLabel("https://github.com/Szaman97");
        JLabel license = new JLabel("License: MIT");
        author.setBounds(10,75,300,25);
        github.setBounds(10,100,200,25);
        license.setBounds(10,125,250,25);
        author.setFont(new Font("LetItBeDefault",Font.PLAIN,16));
        github.setFont(new Font("LetItBeDefault",Font.PLAIN,16));
        license.setFont(new Font("LetItBeDefault",Font.PLAIN,16));

        github.setCursor(new Cursor(Cursor.HAND_CURSOR));
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Szaman97"));
                } catch (URISyntaxException | IOException ex) {}
            }
        });

        add(author);
        add(github);
        add(license);

        JButton ok = new JButton("OK");
        ok.setBounds(135,160,75,25);
        ok.addActionListener(event -> dispose());
        ok.requestFocusInWindow();
        add(ok);
    }
}
