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

    protected Data data;
    protected TreeNode parent;
    protected List<TreeNode> children;
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

    protected List<TreeNode> elementsIndex;

    public TreeNode(Data data) {
        this.data = data;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.registerChildForSearch(this);
        this.data.setId(IDENTIFIER++);
        if (this instanceof Function)
            this.data.setChildAmount(2);
        else if (this instanceof Terminal)
            this.data.setChildAmount(0);
    }

    public TreeNode(Data data, TreeNode parent) {
        this.parent = parent;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.elementsIndex.add(this);
        this.data.setId(IDENTIFIER++);
        if(this instanceof Function)
        	this.data.setChildAmount(2);
        else if (this instanceof Terminal)
        	this.data.setChildAmount(0);

    }

    public TreeNode copyTree() {
        TreeNode clone;
        switch (this.getData().getType()) {
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
        clone.setData(this.getData());
        clone.setParent(this.getParent());
        clone.setChildren(this.getChildren());
        clone.setElementsIndex(this.getElementsIndex());

        TreeNode.IDENTIFIER--;
        if(this.parent != null)
        	this.parent.registerChildForSearch(clone);

        return clone;

    }

    public TreeNode copy() {
        return copyWithParent(parent);
    }

    public TreeNode copyWithParent(TreeNode parent) {

    	TreeNode out;

    	switch (this.getData().getType()) {
        case 10:
            out = new Add(new Data(10), parent);
            break;
        case 11:
            out = new Substract(new Data(11), parent);
            break;
        case 12:
            out = new Multiply(new Data(12), parent);
            break;
        case 13:
            out = new Divide(new Data(13), parent);
            break;
        case 20:
            out = new Constant(new Data(20), parent);
            break;
        case 21:
            out = new Variable(new Data(21), parent);
            break;
        default:
            return null;
        }

        if (!this.getChildren().isEmpty()) {

            if (this.getChildren().get(0) != null) {
                out.getChildren().get(0).copyWithParent(out);
            }

            if (this.getChildren().get(1) != null) {
                out.getChildren().get(1).copyWithParent(out);
            }
        }

        return out;
    }

    public TreeNode addChild(Data childType, TreeNode child) {
        TreeNode childNode = child.copyTree();
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
        return ((data != null) ? this.getData().toString() : "[null]");
    }

    public String printFunction() {
        String left;
        String right;
        if (!this.getChildren().isEmpty()) {
            left = this.getChildren().get(0).printFunction();
            right = this.getChildren().get(1).printFunction();
            return "(" + left + ")" + this.getData().toString() + "(" + right + ")";
        }
        return this.getData().toString();
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
        this.children.addAll(children);
    }

    public List<TreeNode> getElementsIndex() {
        return elementsIndex;
    }

    public void setElementsIndex(List<TreeNode> elementsIndex) {
        this.elementsIndex.addAll(elementsIndex);
    }

    // Ghost Method - should be always overriden
    public TreeNode chooseRandomChild() {
        return null;
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
        } //else if (!children.equals(other.children))
            //return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (elementsIndex == null) {
            if (other.elementsIndex != null)
                return false;
        } //else if (!elementsIndex.equals(other.elementsIndex))
            //return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        return true;
    }

}
