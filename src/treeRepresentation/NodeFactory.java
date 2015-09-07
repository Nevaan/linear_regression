package treeRepresentation;

import java.util.Random;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;

public class NodeFactory {

	public static TreeNode getNode() {
		Random random = new Random();
		if (random.nextBoolean()) {
			return getFunction();
		} else {
			return getTerminal();
		}
	}

	public static TreeNode getFunction() {
		Random random = new Random();
		int option = random.nextInt(4);
		switch (option) {
		case 0:
			return new Add();
		case 1:
			return new Divide();
		case 2:
			return new Multiply();
		case 3:
			return new Substract();
		}
		return null;
	}

	public static TreeNode getTerminal() {
		Random random = new Random();
		int option = random.nextInt(2);
		switch (option) {
		case 0:
			return new Constant();
		case 1:
			return new Variable();
		}
		return null;
	}

}
