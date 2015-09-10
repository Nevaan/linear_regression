package genetics;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import regression.Parameters;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class Population {

	private List<TreeNode> population;

	public Population() {
		population = new ArrayList<TreeNode>();
	}

	// should be used only once for 0 generation
	public void initialize() {
		population = new ArrayList<TreeNode>();
		QueryXML query = new QueryXML();

		for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
			TreeNode singleChromosome = null;

			try {

				singleChromosome = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);

				ClassToXML.convert(singleChromosome, Parameters.CURRENT_GENERATION_ID);
				query.setUniqueIdentifiers(0, i);
				query.setParentParameters(0, i);
				population.add(XMLtoClass.convert(0, i));

			} catch (Exception e) {
				System.out.println("ERROR while initializing population (Tree Generation).");
				e.printStackTrace();
			}
		}

		Parameters.FILE_NAME_ID = 0;
	}

	public void loadGeneration() {
		File directory = new File("./xml/");
		FileFilter fileFilter = new WildcardFileFilter(
				"Generation" + Parameters.CURRENT_GENERATION_ID + "Chromosome*.xml");
		File[] files = directory.listFiles(fileFilter);

		for (int i = 0; i < files.length; i++) {
			population.add(XMLtoClass.convert(i, Parameters.CURRENT_GENERATION_ID));
		}
	}

	public TreeNode getFittest() {

		TreeNode fittest = population.get(0);

		for (TreeNode individual : population) {
			if (individual.getAdjustedFitness() >= fittest.getAdjustedFitness()) {
				fittest = individual;
			}
		}

		return fittest;
	}

	public int getPopulationSize() {
		return this.population.size();
	}

	public void saveChromosomeAt(int index, TreeNode chromosome) {
		population.add(index, chromosome);
	}

	public TreeNode getChromosomeAt(int index) {
		return population.get(index);
	}

	public List<TreeNode> getPopulation() {
		return population;
	}
}
