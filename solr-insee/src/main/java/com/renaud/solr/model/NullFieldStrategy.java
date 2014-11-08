package com.renaud.solr.model;

import java.lang.reflect.Field;

import com.renaud.solr.service.impl.SolrInseeException;

public class NullFieldStrategy implements FieldStrategy{

	@Override
	public Object getValue(Field f, SolrField sf, Object o) throws SolrInseeException {
		// TODO Auto-generated method stub
		return null;
	}

}
