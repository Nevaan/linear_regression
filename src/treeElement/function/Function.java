package treeElement.function;

import java.util.Random;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

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
