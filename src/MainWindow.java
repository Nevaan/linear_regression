
import java.util.ArrayList;
import java.util.List;

import graphics.graphs.TreeGraphView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import regression.Chromosome;
import regression.Genetics;
import regression.Population;
import targetRepresentation.GPParameters;

public class MainWindow extends Application {

	private int actualGraph = 0;
	private List<Chromosome> bestOfAllGenerations;
	@Override
	public void start(Stage primaryStage) throws Exception {

		bestOfAllGenerations = new ArrayList<Chromosome>();
		backend(bestOfAllGenerations);
		
		
		primaryStage.setTitle("Program demonstruj¹cy dzia³anie programowania genetycznego");

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
		
		final Label currentFunction = new Label(bestOfAllGenerations.get(actualGraph).getSchema().printFunction());
		grid.add(currentFunction, 1, 2);

		Label chosenMutationLabel = new Label("Akutalnie wybrana generacja: ");
		grid.add(chosenMutationLabel, 0, 3);

		final Label chosenMutation = new Label(Integer.toString(actualGraph));
		grid.add(chosenMutation, 1, 3);

		Label searchedFunction = new Label("6x^3 + 2x^2 - 9x-7");
		grid.add(searchedFunction, 1, 1);

		final Slider slider = new Slider(1, GPParameters.MUTATION_NUMBER, 0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);

		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				actualGraph = (int) slider.getValue() - 1;
				chosenMutation.textProperty().setValue(String.valueOf((int) slider.getValue()));
				currentFunction.textProperty().setValue(bestOfAllGenerations.get(actualGraph).getSchema().printFunction());
			}
		});

		grid.add(slider, 1, 4);

		Button showTree = new Button("Wyœwietl drzewo");
		grid.add(showTree, 0, 5);
		showTree.setOnAction((new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	TreeGraphView.displayTreeGraph(bestOfAllGenerations.get(actualGraph).getSchema(), "Graf dla generacji: " + actualGraph);
            }
        }));

		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

	public static void backend(List<Chromosome> list) throws Exception {

		Population population = new Population();
		population.initializePopulation();
		for (int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			list.add(population.getFittest());
		}
	}
}
