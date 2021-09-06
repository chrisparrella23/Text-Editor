package view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlotBox {
	private VBox vBox;
	
	public PlotBox(BorderPane pane, String[] truncArr, TextArea theArea, long[] oneLoopTime, long[] threeLoopTime) {
//		long[] oneLoopTimes = ViewBox2.calcOneLoop(truncArr, theArea);
//		long[] threeLoopTimes = ViewBox2.calcThreeLoops(truncArr, theArea);
		
//		CategoryAxis xAxis = new CategoryAxis();
//		xAxis.setLabel("Subfile");
//		NumberAxis yAxis = new NumberAxis();
//		yAxis.setLabel("Time to Calculate Flesch Score");
//		BarChart barChart = new BarChart(xAxis, yAxis);
//		BarChart barChart2 = new BarChart(xAxis, yAxis);
//		XYChart.Series dataSeries1 = new XYChart.Series();
//		XYChart.Series dataSeries2 = new XYChart.Series();
//		dataSeries1.setName("Single Loop");
//		dataSeries2.setName("Three Loops");
//
//		for (int i = 0; i < oneLoopTimes.length; i++) {
//			dataSeries1.getData().add(new XYChart.Data("Entry" + i, oneLoopTimes[i]));
////			dataSeries2.getData().add(new XYChart.Data("Entry" + i, threeLoopTimes[i]));
//		}
//		barChart.getData().addAll(dataSeries1);
//		VBox vBox2 = new VBox(barChart);
//		vBox.getChildren().add(vBox2);
		
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Subfile");
		yAxis.setLabel("Milliseconds");
		final LineChart<String, Number> lineChart = 
				new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("Time Comparisons");
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		series1.setName("Single Loop");
		series1.getData().add(new XYChart.Data("5%", oneLoopTime[0]));
		series1.getData().add(new XYChart.Data("10%", oneLoopTime[1]));
		series1.getData().add(new XYChart.Data("15%", oneLoopTime[2]));
		series1.getData().add(new XYChart.Data("20%", oneLoopTime[3]));
		series1.getData().add(new XYChart.Data("25%", oneLoopTime[4]));
		series1.getData().add(new XYChart.Data("30%", oneLoopTime[5]));
		series1.getData().add(new XYChart.Data("35%", oneLoopTime[6]));
		series1.getData().add(new XYChart.Data("40%", oneLoopTime[7]));
		series1.getData().add(new XYChart.Data("45%", oneLoopTime[8]));
		series1.getData().add(new XYChart.Data("50%", oneLoopTime[9]));
		series1.getData().add(new XYChart.Data("55%", oneLoopTime[10]));
		series1.getData().add(new XYChart.Data("60%", oneLoopTime[11]));
		series1.getData().add(new XYChart.Data("65%", oneLoopTime[12]));
		series1.getData().add(new XYChart.Data("70%", oneLoopTime[13]));
		series1.getData().add(new XYChart.Data("75%", oneLoopTime[14]));
		series1.getData().add(new XYChart.Data("80%", oneLoopTime[15]));
		series1.getData().add(new XYChart.Data("85%", oneLoopTime[16]));
		series1.getData().add(new XYChart.Data("90%", oneLoopTime[17]));
		series1.getData().add(new XYChart.Data("95%", oneLoopTime[18]));
		series1.getData().add(new XYChart.Data("100%", oneLoopTime[19]));
		lineChart.getData().add(series1);
		
		series2.setName("Three Loops");
		series2.getData().add(new XYChart.Data("5%", threeLoopTime[0]));
		series2.getData().add(new XYChart.Data("10%", threeLoopTime[1]));
		series2.getData().add(new XYChart.Data("15%", threeLoopTime[2]));
		series2.getData().add(new XYChart.Data("20%", threeLoopTime[3]));
		series2.getData().add(new XYChart.Data("25%", threeLoopTime[4]));
		series2.getData().add(new XYChart.Data("30%", threeLoopTime[5]));
		series2.getData().add(new XYChart.Data("35%", threeLoopTime[6]));
		series2.getData().add(new XYChart.Data("40%", threeLoopTime[7]));
		series2.getData().add(new XYChart.Data("45%", threeLoopTime[8]));
		series2.getData().add(new XYChart.Data("50%", threeLoopTime[9]));
		series2.getData().add(new XYChart.Data("55%", threeLoopTime[10]));
		series2.getData().add(new XYChart.Data("60%", threeLoopTime[11]));
		series2.getData().add(new XYChart.Data("65%", threeLoopTime[12]));
		series2.getData().add(new XYChart.Data("70%", threeLoopTime[13]));
		series2.getData().add(new XYChart.Data("75%", threeLoopTime[14]));
		series2.getData().add(new XYChart.Data("80%", threeLoopTime[15]));
		series2.getData().add(new XYChart.Data("85%", threeLoopTime[16]));
		series2.getData().add(new XYChart.Data("90%", threeLoopTime[17]));
		series2.getData().add(new XYChart.Data("95%", threeLoopTime[18]));
		series2.getData().add(new XYChart.Data("100%", threeLoopTime[19]));
		lineChart.getData().add(series2);
		
		vBox = new VBox();
		vBox.getChildren().add(lineChart);
	}

	public VBox getVBox() {
		return vBox;
	}
}
