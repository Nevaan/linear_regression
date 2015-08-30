package treeRepresentation;

import java.util.Random;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeRepresentation.Data;

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
		int option = random.nextInt(3);
		switch (option) {
		case 0:
			return new Add(new Data());
		case 1:
			return new Divide(new Data());
		case 2:
			return new Multiply(new Data());
		case 4:
			return new Substract(new Data());
		}
		return null;
	}

	public static TreeNode getTerminal() {
		Random random = new Random();
		int option = random.nextInt(3);
		switch (option) {
		case 0:
			return new Constant(new Data());
		case 1:
			return new Variable(new Data());
		}
		return null;
	}
	
}
