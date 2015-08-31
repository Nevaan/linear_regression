package Regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		TreeNode tree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
		TreeGraphView.displayTreeGraph(tree, "drzewko");
		Chromosome chromosome = new Chromosome();
		chromosome.generateIndividual();
		System.out.println("Random Node: " + chromosome.chooseRandomNode(chromosome.getSchema(), true, 0, 0) + " Fitness: " + chromosome.getFitness());
	}

}
