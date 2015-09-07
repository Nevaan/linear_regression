package treeElement.function;

import javax.xml.bind.annotation.XmlRootElement;

import treeRepresentation.TreeNode;

@XmlRootElement
public class Add extends Function {

	public Add() {
		super();
		this.type = "+";
	}

	public double getValue(double xValue) {
		return ((TreeNode) this.children.get(0)).getValue(xValue) + ((TreeNode) this.children.get(1)).getValue(xValue);
	}

}
