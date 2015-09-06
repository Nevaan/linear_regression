package genetics;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.filefilter.WildcardFileFilter;

public class Genetics {

	public void evolve(int generation){
		loadGeneration(generation);
	}
	
	private void loadGeneration(int generationNumber){
		File directory = new File("./xml/");
		FileFilter fileFilter = new WildcardFileFilter("Generation" + generationNumber +"Chromosome*.xml");
		File[] files = directory.listFiles(fileFilter);
		for (int i=0; i<files.length; i++){
			System.out.println(files[i]);
		}
	}
}
