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

	public static TreeNode copyTree(TreeNode original) {
		TreeNode clone;
		switch(original.getData().getType()) {
		case 10:
			clone = new Add(new Data(10));
			break;
		case 11:
			clone = new Substract(new Data(11));
			break;
		case 12:
			clone = new Multiply(new Data(12));
			break;
		case 13:
			clone = new Divide(new Data(13));
			break;
		case 20:
			clone = new Constant(new Data(20));
			break;
		case 21:
			clone = new Variable(new Data(21));
			break;
		default:
			return null;
		}
		clone.setData(original.getData());
		clone.setParent(original.getParent());
		clone.setChildren(original.getChildren());
		clone.setElementsIndex(original.getElementsIndex());


		return clone;

	}

	public TreeNode addChild(Data childType, TreeNode child) {
		TreeNode childNode = TreeNode.copyTree(child);
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
		return ((data!=null)? this.getData().toString() : "[null]");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children.isEmpty()) ? 0 : children.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((elementsIndex.isEmpty()) ? 0 : elementsIndex.hashCode());
		result = prime * result + ((parent.equals(null)) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (elementsIndex == null) {
			if (other.elementsIndex != null)
				return false;
		} else if (!elementsIndex.equals(other.elementsIndex))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}


}
