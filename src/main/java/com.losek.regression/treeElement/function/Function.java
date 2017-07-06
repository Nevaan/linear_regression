package com.losek.regression.treeElement.function;

import com.losek.regression.treeRepresentation.Data;
import com.losek.regression.treeRepresentation.TreeNode;

import java.util.Random;

public abstract class Function extends TreeNode {

	public Function(Data data) {
		super(data);
	}

	@Override
	public TreeNode chooseRandomChild() {
		Random random = new Random();
		return this.children.get(random.nextInt(2));
	}

}
