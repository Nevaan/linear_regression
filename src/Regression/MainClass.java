package Regression;

import graphics.graphs.TreeGraphView;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();
		System.out.println("wynik 1(x=3) "+fittest.getValue(3.0d));
		TreeGraphView.displayTreeGraph(fittest,"Najlepsza populacja");
		System.out.println("wynik 2(x=7) "+fittest.getValue(7.0));

		int a = 0;
		for(Chromosome pop :population.getPopulation()){
			TreeGraphView.displayTreeGraph(pop.getSchema(),"graf nr: "+(++a));
		}
	}

}
