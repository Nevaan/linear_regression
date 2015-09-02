package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.GPParameters;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();

		//TreeGraphView.displayTreeGraph(fittest, "TEST");
		//TreeNode random = population.getFittest().chooseRandomNode(fittest, true, 0, 0);
		//System.out.println("Random :" + random);
		for(int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
			System.out.println("Fittest fitness: " + population.getFittest().getFitness());
			//TreeGraphView.displayTreeGraph(population.getFittest().getSchema(), "Fittest");
			System.out.println(population.getFittest().getSchema().printFunction());
		}

		//System.out.println(population.getFittest().getSchema().printFunction());

	}

}
