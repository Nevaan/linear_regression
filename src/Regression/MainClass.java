package Regression;

import graphics.graphs.TreeGraphView;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		TreeNode tree = TreeGenerator.generateGrowTree(5);
		TreeGraphView.displayTreeGraph(tree, "drzewko");

	}

}
