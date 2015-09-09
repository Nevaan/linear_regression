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

		try {
			QueryXML process = new QueryXML();
			//directoryCleanUp();
			//TreeNode chromo0 = TreeGenerator.generateGrowTree(5);
			TreeNode chromo1 = TreeGenerator.generateGrowTree(5);

			//ClassToXML.convert(chromo0, 0);
			//ClassToXML.convert(chromo1, 0);

			//process.setUniqueIdentifiers(0, 0);
			//process.setUniqueIdentifiers(0, 1);

			process.setParentParameters(0, 0);
			process.setParentParameters(0, 1);

			TreeGraphView.displayTreeGraph(XMLtoClass.convert(0, 0), "Homosom 0");
			TreeGraphView.displayTreeGraph(XMLtoClass.convert(0, 1),"Homosom 1");

			
			Genetics genetics = new Genetics();
			TreeNode replaced = genetics.wszczyknij(XMLtoClass.convert(0, 0), XMLtoClass.convert(0, 1), 1, 4);
			ClassToXML.convert(replaced, 0);
			TreeGraphView.displayTreeGraph(replaced,"Wszczykniety");
			//genetics.crossover(0, 0, 0, 1);
			//TreeGraphView.displayTreeGraph(XMLtoClass.getReplaced(), "Replaced");

			//process.crossover(0, 0, 1);
			//process.setUniqueIdentifiers(0, 1);	
			//process.setUniqueIdentifiers(0, 1);
			//for(int i=0; i<1000; i++){
			//process.getRandomNode(0, 1);
			//}
			//TreeGraphView.displayTreeGraph(testTree,"Test tree");
			//TreeGraphView.displayTreeGraph(XMLtoClass.getSubtree(),"RandomSubtree");
			
			
			
		} catch (Exception e) {
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
