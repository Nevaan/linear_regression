package treeRepresentation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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

	public void query(int id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		// read file first
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		XPathExpression expr = null;
		builder = factory.newDocumentBuilder();
		File currentDir = new File(".");
		doc = builder.parse(currentDir + "/xml/" + "Generation0Chromosome0.xml");

		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();

		expr = xpath.compile("//children[@id='" + id + "']");

		Object result = expr.evaluate(doc, XPathConstants.NODESET);

		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getAttributes());
		}

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
			StreamResult sresult = new StreamResult("./xml/CurrentSubtree.xml");
			transformer.transform(source, sresult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
