package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.GPParameters;
import treeElement.function.Function;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();
		System.out.println("wynik 1(x=3) "+fittest.getValue(3.0d));
		System.out.println("wynik 2(x=7) "+fittest.getValue(7.0));

		/*
		TreeGraphView.displayTreeGraph(population.getChromosomeAt(3).getSchema(), "TEST");
		
		TreeNode chuj = population.getChromosomeAt(3).getSchema();
		int a = 0;
		while(chuj instanceof Function){
			chuj = chuj.chooseRandomNodeNEW(population.getChromosomeAt(3).getSchema(), true, 0, 0);
			System.out.println(chuj.getData().getId() + " nr: " + a++);
		}
		*/
		//TreeNode random = population.getFittest().chooseRandomNode(fittest, true, 0, 0);
		//System.out.println("Random :" + random);
		for(int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
		}

		//System.out.println(population.getFittest().getSchema().printFunction());

	}

}
