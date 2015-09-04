package regression;

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

		//ClassToXML.convert(testTree);
		TreeNode test = XMLtoClass.convert(1);

		ClassToXML.convert(test);

		TreeGraphView.displayTreeGraph(testTree, "Test Tree");

	}

}
