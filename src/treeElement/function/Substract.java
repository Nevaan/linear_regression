package treeElement.function;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Substract extends Function{

	public Substract(Data data) {
		super(data);
		data.setChildAmount(2);
		data.setType(11);
	}


	public double getValue(double xValue){
		return ((TreeNode) this.getChildren().get(0)).getValue(xValue) - 
				((TreeNode)this.getChildren().get(1)).getValue(xValue);
	}

}
