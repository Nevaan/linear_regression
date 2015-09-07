
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
import targetRepresentation.GPParameters;

public class MainWindow extends Application {

	@Override
	public void start(Stage primaryStage) {

		
		primaryStage.setTitle("Program demonstruj�cy dzia�anie programowania genetycznego");

		GridPane grid = new GridPane();
		//grid.setAlignment(Pos.CENTER);
		grid.setStyle("-fx-background-color: #ffb1b1;");
		grid.setHgap(20);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 1, 1);

		Label searchedFunctionLabel = new Label("Szukana funkcja:");
		searchedFunctionLabel.setFont(new Font("Arial", 30));
		grid.add(searchedFunctionLabel, 0, 1);

		Label currentFunctionLabel = new Label("Aktualnie znaleziona funkcja:");
		grid.add(currentFunctionLabel, 0, 7);

		Label chosenMutationLabel = new Label("Akutalnie wybrana generacja: ");
		grid.add(chosenMutationLabel, 0, 5);
		
		final Label chosenMutation = new Label();
		grid.add(chosenMutation, 1, 6);
		
		Label searchedFunction = new Label("6x^3 + 2x^2 - 9x-7");
		searchedFunction.setFont(new Font("Arial", 30));
		searchedFunction.setStyle("-fx-font-weight: bold");
		grid.add(searchedFunction, 1, 1);

		final Slider slider = new Slider(1, GPParameters.MUTATION_NUMBER, 0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);
		
		slider.valueProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				chosenMutation.textProperty().setValue(
							String.valueOf((int) slider.getValue())
						);
			}	
		});
	
		grid.add(slider,1,5);

		Button showTree = new Button("Wy�wietl drzewo");
		showTree.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		grid.add(showTree, 1, 20);
		
		showTree.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        
		    	//Taki test tu ma by� wyswietlanie kurwa drzewa
		   
		    	primaryStage.setTitle("Accepted");
		        
		        //wyswietl kurwa drzewo
		    	//TreeGraphView.displayTreeGraph(root, title);
		    }
		});

		//miejsce na aktualna kurwa funkcje
		//Label currentFunction = new Label("Jestem kurwa aktualne funkcja");
		//currentFunction.setStyle("-fx-font-weight: bold");
		//grid.add(curentFunction, 1, 7);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 7);

		Scene scene = new Scene(grid, 600, 600);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}