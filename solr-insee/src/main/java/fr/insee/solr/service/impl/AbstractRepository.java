package fr.insee.solr.service.impl;

import fr.insee.solr.connector.SolrConnector;
import fr.insee.solr.service.SolrRepository;

public abstract class AbstractRepository<U> implements SolrRepository<U> {
	
	private SolrConnector connector;
	
	
	public AbstractRepository(SolrConnector connector) {
		this.connector = connector;
		this.connector.setRepository(this);
	}

	private SolrIndexer<U> indexer = new SolrIndexer<>(connector);

	@Override
	public void index(U o) throws SolrInseeException {
		this.indexer.index(o);
	}
	

}
