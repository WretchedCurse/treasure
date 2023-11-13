package com.exercice.treasure.traitement;

import org.springframework.stereotype.Component;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Element;
import com.exercice.treasure.model.OrientationEnum;

@Component
public class Processor {

	boolean isMontagnePresente;

	boolean isAventurierPresent;

	/**
	 * 
	 * @param entree
	 * @return
	 */
	public Carte processGame(Carte entree) {

		// On commence par lire la taille d'une séquence
		// pour savoir combien de tours sont joués par aventurier
		int nombreTours = entree.getAventuriers().get(0).getSequence().length();

		// l'indicateur tourJoue representera l'index de la sequence à lire pour chaque
		// aventurier
		for (int tour = 0; tour < nombreTours; tour++) {
			Integer tourJoue = tour;
			entree.getAventuriers().stream().forEach(aventurier -> {
				String action = aventurier.getSequence().substring(tourJoue, tourJoue + 1);
				this.jouerTour(entree, aventurier, action);
			});
		}

		return entree;
	}

	/**
	 * 
	 * @param entree
	 * @param aventurier
	 * @param action
	 */
	public void jouerTour(Carte entree, Aventurier aventurier, String action) {
		// faire l'action si possible
		switch (action) {
		case "A":
			this.avancer(entree, aventurier);
			// comptabiliser un tresor
			this.collecterTresor(entree, aventurier);
			break;

		case "G", "D":
			this.tourner(aventurier, action);
			break;

		default:
			System.out.println("Le fichier d'entree comporte un caractère non reconnu dans la séquence de mouvements.");
			break;
		}
	}

	/**
	 * Methode de modification de l'orientation de l'aventurier lorsque l'action
	 * consiste à tourner à droite ou à gauche en fonction de son orientation de
	 * depart
	 * 
	 * @param aventurier l'aventurier
	 * @param sens       le sens dans lequel il tourne
	 */
	public void tourner(Aventurier aventurier, String sens) {
		OrientationEnum orientationDepart = aventurier.getOrientation();
		switch (orientationDepart) {
		case N:
			if ("D".equals(sens)) {
				aventurier.setOrientation(OrientationEnum.E);
			} else {
				aventurier.setOrientation(OrientationEnum.O);
			}
			break;

		case E:
			if ("D".equals(sens)) {
				aventurier.setOrientation(OrientationEnum.S);
			} else {
				aventurier.setOrientation(OrientationEnum.N);
			}
			break;

		case S:
			if ("D".equals(sens)) {
				aventurier.setOrientation(OrientationEnum.O);
			} else {
				aventurier.setOrientation(OrientationEnum.E);
			}
			break;

		case O:
			if ("D".equals(sens)) {
				aventurier.setOrientation(OrientationEnum.N);
			} else {
				aventurier.setOrientation(OrientationEnum.S);
			}
			break;

		default:
			// Cas impossible
			break;
		}
	}

	/**
	 * Methode permettant de déterminer si une montagne est présente sur la case où
	 * doit avancer l'aventurier
	 * 
	 * @param carte         la carte en entree
	 * @param caseTheorique la case theorique sur laquelle l'aventurier doit avancer
	 * @return true si une montagne est presente, false si aucune montagne n'est
	 *         presente sur la case theorique
	 */
	public boolean isMontagnePresente(Carte carte, Element caseTheorique) {
		// On suppose que par défaut, aucune montagne n'est presente
		this.isMontagnePresente = false;
		// On parcours la liste des montagnes de la carte pour vérifier que les
		// coordonnées
		// soient differentes de celles de la case theorique de l'aventurier
		carte.getMontagnes().stream().forEach(montagne -> {
			// Si une montagne est présente à l'endroit où doit avancer l'aventurier,
			// on renseigne isMontagnePresente à true
			if (montagne.getValeurHorizontale() == caseTheorique.getValeurHorizontale()
					&& montagne.getValeurVerticale() == caseTheorique.getValeurVerticale()) {
				this.isMontagnePresente = true;
			}
		});
		return this.isMontagnePresente;
	}

	/**
	 * Methode permettant de déterminer si un aventurier est déjà présent sur la
	 * case où doit avancer l'aventurier qui joue
	 * 
	 * @param carte         la carte en entree
	 * @param caseTheorique la case theorique sur laquelle l'aventurier doit avancer
	 * @return true si un aventurier est deja present, false si aucun aventurier
	 *         n'est present sur la case theorique
	 */
	public boolean isAventurierPresent(Carte carte, Element caseTheorique) {
		// On suppose que par défaut, aucun aventurier n'est present
		this.isAventurierPresent = false;
		// On parcours la liste des montagnes de la carte pour vérifier que les
		// coordonnées
		// soient differentes de celles de la case theorique de l'aventurier
		carte.getAventuriers().stream().forEach(aventurier -> {
			// Si un aventurier est présent à l'endroit où doit avancer l'aventurier qui
			// joue,
			// on renseigne isAventurierPresent à true
			if (aventurier.getValeurHorizontale() == caseTheorique.getValeurHorizontale()
					&& aventurier.getValeurVerticale() == caseTheorique.getValeurVerticale()) {
				this.isAventurierPresent = true;
			}
		});
		return this.isAventurierPresent;
	}

	/**
	 * Methode permettant de collecter un tresor s'il y en a sur la case où est
	 * présent l'aventurier
	 * 
	 * @param carte      la carte en entree
	 * @param aventurier l'aventurier jouant
	 */
	public void collecterTresor(Carte carte, Aventurier aventurier) {
		// On parcours la liste des tresors de la carte pour vérifier si les coordonnées
		// sont identiques de celles de la case de l'aventurier
		carte.getTresors().stream().forEach(tresor -> {
			// Si un tresor est présent à l'endroit où l'aventurier se situe,
			// il ramasse un tresor
			if (tresor.getValeurHorizontale() == aventurier.getValeurHorizontale()
					&& tresor.getValeurVerticale() == aventurier.getValeurVerticale() && tresor.getNombreTresor() > 0) {
				carte.getAventuriers().stream().filter(listAventurier -> listAventurier.getNom().equals(aventurier.getNom()))
				.findFirst().get().setNbTresors(aventurier.getNbTresors() + 1);
				tresor.setNombreTresor(tresor.getNombreTresor() - 1);
			}
		});
	}

	/**
	 * Methode pour avancer l'aventurier lorsque son action est "A" si une montagne
	 * n'est pas presente sur la case visee
	 * 
	 * @param carte      la carte au tresor lue
	 * @param aventurier l'aventurier avant son action
	 * @return l'aventurier apres son action
	 */
	public void avancer(Carte carte, Aventurier aventurier) {
		OrientationEnum orientation = aventurier.getOrientation();
		Element caseTheorique;

		switch (orientation) {
		case N:
			if (aventurier.getValeurVerticale() > 0) {
				caseTheorique = new Element(aventurier.getValeurHorizontale(), aventurier.getValeurVerticale() - 1);
				if (!this.isMontagnePresente(carte, caseTheorique) && !this.isAventurierPresent(carte, caseTheorique)) {
					// s'il n'y a pas de montagne ou d'aventurier deja present
					// en case theorique, on deplace l'aventurier
					carte.getAventuriers().stream().filter(listAventurier -> listAventurier.getNom().equals(aventurier.getNom()))
						.findFirst().get().setValeurVerticale(aventurier.getValeurVerticale() - 1);
				}
			}
			break;

		case E:
			if (aventurier.getValeurHorizontale() < carte.getLargeur()) {
				caseTheorique = new Element(aventurier.getValeurHorizontale() + 1, aventurier.getValeurVerticale());
				if (!this.isMontagnePresente(carte, caseTheorique) && !this.isAventurierPresent(carte, caseTheorique)) {
					// s'il n'y a pas de montagne ou d'aventurier deja present
					// en case theorique, on deplace l'aventurier
					carte.getAventuriers().stream().filter(listAventurier -> listAventurier.getNom().equals(aventurier.getNom()))
					.findFirst().get().setValeurHorizontale(aventurier.getValeurHorizontale() + 1);
				}
			}
			break;

		case S:
			if (aventurier.getValeurVerticale() < carte.getHauteur()) {
				caseTheorique = new Element(aventurier.getValeurHorizontale(), aventurier.getValeurVerticale() + 1);
				if (!this.isMontagnePresente(carte, caseTheorique) && !this.isAventurierPresent(carte, caseTheorique)) {
					// s'il n'y a pas de montagne ou d'aventurier deja present
					// en case theorique, on deplace l'aventurier
					carte.getAventuriers().stream().filter(listAventurier -> listAventurier.getNom().equals(aventurier.getNom()))
					.findFirst().get().setValeurVerticale(aventurier.getValeurVerticale() + 1);
				}
			}
			break;

		case O:
			if (aventurier.getValeurHorizontale() > 0) {
				caseTheorique = new Element(aventurier.getValeurHorizontale() - 1, aventurier.getValeurVerticale());
				if (!this.isMontagnePresente(carte, caseTheorique) && !this.isAventurierPresent(carte, caseTheorique)) {
					// s'il n'y a pas de montagne ou d'aventurier deja present
					// en case theorique, on deplace l'aventurier
					carte.getAventuriers().stream().filter(listAventurier -> listAventurier.getNom().equals(aventurier.getNom()))
					.findFirst().get().setValeurHorizontale(aventurier.getValeurHorizontale() - 1);
				}
			}
			break;

		default:
			// Cas impossible
			break;
		}
	}

}
