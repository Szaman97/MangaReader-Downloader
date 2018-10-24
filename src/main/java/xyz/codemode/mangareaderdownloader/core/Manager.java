package xyz.codemode.mangareaderdownloader.core;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    public Manager() {
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }

    /**
     *
     * @param url
     * @return false if MalformedURL or IOException or no chapters found
     */
    public boolean loadPage(String url) {
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement body = page.getBody();
            HtmlElement container = body.getOneHtmlElementByAttribute("div", "id", "container");
            HtmlElement wrapperBody = container.getOneHtmlElementByAttribute("div", "id", "wrapper_body");
            HtmlElement chapterList = wrapperBody.getOneHtmlElementByAttribute("div", "id", "chapterlist");
            HtmlElement listing = chapterList.getOneHtmlElementByAttribute("table", "id", "listing");

            Iterable iter = listing.getFirstElementChild().getHtmlElementDescendants();

            links = new LinkedList<>();
            getMangaTitle(page);

            iter.forEach(x -> {
                if(((HtmlElement)x).getTagName().equals("a")) {
                    links.add(((HtmlElement)x).getAttribute("href"));
                }
            });

            lastChapter = links.size();

            if(lastChapter == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            //MalformedURLException when URL is not correct
            //IOException when could not get page
            //other exceptions, for example ElementNotFoundException from htmlunit
            return false;
        }
    }

    public void downloadChapters(int startChapter, int endChapter) {

        //For ProgressMonitor
        final int min = startChapter-1;
        final int max = endChapter+1;

        try {
            SwingUtilities.invokeAndWait(() -> {
                pm = new ProgressMonitor(MRD.getWindow(), "Downloading " + mangaTitle, "Downloading", min, max);
                pm.setMillisToPopup(0);
                pm.setMillisToDecideToPopup(0);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for(int i=startChapter-1; i<endChapter; i++) {
            try {
                final int progress = i+1;
                final int progressAfter = i+2;

                SwingUtilities.invokeLater(() -> {
                    pm.setNote("Downloading chapter " + progress + "...");
                    pm.setProgress(progress);
                });

                Downloader downloader = new Downloader(client);
                downloader.setMangaTitle(mangaTitle);
                downloader.setDirectory(mangaDirectory);
                downloader.download("https://www.mangareader.net/" + links.get(i));

                //If user pressed Cancel button on progress monitor stop downloading
                if(pm.isCanceled())
                    break;

                SwingUtilities.invokeLater(() -> pm.setProgress(progressAfter));
            } catch (Exception e) {
                System.out.println("Chapter " + (i+1) + " could not be downloaded.");
                //TODO: create log
            }
        }
    }

    public void setMangaDirectory(String dir) {
        if(dir.isEmpty()) {
            mangaDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        } else {
            mangaDirectory = dir;
        }
    }

    public int getLastChapter() { return lastChapter; }
    public ExecutorService getExecutorService() { return exec; }
    public ProgressMonitor getProgressMonitor() { return pm; }

    private void getMangaTitle(HtmlPage mainPage) {
        HtmlElement title = mainPage.getFirstByXPath("//h2[@class='aname']");
        mangaTitle = title.asText();
    }

    private ExecutorService exec = Executors.newSingleThreadExecutor();

    private WebClient client;
    private String mangaDirectory;
    private int lastChapter;
    private String mangaTitle;
    private List<String> links;
    private ProgressMonitor pm;
}
