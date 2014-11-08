package fr.insee.solr.connector;

//import java.util.Map;
import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import fr.insee.solr.service.SolrRepository;
import fr.insee.solr.service.impl.SolrInseeException;

public class SolrJConnector implements SolrConnector{
	
	private SolrRepository<?> repository;

	public SolrJConnector() {
		
	}

	@Override
	public void index(Map<String, Object> o) throws SolrInseeException {
		// TODO Auto-generated method stub
		
	}
	
//	public void index(Object o) throws SolrInseeException {
//		try{
//			if(o instanceof Map<?,?>){
//				SolrInputDocument doc = new SolrInputDocument();
//				
//				@SuppressWarnings("unchecked")
//				Map<String,Object> map = (Map<String, Object>) o;
//				for(String key : map.keySet()){
//					doc.addField(key, map.get(key));
//				}
//				
//				SolrServer server = new HttpSolrServer(this.getSolrUrl()+"/"+this.getSolrCore());
//				try {
//					server.add(doc);
//					server.commit();
//				} catch (SolrServerException | IOException e) {
//					throw new SolrInseeException("// TODO",e);
//				}
//				
//				
//			}else throw new SolrInseeException("Map<String,object> nécessaire pour Solrj connector.");
//		}catch (ClassCastException e){
//			throw new SolrInseeException("Map<String,object> nécessaire pour Solrj connector.",e);
//		}
//	}

	@Override
	public String getSolrUrl() {
		return this.repository.getSolrUrl();
	}

	@Override
	public String getSolrCore() {
		return this.repository.getSolrCore();
	}




	


	@Override
	public void setRepository(SolrRepository<?> repository) {
		// TODO Auto-generated method stub
		
	}



}
