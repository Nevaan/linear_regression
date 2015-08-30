package treeElement.terminal;

import treeRepresentation.Data;

public class Constant extends Terminal {

	public Constant(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(20);
	}

	public double getValue(double xValue) {
		return 4;
	}


}
