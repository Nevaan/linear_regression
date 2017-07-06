package com.losek.regression.treeRepresentation;

import java.util.Random;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;

public class NodeFactory {

	public static TreeNode getNode() throws Exception{
		Random random = new Random();
		if (random.nextBoolean()) {
			return getFunction();
		} else {
			return getTerminal();
		}
	}

	public static TreeNode getFunction() throws Exception{
		Random random = new Random();
		int option = random.nextInt(4);
		switch (option) {
		case 0:
			return new Add(new Data(10));
		case 1:
			return new Divide(new Data(13));
		case 2:
			return new Multiply(new Data(12));
		case 3:
			return new Substract(new Data(11));
		}
		return null;
	}

	public static TreeNode getTerminal() {
		Random random = new Random();
		int option = random.nextInt(2);
		switch (option) {
		case 0:
			return new Constant(new Data(20));
		case 1:
			return new Variable(new Data(21));
		}
		return null;
	}

}
