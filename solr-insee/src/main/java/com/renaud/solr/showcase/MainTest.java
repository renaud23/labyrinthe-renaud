package com.renaud.solr.showcase;

import com.renaud.solr.showcase.BeanPrimary.BeanBuilder;

import fr.insee.solr.connector.SolrJConnector;
import fr.insee.solr.service.impl.SolrInseeException;

public class MainTest {

	public static void main(String[] args) {
		BeanPrimary b = BeanBuilder.prepareBeanTest();
		
		BeanPrimaryIRepository indexer = new BeanPrimaryIRepository();
		
		try {
			indexer.index(b);
		} catch (SolrInseeException e) {
			System.out.println(e.getMessage());
		}

	}

}
