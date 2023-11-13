package com.test.treasure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Element;
import com.exercice.treasure.model.Montagne;
import com.exercice.treasure.model.OrientationEnum;
import com.exercice.treasure.model.Tresor;
import com.exercice.treasure.traitement.Processor;

@ExtendWith(SpringExtension.class)
public class ProcessorTest {

	Processor processor = new Processor();

	/**
	 * Test du processeur complet
	 */
	@Test
	public void processGameTest() {
		// GIVEN : On cree l'objet attendu pour comparer
		Carte expectedCarte = new Carte();
		expectedCarte.setLargeur(3);
		expectedCarte.setHauteur(4);
		Montagne montagne1 = new Montagne(1, 0);
		Montagne montagne2 = new Montagne(2, 1);
		expectedCarte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Tresor expectedTresor1 = new Tresor(0, 3, 0);
		Tresor expectedTresor2 = new Tresor(1, 3, 2);
		expectedCarte.setTresors(Arrays.asList(expectedTresor1, expectedTresor2));
		// TODO : creer les champs manquants
		expectedCarte.setAventuriers(new ArrayList<>());
		Aventurier expectedAventurier = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		expectedAventurier.setNbTresors(3);
		expectedCarte.getAventuriers().add(expectedAventurier);
		
		// GIVEN : On cree l'objet en entree
		Carte carte = new Carte();
		carte.setLargeur(3);
		carte.setHauteur(4);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Tresor tresor1 = new Tresor(0, 3, 2);
		Tresor tresor2 = new Tresor(1, 3, 3);
		carte.setTresors(Arrays.asList(tresor1, tresor2));
		carte.setAventuriers(new ArrayList<>());
		Aventurier aventurier = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		carte.getAventuriers().add(aventurier);
		
		// ACT : on lance le processeur sur notre carte
		Carte result = processor.processGame(carte);
		
		// ASSERT : on verifie tous les champs 
		assertNotNull(result);
		// TODO : ajouter les champs manquants
		assertEquals(expectedCarte.getAventuriers().get(0).getNbTresors(), result.getAventuriers().get(0).getNbTresors());
	}

	/**
	 * Test l'action D - tourner à droite
	 */
	@Test
	public void tournerDroiteTest() {
		// GIVEN
		Aventurier aventurier = new Aventurier("Aventurier", 2, 3, OrientationEnum.S, "sequence");
		String sens = "D";

		Aventurier aventurierExpected = new Aventurier("Aventurier", 2, 3, OrientationEnum.O, "sequence");

		// ACT
		this.processor.tourner(aventurier, sens);

		// ASSERT
		assertEquals(aventurierExpected.getOrientation(), aventurier.getOrientation());
	}

	/**
	 * Test l'action G - tourner à gauche
	 */
	@Test
	public void tournerGaucheTest() {
		// GIVEN
		Aventurier aventurier = new Aventurier("Aventurier", 2, 3, OrientationEnum.E, "sequence");
		String sens = "G";

		Aventurier aventurierExpected = new Aventurier("Aventurier", 2, 3, OrientationEnum.N, "sequence");

		// ACT
		this.processor.tourner(aventurier, sens);

		// ASSERT
		assertEquals(aventurierExpected.getOrientation(), aventurier.getOrientation());
	}

	/**
	 * Test le retour a true si une montagne est presente sur la case theorique
	 */
	@Test
	public void isMontagnePresenteTest() {
		// GIVEN
		Carte carte = new Carte();
		Montagne montagne1 = new Montagne(0, 0);
		Montagne montagne2 = new Montagne(2, 2);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Element caseTheorique = new Element(2, 2);
		// ACT & ASSERT
		assertTrue(this.processor.isMontagnePresente(carte, caseTheorique));
	}

	/**
	 * Test le retour a false si aucune montagne est presente sur la case theorique
	 */
	@Test
	public void isMontagneNonPresenteTest() {
		// GIVEN
		Carte carte = new Carte();
		Montagne montagne1 = new Montagne(0, 0);
		Montagne montagne2 = new Montagne(2, 2);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Element caseTheorique = new Element(2, 3);
		// ACT & ASSERT
		assertFalse(this.processor.isMontagnePresente(carte, caseTheorique));
	}

	/**
	 * Test le retour a true si un aventurier est present sur la case theorique
	 */
	@Test
	public void isAventurierPresentTest() {
		// GIVEN
		Carte carte = new Carte();
		Aventurier aventurier1 = new Aventurier("Lara", 1, 2, OrientationEnum.E, "AADADAGGA");
		Aventurier aventurier2 = new Aventurier("Thomas", 2, 2, OrientationEnum.N, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurier1, aventurier2));
		Element caseTheorique = new Element(2, 2);
		// ACT & ASSERT
		assertTrue(this.processor.isAventurierPresent(carte, caseTheorique));
	}

	/**
	 * Test le retour a false si aucun aventurier est present sur la case theorique
	 */
	@Test
	public void isAventurierNonPresentTest() {
		// GIVEN
		Carte carte = new Carte();
		Aventurier aventurier1 = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		Aventurier aventurier2 = new Aventurier("Thomas", 2, 2, OrientationEnum.N, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurier1, aventurier2));
		Element caseTheorique = new Element(1, 2);
		// ACT & ASSERT
		assertFalse(this.processor.isAventurierPresent(carte, caseTheorique));
	}

	/**
	 * Test la collecte d'un tresor par un aventurier
	 */
	@Test
	public void collecterTresorTest() {
		// GIVEN
		Carte carte = new Carte();
		Tresor tresor = new Tresor(1, 1, 1);
		carte.setTresors(Arrays.asList(tresor));
		Aventurier aventurier = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurier));
		// ACT
		this.processor.collecterTresor(carte, aventurier);
		// ASSERT
		assertEquals(1, carte.getAventuriers().get(0).getNbTresors());
		assertEquals(0, carte.getTresors().get(0).getNombreTresor());
	}

	/**
	 * Test la non collecte d'un tresor déjà pris par un aventurier
	 */
	@Test
	public void collecterAucunTresorTest() {
		// GIVEN
		Carte carte = new Carte();
		Tresor tresor = new Tresor(1, 1, 0);
		carte.setTresors(Arrays.asList(tresor));
		Aventurier aventurier = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurier));
		// ACT
		this.processor.collecterTresor(carte, aventurier);
		// ASSERT
		assertEquals(0, aventurier.getNbTresors());
		assertEquals(0, tresor.getNombreTresor());
	}

	/**
	 * Test qu'un aventurier ne puisse pas avancer sur une case montagne
	 */
	@Test
	public void avancerMontagneTest() {
		// GIVEN
		Carte carte = new Carte();
		Montagne montagne1 = new Montagne(0, 0);
		Montagne montagne2 = new Montagne(2, 2);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Aventurier aventurierDepart = new Aventurier("Lara", 2, 1, OrientationEnum.S, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurierDepart));

		Aventurier aventurierExpected = new Aventurier("Lara", 2, 1, OrientationEnum.S, "AADADAGGA");

		// ACT
		this.processor.avancer(carte, aventurierDepart);

		// ASSERT
		assertEquals(aventurierExpected.getValeurHorizontale(), carte.getAventuriers().get(0).getValeurHorizontale());
		assertEquals(aventurierExpected.getValeurVerticale(), carte.getAventuriers().get(0).getValeurVerticale());
	}

	/**
	 * Test l'action A - qu'un aventurier avance correctement
	 */
	@Test
	public void avancerTest() {
		// GIVEN
		Carte carte = new Carte();
		carte.setHauteur(3);
		carte.setLargeur(3);
		Montagne montagne1 = new Montagne(0, 0);
		Montagne montagne2 = new Montagne(1, 2);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Aventurier aventurierDepart = new Aventurier("Lara", 2, 1, OrientationEnum.S, "AADADAGGA");
		carte.setAventuriers(Arrays.asList(aventurierDepart));

		Aventurier aventurierExpected = new Aventurier("Lara", 2, 2, OrientationEnum.S, "AADADAGGA");

		// ACT
		this.processor.avancer(carte, aventurierDepart);

		// ASSERT
		assertEquals(aventurierExpected.getValeurHorizontale(), carte.getAventuriers().get(0).getValeurHorizontale());
		assertEquals(aventurierExpected.getValeurVerticale(), carte.getAventuriers().get(0).getValeurVerticale());
	}
}
