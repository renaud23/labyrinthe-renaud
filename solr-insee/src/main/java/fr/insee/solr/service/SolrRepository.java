package fr.insee.solr.service;

public interface SolrRepository<U> extends SolrCreateService<U>{
	public String getSolrUrl();
	
	public String getSolrCore();
}
