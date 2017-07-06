package com.losek.regression.treeElement.terminal;


import com.losek.regression.treeRepresentation.Data;
import com.losek.regression.treeRepresentation.TreeNode;

import java.util.LinkedList;
import java.util.Random;

import static com.losek.regression.treeRepresentation.TreeNode.IDENTIFIER;

public class Constant extends Terminal {

	public Constant(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(20);
		Random r = new Random();
		data.setConstant((double) (r.nextInt(20) - 10));
	}

	public Constant(Data data, TreeNode parent) {
		super(data);
        this.parent = parent;
        this.children = new LinkedList<TreeNode>();
        this.elementsIndex = new LinkedList<TreeNode>();
        this.elementsIndex.add(this);
        this.data.setId(IDENTIFIER++);
        this.data.setChildAmount(0);

    }

	public double getValue(double xValue) {
		return (double) this.getData().getConstant();
	}

}
