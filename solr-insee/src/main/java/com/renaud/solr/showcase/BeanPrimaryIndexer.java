package com.renaud.solr.showcase;

import fr.insee.solr.connector.SolrConnector;
import fr.insee.solr.service.impl.AbstractRepository;


public class BeanPrimaryIndexer extends AbstractRepository<BeanPrimary>{
	
	public BeanPrimaryIndexer(SolrConnector connector) {
		super(connector);
	}

	@Override
	public String getSolrUrl() {
		return "http://localhost:8983/solr/";
	}

	@Override
	public String getSolrCore() {
		return "collection1";
	}

}
