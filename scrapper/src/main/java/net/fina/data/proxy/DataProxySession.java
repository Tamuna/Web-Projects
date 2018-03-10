package net.fina.data.proxy;

import net.fina.data.api.DataAccessLocal;
import net.fina.data.entity.Link;
import net.fina.data.model.LinkMetaModel;
import net.fina.data.model.LinkModelHelper;
import net.fina.data.entity.Host;
import net.fina.data.model.HostMetaModel;
import net.fina.util.PaginatedListWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DataProxySession {

    public DataProxySession() {
    }

    @Inject
    private DataAccessLocal dataAccess;

    public void saveData(HostMetaModel hostMetaModel, List<LinkMetaModel> linkMetaModels) {
        List<Link> scrappedLinks = LinkModelHelper.produceLinkEntities(linkMetaModels);
        Host host = LinkModelHelper.produceHostEntity(hostMetaModel);
        host.setScrappedLinkEntities(scrappedLinks);
        dataAccess.saveData(host);
    }

    public Host getHost(HostMetaModel site) {
        return dataAccess.getHost(site.getUrl());
    }

    public PaginatedListWrapper<LinkMetaModel> loadData(int page, int start, int limit, HostMetaModel hostMetaModel) {
        List<LinkMetaModel> data = retrieveDataFromDatabase(hostMetaModel, start, limit);
        PaginatedListWrapper<LinkMetaModel> result = new PaginatedListWrapper<>();
        result.setCurrentPage(page);
        Host host = dataAccess.getHost(hostMetaModel.getUrl());
        result.setTotalResults(dataAccess.countLinks(host));
        result.setList(data);
        result.setPageSize(data.size());
        return result;
    }

    public void removeData(HostMetaModel hostMetaModel) {
        Host hostEntity = LinkModelHelper.produceHostEntity(hostMetaModel);
        dataAccess.removeHostHistory(hostEntity);
    }

    private List<LinkMetaModel> retrieveDataFromDatabase(HostMetaModel site, int start, int limit) {
        Host hostSite = dataAccess.getHost(site.getUrl());
        List<Link> dataFromDb = dataAccess.getHostDataById(hostSite, limit, start);
        return LinkModelHelper.produceLinkModels(dataFromDb);
    }

    public PaginatedListWrapper<LinkMetaModel> getFilteredData(int page, int start, int limit, LinkMetaModel toSearch) {
        PaginatedListWrapper<LinkMetaModel> result = new PaginatedListWrapper<>();
        List<LinkMetaModel> fromDb = searchScrappedLinks(toSearch, start, limit);
        result.setPageSize(fromDb.size());
        result.setCurrentPage(page);
        result.setList(fromDb);
        long count = dataAccess.countFilteredLinks(toSearch);
        result.setTotalResults(count);
        return result;
    }

    private List<LinkMetaModel> searchScrappedLinks(LinkMetaModel linkMetaModel, int start, int limit) {
        List<Link> fromDb = dataAccess.filter(linkMetaModel, limit, start);
        return LinkModelHelper.produceLinkModels(fromDb);
    }
}
