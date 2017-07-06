package com.losek.regression.treeRepresentation;


import com.losek.regression.treeElement.function.Add;
import com.losek.regression.treeElement.function.Divide;
import com.losek.regression.treeElement.function.Multiply;
import com.losek.regression.treeElement.function.Substract;
import com.losek.regression.treeElement.terminal.Constant;
import com.losek.regression.treeElement.terminal.Variable;

import java.util.Random;

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
