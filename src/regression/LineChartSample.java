package regression;

import genetics.Genetics;
import genetics.Population;
import genetics.SampleData;
import graphics.graphs.TreeGraphView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;

public class LineChartSample extends Application {

	private int generation = 0;
	private int chromosome = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		// time log start
		long tStart = System.currentTimeMillis();

		// load resources
		SampleData sampleData = new SampleData();
		TreeNode displayedTree = XMLtoClass.convert(generation, chromosome);

		stage.setTitle("Generacja: " + generation + "Osobnik: " + chromosome);
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("X");
		yAxis.setLabel("Y");

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Regresja");

		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		dataSeries.setName("Dane");
		XYChart.Series<Number, Number> functionSeries = new XYChart.Series<Number, Number>();
		functionSeries.setName("Funkcja");

		Scene scene = new Scene(lineChart, 800, 600);
		scene.getStylesheets().add(this.getClass().getResource("../style.css").toExternalForm());

		for (int i = 0; i < SampleData.sampleX.length; i++) {
			dataSeries.getData().add(new XYChart.Data<Number, Number>(SampleData.sampleX[i], SampleData.sampleY[i]));
			functionSeries.getData().add(new XYChart.Data<Number, Number>(SampleData.sampleX[i], displayedTree.getValue(SampleData.sampleX[i])));
		}

		lineChart.getData().addAll(dataSeries, functionSeries);

		stage.setScene(scene);
		stage.show();

		// time log finish
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		System.out.println("Duration time: " + (tDelta / 1000.0));
	}

	public void displayChart(int generation, int chromosome) {
		this.generation = generation;
		this.chromosome = chromosome;
		launch();
	}

}
