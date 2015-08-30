package treeElement.terminal;

import treeRepresentation.Data;

public class Variable extends Terminal {

	public Variable(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(21);
	}

	public double getValue(double xValue) {
		return xValue;
	}

}
