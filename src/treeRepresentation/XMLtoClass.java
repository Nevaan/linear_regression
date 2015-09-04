package treeRepresentation;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
}
