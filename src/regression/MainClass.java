package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import genetics.Genetics;
import genetics.Population;
import genetics.SampleData;
import treeRepresentation.ClassToXML;

public class MainClass {

	public static List<Integer> bestChromosomes;

	public static void main(String[] args) {

		Genetics genetics = new Genetics();
		bestChromosomes = new ArrayList<Integer>();
		directoryCleanUp();

		try {
			Population population = new Population();
			population.initialize();
			ClassToXML.convertFittest(population.getFittest(), 0);
			SampleData dataInitializer = new SampleData();
			Parameters.CURRENT_CHROMOSOME_ID = 0;
			Parameters.CURRENT_GENERATION_ID++;

			for (int i = 0; i < Parameters.GENERATIONS_AMOUNT; i++) {
				population = genetics.evolve(population);
				ClassToXML.convertFittest(population.getFittest(), Parameters.CURRENT_GENERATION_ID);
				System.out.println("~~~~~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~~~~~");
				Parameters.CURRENT_CHROMOSOME_ID = 0;
				Parameters.CURRENT_GENERATION_ID++;
				bestChromosomes.add(i, population.getPopulation().indexOf(population.getFittest()));

			}

		} catch (Exception e) {
			System.out.println("Error while executing main");
			e.printStackTrace();
		}

	}

	public static void directoryCleanUp() {
		if (!Files.exists(Paths.get("./xml/"))) {
			new File("./xml/").mkdir();
		}

		File folder = new File("./xml/");
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
	}
}
