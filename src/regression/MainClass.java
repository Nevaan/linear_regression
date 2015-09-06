package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import graphics.graphs.TreeGraphView;
import treeRepresentation.ClassToXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class MainClass {

	public static void main(String[] args) {

		TreeNode testTree = null;

		try {
			testTree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
		} catch (Exception e) {
			System.out.println("ERROR: podczas generowania drzewa.");
			e.printStackTrace();
		}
		
		directoryCleanUp();
		
		ClassToXML.convert(testTree);
		
		TreeNode test = XMLtoClass.convert(1);
		
		ClassToXML.convert(test);
		
		TreeGraphView.displayTreeGraph(testTree, "Test Tree");
		TreeGraphView.displayTreeGraph(test, "Test ");
		System.out.println(test.getValue(5));
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
