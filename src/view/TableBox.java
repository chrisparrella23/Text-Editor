package view;

import java.util.HashSet;
import java.util.LinkedList;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.HashTable;

public class TableBox {
	private VBox vBox;
	
	public TableBox(BorderPane pane, TextArea theArea, HashSet<String> table) {
		LinkedList<String> words = HashTable.checkWordSpelling(theArea, table);
		ScrollPane sp = new ScrollPane();
		TableView<String> tView = new TableView<String>();
		TableColumn<String, String> column1 = new TableColumn<>("Misspelled Words");
		column1.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
		
		tView.getColumns().add(column1);
		for (int i = 0; i < words.size(); i++) {
			tView.getItems().add(words.get(i));
		}
		
		vBox = new VBox();
		sp.setContent(tView);
		vBox.getChildren().addAll(sp);
	}
	
	public VBox getVBox() {
		return vBox;
	}
}
