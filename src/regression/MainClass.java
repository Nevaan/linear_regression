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

		bestChromosomes = new ArrayList<Integer>();
		directoryCleanUp();
		
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
