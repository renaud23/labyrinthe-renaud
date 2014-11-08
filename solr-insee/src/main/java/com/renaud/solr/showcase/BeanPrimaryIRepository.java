package com.renaud.solr.showcase;


import fr.insee.solr.connector.SolrJConnector;


public class BeanPrimaryIRepository extends SolrJConnector<BeanPrimary>{
	

	@Override
	public String getSolrUrl() {
		return "http://localhost:8983/solr";
	}

	@Override
	public String getSolrCore() {
		return "collection1";
	}

	



}
