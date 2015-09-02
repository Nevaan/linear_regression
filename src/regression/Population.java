package regression;

import java.util.ArrayList;
import java.util.List;

import targetRepresentation.GPParameters;
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

		for (int i = 0; i < GPParameters.POPULATION_SIZE; i++) {
			Chromosome individual = new Chromosome();
			individual.generateIndividual();
			population.add(individual);
		}
	}

	public Chromosome getFittest() {
		Chromosome fittest = population.get(0);

		/*for(int i = 1; i < population.size(); i++) {
			if(population.get(i).getFitness() >= fittest.getFitness()) {
				fittest = population.get(i);
			}
		}
		return fittest;
	}*/
		for (Chromosome individual : population) {
			if (individual.getFitness() < fittest.getFitness()) {
				fittest = individual;
			}
		}

		return fittest;
	}

	public int getPopulationSize() {
		return this.population.size();
	}

	public void saveChromosomeAt(int index, Chromosome chromosome) {
		population.add(index, chromosome);
	}

	public Chromosome getChromosomeAt(int index) {
		return population.get(index);
	}

}
