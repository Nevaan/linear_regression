package treeElement.terminal;

import treeRepresentation.Data;

public class Variable extends Terminal {

	public Variable(Data data) {
		super(data);
		data.setChildAmount(0);
	}

	public double getValue(double xValue) {
		return 3;
	}

}
