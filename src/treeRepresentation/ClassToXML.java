package treeRepresentation;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import regression.Parameters;

public class ClassToXML {

	public static void convert(TreeNode tree) {

	try {

		File currentDir = new File(".");
		File file = new File(currentDir + "/xml/" + String.valueOf(Parameters.FILE_NAME_ID++) + ".xml");

		JAXBContext jaxbContext = JAXBContext.newInstance(TreeNode.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(tree, file);
		jaxbMarshaller.marshal(tree, System.out);
	} catch (JAXBException e) {
		e.printStackTrace();
	}
	}
}
