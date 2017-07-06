package com.losek.regression.treeElement.function;


import com.losek.regression.treeRepresentation.Data;
import com.losek.regression.treeRepresentation.TreeNode;

import java.util.LinkedList;

public class Multiply extends Function {

	public Multiply(Data data) {
		super(data);
		data.setChildAmount(2);
		data.setType(12);
	}

	public Multiply(Data data, TreeNode parent) {
		super(data);
        this.parent = parent;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.elementsIndex.add(this);
        this.data.setId(IDENTIFIER++);
        this.data.setChildAmount(2);

    }

	public double getValue(double xValue) {
		return (((TreeNode) this.getChildren().get(0)).getValue(xValue)
				* ((TreeNode) this.getChildren().get(1)).getValue(xValue));
	}

}
