package treeRepresentation;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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

	
	// Liczy tylko dzieci, wg taga "children" dlatego nie zadziala dobrze dla wycietego drzewa - doda jeden.
	// dla zwyklego drzewa, wczytanego prosto z xmla poda liczbe nodow - 1 (bo root), czyli daj¹c IDki od zera mamy ideolo
	public int countNodes(int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException {
		// read file first
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
		XPathExpression expr = xpath.compile("//*[@id]"); // wyciagnij wszystkie nody maj¹ce tag "id"
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
	
	public void getRandomNode(int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException  { 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		XPathExpression expr = null;
		builder = factory.newDocumentBuilder();
		File currentDir = new File(".");
		doc = builder.parse(currentDir + "/xml/" + "Generation" +generation + "Chromosome"+ chromosome +".xml");
		
		int nodeNumber = countNodes(generation, chromosome);
		Random random = new Random();
		int chosenNode = random.nextInt(nodeNumber);	
		
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();	
		expr = xpath.compile("//*[@id='" + chosenNode + "']");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		Document newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = newXmlDocument.getDocumentElement();
		String namespaceURL = "http://www.w3.org/2001/XMLSchema-instance";
	    Element messages = newXmlDocument.createElementNS(namespaceURL, "messages");

		newXmlDocument.appendChild(newXmlDocument.importNode(nodes.item(0), true));
		for (int i = 1; i < nodes.getLength(); i++) {		
			Node node = nodes.item(i);
			Node copyNode = newXmlDocument.importNode(node, true);
			root.appendChild(copyNode);
		}

		DOMImplementationLS domImpl = (DOMImplementationLS) newXmlDocument.getImplementation();
		LSSerializer lsSerializer = domImpl.createLSSerializer();
		String string = lsSerializer.writeToString(newXmlDocument);
		System.out.println(string);
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(newXmlDocument);
			StreamResult sresult = new StreamResult("./xml/RandomSubtree.xml");
			transformer.transform(source, sresult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	public void query(int id, int generation, int chromosome) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		// read file first
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		XPathExpression expr = null;
		builder = factory.newDocumentBuilder();
		File currentDir = new File(".");
		doc = builder.parse(currentDir + "/xml/" + "Generation" +generation + "Chromosome"+ chromosome +".xml");

		// read 2nd file
		Document doc2 = null;
		XPathExpression expr2 = null;
		builder = factory.newDocumentBuilder();
		doc2 = builder.parse(currentDir + "/xml/" + "Generation0Chromosome1.xml");

		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();
		//*[@id]
		expr = xpath.compile("//*[@id='" + id + "']");

		Object result = expr.evaluate(doc, XPathConstants.NODESET);

		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getAttributes());
		}

		Document newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = newXmlDocument.getDocumentElement();
		String namespaceURL = "http://www.w3.org/2001/XMLSchema-instance";
	    //Element messages = newXmlDocument.createElementNS(namespaceURL, "messages");
	    nodes.item(0).replaceChild(doc2.cloneNode(true), nodes.item(0));
		for (int i = 1; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Node copyNode = newXmlDocument.importNode(node, true);
			root.appendChild(copyNode);
		}

		//doc.getParentNode().replaceChild(newXmlDocument, doc);



		DOMImplementationLS domImpl = (DOMImplementationLS) newXmlDocument.getImplementation();
		LSSerializer lsSerializer = domImpl.createLSSerializer();
		String string = lsSerializer.writeToString(newXmlDocument);
		System.out.println(string);
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(newXmlDocument);
			DOMSource target = new DOMSource(doc);
			StreamResult sresult = new StreamResult("./xml/CurrentSubtree.xml");
			StreamResult sresult2 = new StreamResult("./xml/lolol.xml");
			transformer.transform(source, sresult);
			transformer.transform(target, sresult2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
