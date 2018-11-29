package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TipoEjercicio2 implements Initializable{
	
	@FXML
	private AnchorPane ejercicios2;
	@FXML
	private HBox mainBox,cajaResultante;
	@FXML
	private VBox cajaIzquierda;
	@FXML
	private ImageView imgCuerpo;
	@FXML
	private Label textoInferior;
	@FXML
	private TextField tanB,tanA;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		imgCuerpo.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true));
		//imgCuerpo.getStyleClass().add("photoProfile");
		ejercicios2.getStyleClass().add("backgrounds");

	}
	
	
	
}
