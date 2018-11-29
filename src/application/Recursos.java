package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Recursos implements Initializable {
	
	@FXML
	AnchorPane recursos;
	@FXML
	VBox btnApuntes,btnVideos;
	@FXML
	ImageView imgApuntes,imgVideos;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		recursos.getStyleClass().add("backgrounds");
		recursos.getStyleClass().add("profileBackground");
		
		btnApuntes.getStyleClass().add("profileBox");
		

		
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
	    btnApuntes.setEffect(ef);
		btnApuntes.setSpacing(10);
		
		btnVideos.setEffect(ef);
		btnVideos.setSpacing(10);
		
		imgApuntes.setImage(new Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true, true));
		imgApuntes.getStyleClass().add("photoProfile");
		
		imgVideos.setImage(new Image(getClass().getResourceAsStream("/images/videos.png"), 100, 100, true, true));
		imgVideos.getStyleClass().add("photoProfile");
		
		btnApuntes.setAlignment(Pos.CENTER);
		btnVideos.setAlignment(Pos.CENTER);
		
	}

}
