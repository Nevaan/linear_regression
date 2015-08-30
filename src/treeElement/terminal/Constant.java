package treeElement.terminal;

import java.util.Random;

import treeRepresentation.Data;

public class Constant extends Terminal {

	private double value;
	
	public Constant(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(20);
	    Random r = new Random(); 
	    value = -10.0 + r.nextDouble() * 10.0; 	
	    data.setConstant(value);
	}

	public double getValue(double xValue) {
		return value;
	}

}
