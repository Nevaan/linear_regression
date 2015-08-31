package Regression;

import java.util.ArrayList;
import java.util.List;

import targetRepresentation.Parameters;
import treeRepresentation.TreeGenerator;

public class Population {

	private List<Chromosome> population;

	public List<Chromosome> getPopulation() {
		return population;
	}

	public void setPopulation(List<Chromosome> population) {
		this.population = population;
	}

	public Population() {
		population = new ArrayList<Chromosome>();
	}

	public void initializePopulation() throws Exception {
		population = new ArrayList<Chromosome>();

		for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
			Chromosome individual = new Chromosome();
			individual.setSchema(TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH));
			population.add(individual);
		}
	}

	public Chromosome getFittest() {
		Chromosome fittest = population.get(0);

		for (Chromosome individual : population) {
			if (individual.getFitness() >= fittest.getFitness()) {
				fittest = individual;
			}
		}

		return fittest;
	}

	public int getPopulationSize() {
		return this.population.size();
	}

}
