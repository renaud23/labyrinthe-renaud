package com.renaud.solr.showcase;

import com.renaud.solr.showcase.BeanPrimary.BeanBuilder;

import fr.insee.solr.connector.SolrJConnector;
import fr.insee.solr.service.impl.SolrInseeException;

public class MainTest {

	public static void main(String[] args) {
		BeanPrimary b = BeanBuilder.prepareBeanTest();
		
		BeanPrimaryIndexer indexer = new BeanPrimaryIndexer(new SolrJConnector());
		
		try {
			indexer.index(b);
		} catch (SolrInseeException e) {
			System.out.println(e.getMessage());
		}

	}

}
