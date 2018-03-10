package net.fina.scrapper.impl;

import net.fina.scrapper.api.Scrapper;
import net.fina.scrapper.exception.ConnectionFailureException;
import net.fina.data.model.LinkMetaModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScrapperSession implements Scrapper {
    private Elements linkElements;
    private Elements imageElements;

    public ScrapperSession() {
    }

    public List<LinkMetaModel> scrapPage(String host, String sourceType) throws ConnectionFailureException {
        List<LinkMetaModel> linkMetaModels = new ArrayList<>();
        parsePage(host);
        switch (sourceType) {
            case "Picture Sources":
                pullImages(linkMetaModels, host);
                break;
            case "Link Sources":
                pullLinks(linkMetaModels, host);
                break;
            case "Image and Link Sources":
                System.out.println("I am here babe");
                pullImages(linkMetaModels, host);
                pullLinks(linkMetaModels, host);
                break;
        }
        return linkMetaModels;
    }


    private void pullImages(List<LinkMetaModel> images, String host) {
        for (Element image : imageElements) {
            if (image.tagName().equals("img")) {
                LinkMetaModel currentSrc = new LinkMetaModel();
                currentSrc.setLink(image.attr("abs:src"));
                currentSrc.setHost(host);
                currentSrc.setType("Picture Sources");
                images.add(currentSrc);
            }
        }
    }

    private void pullLinks(List<LinkMetaModel> linkMetaModels, String host) {
        for (Element link : linkElements) {
            LinkMetaModel currentLinkMetaModel = new LinkMetaModel();
            currentLinkMetaModel.setLink(link.attr("abs:href"));
            currentLinkMetaModel.setType("Link Sources");
            currentLinkMetaModel.setHost(host);
            if (!linkMetaModels.contains(currentLinkMetaModel)) {
                linkMetaModels.add(currentLinkMetaModel);
            }
        }
    }

    private void parsePage(String host) throws ConnectionFailureException {
        Document HTML;
        try {
            HTML = Jsoup.connect(host).get();
            linkElements = HTML.select("a[href]");
            imageElements = HTML.select("[src]");
        } catch (IOException e) {
            throw new ConnectionFailureException("cannot connect to internet!", e.getCause());
        }
    }
}
