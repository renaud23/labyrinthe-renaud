package com.renaud.solr.test;

import com.renaud.solr.test.BeanPrimary.BeanBuilder;

public class MainTest {

	public static void main(String[] args) {
		BeanPrimary b = BeanBuilder.prepareBeanTest();
		
		BeanPrimaryIndexer indexer = new BeanPrimaryIndexer();
		
		indexer.index(b);

	}

}
