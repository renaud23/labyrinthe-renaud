package com.renaud.solr.test;

import com.renaud.solr.service.impl.SolrIndexer;

public class BeanPrimaryIndexer extends SolrIndexer<BeanPrimary>{

	@Override
	public String getSolrUrl() {
		return "http://localhost:8983/solr/";
	}

	@Override
	public String getSolrCore() {
		return "collection1";
	}

}
