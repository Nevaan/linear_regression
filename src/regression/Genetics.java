package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeNode;

public class Genetics {

	public static Population evolve(Population population) {
		Population evolvedPopulation = new Population();

		// Reproduction
		evolvedPopulation.saveChromosomeAt(0, population.getFittest());

		// Crossover
		for (int i = 1; i < Parameters.POPULATION_SIZE; i++) {

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
		for (int i = 0; i < Parameters.TOURNAMENT_SIZE; i++) {
			int index = (int) (Math.random() * Parameters.POPULATION_SIZE);
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
		TreeGraphView.displayTreeGraph(insertionPoint, "Insertion Point");

		TreeNode motherSubTree = mother.chooseRandomNode(mother.getSchema(), true, 0, 0);
		TreeGraphView.displayTreeGraph(motherSubTree, "Mother SubTree");

		insertionPoint = motherSubTree.copyTree();
		TreeGraphView.displayTreeGraph(insertionPoint, "InsertionPoint after change");

		TreeGraphView.displayTreeGraph(child, "Changed child");
		Chromosome offspring = new Chromosome();
		offspring.copyIndividual(child);

		return offspring;
	}

	// TODO
	// Subtree Mutation
	public static Chromosome mutate(Chromosome chromosome) {
		return new Chromosome();
	}

}
