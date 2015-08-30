package treeElement.function;

import treeRepresentation.TreeNode;

public class Multiply extends Function{

	public Multiply(String data) {
		super(data);
		TreeNode.IDENTIFIER++;
		this.data = "Multiply, id: " + TreeNode.IDENTIFIER;
	}

	public double getValue(double xValue){
		return ((TreeNode) this.children.get(0)).getValue(xValue) * ((TreeNode)this.children.get(1)).getValue(xValue);
	}
	

}
