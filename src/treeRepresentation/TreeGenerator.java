package treeRepresentation;

import regression.Parameters;

public class TreeGenerator {

	public static boolean CAN_CHOOSE_TERMINAL = false;

	public static TreeNode generateGrowTree(int maxDepth) throws Exception {

		TreeNode root;

		if (maxDepth > 1) {
			if (CAN_CHOOSE_TERMINAL) {
				root = NodeFactory.getNode();
			} else {
				root = NodeFactory.getFunction();
				CAN_CHOOSE_TERMINAL = true;
			}
		} else {
			root = NodeFactory.getTerminal();
		}

		for (int i = 0; i < root.getChildAmount(); i++) {
			root.addChild(generateGrowTree(maxDepth - 1));

		}
		if(maxDepth==0) {
			Parameters.IDENTIFIER = 0;
		}
		return root;
	}

}