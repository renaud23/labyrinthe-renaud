package fr.insee.solr.connector;

import fr.insee.solr.service.SolrRepository;

public interface SolrConnector<U> extends SolrRepository<U>{

	public String getSolrUrl();

	public String getSolrCore();
	
}
