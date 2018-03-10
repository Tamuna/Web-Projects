package net.fina.data.api;

import net.fina.data.entity.Host;
import net.fina.data.entity.Link;
import net.fina.data.model.LinkMetaModel;

import java.util.List;

public interface DataAccessLocal {
    void saveData(Host hs);

    void removeHostHistory(Host hs);

    Host getHost(String host);

    List<Link> getHostDataById(Host host, int maxResult, int firstResult);

    List<Link> filter(LinkMetaModel toSearch, int maxResult, int firstResult);

    long countLinks(Host host);

    long countFilteredLinks(LinkMetaModel toSearch);
}
