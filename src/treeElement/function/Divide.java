package treeElement.function;

import java.util.LinkedList;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Divide extends Function {

	public Divide(Data data) {
		super(data);
		data.setChildAmount(2);
		data.setType(13);
	}

	public Divide(Data data, TreeNode parent) {
		super(data);
        this.parent = parent;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.elementsIndex.add(this);
        this.data.setId(IDENTIFIER++);
        this.data.setChildAmount(2);

    }

	public double getValue(double xValue) {
		if (((TreeNode) this.getChildren().get(1)).getValue(xValue) != 0) {
			return ((TreeNode) this.getChildren().get(0)).getValue(xValue)
					/ ((TreeNode) this.getChildren().get(1)).getValue(xValue);
		} else {
			return 0;
		}

	}

}
