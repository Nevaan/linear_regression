package graphics.graphs;

import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import treeRepresentation.Edge;
import treeRepresentation.TreeNode;

public class TreeGraphGenerator {

	private DelegateTree<TreeNode, Edge> treeGraph;

	public TreeGraphGenerator(TreeNode root) {

		treeGraph = new DelegateTree<TreeNode, Edge>(new DirectedOrderedSparseMultigraph<TreeNode, Edge>());
		treeGraph.setRoot(root);
		generateTreeGraph(root);
	}

	public void generateTreeGraph(TreeNode root) {

		for(int i = 0; i < root.getChildAmount(); i++) {
			treeGraph.addChild(new Edge(), root, root.children.get(i));
			generateTreeGraph(root.children.get(i));
		}
	}

	public DelegateTree<TreeNode, Edge> getTreeGraph() {
		return treeGraph;
	}

	public void setTreeGraph(DelegateTree<TreeNode, Edge> treeGraph) {
		this.treeGraph = treeGraph;
	}

}

