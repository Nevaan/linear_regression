package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import genetics.Genetics;
import genetics.Genetics;
import graphics.graphs.TreeGraphView;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class MainClass {

	public static void main(String[] args) {
		
		
		
		QueryXML query = new QueryXML();
		
		try {			

			
			
			Genetics genetics = new Genetics();
			for (int i = 0; i<1; i++) {
				System.out.println(i);
				ClassToXML.convert(TreeGenerator.generateGrowTree(5), 0);
				ClassToXML.convert(TreeGenerator.generateGrowTree(5), 0);
				
				query.setUniqueIdentifiers(0, 0);
				query.setUniqueIdentifiers(0, 1);	
				query.setParentParameters(0, 0);
				query.setParentParameters(0, 1);
				
				
				Parameters.FILE_NAME_ID = 0;
				genetics.crossover(0, 0, 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		TreeGraphView.displayTreeGraph(XMLtoClass.convert(0, 0), "Homosom 0");
		TreeGraphView.displayTreeGraph(XMLtoClass.convert(0, 1),"Homosom 1");
		TreeGraphView.displayTreeGraph(XMLtoClass.convert(1, 0), "Skrzyzowane drzewo");
		
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
