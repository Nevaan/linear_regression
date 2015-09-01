package regression;

import java.util.Random;

import graphics.graphs.TreeGraphView;
import targetRepresentation.GPParameters;
import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class Genetics {

	public static Population evolve(final Population population) {
		Population evolvedPopulation = new Population();

		// Reproduction
		evolvedPopulation.saveChromosomeAt(0, population.getFittest());

		// Crossover
		for (int i = 1; i < GPParameters.POPULATION_SIZE; i++) {

			// Select parents
			Chromosome father = selectIndividual(population);
			Chromosome mother = selectIndividual(population);

			Chromosome child = crossover3(father, mother);

			evolvedPopulation.saveChromosomeAt(i, child);
		}

		return evolvedPopulation;
	}

	// Tournament Selection
	public static Chromosome selectIndividual(Population population) {

		Population tournament = new Population();
		for (int i = 0; i < GPParameters.TOURNAMENT_SIZE; i++) {
			int index = (int) (Math.random() * GPParameters.POPULATION_SIZE);
			tournament.saveChromosomeAt(i, population.getChromosomeAt(index));
		}

		Chromosome bestIndividual = tournament.getFittest();
		return bestIndividual;
	}

	public static Chromosome crossover3(Chromosome father, Chromosome mother) {
		Random r = new Random();
		TreeNode child = father.getSchema();
		TreeNode m = mother.getSchema();
		//TreeGraphView.displayTreeGraph(child, "Init Child");
		//TreeGraphView.displayTreeGraph(m, "Mother");
		//child.chooseRandomNodeMINE(child).replaceChild(r.nextInt(2),m);
		child.chooseRandomNodeNEW(child,true,0,0).replaceChild(r.nextInt(2),m);
		//TreeGraphView.displayTreeGraph(child, "Swapped");

		return new Chromosome(child);
	}

	
	
	// Subtree Crossover
	/*
	 * public static Chromosome crossover2(Chromosome father, Chromosome mother)
	 * { TreeNode f = father.getSchema().copyTree(); TreeNode m =
	 * mother.getSchema().copyTree(); TreeGraphView.displayTreeGraph(f,
	 * "Init Child"); int id = 0;
	 * 
	 * for (TreeNode node : f) { node.getData().setId(id++); }
	 * 
	 * for (TreeNode node2 : m) { node2.getData().setId(id++); }
	 * 
	 * TreeNode insertionPoint = father.chooseRandomNode(f, true, 0, 0);
	 * 
	 * insertionPoint.getData().setId(f.getData().getId());
	 * 
	 * TreeNode temp = insertionPoint.copyTree();
	 * temp.getData().setId(insertionPoint.getData().getId());
	 * TreeGraphView.displayTreeGraph(insertionPoint, "Insertion Point");
	 * 
	 * TreeNode motherSubTree = mother.chooseRandomNode(m, true, 0, 0);
	 * TreeGraphView.displayTreeGraph(motherSubTree, "Mother SubTree");
	 * 
	 * for (int i = 0; i < motherSubTree.getChildren().size(); i++) {
	 * insertionPoint.getChildren().add(motherSubTree.getChildren().get(i)); }
	 * 
	 * TreeNode temp2 = temp.copyTree();
	 * temp2.getData().setId(temp.getData().getId());
	 * 
	 * if(insertionPoint.getParent() == null) temp = search(f, temp); else
	 * temp.setParent(insertionPoint.getParent());
	 * 
	 * if (temp != null) { if (temp.getParent() != null) { for (int i = 0; i <
	 * temp.getParent().getChildren().size(); i++) { if
	 * (temp.getParent().getChildren().get(i) != null &&
	 * temp.getParent().getChildren().get(i).equals(temp))
	 * temp.getParent().getChildren().set(i, motherSubTree); }
	 * TreeGraphView.displayTreeGraph(temp, "Changed child (normal)");
	 * Chromosome offspring = new Chromosome(); offspring.copyIndividual(temp);
	 * 
	 * return offspring; } else {
	 * //TreeGraphView.displayTreeGraph(motherSubTree,
	 * "Changed child (null parent)"); Chromosome offspring = new Chromosome();
	 * offspring.copyIndividual(motherSubTree);
	 * 
	 * return offspring; } } else { // to nie powinno nigdy zajsc
	 * TreeGraphView.displayTreeGraph(temp2, "Changed child (null)"); Chromosome
	 * offspring = new Chromosome(); offspring.copyIndividual(temp2);
	 * 
	 * return offspring; }
	 * 
	 * 
	 * }
	 * 
	 * // Subtree Crossover public static Chromosome crossover(Chromosome
	 * father, Chromosome mother) { TreeNode child =
	 * (father.getSchema()).copyTree(); //TreeGraphView.displayTreeGraph(child,
	 * "Init Child");
	 * 
	 * TreeNode insertionPoint = father.chooseRandomNode(child, true, 0, 0);
	 * TreeNode temp = insertionPoint;
	 * //TreeGraphView.displayTreeGraph(insertionPoint, "Insertion Point");
	 * 
	 * TreeNode motherSubTree = mother.chooseRandomNode(mother.getSchema(),
	 * true, 0, 0); //TreeGraphView.displayTreeGraph(motherSubTree,
	 * "Mother SubTree");
	 * 
	 * //insertionPoint = motherSubTree.copyTree(); for (int i = 0; i <
	 * motherSubTree.getChildren().size(); i++) {
	 * insertionPoint.getChildren().add(motherSubTree.getChildren().get(i)); }
	 * //insertionPoint.setParent(temp.getParent());
	 * 
	 * //TreeGraphView.displayTreeGraph(insertionPoint,
	 * "InsertionPoint after change");
	 * 
	 * TreeNode temp2 = temp.copyTree(); temp = search(child, temp);
	 * 
	 * if (temp != null) { if (temp.getParent() != null) { for (int i = 0; i <
	 * temp.getParent().getChildren().size(); i++) { if
	 * (temp.getParent().getChildren().get(i) != null &&
	 * temp.getParent().getChildren().get(i).equals(temp))
	 * temp.getParent().getChildren().set(i, motherSubTree); }
	 * 
	 * TreeGraphView.displayTreeGraph(child, "Changed child (normal)");
	 * Chromosome offspring = new Chromosome(); offspring.copyIndividual(child);
	 * 
	 * return offspring; } else { //
	 * TreeGraphView.displayTreeGraph(motherSubTree,
	 * "Changed child (null parent)"); Chromosome offspring = new Chromosome();
	 * offspring.copyIndividual(motherSubTree);
	 * 
	 * return offspring; } } else { // to nie powinno nigdy zajsc
	 * //TreeGraphView.displayTreeGraph(temp2, "Changed child (null)");
	 * Chromosome offspring = new Chromosome(); offspring.copyIndividual(temp2);
	 * 
	 * return offspring; } }
	 */

	// TODO
	// Subtree Mutation
	public static Chromosome mutate(Chromosome chromosome) {
		return new Chromosome();
	}

	private static TreeNode search(TreeNode base, final TreeNode target) {

		Comparable<Data> searchCriteria = new Comparable<Data>() {
			@Override
			public int compareTo(Data data) {
				if (data == null)
					return 1;
				boolean nodeOk = false;
				if (data.getId() == target.getData().getId())
					nodeOk = true;
				return nodeOk ? 0 : 1;
			}
		};

		TreeNode found = base.findTreeNode(searchCriteria);
		return found;
	}

	// Node Search
	/*
	 * private static TreeNode search(TreeNode base, final TreeNode target) {
	 * 
	 * Comparable<Data> searchCriteria = new Comparable<Data>() {
	 * 
	 * @Override public int compareTo(Data data) { if (data == null) return 1;
	 * boolean nodeOk = data.equals(target.getData()); return nodeOk ? 0 : 1; }
	 * };
	 * 
	 * 
	 * TreeNode found = base.findTreeNode(searchCriteria);
	 * 
	 * return found; }
	 * 
	 * /* private static TreeNode search2(TreeNode mainTree, TreeNode target) {
	 * if (mainTree != null) { if (mainTree.equals(target)) { return mainTree; }
	 * else { if (!mainTree.getChildren().isEmpty()) { TreeNode foundNode =
	 * search2(mainTree.getChildren().get(0), target); if (foundNode == null) {
	 * foundNode = search2(mainTree.getChildren().get(1), target); } return
	 * foundNode; } else { return null; } } } else { return null; } }
	 */

}
