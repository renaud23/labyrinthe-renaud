package com.renaud.solr.showcase;

import java.util.List;

import com.renaud.solr.showcase.BeanPrimary.BeanBuilder;

import fr.insee.solr.connector.SolrJConnector;
import fr.insee.solr.service.impl.SolrInseeException;

public class MainTest {

	public static void main(String[] args) {
		BeanPrimary b = BeanBuilder.prepareBeanTest();
		
		BeanPrimaryIRepository repo = new BeanPrimaryIRepository();
		
		try {
//			repo.index(b);
			List<BeanPrimary> li = repo.findAll();
			System.out.println(li);
			
		} catch (SolrInseeException e) {
			System.out.println(e.getMessage());
		}

	}

}
