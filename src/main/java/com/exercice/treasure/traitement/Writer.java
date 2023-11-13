package com.exercice.treasure.traitement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Montagne;
import com.exercice.treasure.model.Tresor;

@Component
public class Writer {

	/**
	 * Utilisation d'un bufferWriter pour ecrire les donnees de sortie
	 * 
	 * @param carte la carte en fin de jeu
	 * @throws IOException
	 */
	public void writeOutput(Carte carte) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./src/test/resources/output/resultats.txt"));
		writer.write(this.writeCarte(carte));
		writer.close();
	}

	/**
	 * Mise en forme des resultats de la carte pour ecriture
	 * 
	 * @param carte la carte en fin de jeu
	 * @return le texte à ecrire en sortie
	 */
	public String writeCarte(Carte carte) {
		StringBuilder carteStr = new StringBuilder("C - ");
		carteStr.append(carte.getLargeur() + " - ");
		carteStr.append(carte.getHauteur());

		// On ecrit toutes les montagnes
		carte.getMontagnes().stream().forEach(montagne -> {
			carteStr.append("\n");
			this.writeMontagne(carteStr, montagne);
		});

		// On ecrit tous les tresors
		carte.getTresors().stream().forEach(tresor -> {
			carteStr.append("\n");
			this.writeTresor(carteStr, tresor);
		});

		// On ecrit tous les aventuriers
		carte.getAventuriers().stream().forEach(aventurier -> {
			carteStr.append("\n");
			this.writeAventurier(carteStr, aventurier);
		});

		return carteStr.toString();
	}

	/**
	 * Mise en forme de la ligne montagne
	 * 
	 * @param str les informations a ecrire apres le jeu
	 * @param montagne les donnees de la montagne
	 * @return les informations a ecrire apres le jeu
	 */
	public StringBuilder writeMontagne(StringBuilder str, Montagne montagne) {
		str.append("M - ");
		str.append(montagne.getValeurHorizontale() + " - ");
		str.append(montagne.getValeurVerticale());
		return str;
	}

	/**
	 * Mise en forme de la ligne tresor
	 * 
	 * @param str les informations a ecrire apres le jeu
	 * @param montagne les donnees du tresor
	 * @return les informations a ecrire apres le jeu
	 */
	public StringBuilder writeTresor(StringBuilder str, Tresor tresor) {
		str.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append("\n")
				.append("T - ");
		str.append(tresor.getValeurHorizontale() + " - ");
		str.append(tresor.getValeurVerticale() + " - ");
		str.append(tresor.getNombreTresor());
		return str;
	}

	/**
	 * Mise en forme de la ligne aventurier
	 * 
	 * @param str les informations a ecrire apres le jeu
	 * @param montagne les donnees de l'aventurier
	 * @return les informations a ecrire apres le jeu
	 */
	public StringBuilder writeAventurier(StringBuilder str, Aventurier aventurier) {
		str.append(
				"# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}")
				.append("\n").append("A - ");
		str.append(aventurier.getNom() + " - ");
		str.append(aventurier.getValeurHorizontale() + " - ");
		str.append(aventurier.getValeurVerticale() + " - ");
		str.append(aventurier.getOrientation().toString() + " - ");
		str.append(aventurier.getNbTresors());
		return str;
	}
}
