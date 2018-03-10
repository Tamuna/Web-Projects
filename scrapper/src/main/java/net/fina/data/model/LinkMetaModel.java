package net.fina.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkMetaModel {
    private String link;
    private String type;
    private String host;

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }
}
