package treeElement.function;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Substract extends Function{

	public Substract(Data data) {
		super(data);
	}


	public double getValue(double xValue){
		return ((TreeNode) this.children.get(0)).getValue(xValue) - ((TreeNode)this.children.get(1)).getValue(xValue);
	}

}
