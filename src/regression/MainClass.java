package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) {

		// Odkomentowa� przy pierwszym odpaleniu - wrzuca do katalogu xml pierwsza populacje; potem mozna zakomentowac i zostawic to co jest nizej zeby zobaczyc,
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

		//TreeGraphView.displayTreeGraph(testTree, "Test tree");
		//TreeGraphView.displayTreeGraph(testTree2, "Test tree");

		ClassToXML.convert(testTree, 0);
		ClassToXML.convert(testTree2, 0);*/


		//**************************************************

		QueryXML process = new QueryXML();
		try {
			process.query();




			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/lol.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TreeNode treeNode = (TreeNode) jaxbUnmarshaller.unmarshal(file);
			System.out.println(treeNode);



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
