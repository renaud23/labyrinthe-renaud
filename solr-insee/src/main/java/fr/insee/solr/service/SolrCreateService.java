package fr.insee.solr.service;

import fr.insee.solr.service.impl.SolrInseeException;

public interface SolrCreateService<U> {
	public void index(U o) throws SolrInseeException ;

}
