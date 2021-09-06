package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.Demo;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.MasterLinkedList;
import utils.Calculations;
import utils.FileHelper;
import utils.HashTable;
import utils.TextGenerator;

public class ViewBox {
	private MenuBar menuBar;
	private MenuBar statusBar;
	private File file;
	private VBox vBox;
	private HBox hBox1;
	private HBox hBox2;

	private TextField wordField;
	private TextField numField;
	private Button makeBtn;
	private Button checkBtn;
	private TextArea theArea;
	private static double wordCount = 0;
	private static double sentenceCount = 0;
	private static double syllableCount = 0;
	private static double fleschScore = 0;
	private MasterLinkedList theList;
	private String[] text;
	private String[] truncArr;
	DecimalFormat df = new DecimalFormat("0.00");
	private Stage plotStage;
	private Stage tableStage;
	private File selectedFile;
	private String wordList;
	HashSet<String> set;
	Clipboard systemClipboard = Clipboard.getSystemClipboard();

	public ViewBox(BorderPane pane) {
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(pane.widthProperty());
		statusBar = new MenuBar();
		statusBar.prefWidthProperty().bind(pane.widthProperty());

		vBox = new VBox(30);
		vBox.setAlignment(Pos.CENTER);
		wordField = new TextField();
		wordField.setPromptText("Enter a starting word");
		numField = new TextField();
		numField.setPromptText("Enter the desired length");
		makeBtn = new Button("Make Passage");
		checkBtn = new Button("Check Spelling");
		theArea = new TextArea();
		theArea.setWrapText(true);
		theArea.setPrefWidth(800);
		theArea.setPrefHeight(500);

		hBox1 = new HBox(30);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.getChildren().addAll(wordField, numField, makeBtn, checkBtn);

		hBox2 = new HBox(30);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(theArea);

		vBox.getChildren().addAll(hBox1, hBox2);

		set = new HashSet<String>();
		wordList = HashTable.getDictionary("data/dictionary.txt");
		HashTable.fillHashTable(set, wordList);

		Menu fileMenu = new Menu("File");
		MenuItem newItem = new MenuItem("New");
		MenuItem openItem = new MenuItem("Open");
		MenuItem openDatItem = new MenuItem("Open .dat");
		MenuItem closeItem = new MenuItem("Close");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem exitItem = new MenuItem("Exit");

		Menu viewMenu = new Menu("View");
		MenuItem wordItem = new MenuItem("Word Count");
		MenuItem sentenceItem = new MenuItem("Sentence Count");
		MenuItem fleschItem = new MenuItem("Flesch Score");

		Menu editMenu = new Menu("Edit");
		MenuItem copyItem = new MenuItem("Copy");
		MenuItem cutItem = new MenuItem("Cut");
		MenuItem deleteItem = new MenuItem("Delete");
		MenuItem pasteItem = new MenuItem("Paste");

		Menu actionMenu = new Menu("Action");
		MenuItem markovItem = new MenuItem("Markov");
		MenuItem truncateItem = new MenuItem("Truncate");
		MenuItem showItem = new MenuItem("Show");
		MenuItem plotItem = new MenuItem("Plot");
		MenuItem discussItem = new MenuItem("Discussion");
		pane.setTop(this.menuBar);

		Menu wordMenu = new Menu();
		Menu sentenceMenu = new Menu();
		Menu fleschMenu = new Menu();
		pane.setBottom(statusBar);

		fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), openItem, new SeparatorMenuItem(), openDatItem,
				new SeparatorMenuItem(), closeItem, new SeparatorMenuItem(), saveItem, new SeparatorMenuItem(),
				exitItem);

		newItem.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Current file is modified");
			alert.setContentText("Do you wish to save this file?");
			ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
			ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
			ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn);
			alert.showAndWait().ifPresent(type -> {
				if (type == yesBtn) {
					save(theArea);
					theArea.clear();
				} else if (type == noBtn) {
					theArea.clear();
				} else {

				}
			});
		});

		openItem.setOnAction(e -> {
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			boolean textFiles = fileChooser.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			selectedFile = fileChooser.showOpenDialog(stage);
			FileHelper.open(theArea, selectedFile);
		});

		openDatItem.setOnAction(e -> {
			importDatFile();
		});

		closeItem.setOnAction(e -> {
			theArea.clear();
			wordCount = 0;
			sentenceCount = 0;
			fleschScore = 0;
		});

		saveItem.setOnAction(e -> {
			save(theArea);
		});

		exitItem.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Exit");
			alert.setContentText("Do you wish to save this file before exiting?");
			ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
			ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
			ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn);
			alert.showAndWait().ifPresent(type -> {
				if (type == yesBtn) {
					save(theArea);
					Platform.exit();
				} else if (type == noBtn) {
					Platform.exit();
				} else {

				}
			});
		});

		copyItem.setOnAction(e -> {
			copy();
		});

		cutItem.setOnAction(e -> {
			cut();
		});

		deleteItem.setOnAction(e -> {
			delete();
		});

		pasteItem.setOnAction(e -> {
			if (systemClipboard.hasString()) {
				paste();
			} else {
				Alerts.emptyClipboard();
			}
		});

		viewMenu.getItems().addAll(wordItem, new SeparatorMenuItem(), sentenceItem, new SeparatorMenuItem(),
				fleschItem);

		wordItem.setOnAction(e -> {
			wordMenu.setDisable(false);
			sentenceMenu.setDisable(false);
			fleschMenu.setDisable(false);
			wordMenu.setText("");
			wordCount = Calculations.wordCountFromText(theArea);
			sentenceMenu.setDisable(true);
			fleschMenu.setDisable(true);
			wordMenu.setText("Word Count: " + String.valueOf((int) wordCount));
		});

		sentenceItem.setOnAction(e -> {
			wordMenu.setDisable(false);
			sentenceMenu.setDisable(false);
			fleschMenu.setDisable(false);
			sentenceMenu.setText("");
			sentenceCount = Calculations.sentenceCountFromText(theArea);
			wordMenu.setDisable(true);
			fleschMenu.setDisable(true);
			sentenceMenu.setText("Sentence Count: " + String.valueOf((int) sentenceCount));
		});

		fleschItem.setOnAction(e -> {
			wordMenu.setDisable(false);
			sentenceMenu.setDisable(false);
			fleschMenu.setDisable(false);
			fleschMenu.setText("");
			fleschScore = Calculations.fleschFromText(theArea);
			wordMenu.setDisable(true);
			sentenceMenu.setDisable(true);
			String flesch = df.format(fleschScore);
			fleschMenu.setText("Flesch Score: " + flesch);
		});

		editMenu.getItems().addAll(copyItem, new SeparatorMenuItem(), cutItem, new SeparatorMenuItem(), deleteItem,
				new SeparatorMenuItem(), pasteItem);

		actionMenu.getItems().addAll(markovItem, new SeparatorMenuItem(), truncateItem, new SeparatorMenuItem(),
				showItem, new SeparatorMenuItem(), plotItem, new SeparatorMenuItem(), discussItem);

		markovItem.setOnAction(e -> {
			if (theArea.getText().equalsIgnoreCase("")) {
				Alerts.markovAlert();
			} else {
//				long time = System.currentTimeMillis();
//				theList = Markov.parser(theArea.getText());
//				long time = System.currentTimeMillis();
//				theList = FileHelper.makeMasterList(theArea);
//				System.out.println((System.currentTimeMillis() - time));
//				long time2 = System.currentTimeMillis();
//				theList = FileHelper.makeBabyLists(selectedFile.getPath(), theList);
//				System.out.println((System.currentTimeMillis() - time2));
//				long time3 = System.currentTimeMillis();
//				theList = FileHelper.fillBabyLists(selectedFile.getPath(), theList);
//				System.out.println((System.currentTimeMillis() - time3));
				
				theList = getMasterList();
//				String passage = FileHelper.makeRandomText(word, num)
			}
		});

		truncateItem.setOnAction(e -> {
			text = theArea.getText().split(" ");
			truncArr = Calculations.truncate(theArea.getText());
			for (int i = 0; i < truncArr.length; i++) {
				// System.out.println(truncArr[i]);
			}
		});

		showItem.setOnAction(e -> {
			FileHelper.getResults("output/results.txt", theArea);
		});

		plotItem.setOnAction(e -> {
			long[] oneLoopTimes = Calculations.calcOneLoop(truncArr, theArea);
			long[] threeLoopTimes = Calculations.calcThreeLoops(truncArr, theArea);
			BorderPane root = new BorderPane();
			plotStage = new Stage();

			PlotBox plotBox = new PlotBox(root, truncArr, theArea, oneLoopTimes, threeLoopTimes);
			VBox theBox = plotBox.getVBox();
			root.setCenter(theBox);
			Scene scene = new Scene(root, 900, 600);
			plotStage.setScene(scene);
			plotStage.show();

			exportResults(oneLoopTimes, threeLoopTimes);
		});

		discussItem.setOnAction(e -> {
			FileHelper.getResults("output/Discussion", theArea);
		});

		menuBar.getMenus().addAll(fileMenu, viewMenu, editMenu, actionMenu);
		statusBar.getMenus().addAll(wordMenu, sentenceMenu, fleschMenu);

		makeBtn.setOnAction(e -> {
			TextInputDialog wordDialog = new TextInputDialog();
			wordDialog.setTitle("Input Word");
			wordDialog.setContentText("Enter the starting word:");
			Optional<String> result = wordDialog.showAndWait();

			if (result.isPresent()) {
				String word = result.get();
				wordField.setText(word);
			}

			TextInputDialog numDialog = new TextInputDialog();
			numDialog.setTitle("Input Integer");
			numDialog.setContentText("Enter an integer");
			Optional<String> result1 = numDialog.showAndWait();
			if (result1.isPresent()) {
				String num = result1.get();
				numField.setText(num);
			}

//			String passage = FileHelper.makeParagraph(theList, wordField.getText(),
//					Integer.valueOf(numField.getText()));
////			String passage = makeParagraph(wordField.getText(), Integer.valueOf(numField.getText()));
//			theArea.setText(passage);
			
			generateText(theList, wordField.getText(), Integer.valueOf(numField.getText()));
		});

		checkBtn.setOnAction(e -> {
			BorderPane root = new BorderPane();
			TableBox tableBox = new TableBox(root, theArea, set);
			VBox theBox = tableBox.getVBox();
			tableStage = new Stage();
			root.setCenter(theBox);
			Scene scene = new Scene(root, 125, 400);
			tableStage.setScene(scene);
			tableStage.show();
		});

		theArea.textProperty().addListener((observable, oldValue, newValue) -> {
			wordMenu.setDisable(false);
			sentenceMenu.setDisable(false);
			fleschMenu.setDisable(false);
			int words = 0;
			int sentences = 0;
			wordCount = Calculations.wordCountFromText(theArea);
			words = (int) wordCount;
			wordMenu.setText("Word Count: " + String.valueOf(words));
			sentenceCount = Calculations.sentenceCountFromText(theArea);
			sentences = (int) sentenceCount;
			sentenceMenu.setText("Sentence Count: " + String.valueOf(sentences));
			syllableCount = Calculations.totalSyllableCount(theArea);
			fleschScore = Calculations.fleschFromText(theArea);
			String flesch = df.format(fleschScore);
			fleschMenu.setText("Flesch Score: " + flesch);
		});
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public VBox getVBox() {
		return vBox;
	}

	public void importDatFile() {
		String text = "";
		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Binary Files", "*.dat"));
		Stage newStage = new Stage();
		file = fc.showOpenDialog(newStage);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String temp = buff.readLine();
			text += temp;
			theArea.appendText(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setWordCount(int num) {
		wordCount = num;
	}

	public void save(TextArea theArea) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT files (*.txt)", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Data Files", "*.dat"));
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			SaveFile(theArea.getText(), file);
		}
	}

	public void SaveFile(String content, File file) {
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static String exportResults(long[] oneLoopTime, long[] threeLoopTime) {
		String output = "";
		int percentage = 0;
		int[] oneTimes = new int[oneLoopTime.length];
		int[] threeTimes = new int[threeLoopTime.length];
		for (int i = 0; i < oneLoopTime.length; i++) {
			oneTimes[i] = (int) oneLoopTime[i];
			threeTimes[i] = (int) threeLoopTime[i];
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter("output/results.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.format("%s\t\t%s\t\t%s\n", "File Percentage", "One-Loop Method", "Three-Loop Method");
		for (int i = 0; i < oneLoopTime.length; i++) {
			pw.format("%s\t\t\t\t\t\t%s\t\t\t\t\t\t%s\n", percentage += 5, (int) oneLoopTime[i],
					(int) threeLoopTime[i]);
		}
		pw.close();
		return output;
	}

	public void copy() {
		String text = theArea.getSelectedText();
		ClipboardContent content = new ClipboardContent();
		content.putString(text);
		systemClipboard.setContent(content);
	}

	public void cut() {
		String text = theArea.getSelectedText();
		ClipboardContent content = new ClipboardContent();
		content.putString(text);
		systemClipboard.setContent(content);

		IndexRange range = theArea.getSelection();
		String origText = theArea.getText();
		String firstPart = origText.substring(0, range.getStart());
		String lastPart = origText.substring(range.getEnd(), origText.length());
		theArea.setText(firstPart + lastPart);
		theArea.positionCaret(range.getStart());
	}

	public void delete() {
		IndexRange range = theArea.getSelection();
		String origText = theArea.getText();
		String firstPart = origText.substring(0, range.getStart());
		String lastPart = origText.substring(range.getEnd(), origText.length());
		theArea.setText(firstPart + lastPart);
		theArea.positionCaret(range.getStart());
	}

	public void paste() {
		String clipboardText = systemClipboard.getString();
		IndexRange range = theArea.getSelection();
		String origText = theArea.getText();

		int endPos = 0;
		String updatedText = "";
		String firstPart = origText.substring(0, range.getStart());
		String lastPart = origText.substring(range.getEnd(), origText.length());

		updatedText = firstPart + clipboardText + lastPart;

		if (range.getStart() == range.getEnd()) {
			endPos = range.getEnd() + clipboardText.length();
		} else {
			endPos = range.getStart() + clipboardText.length();
		}
		theArea.setText(updatedText);
	}

	public void copyText() {
		String text = theArea.getText();
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(text);
		clipboard.setContent(content);
	}
	
	public MasterLinkedList getMasterList() {
		String fileName = selectedFile.getAbsolutePath();
		MasterLinkedList theMasterList = new MasterLinkedList();
		FileHelper theReader = new FileHelper(fileName);
		theReader.readInFile(theMasterList);
		return theMasterList;
	}
	
	private void generateText(MasterLinkedList masterList, String startingWord, int numOfWords) {
		TextGenerator theGenerator = new TextGenerator(masterList);
		theArea.setText(theGenerator.generateRandomText(startingWord, numOfWords));
		
	}
	
//	public String makeParagraph(String word, int num) {
//		int random;
//		MasterLink current = this.containsLink(word);
//		StringBuilder paragraph = new StringBuilder(word + " ");
//		
//		for (int i = 0; i < num; i++) {
//			if (current != null) {
//				random = (int) (Math.random() * current.getBabyLinkedList().size());
//				paragraph.append(current.getBabyLinkedList().get(random).getNextWord()).append(" ");
//				current = get(i);
//			}
//		}
//		return paragraph.toString();
//	}
//	
//	public MasterLink containsLink(String s) {
//		for (MasterLink masterLink : this) {
//			if (masterLink.getKeyword().equals(s)) {
//				return masterLink;
//			}
//		}
//		return null;
//	}

}
