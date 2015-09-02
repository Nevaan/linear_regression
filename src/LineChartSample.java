import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import regression.Genetics;
import regression.Population;
import targetRepresentation.GPParameters;
import treeRepresentation.TreeNode;


public class LineChartSample extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Guwno gandora");

        XYChart.Series series = new XYChart.Series();
        series.setName("Wykres");

        
        // **************** GP
        Population population = new Population();
        try {
        	population.initializePopulation();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        /*
		for(int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
			System.out.println("Fittest fitness: " + population.getFittest().getFitness());
			//TreeGraphView.displayTreeGraph(population.getFittest().getSchema(), "Fittest");
			System.out.println(population.getFittest().getSchema().printFunction());
		}
		// **************** GP */
		
        for (int i = 0; i < population.get)
		
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}