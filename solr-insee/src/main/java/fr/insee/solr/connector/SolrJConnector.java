package fr.insee.solr.connector;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import fr.insee.solr.service.SolrRepository;
import fr.insee.solr.service.impl.ConcreateRepository;
import fr.insee.solr.service.impl.SolrInseeException;

public abstract class SolrJConnector<U> implements SolrConnector<U>{
	
	private SolrRepository<U> repository = new ConcreateRepository<>();

	@Override
	public void index(U o) throws SolrInseeException {
		synchronized (repository) {
			this.repository.index(o);
			
			SolrInputDocument doc = new SolrInputDocument();
			Map<String,Object> map = repository.getFields();
			for(String key : map.keySet()){
				doc.addField(key, map.get(key));
			}
			
			SolrServer server = new HttpSolrServer(this.getSolrUrl()+"/"+this.getSolrCore());
			try {
				server.add(doc);
				server.commit();
			} catch (SolrServerException | IOException e) {
				throw new SolrInseeException("// TODO",e);
			}
		}
	}


	@Override
	public Map<String, Object> getFields() {
		return repository.getFields();
	}
	
	@Override
	public List<U> findAll()  throws SolrInseeException{
		SolrServer server = new HttpSolrServer(this.getSolrUrl()+"/"+this.getSolrCore()+"/"+"select");
		
		
		
		return repository.findAll();
	}
}
