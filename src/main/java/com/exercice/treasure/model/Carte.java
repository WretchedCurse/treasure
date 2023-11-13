package com.exercice.treasure.model;

import java.util.List;

public class Carte {

	private int largeur;
	
	private int hauteur;
	
	List<Montagne> montagnes;
	
	List<Tresor> tresors;
	
	List<Aventurier> aventuriers;

	public Carte() {
		// constructeur par défaut
	}
	
	public Carte(int largeur, int hauteur, List<Montagne> montagnes, List<Tresor> tresors, 
			List<Aventurier> aventuriers) {
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.montagnes=montagnes;
		this.tresors=tresors;
		this.aventuriers=aventuriers;
	}
	
	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public List<Montagne> getMontagnes() {
		return montagnes;
	}

	public void setMontagnes(List<Montagne> montagnes) {
		this.montagnes = montagnes;
	}

	public List<Tresor> getTresors() {
		return tresors;
	}

	public void setTresors(List<Tresor> tresors) {
		this.tresors = tresors;
	}

	public List<Aventurier> getAventuriers() {
		return aventuriers;
	}

	public void setAventuriers(List<Aventurier> aventuriers) {
		this.aventuriers = aventuriers;
	}
	
	
}
