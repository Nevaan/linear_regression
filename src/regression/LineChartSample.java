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

public class LineChartSample extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// time log start
		long tStart = System.currentTimeMillis();

		stage.setTitle("Line Chart Sample");
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Month");

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		Genetics genetics = new Genetics();

		MainClass.directoryCleanUp();
		TreeNode fittest = null;

		lineChart.setTitle("Regresja");
		XYChart.Series series = new XYChart.Series();
		series.setName("Wykres");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("");

		Scene scene = new Scene(lineChart, 800, 600);
		scene.getStylesheets().add(this.getClass().getResource("../style.css").toExternalForm());

		for (int i = 0; i < SampleData.sampleX.length; i++) {
			series.getData().add(new XYChart.Data(SampleData.sampleX[i], SampleData.sampleY[i]));
		}

		try {
			Population population = new Population();
			population.initialize();
			regression.Parameters.CURRENT_CHROMOSOME_ID = 0;
			regression.Parameters.CURRENT_GENERATION_ID++;

			for (int i = 0; i < regression.Parameters.GENERATIONS_AMOUNT; i++) {
				population = genetics.evolve(population);
				System.out.println("~~~~~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~~~~~");
				regression.Parameters.CURRENT_CHROMOSOME_ID = 0;
				regression.Parameters.CURRENT_GENERATION_ID++;
				fittest = population.getFittest();
				for (int k = 0; k < SampleData.sampleX.length; k++) {
					series2.getData().clear();
					XYChart.Series<Number, Number> s = lineChart.getData().get(0);
					series2.getData().add(new XYChart.Data(SampleData.sampleX[k], fittest.getValue(SampleData.sampleX[k])));
				}
				lineChart.getData().addAll(series, series2);

				stage.setScene(scene);
				stage.show();

			}

			TreeGraphView.displayTreeGraph(fittest, "fittest");
		} catch (Exception e) {
			System.out.println("Error while executing main");
			e.printStackTrace();
		}

		// time log finish
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		System.out.println("Duration time: " + (tDelta / 1000.0));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
