package genetics;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import graphics.graphs.TreeGraphView;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class Genetics {

	public void evolve(int generation){
		List<TreeNode> currentGeneration = new ArrayList<TreeNode>();
		currentGeneration = loadGeneration(generation);
		int i = 0;
		for(TreeNode t : currentGeneration) {
			TreeGraphView.displayTreeGraph(t ,"Generation: " + generation + " Chromosome: " + (i++) );
		}
	}
	
	private List<TreeNode> loadGeneration(int generationNumber){
		List<TreeNode> generation = new ArrayList<TreeNode>();
		File directory = new File("./xml/");
		FileFilter fileFilter = new WildcardFileFilter("Generation" + generationNumber +"Chromosome*.xml");
		File[] files = directory.listFiles(fileFilter);
		for (int i=0; i<files.length; i++){
			generation.add(XMLtoClass.convert(i, generationNumber));
		}
		return generation;
	}
}
