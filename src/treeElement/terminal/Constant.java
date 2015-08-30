package treeElement.terminal;

import treeRepresentation.Data;

public class Constant extends Terminal {

	public Constant(Data data) {
		super(data);
	}

	public double getValue(double xValue) {
		return 4;
	}

}
