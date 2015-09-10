package treeRepresentation;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import regression.Parameters;
import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;

public class ClassToXML {

	public static void convert(TreeNode tree, int generation) {
		try {
			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/Generation" + generation + "Chromosome"
					+  String.valueOf(Parameters.FILE_NAME_ID++) + ".xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tree, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void convertCrossovered(TreeNode tree, int generation) {
		try {
			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/Generation" + generation + "Chromosome"
					+  String.valueOf(Parameters.CROSSOVERED_FILE_NAME_ID++) + ".xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(Add.class, Divide.class, Multiply.class, Substract.class, Constant.class, Variable.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tree, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
