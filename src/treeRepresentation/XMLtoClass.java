package treeRepresentation;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPathExpressionException;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;


public class XMLtoClass {

	public static TreeNode convert(int fileId) {

		try {

			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/" + String.valueOf(fileId) + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TreeNode.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TreeNode treeNode = (TreeNode) jaxbUnmarshaller.unmarshal(file);
			System.out.println(treeNode);

			return treeNode;

		} catch (JAXBException e) {
			System.out.println("ERROR while unmarshalling in convert()");
			e.printStackTrace();
		}

		return null;

	}

	public static TreeNode search(String source, String criteria) throws JAXBException, XPathExpressionException {

		// read the XML into a JDOM2 document
		SAXBuilder jdomBuilder = new SAXBuilder();
		Document jdomDocument = jdomBuilder.build(source);



	}
}

