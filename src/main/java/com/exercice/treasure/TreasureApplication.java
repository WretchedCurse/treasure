package com.exercice.treasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.exercice.treasure.model.Carte;
import com.exercice.treasure.traitement.Processor;
import com.exercice.treasure.traitement.Reader;
import com.exercice.treasure.traitement.Writer;

@SpringBootApplication
@ComponentScan("com.exercice.treasure")
@PropertySource("classpath:application.properties")
public class TreasureApplication implements CommandLineRunner {
	
	@Autowired 
	Reader reader;

	@Autowired 
	Processor processor;

	@Autowired 
	Writer writer;
	
	@Value("${file.path}")
	String inputFilePath;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TreasureApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Carte carteLue = reader.readFile(inputFilePath);
		Carte resultatJeu = processor.processGame(carteLue);
		writer.writeOutput(resultatJeu);
	}
}
