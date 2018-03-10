package net.fina.data.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Link", schema = "Fina_Project")
public class Link {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "LINK")
    private String link;
    @Column(name = "TYPE")
    private String type;

    @ManyToOne
    @JoinColumn(name = "HOST_ID")
    private Host host;

    public Link() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }
}
