package treeElement.function;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Divide extends Function{

	public Divide(Data data) {
		super(data);
		data.setChildAmount(2);
	}

	public double getValue(double xValue){
		return ((TreeNode) this.getChildren().get(0)).getValue(xValue) / ((TreeNode)this.getChildren().get(1)).getValue(xValue);
	}

}
