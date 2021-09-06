package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ViewBox;

public class App extends Application {
	
	// while loop to read the file
	// one while loop or 3 while loops
	// if using one: if we find a period/question mark/exclamation mark, increment sentence count by one.
	// if we find a space, increment word count by one. Don't increment if there are multiple consecutive spaces.
	// counting number of syllables for each word.
	
	// or do the above 3 actions in three separate while loops; one goes thru whole document for sentence count. second
	// loop deals with spaces. third loop deals with syllables.
	
	// consider using split (your own method and built=in API)

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Parrella Text Editor");
		
		BorderPane root = new BorderPane();
		ViewBox viewBox = new ViewBox(root);
		VBox vBox = viewBox.getVBox();
		root.setCenter(vBox);
		
		
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// load warAndPeace.txt once and save it to a .dat file
	// load it from there
	// save menu could save as a .dat, not as a .txt; one menu item for each

	// The project is due on the 14th at 11:30 AM
	
	// when a count item 
}
