package com.renaud.solr.service;

import com.renaud.solr.service.impl.SolrInseeException;

public interface SolrCreateService<U> {
	public void index(U o) throws SolrInseeException ;

}
