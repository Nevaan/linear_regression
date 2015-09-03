package treeElement.terminal;

public class Variable extends Terminal {

	public Variable() {
		super();
		this.type = "x";
	}

	public double getValue(double xValue) {
		return xValue;
	}

}
