package treeElement.terminal;

import java.util.Random;

import treeRepresentation.Data;

public class Constant extends Terminal {

	private int value;
	
	public Constant(Data data) {
		super(data);
		data.setChildAmount(0);
		data.setType(20);
	    Random r = new Random(); 
	    value = r.nextInt(20) - 10; 	
	    data.setConstant(value);
	}

	public double getValue(double xValue) {
		return (double) value;
	}

}
