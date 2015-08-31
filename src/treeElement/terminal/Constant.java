package treeElement.terminal;

import java.util.Random;

import treeRepresentation.Data;

public class Constant extends Terminal {

	public Constant(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(20);
		Random r = new Random();
		data.setConstant((double) (r.nextInt(20) - 10));
	}

	public double getValue(double xValue) {
		return (double) this.getData().getConstant();
	}

}
