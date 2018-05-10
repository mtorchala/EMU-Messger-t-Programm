package gui;
import java.io.IOException;
import java.sql.SQLException;

import business.BasisModel;
import javafx.stage.Stage;

public class BasisControl {
	private BasisModel basisModel;
	private BasisView basisView;
	private Stage s;
	
	public BasisControl(Stage stage){
		s = stage;
		basisModel = new BasisModel();
		basisView = new BasisView(basisModel,this,stage);
		stage.show();
	}
	
	public String leseDatenAusDB(int messreihenId){
		try {
			return basisModel.leseDatenAusDB(messreihenId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	public double fuehreMessungDurch(int messreihenId, int laufendeNummer) throws Exception {
		try {
			return basisModel.fuehreMessungDurch(messreihenId, laufendeNummer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
	}
	

}
