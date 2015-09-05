package treeRepresentation;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPathExpressionException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;


public class XMLtoClass {

	public static TreeNode convert(int fileId) {

		try {

			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/" + String.valueOf(fileId) + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);

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

	public static TreeNode search(String source, String criteria) throws JAXBException, XPathExpressionException, JDOMException, IOException {

		// read the XML into a JDOM2 document
		SAXBuilder jdomBuilder = new SAXBuilder();
		Document jdomDocument = jdomBuilder.build(source);

		return new TreeNode();

	}
}

