package com.renaud.solr.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD} )
public @interface SolrField {
	String fieldName();
	String beanName() default "";
	boolean multivalued() default false;
	Class<? extends FieldStrategy> fieldStrategy() default NullFieldStrategy.class;
}
