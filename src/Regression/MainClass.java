package Regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Chromosome chromosome = new Chromosome();
		chromosome.generateIndividual();
		System.out.println("Random Node: " + chromosome.chooseRandomNode(chromosome.getSchema(), true, 0, 0) + " Fitness: " + chromosome.getFitness());
		TreeGraphView.displayTreeGraph(chromosome.getSchema(), "drzewko");
		System.out.println("TreeDepth: " + chromosome.countTreeDepth(chromosome.getSchema()));
	}

}
