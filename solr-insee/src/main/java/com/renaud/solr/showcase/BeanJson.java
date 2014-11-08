package com.renaud.solr.showcase;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BeanJson {
	private String libelle;
	private Long id;
	
	
	
	
	
	public BeanJson() {
	}
	
	
	public BeanJson(String libelle, Long id) {
		this.libelle = libelle;
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
}
