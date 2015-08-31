package regression;

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
	private int treeHeight;

	public Chromosome() {
		cartesian = new Cartesian();
	}

	public void generateIndividual() {
		cartesian.init();
		try {
			schema = TreeGenerator.generateGrowTree(GPParameters.GROW_TREE_MAX_DEPTH);
			TreeGenerator.CAN_CHOOSE_TERMINAL = false;
			this.treeHeight = this.countTreeDepth(this.getSchema());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyIndividual(TreeNode schema) {
		cartesian.init();
		try {
			this.schema = schema;
			this.treeHeight = this.countTreeDepth(this.getSchema());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TreeNode chooseRandomNode(TreeNode remainingSubtree, boolean isInitial, int chosenMaxLevel,
			int currentLevel) {
		int maxLevel = 0;
		TreeNode chosenNode = remainingSubtree.getParent();
		if (isInitial) {
			// if method was called on tree with single node
			if (remainingSubtree instanceof Terminal)
				return remainingSubtree;
			this.treeHeight = countTreeDepth(this.getSchema());
			Random random = new Random();
			maxLevel = random.nextInt(treeHeight) + 1;
		} else {
			maxLevel = chosenMaxLevel;
		}

		if (currentLevel < maxLevel) {
			TreeNode temp = remainingSubtree.chooseRandomChild();
			if (temp instanceof Function)
				chosenNode = chooseRandomNode(temp, false, maxLevel, currentLevel + 1);
			else
				chosenNode = temp;
		}

		return chosenNode;
	}

	public int countTreeDepth(TreeNode node) {
		if (node.equals(null)) {
			return 0;
		}
		if (!node.getChildren().isEmpty()) {
			int leftChild = countTreeDepth(node.getChildren().get(0));
			int rightChild = countTreeDepth(node.getChildren().get(1));
			return (leftChild > rightChild) ? leftChild + 1 : rightChild + 1;
		}
		return 1;
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

	public int getTreeHeight() {
		return treeHeight;
	}

	public void setTreeHeight(int treeHeight) {
		this.treeHeight = treeHeight;
	}
}
