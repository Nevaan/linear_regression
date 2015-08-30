package Regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		TreeNode tree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
		TreeGraphView.displayTreeGraph(tree, "drzewko");
		System.out.println("Tree Height: " + tree.calculateTreeHeight());
	}

}
