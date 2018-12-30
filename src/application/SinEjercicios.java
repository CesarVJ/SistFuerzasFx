package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class SinEjercicios implements Initializable {
	@FXML
	AnchorPane sinEjercicios;
	@FXML
	VBox anuncioError;
	@FXML
	Label txtErrorEjercicios;
	@FXML
	ImageView sinEjer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		sinEjercicios.getStyleClass().add("backgrounds");
		//sinEjercicios.getStyleClass().add("profileBackground");
		anuncioError.getStyleClass().addAll("profileBox");
		anuncioError.setAlignment(Pos.CENTER);
		
		sinEjer.setImage(new Image(getClass().getResourceAsStream("/images/sinEjercicios.png"), 100, 100, true, true));
		sinEjer.getStyleClass().addAll("photoProfile");
		
		txtErrorEjercicios.getStyleClass().add("textForm");



	}
}
