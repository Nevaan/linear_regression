package treeRepresentation;

import java.io.File;
import java.io.IOException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class QueryXML {

	public int countNodes(int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		File currentDir = new File(".");
		Document document = builder.parse(currentDir + "/xml/" + "Generation" +generation + "Chromosome"+ chromosome +".xml");
		NodeList nodeList = document.getElementsByTagName("children");
		return nodeList.getLength();
	}

	public void setUniqueIdentifiers(int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {

		File currentDir = new File(".");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(currentDir + "/xml/" + "Generation" +generation + "Chromosome"+ chromosome +".xml");
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//*[@id]");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {
			Node attribute = nodes.item(i).getAttributes().getNamedItem("id");
			attribute.setTextContent(Integer.toString(i));
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(currentDir + "/xml/" + "Generation" +generation + "Chromosome"+ chromosome +".xml"));
		transformer.transform(source, result);
	}

	public void setParentParameters(int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {

		// standard opening and parsing xml to DOM object
		File currentDir = new File(".");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(currentDir + "/xml/" + "Generation" + generation + "Chromosome" + chromosome + ".xml");

		// magic time
		NodeList nodeList = doc.getElementsByTagName("*");
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
			((Element)nodeList.item(0)).setAttribute("parentId", "-1");
		for (int i = 1; i < nodeList.getLength(); i++) {
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				((Element)nodeList.item(i)).setAttribute("parentId", String.valueOf(nodeList.item(i).getParentNode().getAttributes().getNamedItem("id").getNodeValue()));
			}
		}

		// updating document
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult sresult = new StreamResult(new File(currentDir + "/xml/" + "Generation" + generation + "Chromosome" + chromosome + ".xml"));
		transformer.transform(source, sresult);
	}

	public void replaceSubtree(int fatherGeneration, int fatherChromosome, int motherSubtreeChromosome, int insertionPointId, int subtreeId) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
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

		// loading father tree
		Document fatherDoc = null;
		fatherDoc = builder.parse(currentDir + "/xml/" + "Generation" + fatherGeneration + "Chromosome"+ fatherChromosome + ".xml");
		XPathExpression fatherExpr = null;

		// loading mother subtree
		Document motherSubtreeDoc = null;
		motherSubtreeDoc = builder.parse(currentDir + "/xml/" + "RandomSubtree" + motherSubtreeChromosome + ".xml");
		XPathExpression motherSubtreeExpr = null;

		// finding insertionPoint in father tree
		fatherExpr = xpath.compile("//*[@id='" + insertionPointId + "']");
		NodeList nodes = (NodeList) fatherExpr.evaluate(fatherDoc, XPathConstants.NODESET);

		// magic
		Node insertedSubtree = fatherDoc.importNode(motherSubtreeDoc.getFirstChild(), true);
		if(nodes.item(0).getParentNode() != null)
			nodes.item(0).getParentNode().replaceChild(insertedSubtree, nodes.item(0));
		else
			fatherDoc = motherSubtreeDoc;

		// updating document
		DOMSource source = new DOMSource(fatherDoc);
		StreamResult sresult = new StreamResult(new File(currentDir + "/xml/replaced.xml"));
		transformer.transform(source, sresult);

	}

}
