package regression;

import java.util.List;
import java.util.Set;

import genetics.Genetics;
import genetics.Population;
import genetics.SampleData;
import graphics.graphs.TreeGraphView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RegressionChart extends Application {

	private LineChart<Number, Number> lineChart;
	private ObservableList<Event> events;
	private Pane layout = new HBox();

	@Override
	public void start(Stage stage) {
		stage.setTitle("Regresja");

		Scene scene = new Scene(layout, 800, 600);
		stage.setScene(scene);
		stage.show();

		styleSeries(events, lineChart);
	}

	@Override
	public void init() throws Exception {
		// defining axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("X");
		yAxis.setLabel("Y");
		xAxis.setMinorTickVisible(false);

		// creating the chart
		lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setAnimated(false);
		lineChart.setTitle("Regresja");
		events = FXCollections.observableArrayList(
				new Event("Data", "",
						FXCollections.observableArrayList(
								createSeries("Data X", FXCollections.observableArrayList(SampleData.dataX)),
								createSeries("Data Y", FXCollections.observableArrayList(SampleData.dataY)))),
				new Event("Values", "", FXCollections.observableArrayList(
						createSeries("Values", FXCollections.observableArrayList(SampleData.dataF)))));
		populateData(events, lineChart);

		// controls
		final VBox eventChecks = new VBox(20);
		eventChecks.setStyle("-fx-padding: 10;");
		final TitledPane controlPane = new TitledPane("Event Selection", eventChecks);
		controlPane.setCollapsible(false);
		for (final Event event : events) {
			final CheckBox box = new CheckBox(event.getName());
			box.setSelected(true);
			Line line = new Line(0, 10, 50, 10);
			StringBuilder styleString = new StringBuilder("-fx-stroke-width: 3; -fx-stroke: gray;");
			if (event.getStrokeDashArray() != null && !event.getStrokeDashArray().isEmpty()) {
				styleString.append("-fx-stroke-dash-array: ").append(event.getStrokeDashArray()).append(";");
			}
			line.setStyle(styleString.toString());

			box.setGraphic(line);
			eventChecks.getChildren().add(box);
			box.setOnAction(action -> {
				event.setActive(box.isSelected());
				populateData(events, lineChart);
				styleSeries(events, lineChart);
			});
		}

		Label caption = new Label("lol");
		caption.setWrapText(true);

		HBox controlledChart = new HBox(10, controlPane, lineChart);
		controlledChart.setAlignment(Pos.CENTER);
		VBox captionedChart = new VBox(10, controlledChart, caption);
		captionedChart.setAlignment(Pos.CENTER);
		HBox.setHgrow(lineChart, Priority.ALWAYS);
		VBox.setVgrow(captionedChart.getChildren().get(0), Priority.ALWAYS);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(captionedChart);
	}

	private void populateData(final ObservableList<Event> events, final LineChart<Number, Number> lineChart) {
		lineChart.getData().clear();
		for (Event event : events) {
			if (event.isActive()) {
				lineChart.getData().addAll(event.getSeries());
			}
		}
	}

	private void styleSeries(ObservableList<Event> events, final LineChart<Number, Number> lineChart) {
		lineChart.applyCss();

		int nSeries = 0;
		for (Event event : events) {
			if (!event.isActive())
				continue;
			for (int j = 0; j < event.getSeries().size(); j++) {
				XYChart.Series<Number, Number> series = event.getSeries().get(j);
				Set<Node> nodes = lineChart.lookupAll(".series" + nSeries);
				for (Node n : nodes) {
					StringBuilder style = new StringBuilder();
					if (event.isBelowAverage(series)) {
						style.append("-fx-stroke: red; -fx-background-color: red, white; ");
					} else {
						style.append("-fx-stroke: blue; -fx-background-color: blue, white; ");
					}
					if (event.getStrokeDashArray() != null && !event.getStrokeDashArray().isEmpty()) {
						style.append("-fx-stroke-dash-array: ").append(event.getStrokeDashArray()).append(";");
					}
					n.setStyle(style.toString());
				}
				nSeries++;
			}
		}
	}

	private XYChart.Series<Number, Number> createSeries(String name, List<Number> data) {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName("name");
		ObservableList<XYChart.Data<Number, Number>> seriesData = FXCollections.observableArrayList();
		for (int i = 0; i < data.size(); i++) {
			seriesData.add(new XYChart.Data<>(i + 1, data.get(i)));
		}
		series.setData(seriesData);

		return series;
	}

	private class Event {
		private String name;
		private ObservableList<XYChart.Series<Number, Number>> series;
		private String strokeDashArray;
		private boolean isActive = true;

		public Event(String name, String strokeDashArray, ObservableList<XYChart.Series<Number, Number>> series) {
			this.name = name;
			this.strokeDashArray = strokeDashArray;
			this.series = series;
		}

		public boolean isBelowAverage(XYChart.Series<Number, Number> checkedSeries) {
			double checkedSeriesAvg = calcSeriesAverage(checkedSeries);
			double allSeriesAvgTot = 0;
			double seriesCount = series.size();
			for (XYChart.Series<Number, Number> curSeries : series) {
				allSeriesAvgTot += calcSeriesAverage(curSeries);
			}
			double allSeriesAvg = seriesCount != 0 ? allSeriesAvgTot / seriesCount : 0;

			return checkedSeriesAvg < allSeriesAvg;
		}

		private double calcSeriesAverage(XYChart.Series<Number, Number> series) {
			double sum = 0;
			int count = series.getData().size();
			for (XYChart.Data<Number, Number> data : series.getData()) {
				sum += data.YValueProperty().get().doubleValue();
			}

			return count != 0 ? sum / count : 0;
		}

		private boolean isActive() {
			return isActive;
		}

		private void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public ObservableList<XYChart.Series<Number, Number>> getSeries() {
			return series;
		}

		public String getName() {
			return name;
		}

		public String getStrokeDashArray() {
			return strokeDashArray;
		}
	}

	public static void main(String[] args) {

		Genetics genetics = new Genetics();
		SampleData sampleData = new SampleData();

		MainClass.directoryCleanUp();


		Thread thread = new Thread(new Runnable() {
			public void run() {
				launch(args);
			}
		});
		thread.start();

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
				//TreeGraphView.displayTreeGraph(population.getFittest(), "fittest");
			}

		} catch (Exception e) {
			System.out.println("Error while executing main");
			e.printStackTrace();
		}

	}
}
