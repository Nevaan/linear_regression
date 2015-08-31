package Regression;

import java.util.Random;

import targetRepresentation.Cartesian;
import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class Chromosome {

	private TreeNode schema;
	private double fitness;
	private Cartesian cartesian;
	private int treeHeight;

	public Chromosome() {
		cartesian = new Cartesian();
	}

	public void generateIndividual() {
		cartesian.init();
		try {
			schema = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
			TreeGenerator.CAN_CHOOSE_TERMINAL = false;
			this.treeHeight = this.calculateTreeHeight();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TreeNode chooseRandomNode(TreeNode remainingSubtree, boolean isInitial, int chosenMaxLevel, int currentLevel) {
		int maxLevel = 0;
		TreeNode chosenNode = null;
		if(isInitial) {
			this.treeHeight = calculateTreeHeight();
			Random random = new Random();
			maxLevel = random.nextInt(treeHeight);
		} else {
			maxLevel = chosenMaxLevel;
		}

		if(currentLevel < maxLevel) {
			TreeNode temp = null;
			for(int i = 0; i < remainingSubtree.getChildren().size(); i++) {
				temp = chooseRandomNode(remainingSubtree.getChildren().get(i), false, maxLevel, currentLevel++);
			}
			if (temp != null) {
				chosenNode = temp;
			}
		}

		return chosenNode;
	}
	public int calculateTreeHeight() {
		int currentHeight = 0;
		for(TreeNode element : this.schema) {
			currentHeight = Math.max(currentHeight, element.getLevel());
		}
		return currentHeight;
	}

	public double calculateRawFitness() {
		double sum = 0;
		for(int i = 0; i < cartesian.getBoard().size(); i++) {
			double residual = cartesian.getBoard().get(i).getY() - this.schema.getValue(cartesian.getBoard().get(i).getX());
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

	public int getTreeHeight() {
		return treeHeight;
	}

	public void setTreeHeight(int treeHeight) {
		this.treeHeight = treeHeight;
	}
}
