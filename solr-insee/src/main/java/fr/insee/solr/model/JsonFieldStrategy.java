package fr.insee.solr.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.map.ObjectMapper;

import fr.insee.solr.service.impl.SolrInseeException;

public class JsonFieldStrategy implements FieldStrategy{

	@Override
	public Object getValue(Field f, SolrField sf, Object o) throws SolrInseeException {
		String beanName = (sf.beanName() != null && !sf.beanName().isEmpty()) ? sf.beanName() : f.getName(); 
		try {
			Object value = PropertyUtils.getProperty(o, beanName);
			
			ObjectMapper mapper = new ObjectMapper();
			// TODO mapper conf
		
			
			return mapper.writeValueAsString(value);
		} catch (IllegalAccessException | InvocationTargetException	| NoSuchMethodException | IOException e) {
			throw new SolrInseeException("Impossible d'obtenir la valeur du field : "+sf.fieldName(),e);
		}
		
		
		
		
	}

}
