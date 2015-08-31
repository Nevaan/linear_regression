package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.Parameters;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();
		System.out.println("wynik 1(x=3) "+fittest.getValue(3.0d));
		System.out.println("wynik 2(x=7) "+fittest.getValue(7.0));

		System.out.println("Random: " + population.getFittest().chooseRandomNode(population.getFittest().getSchema(), true, 0, 0));


		for(int i = 0; i < Parameters.POPULATION_SIZE; i++) {
			population = Genetics.evolve(population);
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
		}

		System.out.println(population.getFittest().getSchema().printFunction());

	}

}
