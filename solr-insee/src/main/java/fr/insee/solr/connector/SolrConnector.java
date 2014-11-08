package fr.insee.solr.connector;

import java.util.Map;

import fr.insee.solr.service.SolrRepository;

public interface SolrConnector extends SolrRepository<Map<String, Object>>{
	
	
	public void setRepository(SolrRepository<?> repository);
	
}
