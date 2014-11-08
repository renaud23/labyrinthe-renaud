package com.renaud.solr.model;

import java.lang.reflect.Field;
import com.renaud.solr.service.impl.SolrInseeException;

public interface FieldStrategy {
	public Object getValue(Field f, SolrField sf) throws SolrInseeException;
}
