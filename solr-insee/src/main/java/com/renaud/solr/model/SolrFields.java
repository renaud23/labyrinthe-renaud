package com.renaud.solr.model;

public @interface SolrFields {
	SolrField[] fields() default {};
}
