package com.exercice.treasure.model;

public class Aventurier extends Element {

	private String nom;
	
	private OrientationEnum orientation; 
	
	private String sequence;
	
	private int nbTresors;
	
	public Aventurier(String nom, int valeurHorizontale, int valeurVerticale, 
			OrientationEnum orientation, String sequence) {
		super(valeurHorizontale, valeurVerticale);
		this.nom=nom;
		this.orientation=orientation;
		this.sequence=sequence;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public OrientationEnum getOrientation() {
		return orientation;
	}

	public void setOrientation(OrientationEnum orientation) {
		this.orientation = orientation;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public int getNbTresors() {
		return nbTresors;
	}

	public void setNbTresors(int nbTresors) {
		this.nbTresors = nbTresors;
	}
	
}
