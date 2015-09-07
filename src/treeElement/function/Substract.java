package treeElement.function;

import javax.xml.bind.annotation.XmlRootElement;

import treeRepresentation.TreeNode;

@XmlRootElement
public class Substract extends Function{

	public Substract() {
		super();
		this.type = "-";
	}


	public double getValue(double xValue){
		return ((TreeNode) this.children.get(0)).getValue(xValue) - ((TreeNode)this.children.get(1)).getValue(xValue);
	}

}
