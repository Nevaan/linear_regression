package regression;

import graphics.graphs.TreeGraphView;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.xmlConversion.ClassToXML;

public class MainClass {

	public static void main(String[] args) {

		TreeNode testTree = null;

		try {
			testTree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
		} catch (Exception e) {
			System.out.println("ERROR: podczas generowania drzewa.");
			e.printStackTrace();
		}

		ClassToXML.convert(testTree);

		TreeGraphView.displayTreeGraph(testTree, "Test Tree");

	}

}
