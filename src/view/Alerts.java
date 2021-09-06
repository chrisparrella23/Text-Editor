package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Alerts {
	
	public static void emptyClipboard() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setContentText("There is no content on the clipboard.");
		alert.showAndWait();
	}
	
	public static void markovAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("There is no text.");
		alert.showAndWait();
	}
	
}
