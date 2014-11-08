package fr.insee.solr.service.impl;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;


import org.apache.commons.lang.StringUtils;
import fr.insee.solr.model.NullFieldStrategy;
import fr.insee.solr.model.SolrField;
import fr.insee.solr.model.SolrFields;
import fr.insee.solr.service.SolrCreateService;
import fr.insee.solr.utils.ClassUtil;

public class SolrIndexer<U> implements SolrCreateService<U>{

	
	private Map<String, Object> fieldsValue = new HashMap<String, Object>();
	

	public SolrIndexer() {
		
	}


	public void index(U o) throws SolrInseeException {
		Field[] simplefield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(), SolrField.class, false);
		Field[] arrayfield = ClassUtil.getAnnotatedDeclaredFields(o.getClass(),  SolrFields.class, false);		
		
		for(Field f : simplefield){
			Object value = null;
			
			SolrField a = f.getAnnotation(SolrField.class);
			value = this.getSolrFieldValue(f,a,o);
			
			if(value != null) fieldsValue.put(a.fieldName(), value);
		}
		
		for(Field f : arrayfield){
			SolrFields a = f.getAnnotation(SolrFields.class);
			for(SolrField sf : a.fields()){
				Object value = null;
				value = this.getSolrFieldValue(f,sf,o);
				
				if(value != null) fieldsValue.put(sf.fieldName(), value);
			}
		}
		
		System.out.println(fieldsValue);
	}

	
	private Object getSolrFieldValue(Field f,SolrField a,Object o) throws SolrInseeException{
		Object value = null;
		if(!a.fieldStrategy().equals(NullFieldStrategy.class)) value = this.getStrategyValue(f,a,o);
		else if(a.multivalued()) value = this.getMultiValue(f,a,o);
		else value = this.getSimpleValue(f,a,o);
		
		return value;
	}
	
	
	
	
	private Object getStrategyValue(Field f,SolrField a,Object o) throws SolrInseeException{
		try {
			return a.fieldStrategy().newInstance().getValue(f, a, o);
		} catch (InstantiationException | IllegalAccessException | SolrInseeException e) {
			throw new SolrInseeException("Impossible d'obtenir la valeur du field : "+a.fieldName(),e);
		}
	}
	
	private Object getSimpleValue(Field f,SolrField a,Object o) throws SolrInseeException{
		String beanName = (a.beanName() != null && !a.beanName().isEmpty()) ? a.beanName() : f.getName();
		try {
			return PropertyUtils.getProperty(o, beanName);
		} catch (IllegalAccessException | InvocationTargetException	| NoSuchMethodException | NestedNullException e) {
			throw new SolrInseeException("Impossible d'obtenir la valeur du field : "+a.fieldName(),e);
		}
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


	@Override
	public Map<String, Object> getFields() {
		return this.fieldsValue;
	}

}
