package com.losek.regression.treeElement.terminal;


import com.losek.regression.treeRepresentation.Data;
import com.losek.regression.treeRepresentation.TreeNode;

public abstract class Terminal extends TreeNode {

	public Terminal(Data data) {
		super(data);
		data.setChildAmount(0);
	}

	@Override
	public TreeNode chooseRandomChild() {
		return null;
	}

}
