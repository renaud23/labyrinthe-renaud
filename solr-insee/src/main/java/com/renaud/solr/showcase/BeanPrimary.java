package com.renaud.solr.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.insee.solr.model.JsonFieldStrategy;
import fr.insee.solr.model.SolrField;
import fr.insee.solr.model.SolrFields;

public class BeanPrimary {
	@SolrField(fieldName="id")
	private Long id;
	@SolrField(fieldName="name", fieldStrategy=JsonFieldStrategy.class)
	private BeanJson name;
	@SolrField(fieldName="links", beanName="liste.libelle", multivalued=true)
	private List<BeanSecondary> liste = new ArrayList<>();
	@SolrField(fieldName="features", multivalued=true)
	private List<String> features = new ArrayList<>();
	@SolrFields(fields = {
		@SolrField(fieldName="categorie", beanName="nested.categorie"),
		@SolrField(fieldName="author", beanName="nested.author", fieldStrategy=JsonFieldStrategy.class)
	})
	private BeanNested nested;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BeanJson getName() {
		return name;
	}
	public void setName(BeanJson name) {
		this.name = name;
	}
	public List<BeanSecondary> getListe() {
		return liste;
	}
	public void setListe(List<BeanSecondary> liste) {
		this.liste = liste;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public BeanNested getNested() {
		return nested;
	}
	public void setNested(BeanNested nested) {
		this.nested = nested;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static class BeanBuilder{
		public static BeanPrimary prepareBeanTest(){
			BeanPrimary bt = new BeanPrimary();
			bt.setId((long) new Random().nextInt(100000));
			bt.setName(new BeanJson("mon bean en example", (long) new Random().nextInt(100000)));
			bt.getListe().add(new BeanSecondary("sous-bean 1 "));
			bt.getListe().add(new BeanSecondary("sous-bean 2 "));
			bt.getListe().add(new BeanSecondary("sous-bean 3 "));
			bt.getFeatures().add("features 1");
			bt.getFeatures().add("features 2");
			bt.getFeatures().add("features 3");
			bt.setNested(new BeanNested("une catégorie", new BeanSecondary("un bean imbriqué")));
			
			return bt;
		}
	}
}
