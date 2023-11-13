package com.exercice.treasure.traitement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Montagne;
import com.exercice.treasure.model.OrientationEnum;
import com.exercice.treasure.model.Tresor;

@Component
public class Reader {

	/**
	 * Methode de lecture d'un fichier en entrée pour créer 
	 * un objet Carte qui recense toutes les données
	 * 
	 * @param pathfile le chemin du fichier d'entrée
	 * @return l'objet Carte contenant toutes lees informations du fichier d'entrée
	 */
	public Carte readFile(String pathfile) {

		// Creation de la carte
		Carte carte = new Carte();

		// Lecture des lignes du fichier en entrée
		File file = new File(pathfile);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			String brLine;
			while ((brLine = br.readLine()) != null) {
				lines.add(brLine);
			}

			// Une fois que les lignes sont lues, on va créer les éléments associés
			// en fonction du premier caractère de chaque ligne
			lines.stream().forEach(line -> {
				String categorie = line.substring(0, 1);

				switch (categorie) {

				case "C":
					String[] attributs = line.split(" - ");
					carte.setLargeur(Integer.parseInt(attributs[1]));
					carte.setHauteur(Integer.parseInt(attributs[2]));
					break;

				case "M":
					if (carte.getMontagnes() == null) {
						carte.setMontagnes(new ArrayList<>());
					}
					carte.getMontagnes().add(readMontagne(line));
					break;

				case "T":
					if (carte.getTresors() == null) {
						carte.setTresors(new ArrayList<>());
					}
					carte.getTresors().add(readTresor(line));
					break;

				case "A":
					if (carte.getAventuriers() == null) {
						carte.setAventuriers(new ArrayList<>());
					}
					carte.getAventuriers().add(readAventurier(line));
					break;

				case "#":
					// C'est une ligne de commentaire qu'on ignore
					break;
					
				default:
					System.out.println("Le fichier d'entree comporte une ligne non reconnue.");
					break;
				}
			});

		} catch (Exception e) {
			// Auto-generated catch block
			// TODO : mettre en place une gestion d'erreur
			e.printStackTrace();
		}

		// On retourne l'élément carte complété
		return carte;
	}



	/**
	 * Methode de lecture d'une ligne commençant par M pour la traduire en objet Montagne
	 * 
	 * @param line la ligne lue par le bufferReader
	 * @return l'objet Montagne associé
	 */
	public Montagne readMontagne(String line) {
		String[] attributs = line.split(" - ");
		int valeurHorizontale = Integer.parseInt(attributs[1]);
		int valeurVerticale = Integer.parseInt(attributs[2]);
		
		return new Montagne(valeurHorizontale, valeurVerticale);
	}

	/**
	 * Methode de lecture d'une ligne commençant par T pour la traduire en objet Tresor
	 * 
	 * @param line la ligne lue par le bufferReader
	 * @return l'objet Tresor associé
	 */
	public Tresor readTresor(String line) {
		String[] attributs = line.split(" - ");
		int valeurHorizontale = Integer.parseInt(attributs[1]);
		int valeurVerticale = Integer.parseInt(attributs[2]);
		int nbTresor = Integer.parseInt(attributs[3]);
		
		return new Tresor(valeurHorizontale, valeurVerticale, nbTresor);
	}

	/**
	 * Methode de lecture d'une ligne commençant par A pour la traduire en objet Aventurier
	 * 
	 * @param line la ligne lue par le bufferReader
	 * @return l'objet Aventurier associé
	 */
	public Aventurier readAventurier(String line) {
		String[] attributs = line.split(" - ");
		String nomAventurier = attributs[1];
		int valeurHorizontale = Integer.parseInt(attributs[2]);
		int valeurVerticale = Integer.parseInt(attributs[3]);
		OrientationEnum orientation = OrientationEnum.valueOf(attributs[4]);
		String sequence = attributs[5];
		
		return new Aventurier(nomAventurier, valeurHorizontale, valeurVerticale, orientation, sequence);
	}
}
