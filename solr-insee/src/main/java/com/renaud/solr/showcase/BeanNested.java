package com.renaud.solr.showcase;

public class BeanNested {
	
	private String categorie;
	
	private BeanSecondary author;

	public BeanNested(String categorie, BeanSecondary nestedBean) {
		this.categorie = categorie;
		this.author = nestedBean;
	}
	
	public BeanNested() {
	}

	

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public BeanSecondary getAuthor() {
		return author;
	}

	public void setAuthor(BeanSecondary author) {
		this.author = author;
	}


	
	
	
	
}
