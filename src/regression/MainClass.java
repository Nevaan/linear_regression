package regression;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import graphics.graphs.TreeGraphView;
import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;
import treeRepresentation.ClassToXML;
import treeRepresentation.QueryXML;
import treeRepresentation.TreeGenerator;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class MainClass {

	public static void main(String[] args) {

		QueryXML process = new QueryXML();
		try {
			for (int i = 0; i< 10000 ; i++){
			Random random = new Random();
			
			directoryCleanUp();
			TreeNode chromo0 = TreeGenerator.generateGrowTree(5);
			TreeNode chromo1 = TreeGenerator.generateGrowTree(5);

			ClassToXML.convert(chromo0, 0);
			ClassToXML.convert(chromo1, 0); 

			process.setUniqueIdentifiers(0, 0);
			process.setUniqueIdentifiers(0, 1);
			
			int insertionPointId;
			int subtreeId;

			int randomInFather = random.nextInt(process.countNodes(0, 0) + 1);
			int randomInMorher = random.nextInt(process.countNodes(0, 1) + 1);
			
			insertionPointId = process.query(randomInFather,0, 0);
			subtreeId 		 = process.query(randomInMorher,0, 1);
		
			process.replaceSubtree(0, 0, 1, insertionPointId, subtreeId);
			
			process.setUniqueIdentifiers("replaced");
			process.setParentParameters("replaced");
			Parameters.FILE_NAME_ID = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void directoryCleanUp(){
		if(!Files.exists(Paths.get("./xml/"))){
			new File("./xml/").mkdir();
		}

		File folder = new File("./xml/");
		File[] files = folder.listFiles();
		if(files!=null){
			for(File f:files){
				f.delete();
			}
		}
	}
}
