package com.renaud.solr.service.impl;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;


import org.apache.commons.lang.StringUtils;

import com.renaud.solr.model.SolrField;
import com.renaud.solr.model.SolrFields;
import com.renaud.solr.service.SolrCreateService;
import com.renaud.solr.utils.ClassUtil;

public abstract class SolrIndexer<U> implements SolrCreateService<U>{
	
	protected abstract String getSolrUrl();
	
	protected abstract String getSolrCore();

	public void index(U o) throws SolrInseeException {
		Field[] simplefield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(), SolrField.class, false);
		Field[] arrayfield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(),  SolrFields.class, false);		
		
		
		
		for(Field f : simplefield){
			Object value = null;
			
			SolrField a = f.getAnnotation(SolrField.class);
			value = this.getSimpleValue(f,a,o);
			
			
			System.out.println(a.fieldName()+" "+value);
			
		}
		
		
	}

	
	private Object getSimpleValue(Field f,SolrField a,Object o) throws SolrInseeException{
		Object value = null;
		if(a.multivalued()) value= this.getMultiValue(f,a,o);
		else value = this.getSimpleValue();
		
		return value;
	}
	
	
	
	private Object getSimpleValue(){
		return null;
	}
	
	
	private Object getMultiValue(Field f,SolrField a,Object o) throws SolrInseeException{
		List<Object> liste = new ArrayList<>();
		
		String beanName = null;
		String listeName = f.getName();
		if(a.beanName() != null && !a.beanName().isEmpty()){
			String[] tmp = StringUtils.split(a.beanName(), '.');
			listeName = tmp[0];
			beanName = StringUtils.substring(a.beanName(), listeName.length()+1);
		}

		try {
			Object iterable = PropertyUtils.getProperty(o, listeName);
			if(iterable instanceof Iterable<?>){
				for(Object v : (Iterable<?>)iterable){
					Object n = v;
					if(beanName != null) n = PropertyUtils.getProperty(v, beanName);
					liste.add(n);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			new SolrInseeException("Impossible d'obtenir la valeur du field : "+a.fieldName(),e);
		}
		
		return liste;
	}

}
