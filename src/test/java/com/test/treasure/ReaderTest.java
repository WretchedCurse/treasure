package com.test.treasure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Montagne;
import com.exercice.treasure.model.OrientationEnum;
import com.exercice.treasure.traitement.Reader;

@ExtendWith(SpringExtension.class)
public class ReaderTest {

	Reader reader = new Reader();
	
	/**
	 * Test du reader complet
	 */
	@Test
	public void readFileTest() {
		// GIVEN : On cree l'objet attendu pour comparer
		Carte expectedCarte = new Carte();
		expectedCarte.setLargeur(3);
		expectedCarte.setHauteur(4);
		// TODO : creer les champs manquants
		expectedCarte.setAventuriers(new ArrayList<>());
		Aventurier expectedAventurier = new Aventurier("Lara", 1, 1, OrientationEnum.S, "AADADAGGA");
		expectedCarte.getAventuriers().add(expectedAventurier);
		
		// ACT : on lance le reader sur notre fichier source
		Carte result = reader.readFile("./src/test/resources/input/entree.txt");
		
		// ASSERT : on verifie tous les champs 
		assertNotNull(result);
		assertEquals(expectedCarte.getLargeur(), result.getLargeur());
		assertEquals(expectedCarte.getHauteur(), result.getHauteur());
		// TODO : ajouter les champs manquants
		assertEquals(expectedCarte.getAventuriers().get(0).getNom(), result.getAventuriers().get(0).getNom());
	}
	
	
	/**
	 * Test de la méthode de lecture d'une ligne associée à un objet Montagne
	 */
	@Test
	public void readMontagneTest() {
		// GIVEN
		Montagne expected = new Montagne(2,1);
		
		// ACT 
		Montagne result = reader.readMontagne("M - 2 - 1");
		
		// ASSERT
		assertEquals(expected.getValeurHorizontale(), result.getValeurHorizontale());
		assertEquals(expected.getValeurVerticale(), result.getValeurVerticale());
	}
	
	
	// TODO : compéter les tests unitaires en ajoutant des tests 
	// sur les méthodes des objets Aventurier et Tresor, puis tester des cas d'erreur
}
