package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business.BasisModel;
import business.Messung;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LineChartDarstellung{

	Stage stage;
	Series<Number,Number> series;
	Messung[] messungen;

	@FXML
	LineChart<Number, Number> diagramm;
	
	@FXML
	NumberAxis xAchse;
	
	@FXML
	NumberAxis yAchse;
	
	public void initialisiereDiagramm(Messung[] messungen) {
		series = new Series<Number, Number>();
		for(Messung m : messungen){
			series.getData().add(new Data<Number, Number>(m.getLaufendeNummer(),m.getWert()));
		}
		diagramm.getData().add(series);
	}

	

}
