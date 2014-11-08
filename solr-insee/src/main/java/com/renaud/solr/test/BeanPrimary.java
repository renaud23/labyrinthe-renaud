package com.renaud.solr.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeanPrimary {
	private Long id;
	private String name;
	private List<BeanSecondary> liste = new ArrayList<BeanSecondary>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BeanSecondary> getListe() {
		return liste;
	}
	public void setListe(List<BeanSecondary> liste) {
		this.liste = liste;
	}
	
	
	
	
	public static class BeanBuilder{
		public static BeanPrimary prepareBeanTest(){
			BeanPrimary bt = new BeanPrimary();
			bt.setId((long) new Random().nextInt(100000));
			bt.setName("bean d'essai avec des car@ctères spéciaux !");
			bt.getListe().add(new BeanSecondary("sous-bean 1 "));
			bt.getListe().add(new BeanSecondary("sous-bean 2 "));
			bt.getListe().add(new BeanSecondary("sous-bean 2 "));
			
			return bt;
		}
	}
}
