package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import genetics.Genetics;
import genetics.Population;

public class MainClass {

	public static void main(String[] args) {

		Genetics genetics = new Genetics();

		try {
			Population population = new Population();
			population.initialize();
			Parameters.CURRENT_GENERATION_ID++;

			for(int i = 0; i < Parameters.GENERATIONS_AMOUNT; i++) {
				population = genetics.evolve(population);
				System.out.println("~~~~~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~~~~~");
				Parameters.CROSSOVERED_FILE_NAME_ID = 0;
				Parameters.CURRENT_GENERATION_ID++;
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
