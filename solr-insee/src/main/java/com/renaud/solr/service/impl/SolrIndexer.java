package com.renaud.solr.service.impl;


import java.lang.reflect.Field;

import com.renaud.solr.model.SolrField;
import com.renaud.solr.model.SolrFields;
import com.renaud.solr.service.SolrCreateService;
import com.renaud.solr.utils.ClassUtil;

public abstract class SolrIndexer<U> implements SolrCreateService<U>{
	
	protected abstract String getSolrUrl();
	
	protected abstract String getSolrCore();

	public void index(U o) {
		Field[] simplefield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(), SolrField.class, false);
		Field[] arrayfield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(),  SolrFields.class, false);		
		
		for(Field f : simplefield){
			SolrField a = f.getAnnotation(SolrField.class);
			
			
		}
		
		
	}

}
