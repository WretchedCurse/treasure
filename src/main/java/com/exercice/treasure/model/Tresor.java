package com.exercice.treasure.model;

public class Tresor extends Element {
	
	private int nombreTresor;
	
	public Tresor(int valeurHorizontale, int valeurVerticale, int nombreTresor) {
		super(valeurHorizontale, valeurVerticale);
		this.nombreTresor=nombreTresor;
	}
	
	public int getNombreTresor() {
		return nombreTresor;
	}
	
	public void setNombreTresor(int nombreTresor) {
		this.nombreTresor = nombreTresor;
	}
}
