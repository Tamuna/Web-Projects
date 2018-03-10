package net.fina.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fina.data.model.LinkMetaModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HostMetaModel {
    private String url;
    private String dataType;

    private List<LinkMetaModel> scrappedLinkMetaModels;

    public List<LinkMetaModel> getScrappedLinkMetaModels() {
        return scrappedLinkMetaModels;
    }

    public void setScrappedLinkMetaModels(List<LinkMetaModel> scrappedLinkMetaModels) {
        this.scrappedLinkMetaModels = scrappedLinkMetaModels;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUrl() {
        return url;
    }

    public String getDataType() {
        return dataType;
    }
}
