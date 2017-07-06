package com.losek.regression.treeElement.terminal;


import com.losek.regression.treeRepresentation.Data;
import com.losek.regression.treeRepresentation.TreeNode;

import java.util.LinkedList;

public class Variable extends Terminal {

	public Variable(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(21);
	}

	public Variable(Data data, TreeNode parent) {
		super(data);
        this.parent = parent;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.elementsIndex.add(this);
        this.data.setId(IDENTIFIER++);
        this.data.setChildAmount(0);

    }

	public double getValue(double xValue) {
		return xValue;
	}

}
