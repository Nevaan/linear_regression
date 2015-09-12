package genetics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import graphics.graphs.TreeGraphView;
import regression.Parameters;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class Genetics {

	public Population evolve(final Population population) {
		Population evolvedPopulation = new Population();
		QueryXML query = new QueryXML();

		// Reproduction
		TreeNode fittestFromPreviousPop = population.getFittest();
		ClassToXML.convert(fittestFromPreviousPop, Parameters.CURRENT_GENERATION_ID);
		evolvedPopulation.saveChromosomeAt(0, fittestFromPreviousPop);
		Parameters.CURRENT_CHROMOSOME_ID++;
		try {
			query.setUniqueIdentifiers(Parameters.CURRENT_GENERATION_ID, 0);
			query.setParentParameters(Parameters.CURRENT_GENERATION_ID, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Crossover
		for (int i = 1; i < Parameters.POPULATION_SIZE; i++) {

			// Select parents
			int fatherChromosomeId = selectIndividual(population);
			int motherChromosomeId = selectIndividual(population);

			try {
				TreeNode child = crossover(Parameters.CURRENT_GENERATION_ID - 1, fatherChromosomeId,
						motherChromosomeId);
				Parameters.CURRENT_CHROMOSOME_ID++;
				evolvedPopulation.saveChromosomeAt(i, child);
			} catch (Exception e) {
				System.out.println("Error while performing crossover.");
				e.printStackTrace();
			}
		}

		// Mutation
		Random random = new Random();
		for (int i = 1; i < Parameters.POPULATION_SIZE; i++) {
			if (random.nextInt(100) < Parameters.MUTATION_RATE)
				try {
					mutate(Parameters.CURRENT_GENERATION_ID, i);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		SampleData.dataF.clear();
		for (int i = 0; i < evolvedPopulation.getPopulationSize(); i++) {
			for (int k = 0; k < SampleData.dataX.size(); k++)
				SampleData.dataF.add(evolvedPopulation.getChromosomeAt(i).getValue(k));
		}
		return evolvedPopulation;
	}

	// Tournament Selection
	public int selectIndividual(Population population) {
		Population tournament = new Population();
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		Map.Entry<Integer, Double> maxEntry = null;
		Random random = new Random();
		int index = -100;

		for (int i = 0; i < Parameters.TOURNAMENT_SIZE; i++) {
			index = random.nextInt(Parameters.POPULATION_SIZE);
			tournament.saveChromosomeAt(i, population.getChromosomeAt(index));
			map.put(i, population.getChromosomeAt(index).getAdjustedFitness());
		}

		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		return maxEntry.getKey().intValue();

	}

	public static TreeNode findChild(int id, TreeNode root) {
		if (root.getId() == id) {
			return root;
		}
		TreeNode result = null;
		for (int i = 0; result == null && i < root.children.size(); i++) {
			result = findChild(id, root.children.get(i));
		}
		return result;
	}

	public TreeNode crossover(int generation, int fatherId, int motherId) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException, TransformerException {

		QueryXML query = new QueryXML();
		Random random = new Random();

		TreeNode father = XMLtoClass.convert(generation, fatherId);
		TreeNode mother = XMLtoClass.convert(generation, motherId);

		int randomFatherNodeNumber = random.nextInt(query.countNodes(generation, fatherId) + 1);
		int randomMotherNodeNumber = random.nextInt(query.countNodes(generation, motherId) + 1);

		TreeNode insertionNode = findChild(randomFatherNodeNumber, father);
		TreeNode subTree = findChild(randomMotherNodeNumber, mother);

		if (insertionNode.getParentId() == -1) {
			ClassToXML.convert(subTree, generation + 1);
			query.setUniqueIdentifiers(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
			query.setParentParameters(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
			return subTree;
		} else {
			insertionNode.replace(randomFatherNodeNumber, subTree, father);

		}

		ClassToXML.convert(father, generation + 1);
		query.setUniqueIdentifiers(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
		query.setParentParameters(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
		return father;
	}

	public TreeNode mutate(int generation, int chromosomeId) throws Exception {
		QueryXML query = new QueryXML();
		Random random = new Random();

		TreeNode chromosome = XMLtoClass.convert(generation, chromosomeId);
		int randomNodeNumber = random.nextInt(query.countNodes(generation, chromosomeId) + 1);

		TreeNode insertionNode = findChild(randomNodeNumber, chromosome);

		TreeNode randomSubtree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);

		if (insertionNode.getParentId() == -1) {
			ClassToXML.convert(randomSubtree, generation + 1);
			query.setUniqueIdentifiers(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
			query.setParentParameters(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
			return randomSubtree;
		} else {
			insertionNode.replace(randomNodeNumber, randomSubtree, chromosome);
		}
		ClassToXML.convert(chromosome, generation + 1);
		query.setUniqueIdentifiers(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
		query.setParentParameters(generation + 1, Parameters.CURRENT_CHROMOSOME_ID);
		return chromosome;
	}

}
