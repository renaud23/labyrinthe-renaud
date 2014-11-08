package fr.insee.solr.model;

import java.lang.reflect.Field;

import fr.insee.solr.service.impl.SolrInseeException;

public interface FieldStrategy {
	public Object getValue(Field f, SolrField sf, Object o) throws SolrInseeException;
}
