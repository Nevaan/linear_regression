package Regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		//TreeGraphView.displayTreeGraph(chromosome.getSchema(), "drzewko");

		Population population = new Population();
		population.initializePopulation();
		population.getFittest();
	}

}
