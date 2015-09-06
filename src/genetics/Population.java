package genetics;

import regression.Parameters;
import treeRepresentation.ClassToXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;

public class Population {

	public void initialize(int generation){
		for(int i=0; i<Parameters.POPULATION_SIZE; i++) {
			TreeNode singleChromosome = null;
			try {
				singleChromosome = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
			} catch (Exception e) {
				System.out.println("ERROR: podczas generowania drzewa.");
				e.printStackTrace();
			}
			
			ClassToXML.convert(singleChromosome,generation);
		}
	}
	
}
