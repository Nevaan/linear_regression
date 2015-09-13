package regression;

import treeRepresentation.TreeNode;
import treeRepresentation.XMLtoClass;
import genetics.SampleData;
import graphics.graphs.TreeGraphView;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Program demonstrujacy dzialanie programowania genetycznego");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 1, 1);

		Label searchedFunctionLabel = new Label("Szukana funcja:");
		grid.add(searchedFunctionLabel, 0, 1);

		Label currentFunctionLabel = new Label("Aktualnie znaleziona funkcja:");
		grid.add(currentFunctionLabel, 0, 2);

		Label chosenMutationLabel = new Label("Akutalnie wybrana generacja: ");
		grid.add(chosenMutationLabel, 0, 3);

		Label chosenChromosomeLabel = new Label("Aktualnie wybrany chromosom: ");
		grid.add(chosenChromosomeLabel, 0, 5);

		final Label chosenMutation = new Label("1");
		grid.add(chosenMutation, 1, 3);

		final Label chosenChromosome = new Label("1");
		grid.add(chosenChromosome, 1, 5);

		Label searchedFunction = new Label("6x^3 + 2x^2 - 9x-7");
		grid.add(searchedFunction, 1, 1);

		final Slider slider = new Slider(1, regression.Parameters.GENERATIONS_AMOUNT, 0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);

		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				chosenMutation.textProperty().setValue(String.valueOf((int) slider.getValue()));
			}
		});
		grid.add(slider, 1, 4);

		final Slider slider2 = new Slider(1, regression.Parameters.POPULATION_SIZE, 0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);

		slider2.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				chosenChromosome.textProperty().setValue(String.valueOf((int) slider2.getValue()));
			}
		});
		grid.add(slider2, 1, 6);

		final Button showTree = new Button("Wyswietl drzewo");
		showTree.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showTree, 1, 8);
		showTree.setDisable(true);
		showTree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TreeGraphView.displayTreeGraph(XMLtoClass.convert((int) slider.getValue() - 1, (int) slider2.getValue() - 1), "Test");
			}
		});

		final Button showFittestTree = new Button("Wyswietl drzewo najlepszego osobnika");
		showFittestTree.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showFittestTree, 1, 9);
		showFittestTree.setDisable(true);
		showFittestTree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TreeGraphView.displayTreeGraph(XMLtoClass.convert((int) slider.getValue() - 1,
						MainClass.bestChromosomes.get((int) slider.getValue() - 1)), "Test");
			}
		});

		final Button showFunction = new Button("Wyswietl funkcje");
		showFunction.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showFunction, 1, 10);
		showFunction.setDisable(true);
		showFunction.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = new Stage();
				// load resources
				SampleData sampleData = new SampleData();
				TreeNode displayedTree = XMLtoClass.convert((int) slider.getValue() - 1, (int) slider2.getValue() - 1);

				stage.setTitle("Generacja: " + ((int) slider.getValue() - 1) + " Osobnik: " + ((int) slider2.getValue() - 1));
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
					dataSeries.getData()
							.add(new XYChart.Data<Number, Number>(SampleData.sampleX[i], SampleData.sampleY[i]));
					functionSeries.getData().add(new XYChart.Data<Number, Number>(SampleData.sampleX[i],
							displayedTree.getValue(SampleData.sampleX[i])));
				}

				lineChart.getData().addAll(dataSeries, functionSeries);

				stage.setScene(scene);
				stage.show();
			}
		});

		final Button showFittest = new Button("Wyswietl funkcje najlepszego osobnika");
		showFittest.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showFittest, 1, 11);
		showFittest.setDisable(true);
		showFittest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = new Stage();
				// load resources
				TreeNode displayedTree = XMLtoClass.convertFittest((int) slider.getValue() - 1);

				stage.setTitle("Generacja: " + ((int) slider.getValue() - 1) + " Najlepszy osobnik");
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
					dataSeries.getData()
							.add(new XYChart.Data<Number, Number>(SampleData.sampleX[i], SampleData.sampleY[i]));
					functionSeries.getData().add(new XYChart.Data<Number, Number>(SampleData.sampleX[i],
							displayedTree.getValue(SampleData.sampleX[i])));
				}

				lineChart.getData().addAll(dataSeries, functionSeries);

				stage.setScene(scene);
				stage.show();
			}
		});

		Button startAlgorithm = new Button("Start");
		startAlgorithm.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		startAlgorithm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				regression.Parameters.CURRENT_GENERATION_ID = 0;
				Task<Integer> task = new Task<Integer>() {
					@Override
					protected Integer call() {
						showTree.setDisable(true);
						MainClass.main(null);
						showTree.setDisable(false);
						showFunction.setDisable(false);
						showFittest.setDisable(false);
						showFittestTree.setDisable(false);
						return 0;
					}
				};
				new Thread(task).start();

			}

		});

		grid.add(startAlgorithm, 0, 7);

		Scene scene = new Scene(grid, 800, 600);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
