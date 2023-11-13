package com.exercice.treasure.model;

public class Element {

	private int valeurHorizontale;
	
	private int valeurVerticale;
	
	public Element(int valeurHorizontale, int valeurVerticale) {
		this.valeurHorizontale = valeurHorizontale;
		this.valeurVerticale = valeurVerticale;
	}
	
	public int getValeurHorizontale() {
		return valeurHorizontale;
	}

	public void setValeurHorizontale(int valeurHorizontale) {
		this.valeurHorizontale = valeurHorizontale;
	}

	public int getValeurVerticale() {
		return valeurVerticale;
	}

	public void setValeurVerticale(int valeurVerticale) {
		this.valeurVerticale = valeurVerticale;
	}
}
