package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.GPParameters;
import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Genetics {

	public static Population evolve(Population population) {
		Population evolvedPopulation = new Population();

		// Reproduction
		evolvedPopulation.saveChromosomeAt(0, population.getFittest());

		// Crossover
		for (int i = 1; i < GPParameters.POPULATION_SIZE; i++) {

			// Select parents
			Chromosome father = selectIndividual(population);
			Chromosome mother = selectIndividual(population);

			Chromosome child = crossover(father, mother);

			evolvedPopulation.saveChromosomeAt(i, child);
		}

		// Mutation
		/*
		 * for (Chromosome chromosome : evolvedPopulation.getPopulation()) { if
		 * (Math.random() < Parameters.MUTATION_RATE) { chromosome =
		 * mutate(chromosome); } }
		 */

		return evolvedPopulation;
	}

	// Tournament Selection
	public static Chromosome selectIndividual(Population population) {

		Population tournament = new Population();
		for (int i = 0; i < GPParameters.TOURNAMENT_SIZE; i++) {
			int index = (int) (Math.random() * GPParameters.POPULATION_SIZE);
			tournament.saveChromosomeAt(i, population.getChromosomeAt(index));
		}

		Chromosome bestIndividual = tournament.getFittest();
		return bestIndividual;
	}

	// Subtree Crossover
	public static Chromosome crossover(Chromosome father, Chromosome mother) {
		TreeNode child = father.getSchema();
		TreeGraphView.displayTreeGraph(child, "Init Child");

		TreeNode insertionPoint = father.chooseRandomNode(child, true, 0, 0);
		TreeNode temp = insertionPoint.copyTree();
		TreeGraphView.displayTreeGraph(insertionPoint, "Insertion Point");

		TreeNode motherSubTree = mother.chooseRandomNode(mother.getSchema(), true, 0, 0);
		TreeGraphView.displayTreeGraph(motherSubTree, "Mother SubTree");

		//insertionPoint = motherSubTree.copyTree();
		TreeGraphView.displayTreeGraph(insertionPoint, "InsertionPoint after change");

		temp = search(child, insertionPoint);
		if (temp.getParent() != null) {
			for (int i = 0; i < temp.getChildren().size(); i++) {
				if (temp.getChildren().get(i) != null && temp.getChildren().get(i).equals(temp))
					temp.getParent().getChildren().set(i, motherSubTree.copyTree());
			}

			TreeGraphView.displayTreeGraph(child, "Changed child");
			Chromosome offspring = new Chromosome();
			offspring.copyIndividual(child);

			return offspring;

		} else {

			TreeGraphView.displayTreeGraph(child, "Changed child");
			Chromosome offspring = new Chromosome();
			offspring.copyIndividual(insertionPoint);

			return offspring;
		}
	}

	// TODO
	// Subtree Mutation
	public static Chromosome mutate(Chromosome chromosome) {
		return new Chromosome();
	}

	// Node Search
	private static TreeNode search(TreeNode base, TreeNode target) {

		Comparable<Data> searchCriteria = new Comparable<Data>() {
			@Override
			public int compareTo(Data data) {
				if (data == null)
					return 1;
				boolean nodeOk = data.equals(target.getData());
				return nodeOk ? 0 : 1;
			}
		};

		TreeNode found = base.findTreeNode(searchCriteria);
		return found;
	}

}
