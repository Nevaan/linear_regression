package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import genetics.Population;
import graphics.graphs.TreeGraphView;
import treeRepresentation.ClassToXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class MainClass {

	public static void main(String[] args) {

		// Odkomentowaæ przy pierwszym odpaleniu - wrzuca do katalogu xml pierwsza populacje; potem mozna zakomentowac i zostawic to co jest nizej zeby zobaczyc,
		// ze poprawnie wczytuje sie ta populacja
		/*
		directoryCleanUp();		
		Population population = new Population();
		population.initialize(0);
		*/
		
		// Odkomentowac po min. jednokrotnym odpaleniu powyzszego - inaczej wywala error bo brakuje xmli 
		/*
		for (int i = 0; i<Parameters.POPULATION_SIZE; i++){
			TreeNode loadedChromosome = XMLtoClass.convert(i,0);	
			TreeGraphView.displayTreeGraph(loadedChromosome , "Test ");
		}*/
		

	}

	
	public static void directoryCleanUp(){
		if(!Files.exists(Paths.get("./xml/"))){
			new File("./xml/").mkdir();
		}
		
		File folder = new File("./xml/");
		File[] files = folder.listFiles();
		if(files!=null){
			for(File f:files){
				f.delete();
			}
		}
	}
}
