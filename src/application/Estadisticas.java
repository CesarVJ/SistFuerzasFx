package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Estadisticas implements Initializable {
	@FXML
	AnchorPane ventanaEstadist;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ventanaEstadist.getStyleClass().add("backgrounds");
		ventanaEstadist.getStyleClass().add("profileBackground");		
	}

}
