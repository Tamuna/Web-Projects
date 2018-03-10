package net.fina.data.model;

import net.fina.data.entity.Link;
import net.fina.data.entity.Host;

import java.util.ArrayList;
import java.util.List;

public class LinkModelHelper {

    public static Host produceHostEntity(HostMetaModel hostMetaModel) {
        Host host = new Host();
        host.setUrl(hostMetaModel.getUrl());
        return host;
    }

    public static List<Link> produceLinkEntities(List<LinkMetaModel> linkMetaModels) {
        List<Link> scrappedData = new ArrayList<>();
        for (LinkMetaModel linkMetaModel : linkMetaModels) {
            Link link = new Link();
            link.setLink(linkMetaModel.getLink());
            link.setType(linkMetaModel.getType());
            scrappedData.add(link);
        }
        return scrappedData;
    }

    public static List<LinkMetaModel> produceLinkModels(List<Link> dataFromDb) {
        List<LinkMetaModel> dataToSend = new ArrayList<>();
        for (Link ld : dataFromDb) {
            LinkMetaModel l = new LinkMetaModel();
            l.setLink(ld.getLink());
            l.setHost(ld.getHost().getUrl());
            l.setType(ld.getType());
            dataToSend.add(l);
        }
        return dataToSend;
    }
}
