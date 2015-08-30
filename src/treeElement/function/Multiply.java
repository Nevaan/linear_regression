package treeElement.function;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Multiply extends Function{

	public Multiply(Data data) {
		super(data);

	}

	public double getValue(double xValue){
		return ((TreeNode) this.getChildren().get(0)).getValue(xValue) * ((TreeNode)this.getChildren().get(1)).getValue(xValue);
	}


}
