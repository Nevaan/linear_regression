package treeElement.terminal;

import treeRepresentation.Data;

public class Variable extends Terminal {

	public Variable(Data data) {
		super(data);
	}

	public double getValue(double xValue) {
		return 3;
	}

}
