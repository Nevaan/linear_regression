package genetics;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.xml.sax.SAXException;

import graphics.graphs.TreeGraphView;
import regression.Parameters;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class Genetics {

	public Population evolve(final Population population) {
		Population evolvedPopulation = new Population();

		// Crossover
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++) {

			// Select parents
			TreeNode father = selectIndividual(population);
			TreeNode mother = selectIndividual(population);

			try {
				TreeNode child = crossover(Parameters.CURRENT_GENERATION_ID - 1, father.getFileChromosomeID(), mother.getFileChromosomeID());
				evolvedPopulation.saveChromosomeAt(i, child);
			} catch (Exception e) {
				System.out.println("Error while performing crossover.");
				e.printStackTrace();
			}
		}
		return evolvedPopulation;
	}



	// Tournament Selection
	public static TreeNode selectIndividual(Population population) {
		Population tournament = new Population();
		Random random = new Random();
		for (int i = 0; i < Parameters.TOURNAMENT_SIZE; i++) {
			int index = random.nextInt(Parameters.POPULATION_SIZE);
			tournament.saveChromosomeAt(i, population.getChromosomeAt(index));
		}

		TreeNode bestIndividual = tournament.getFittest();
		return bestIndividual;
	}


	//Dzia³a w chuj !!
	public static TreeNode findChild(int id, TreeNode root) {
		if (root.getId() == id) {
			return root;
		}
		TreeNode result = null;
		for(int i = 0; result == null && i < root.children.size(); i++) {
			result = findChild(id, root.children.get(i));
		}
		return result;
	}
	/*
	public void crossover(int fatherGeneration, int fatherChromosome, int motherGeneration, int motherChromosome) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
		// factories and builders
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		DocumentBuilder builder;
		builder = docFactory.newDocumentBuilder();
		File currentDir = new File(".");
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		QueryXML queryXML = new QueryXML();
		Random random = new Random();

		// loading father tree
		Document fatherDoc = null;
		fatherDoc = builder.parse(currentDir + "/xml/" + "Generation" + fatherGeneration + "Chromosome" + fatherChromosome + ".xml");
		XPathExpression fatherExpr = null;

		// loading mother tree
		Document motherDoc = null;
		motherDoc = builder.parse(currentDir + "/xml/" + "Generation" + motherGeneration + "Chromosome" + motherChromosome + ".xml");
		XPathExpression motherExpr = null;

		// choose random insertion Point (from fatherTree)
		int fatherNodesAmount = queryXML.countNodes(fatherGeneration, fatherChromosome);
		int insertionPointId = random.nextInt(fatherNodesAmount);

		// choose random subtree (from motherTree)
		int motherNodesAmount = queryXML.countNodes(motherGeneration, motherChromosome);
		int motherSubtreeId = random.nextInt(motherNodesAmount);

		// finding chosen insertionPoint in father tree
		fatherExpr = xpath.compile("//*[@id='" + insertionPointId + "']");
		NodeList fatherNodes = (NodeList) fatherExpr.evaluate(fatherDoc, XPathConstants.NODESET);

		// finding chosen subtree in mother tree
		motherExpr = xpath.compile("//*[@id='" + motherSubtreeId + "']");
		NodeList motherNodes = (NodeList) motherExpr.evaluate(motherDoc, XPathConstants.NODESET);

		// magic
		Node insertedSubtree = fatherDoc.importNode(motherNodes.item(0), true);
		if(fatherNodes.item(0).getParentNode() != null)
			fatherNodes.item(0).getParentNode().replaceChild(insertedSubtree, fatherNodes.item(0));
		else
			fatherDoc = motherNodes.item(0).getOwnerDocument();

		// updating document

		DOMSource source = new DOMSource(fatherDoc);
		StreamResult sresult = new StreamResult(new File(currentDir + "/xml/replaced.xml"));
		transformer.transform(source, sresult);

	}
	*/

	/* ------------------------------------- Podejscie javowe ------------------------------------- */

	/*
	 * 	ta metodka jest ju¿ na gotowo: pobiera trzy id: id generacji, id chromosomu - matki, id chromosomu - ojca,
	 *  w wyniku jej dzialania powstaje skrzyzowane drzewo zapisane jako plik "Generation" + generacja+1 + "Chromosome" + Params.File_name_id
	 *  przy czym trzeba pilnowac, zeby przy kazdej mutacji - tworzeniu nowej generacji zerowac ten params
	 */
	public TreeNode crossover(int generation, int fatherId, int motherId) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {

		QueryXML query= new QueryXML();
		Random random = new Random();

		TreeNode father = XMLtoClass.convert(generation, fatherId);
		TreeNode mother = XMLtoClass.convert(generation, motherId);


		// Nie jestem pewny co do tego, czy powinno byæ + 1: teoretycznie moze byæ 0 (sam root) i wtedy tutaj nastepuje blad - argument nextInt
		// musi byc pozytywny. Jesli beda problemy to jest potencjalne miejsce, w ktorym moze sie jebac
		int randomFatherNodeNumber = random.nextInt(query.countNodes(generation, fatherId) + 1);
		int randomMotherNodeNumber = random.nextInt(query.countNodes(generation, motherId) + 1);

		TreeNode insertionNode = findChild(randomFatherNodeNumber , father);
		//TreeGraphView.displayTreeGraph(insertionNode, "Insertion Node");
		TreeNode subTree = findChild(randomMotherNodeNumber , mother);
		//TreeGraphView.displayTreeGraph(subTree, "Sub Tree");

		if(insertionNode.getParentId() == -1) {
			ClassToXML.convertCrossovered(subTree, generation + 1);
			return subTree;
		}
		else {
			insertionNode.replace(randomFatherNodeNumber, subTree, father);
		}

		father.setFileChromosomeID(Parameters.CROSSOVERED_FILE_NAME_ID - 1);
		ClassToXML.convertCrossovered(father, generation + 1);
		query.setUniqueIdentifiers(generation + 1, Parameters.CROSSOVERED_FILE_NAME_ID - 1);   // -1 dlatego, ze przy tworzeniu XMLa zaszla inkrementacja stalej
		query.setParentParameters(generation + 1, Parameters.CROSSOVERED_FILE_NAME_ID - 1);	 // wiec aby dostac sie do ostatnio zapisanego pliku - trzeba zdekrementowac
		return father;
	}
}
