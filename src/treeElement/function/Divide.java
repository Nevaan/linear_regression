package treeElement.function;

import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Divide extends Function{

	public Divide(Data data) {
		super(data);
		data.setChildAmount(2);
		data.setType(13);
	}

	public double getValue(double xValue){
		if(((TreeNode)this.getChildren().get(1)).getValue(xValue) != 0){
		return ((TreeNode) this.getChildren().get(0)).getValue(xValue) / 
				((TreeNode)this.getChildren().get(1)).getValue(xValue);
		} else {
			return 0;
		}
		
	}

}
