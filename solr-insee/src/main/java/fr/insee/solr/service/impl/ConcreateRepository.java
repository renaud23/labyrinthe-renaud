package fr.insee.solr.service.impl;


import java.util.Map;
import fr.insee.solr.service.SolrRepository;


public class ConcreateRepository<U> implements SolrRepository<U> {
	
	public ConcreateRepository() {
	
	}

	private SolrIndexer<U> indexer = new SolrIndexer<>();

	@Override
	public void index(U o) throws SolrInseeException {
		this.indexer.index(o);
	}

	@Override
	public Map<String, Object> getFields() {
		return this.indexer.getFields();
	}


}
