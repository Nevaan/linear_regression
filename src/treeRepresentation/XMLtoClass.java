package treeRepresentation;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
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

	public static TreeNode convert(int generation, int fileId) {

		try {

			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/Generation"+ generation + "Chromosome"+ String.valueOf(fileId) + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TreeNode treeNode = (TreeNode) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file));
			System.out.println(treeNode);

			return treeNode;

		} catch (JAXBException e) {
			System.out.println("ERROR while unmarshalling in convert()");
			e.printStackTrace();
		}

		return null;

	}

	public static TreeNode getSubtree(){
		try {
			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/RandomSubtree.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TreeNode treeNode = (TreeNode) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file));
			System.out.println(treeNode);

			return treeNode;

		} catch (JAXBException e) {
			System.out.println("ERROR while unmarshalling in convert()");
			e.printStackTrace();
		}

		return null;
	}

	public static TreeNode getLolol(){
		try {
			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/lolol.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TreeNode treeNode = (TreeNode) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file));
			System.out.println(treeNode);

			return treeNode;

		} catch (JAXBException e) {
			System.out.println("ERROR while unmarshalling in convert()");
			e.printStackTrace();
		}

		return null;
	}

}

