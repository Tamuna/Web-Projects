package net.fina.scrapper.proxy;

import net.fina.data.model.LinkMetaModel;
import net.fina.scrapper.api.Scrapper;
import net.fina.data.entity.Host;
import net.fina.scrapper.exception.ConnectionFailureException;
import net.fina.data.model.HostMetaModel;
import net.fina.scrapper.impl.ScrapperSession;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ScrapperProxySession {

    public ScrapperProxySession() {
    }

    public List<LinkMetaModel> scrapHost(HostMetaModel hostMetaModel, Host hostInDb) {
        Scrapper scrapper = new ScrapperSession();

        if (hostMetaModel.getUrl().length() == 0) {
            return null;
        }
        if (hostInDb == null) {
            List<LinkMetaModel> linkMetaModels = new ArrayList<>();
            try {
                linkMetaModels = scrapper.scrapPage(hostMetaModel.getUrl(), hostMetaModel.getDataType());
            } catch (ConnectionFailureException e) {
                System.out.println(e.getMessage());
            }
            return linkMetaModels;
        }
        return null;
    }
}