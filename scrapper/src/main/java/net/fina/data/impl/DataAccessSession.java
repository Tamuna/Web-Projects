package net.fina.data.impl;

import net.fina.data.api.DataAccessLocal;
import net.fina.data.entity.Host;
import net.fina.data.entity.Link;
import net.fina.data.model.LinkMetaModel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Local(DataAccessLocal.class)
@Stateless
public class DataAccessSession implements DataAccessLocal {

    @Inject
    private EntityManager em;

    @Override
    public void saveData(Host hostSite) {
        em.persist(hostSite);
        addLinks(hostSite.getScrappedLinkEntities(), hostSite);
    }

    @Override
    public void removeHostHistory(Host hs) {
        Host hostToClean = getHost(hs.getUrl());
        if (hostToClean != null) {
            em.remove(hostToClean);
        }
    }

    @Override
    public Host getHost(String host) {
        TypedQuery query = em.createQuery("select h from Host h where h.url = :host", Host.class);
        query.setParameter("host", host);
        List<Host> hosts = query.getResultList();
        if (hosts.size() > 0) {
            return hosts.get(0);
        }
        return null;
    }

    @Override
    public List<Link> getHostDataById(Host host, int maxResult, int firstResult) {
        host = getHost(host.getUrl());
        TypedQuery query = em.createQuery("select l from Link l where l.host = :host", Link.class);
        query.setParameter("host", host);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<Link> links = query.getResultList();
        return links;
    }

    @Override
    public List<Link> filter(LinkMetaModel toSearch, int maxResult, int firstResult) {
        Host host = getHost(toSearch.getHost());
        TypedQuery query = em.createQuery("select l from Link l where l.link like :toSearch and l.type like :searchType and l.host = :host", Link.class);
        query.setParameter("toSearch", "%" + toSearch.getLink() + "%");
        if (toSearch.getType().equals("Image and Link Sources")) {
            query.setParameter("searchType", "%%");
        } else {
            query.setParameter("searchType", toSearch.getType());
        }
        query.setParameter("host", host);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<Link> result = query.getResultList();
        return result;
    }

    @Override
    public long countLinks(Host host) {
        host = getHost(host.getUrl());
        TypedQuery query = em.createQuery("select count(*) from Link l where l.host = :host", Long.class);

        query.setParameter("host", host);
        long count = (long) query.getSingleResult();
        return count;
    }

    @Override
    public long countFilteredLinks(LinkMetaModel toSearch) {
        Host host = getHost(toSearch.getHost());
        TypedQuery query = em.createQuery("select count(*) from Link l where l.link like :toSearch and l.type like :searchType and l.host = :host", Long.class);
        query.setParameter("toSearch", "%" + toSearch.getLink() + "%");
        if (toSearch.getType().equals("Image and Link Sources")) {
            query.setParameter("searchType", "%%");
        } else {
            query.setParameter("searchType", toSearch.getType());
        }
        query.setParameter("host", host);
        long count = (long) query.getSingleResult();
        return count;
    }

    private void addLinks(List<Link> links, Host host) {
        for (Link link : links) {
            link.setHost(host);
            em.persist(link);
        }
    }
}
