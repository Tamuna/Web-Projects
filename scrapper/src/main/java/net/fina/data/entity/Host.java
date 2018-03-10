package net.fina.data.entity;

import net.fina.data.entity.Link;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Host", schema = "Fina_Project")
public class Host implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "URL")
    private String url;

    @OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE)
    private List<Link> scrappedLinkEntities;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Link> getScrappedLinkEntities() {
        return scrappedLinkEntities;
    }

    public void setScrappedLinkEntities(List<Link> scrappedLinkEntities) {
        this.scrappedLinkEntities = scrappedLinkEntities;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

}
