package net.fina.scrapper.api;

import net.fina.scrapper.exception.ConnectionFailureException;
import net.fina.data.model.LinkMetaModel;

import java.util.List;

public interface Scrapper {
   List<LinkMetaModel> scrapPage(String host, String sourceType) throws ConnectionFailureException;
}
