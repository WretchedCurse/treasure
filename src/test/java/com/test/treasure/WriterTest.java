package com.test.treasure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.exercice.treasure.model.Aventurier;
import com.exercice.treasure.model.Carte;
import com.exercice.treasure.model.Montagne;
import com.exercice.treasure.model.OrientationEnum;
import com.exercice.treasure.model.Tresor;
import com.exercice.treasure.traitement.Writer;

@ExtendWith(SpringExtension.class)
public class WriterTest {

	Writer wrtier = new Writer();

	@BeforeAll
	public static void init() {
		File file = new File("./src/test/resources/output/resultats.txt");
		file.delete();
	}

	/**
	 * Test du writer complet
	 * 
	 * @throws IOException
	 */
	@Test
	public void writeOutputTest() throws IOException {
		// GIVEN : On cree la carte en entree
		Carte carte = new Carte();
		carte.setLargeur(3);
		carte.setHauteur(4);
		Montagne montagne1 = new Montagne(1, 0);
		Montagne montagne2 = new Montagne(2, 1);
		carte.setMontagnes(Arrays.asList(montagne1, montagne2));
		Tresor tresor = new Tresor(1, 3, 2);
		carte.setTresors(Arrays.asList(tresor));
		carte.setAventuriers(new ArrayList<>());
		Aventurier aventurier = new Aventurier("Lara", 0, 3, OrientationEnum.S, "AADADAGGA");
		aventurier.setNbTresors(3);
		carte.getAventuriers().add(aventurier);

		// ACT : on lance le writrer sur notre carte
		wrtier.writeOutput(carte);

		// ASSERT : on verifie la creation du fichier dans le dossier
		// test/resources/output
	}

	// TODO : compéter les tests unitaires en ajoutant des tests sur chaque methode
}
