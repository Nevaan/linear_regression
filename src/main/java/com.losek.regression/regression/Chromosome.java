package com.losek.regression.regression;

import java.util.Random;

import targetRepresentation.Cartesian;
import targetRepresentation.GPParameters;
import treeElement.function.Function;
import treeElement.terminal.Terminal;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class Chromosome {

	private TreeNode schema;
	private double fitness;
	private Cartesian cartesian;

	public Chromosome() {
		cartesian = new Cartesian();
	}

	public Chromosome(TreeNode root) {
		cartesian = new Cartesian();
		schema = root;
	}

	public void generateIndividual() {
		cartesian.init();
		try {
			schema = TreeGenerator.generateGrowTree(GPParameters.GROW_TREE_MAX_DEPTH);
			TreeGenerator.CAN_CHOOSE_TERMINAL = false;
			schema.setTreeHeight(countTreeDepth(this.getSchema()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public void copyIndividual(TreeNode schema) {
		cartesian.init();
		try {
			this.schema = schema;
			schema.setTreeHeight(countTreeDepth(this.getSchema()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*
	 * public TreeNode chooseRandomNode(TreeNode remainingSubtree, boolean
	 * isInitial, int chosenMaxLevel, int currentLevel) { int maxLevel = 0;
	 * TreeNode chosenNode = remainingSubtree; if (isInitial) { // if method was
	 * called on tree with single node if (remainingSubtree instanceof Terminal)
	 * return remainingSubtree;
	 * schema.setTreeHeight(countTreeDepth(this.getSchema())); Random random =
	 * new Random(); maxLevel = random.nextInt(schema.getTreeHeight()) + 1; }
	 * else { maxLevel = chosenMaxLevel; }
	 * 
	 * if (currentLevel < maxLevel) { TreeNode temp =
	 * remainingSubtree.chooseRandomChild().copyTree(); if (temp instanceof
	 * Function) chosenNode = chooseRandomNode(temp, false, maxLevel,
	 * currentLevel + 1).copyTree(); else chosenNode = remainingSubtree; }
	 * 
	 * 
	 * return chosenNode; }
	 */

	public static int countTreeDepth(TreeNode node) {
		
		if (node instanceof Terminal) {
			return 1;
		}
		if (node instanceof Function) {
			int leftChild = countTreeDepth(node.getChildren().get(0));
			int rightChild = countTreeDepth(node.getChildren().get(1));
			return (leftChild > rightChild) ? leftChild + 1 : rightChild + 1;
		}
		return 0;
	}

	public double calculateRawFitness() {
		double sum = 0;
		for (int i = 0; i < cartesian.getBoard().size(); i++) {
			double residual = cartesian.getBoard().get(i).getY()
					- this.schema.getValue(cartesian.getBoard().get(i).getX());
			sum += residual * residual;
		}
		return sum;
	}

	public double calculateAdjustedFitness() {
		double denominator = 1 + this.calculateRawFitness();
		return 1 / denominator;
	}

	public double getFitness() {
		if (fitness == 0) {
			fitness = this.calculateAdjustedFitness();
		}
		return fitness;
	}

	public TreeNode getSchema() {
		return schema;
	}

	public void setSchema(TreeNode schema) {
		this.schema = schema;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Cartesian getCartesian() {
		return cartesian;
	}

	public void setCartesian(Cartesian cartesian) {
		this.cartesian = cartesian;
	}

}
