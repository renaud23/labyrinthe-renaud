package com.renaud.solr.showcase;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BeanSecondary {
	private String libelle;
	
	public BeanSecondary() {
	}

	public BeanSecondary(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
