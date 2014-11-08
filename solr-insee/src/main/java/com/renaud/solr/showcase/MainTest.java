package com.renaud.solr.showcase;

import com.renaud.solr.service.impl.SolrInseeException;
import com.renaud.solr.showcase.BeanPrimary.BeanBuilder;

public class MainTest {

	public static void main(String[] args) {
		BeanPrimary b = BeanBuilder.prepareBeanTest();
		
		BeanPrimaryIndexer indexer = new BeanPrimaryIndexer();
		
		try {
			indexer.index(b);
		} catch (SolrInseeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
