package regression;

import treeRepresentation.XMLtoClass;
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

		final Label chosenMutation = new Label("1");
		grid.add(chosenMutation, 1, 3);

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


		
		final Button showTree = new Button("Wyswietl drzewo");
		showTree.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showTree, 1, 5);
		showTree.setDisable(true);
		showTree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TreeGraphView.displayTreeGraph(XMLtoClass.convert((int) slider.getValue() - 1, MainClass.bestChromosomes.get((int) slider.getValue() - 1)), "Test");
			}
		});

		Button startAlgorithm = new Button("Start");
		startAlgorithm.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		startAlgorithm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				regression.Parameters.CURRENT_GENERATION_ID=0;		
				Task<Integer> task = new Task<Integer>(){
					@Override 
					protected Integer call() {
						showTree.setDisable(true);
						MainClass.main(null);	
						showTree.setDisable(false);
						return 0;
					}
				};
				new Thread(task).start();	
				
			}
			
		});
		
		grid.add(startAlgorithm,0,5);

		Scene scene = new Scene(grid, 500, 400);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
