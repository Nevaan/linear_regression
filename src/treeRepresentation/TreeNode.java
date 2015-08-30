package treeRepresentation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode implements Iterable<TreeNode> {

	public Data data;
	public TreeNode parent;
	public List<TreeNode> children;
	public static Long IDENTIFIER = 0L;

	public double getValue(double xValue) {
		return 0;
	};

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<TreeNode> elementsIndex;

	public TreeNode(Data data) {
		this.data = data;
		this.children = new LinkedList<TreeNode>();
		this.elementsIndex = new LinkedList<TreeNode>();
		this.elementsIndex.add(this);
		this.data.setId(IDENTIFIER++);
	}

	public TreeNode addChild(Data child) {
		
		TreeNode childNode = new TreeNode(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(TreeNode node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public TreeNode findTreeNode(Comparable<Data> cmp) {
		for (TreeNode element : this.elementsIndex) {
			Data elData = element.data;
			if (cmp.compareTo(elData) == 0)
				return element;
		}

		return null;
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<TreeNode> iterator() {
		TreeNodeIter iter = new TreeNodeIter(this);
		return iter;
	}

}
