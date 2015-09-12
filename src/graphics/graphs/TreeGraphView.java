package graphics.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import treeElement.function.Function;
import treeElement.terminal.Terminal;
import treeRepresentation.Edge;
import treeRepresentation.TreeNode;

public class TreeGraphView {

	public static void displayTreeGraph(TreeNode root, String title) {

		TreeGraphGenerator treeGraphGenerator = new TreeGraphGenerator(root);

		BasicVisualizationServer<TreeNode, Edge> vv = new BasicVisualizationServer<TreeNode, Edge>
			(new TreeLayout<TreeNode, Edge>(treeGraphGenerator.getTreeGraph()), new Dimension(1100, 640));
		vv.setPreferredSize(new Dimension(1100, 640));

		Transformer<TreeNode, Paint> vertexPaint = new Transformer<TreeNode, Paint>() {
			public Paint transform(TreeNode n) {
				return determineTreeNodeColor(n);
			}
		};

		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer()
			.setPosition(Renderer.VertexLabel.Position.CNTR);

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

	}

	public static Paint determineTreeNodeColor(TreeNode n) {
		if(n instanceof Function)
			return Color.RED;
		else if (n instanceof Terminal)
			return Color.ORANGE;
		else
			return null;
	}

}
