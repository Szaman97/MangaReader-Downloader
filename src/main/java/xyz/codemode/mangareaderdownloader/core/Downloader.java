package xyz.codemode.mangareaderdownloader.core;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {
    public Downloader(WebClient client) { this.client = client; }

    public void download(String url) {
        this.url = url;
        ProgressMonitor pm = MRD.getManager().getProgressMonitor();

        try {
            HtmlPage page = client.getPage(url);
            getMaxPageNumber(page);
            getCurrentChapter();
            String currentDirectory = directory + "/" + mangaTitle + "/chapter " + currentChapter;
            createDirectory(currentDirectory);

            for(int i=1; i<=maxPage; i++) {
                page = client.getPage(url + "/" + i);
                HtmlImage image = page.getFirstByXPath("//img");
                String fileName = mangaTitle + " " + "ch" + currentChapter + "p" + i + ".jpg";
                File file = new File(currentDirectory + "/" + fileName);
                image.saveAs(file);

                //If user pressed Cancel button on progress monitor stop downloading
                if(pm.isCanceled())
                    break;
            }
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException in Downloader.download()!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMangaTitle(String mangaTitle) { this.mangaTitle = mangaTitle; }
    public void setDirectory(String dir) { directory = dir; }

    private int getMaxPageNumber(HtmlPage page) {
        HtmlElement chapter = page.getFirstByXPath("//div[@id='selectpage']");

        Pattern pattern = Pattern.compile(".+?(\\d+)");
        Matcher matcher = pattern.matcher(chapter.asText());
        matcher.find();

        maxPage = Integer.parseInt(matcher.group(1));
        return maxPage;
    }


    private int getCurrentChapter() {
        Pattern pattern = Pattern.compile(".+?(\\d+)");
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        currentChapter = Integer.parseInt(matcher.group(1));
        return currentChapter;
    }

    private void createDirectory(String path) {
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
    }

    private WebClient client;
    private String url;
    private String directory;
    private String mangaTitle;
    private int currentChapter;
    private int maxPage;
}
