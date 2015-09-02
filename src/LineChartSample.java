import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import regression.Genetics;
import regression.Population;
import targetRepresentation.Cartesian;
import targetRepresentation.GPParameters;
import treeRepresentation.TreeNode;


public class LineChartSample extends Application {

    @Override public void start(Stage stage) throws Exception{
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        Cartesian.init();

		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();

		for(int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
			System.out.println("Fittest fitness: " + population.getFittest().getFitness());
			//TreeGraphView.displayTreeGraph(population.getFittest().getSchema(), "Fittest");
			System.out.println(population.getFittest().getSchema().printFunction());
		}

        lineChart.setTitle("Guwno gandora");

        XYChart.Series series = new XYChart.Series();
        series.setName("Wykres");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("dupa gandora");

        Cartesian uno = new Cartesian();
        for (int i = 0; i< GPParameters.board.size();i++) {
        	series.getData().add(new XYChart.Data(GPParameters.board.get(i).getX(), GPParameters.board.get(i).getY()));
        	series2.getData().add(new XYChart.Data(GPParameters.board.get(i).getX(), fittest.getValue(GPParameters.board.get(i).getX())));
        }



        Scene scene  = new Scene(lineChart,800,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        lineChart.getData().addAll(series,series2);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}