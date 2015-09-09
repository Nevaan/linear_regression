package genetics;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import graphics.graphs.TreeGraphView;
import regression.Parameters;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeElement.terminal.Terminal;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class Genetics {

	public void evolve(int generation){
		List<TreeNode> currentGeneration = new ArrayList<TreeNode>();
		currentGeneration = loadGeneration(generation);
		int i = 0;
		for(TreeNode t : currentGeneration) {
			TreeGraphView.displayTreeGraph(t ,"Generation: " + generation + " Chromosome: " + (i++) );
		}
	}

	private List<TreeNode> loadGeneration(int generationNumber){
		List<TreeNode> generation = new ArrayList<TreeNode>();
		File directory = new File("./xml/");
		FileFilter fileFilter = new WildcardFileFilter("Generation" + generationNumber +"Chromosome*.xml");
		File[] files = directory.listFiles(fileFilter);
		for (int i=0; i<files.length; i++){
			generation.add(XMLtoClass.convert(i, generationNumber));
		}
		return generation;
	}


	//Dzia�a w chuj !!
	public static TreeNode findChild(int id, TreeNode root) {
		if (root.getId() == id) {
			return root;
		}
		TreeNode result = null;
		for(int i = 0; result==null && i< root.children.size(); i++) {
			result = findChild( id, root.children.get(i));
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
	 * 	ta metodka jest ju� na gotowo: pobiera trzy id: id generacji, id chromosomu - matki, id chromosomu - ojca,
	 *  w wyniku jej dzialania powstaje skrzyzowane drzewo zapisane jako plik "Generation" + generacja+1 + "Chromosome" + Params.File_name_id
	 *  przy czym trzeba pilnowac, zeby przy kazdej mutacji - tworzeniu nowej generacji zerowac ten params
	 */
	public void crossover(int generation, int fatherId, int motherId) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
		
		QueryXML query= new QueryXML();
		Random random = new Random();
		
		TreeNode father = XMLtoClass.convert(generation, fatherId);
		TreeNode mother = XMLtoClass.convert(generation, motherId);
		
		
		// Nie jestem pewny co do tego, czy powinno by� + 1: teoretycznie moze by� 0 (sam root) i wtedy tutaj nastepuje blad - argument nextInt 
		// musi byc pozytywny. Jesli beda problemy to jest potencjalne miejsce, w ktorym moze sie jebac
		int randomFatherNodeNumber = random.nextInt(query.countNodes(generation, fatherId) + 1);
		int randomMotherNodeNumber = random.nextInt(query.countNodes(generation, motherId) + 1);
		
		TreeNode insertionNode = findChild(randomFatherNodeNumber , father);
		TreeNode subTree = findChild(randomMotherNodeNumber , mother);
		
		if(insertionNode.getParentId() == -1) {
			ClassToXML.convert(subTree, generation+1);
			return;
		}
		else {
			insertionNode.replace(randomFatherNodeNumber, subTree, father);
		}
		
		ClassToXML.convert(father, generation+1);
		query.setUniqueIdentifiers(generation+1, Parameters.FILE_NAME_ID - 1);   // -1 dlatego, ze przy tworzeniu XMLa zaszla inkrementacja stalej
		query.setParentParameters(generation+1, Parameters.FILE_NAME_ID - 1);	 // wiec aby dostac sie do ostatnio zapisanego pliku - trzeba zdekrementowac
	}
}
