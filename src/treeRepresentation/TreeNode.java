package treeRepresentation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Function;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Terminal;
import treeElement.terminal.Variable;

public class TreeNode implements Iterable<TreeNode> {

	private Data data;
	private TreeNode parent;
	private List<TreeNode> children;
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
		if(this instanceof Function)
			this.data.setChildAmount(2);
		else if (this instanceof Terminal)
			this.data.setChildAmount(0);
	}

	public TreeNode copyTree(TreeNode original) {
		TreeNode clone = new TreeNode(new Data(0));

		clone.setData(original.getData());
		clone.setParent(original.getParent());
		clone.setChildren(original.getChildren());
		clone.setElementsIndex(original.getElementsIndex());


		return clone;

	}

	public TreeNode addChild(Data childType, TreeNode child) {
		TreeNode childNode = selectSubClass(childType);
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

	private TreeNode selectSubClass(Data data) {
		switch (data.getType()) {
		case 10:
			return new Add(data);
		case 11:
			return new Substract(data);
		case 12:
			return new Multiply(data);
		case 13:
			return new Divide(data);
		case 20:
			return new Constant(data);
		case 21:
			return new Variable(data);
		default:
			return null;
		}
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

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public List<TreeNode> getElementsIndex() {
		return elementsIndex;
	}

	public void setElementsIndex(List<TreeNode> elementsIndex) {
		this.elementsIndex = elementsIndex;
	}

}
