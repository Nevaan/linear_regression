package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import graphics.graphs.TreeGraphView;
import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class MainClass {

	public static void main(String[] args) {

		// Odkomentowaæ przy pierwszym odpaleniu - wrzuca do katalogu xml pierwsza populacje; potem mozna zakomentowac i zostawic to co jest nizej zeby zobaczyc,
		// ze poprawnie wczytuje sie ta populacja

		//directoryCleanUp();
		//Population population = new Population();
		//population.initialize(0);


		// Odkomentowac po min. jednokrotnym odpaleniu powyzszego - inaczej wywala error bo brakuje xmli

		/*for (int i = 0; i<Parameters.POPULATION_SIZE; i++){
			TreeNode loadedChromosome = XMLtoClass.convert(i,0);
			TreeGraphView.displayTreeGraph(loadedChromosome , "Test ");
		}


		Genetics g = new Genetics();
		g.evolve(1);*/



		//**************************************************



		/*TreeNode testTree = null;
		TreeNode testTree2 = null;

		try {
			testTree = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
			testTree2 = TreeGenerator.generateGrowTree(Parameters.GROW_TREE_MAX_DEPTH);
		} catch (Exception e) {
			System.out.println("Error while generating test tree.");
			e.printStackTrace();
		}

		TreeGraphView.displayTreeGraph(testTree, "Test tree");
		TreeGraphView.displayTreeGraph(testTree2, "Test tree");

		ClassToXML.convert(testTree, 0);
		ClassToXML.convert(testTree2, 0);*/


		//**************************************************

		QueryXML process = new QueryXML();
		try {
<<<<<<< HEAD
			process.query(6);
			TreeNode treeNode = XMLtoClass.convert(0, 0);
			TreeNode treeNode2 = XMLtoClass.convert(1, 0);
=======
			process.query(21,0,1);
			TreeNode treeNode = XMLtoClass.convert(0, 1);
>>>>>>> beaec2bb9c01aa8d55951ad4a95a1e8aa7ed9fa6
			TreeNode subtree = XMLtoClass.getSubtree();
			TreeNode lolol = XMLtoClass.getLolol();

			TreeGraphView.displayTreeGraph(treeNode, "lol");
<<<<<<< HEAD
			TreeGraphView.displayTreeGraph(treeNode2, "tez lol");
			TreeGraphView.displayTreeGraph(subtree, "subTree");
			TreeGraphView.displayTreeGraph(lolol, "lolol");
=======
			TreeGraphView.displayTreeGraph(subtree, "subtree");
>>>>>>> beaec2bb9c01aa8d55951ad4a95a1e8aa7ed9fa6

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void directoryCleanUp(){
		if(!Files.exists(Paths.get("./xml/"))){
			new File("./xml/").mkdir();
		}

		File folder = new File("./xml/");
		File[] files = folder.listFiles();
		if(files!=null){
			for(File f:files){
				f.delete();
			}
		}
	}
}
