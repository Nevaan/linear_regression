package treeElement.terminal;

import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Constant extends Terminal {

	private double value;

	public Constant() {
		super();
		Random random = new Random();
		this.value = (double) (random.nextInt(20) - 10);
		this.type = "Constant";
	}
	

	public double getValue(double xValue) {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return Double.toString(value);
	}

}
