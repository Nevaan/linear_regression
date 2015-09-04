package treeRepresentation.xmlConversion;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import regression.Parameters;
import treeRepresentation.TreeNode;

public class ClassToXML {

	public static void convert(TreeNode tree) {

		try {
			File currentDir = new File(".");
			File file = new File(currentDir + "/xml/" + String.valueOf(Parameters.FILE_NAME_ID++));
			JAXBContext jaxbContext = JAXBContext.newInstance(TreeNode.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tree, file);
			jaxbMarshaller.marshal(tree, System.out);
		} catch (JAXBException e) {
			System.out.println("ERROR: while marshalling at convert()");
			e.printStackTrace();
		}
	}
}
