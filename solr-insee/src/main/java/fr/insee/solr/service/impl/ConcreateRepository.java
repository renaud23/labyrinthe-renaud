package fr.insee.solr.service.impl;


import java.util.List;
import java.util.Map;

import fr.insee.solr.service.SolrCreateService;
import fr.insee.solr.service.SolrReadService;
import fr.insee.solr.service.SolrRepository;


public class ConcreateRepository<U> implements SolrRepository<U> {
	
	public ConcreateRepository() {
	
	}

	private SolrCreateService<U> indexer = new SolrIndexer<>();
	
	private SolrReadService<U> reader = new SolrQuery<>();

	@Override
	public void index(U o) throws SolrInseeException {
		this.indexer.index(o);
	}

	@Override
	public Map<String, Object> getFields() {
		return this.indexer.getFields();
	}

	@Override
	public List<U> findAll() throws SolrInseeException{
		return this.reader.findAll();
	}


}
