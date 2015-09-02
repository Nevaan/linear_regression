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
        
        final ScatterChart<Number,Number> lineChart = 
                new ScatterChart<Number,Number>(xAxis,yAxis);
                
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

        
        Cartesian uno = new Cartesian();
        for (int i = 0; i< uno.getBoard().size();i++) {
        	
        	series.getData().add(new XYChart.Data(uno.getBoard().get(i).getX(), uno.getBoard().get(i).getY()));
        }


        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}