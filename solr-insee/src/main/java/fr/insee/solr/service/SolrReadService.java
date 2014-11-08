package fr.insee.solr.service;

import java.util.List;

import fr.insee.solr.service.impl.SolrInseeException;

public interface SolrReadService<U> {
	public List<U> findAll() throws SolrInseeException;
}
