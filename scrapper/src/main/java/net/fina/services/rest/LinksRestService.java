package net.fina.services.rest;

import net.fina.data.proxy.DataProxySession;
import net.fina.data.entity.Host;
import net.fina.data.model.HostMetaModel;
import net.fina.data.model.LinkMetaModel;
import net.fina.scrapper.proxy.ScrapperProxySession;
import net.fina.util.PaginatedListWrapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/links")
@RequestScoped
public class LinksRestService {

    @Inject
    private DataProxySession dataProxySession;

    @Inject
    private ScrapperProxySession scrapperProxySession;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void processHostSite(HostMetaModel hostMetaModel) {
        Host hostToAdd = dataProxySession.getHost(hostMetaModel);
        List<LinkMetaModel> data = scrapperProxySession.scrapHost(hostMetaModel, hostToAdd);
        dataProxySession.saveData(hostMetaModel, data);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<LinkMetaModel> loadData(@QueryParam("page") int page,
                                                        @QueryParam("start") int start,
                                                        @QueryParam("limit") int limit,
                                                        @QueryParam("url") String url,
                                                        @QueryParam("type") String type) {
        HostMetaModel hostMetaModel = new HostMetaModel();
        hostMetaModel.setDataType(type);
        hostMetaModel.setUrl(url);
        return dataProxySession.loadData(page, start, limit, hostMetaModel);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void clearData(HostMetaModel site) {
        dataProxySession.removeData(site);
    }


    @GET
    @Path("/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<LinkMetaModel> filterData(@QueryParam("page") int page,
                                                          @QueryParam("start") int start,
                                                          @QueryParam("limit") int limit,
                                                          @QueryParam("url") String url,
                                                          @QueryParam("link") String link,
                                                          @QueryParam("type") String type) {
        LinkMetaModel toSearch = new LinkMetaModel();
        toSearch.setHost(url);
        toSearch.setLink(link);
        toSearch.setType(type);
        return dataProxySession.getFilteredData(page, start, limit, toSearch);
    }
}