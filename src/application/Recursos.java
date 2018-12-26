package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Recursos implements Initializable {
	
	@FXML
	AnchorPane recursos,contenedor2,contenedor1;
	@FXML
	VBox btnApuntes,btnVideos,btnWikis,btnEjemplos,btnCuestionarios,btnObjetivo,btnPropuestos;
	@FXML
	ImageView imgApuntes,imgVideos,imgWikis,imgEjemplos,imgCuestionarios,imgObjetivo,imgPropuestos,btnAtras;
	@FXML
	Label txtApuntes,txtVideos,txtWikis,txtEjemplos,txtCuestionarios,txtObjetivo,txtPropuestos;
	@FXML
	Hyperlink myHyperlink;
	@FXML
	WebView video1,video2;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		recursos.getStyleClass().add("backgrounds");
		recursos.getStyleClass().add("profileBackground");
		
		btnApuntes.getStyleClass().addAll("profileBox","resourcesBox");
		btnWikis.getStyleClass().addAll("profileBox","resourcesBox");
		btnEjemplos.getStyleClass().addAll("profileBox","resourcesBox");
		btnCuestionarios.getStyleClass().addAll("profileBox","resourcesBox");
		btnVideos.getStyleClass().addAll("profileBox","resourcesBox");
		btnPropuestos.getStyleClass().addAll("profileBox","resourcesBox");
		btnObjetivo.getStyleClass().addAll("profileBox","resourcesBox");

		txtApuntes.getStyleClass().add("textForm");
		txtVideos.getStyleClass().add("textForm");
		txtWikis.getStyleClass().add("textForm");
		txtEjemplos.getStyleClass().add("textForm");
		txtCuestionarios.getStyleClass().add("textForm");
		txtPropuestos.getStyleClass().add("textForm");
		txtObjetivo.getStyleClass().add("textForm");
		

		
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
		
		btnWikis.setEffect(ef);
		btnWikis.setSpacing(10);
		
		btnEjemplos.setEffect(ef);
		btnEjemplos.setSpacing(10);
		
		btnCuestionarios.setEffect(ef);
		btnCuestionarios.setSpacing(10);
		
		btnObjetivo.setEffect(ef);
		btnObjetivo.setSpacing(10);
		
		btnPropuestos.setEffect(ef);
		btnPropuestos.setSpacing(10);
		txtPropuestos.setWrapText(true);
		txtObjetivo.setText("Objetivo de\naprendizaje");
		txtPropuestos.setText(" Ejercicios\nPropuestos");

		
		
		imgApuntes.setImage(new Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true, true));
		imgApuntes.getStyleClass().add("photoProfile");
		
		imgVideos.setImage(new Image(getClass().getResourceAsStream("/images/videos.png"), 100, 100, true, true));
		imgVideos.getStyleClass().add("photoProfile");
		
		imgWikis.setImage(new Image(getClass().getResourceAsStream("/images/wikis.png"), 100, 100, true, true));
		imgWikis.getStyleClass().add("photoProfile");
		
		imgCuestionarios.setImage(new Image(getClass().getResourceAsStream("/images/cuestionarios.png"), 100, 100, true, true));
		imgCuestionarios.getStyleClass().add("photoProfile");
		
		imgObjetivo.setImage(new Image(getClass().getResourceAsStream("/images/objetivo.png"), 85, 85, true, true));
		imgObjetivo.getStyleClass().add("photoProfile");
		
		imgEjemplos.setImage(new Image(getClass().getResourceAsStream("/images/ejemplos.png"), 85, 85, true, true));
		imgEjemplos.getStyleClass().add("photoProfile");
		
		imgPropuestos.setImage(new Image(getClass().getResourceAsStream("/images/propuestos.png"), 85, 85, true, true));
		imgPropuestos.getStyleClass().add("photoProfile");
		
		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile","boton");

		
		
		
		
		
		
		btnApuntes.setAlignment(Pos.CENTER);
		btnVideos.setAlignment(Pos.CENTER);
		btnWikis.setAlignment(Pos.CENTER);
		btnEjemplos.setAlignment(Pos.CENTER);
		btnCuestionarios.setAlignment(Pos.CENTER);
		btnPropuestos.setAlignment(Pos.CENTER);
		btnObjetivo.setAlignment(Pos.CENTER);
		
		
		contenedor2.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		contenedor2.getStyleClass().add("backgrounds");
		contenedor1.getStyleClass().add("backgrounds");
		contenedor1.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());


		contenedor2.setPrefWidth(1100);
		contenedor2.setPrefHeight(580);

		contenedor1.toFront();
		//Links
	
		btnVideos.setOnMouseClicked(e->{			
			//ventanaProfile.toFront();
			contenedor2.toFront();
			
		});
		
		btnApuntes.setOnMouseClicked(e->{
			irPagina("http://www.google.com");		
		});
		btnWikis.setOnMouseClicked(e->{
			irPagina("http://www.google.com");		
		});
		btnEjemplos.setOnMouseClicked(e->{
			irPagina("http://www.google.com");		
		});
		
		btnCuestionarios.setOnMouseClicked(e->{
			irPagina("http://www.google.com");
		});
		
		btnPropuestos.setOnMouseClicked(e->{
			irPagina("http://www.google.com");					
		});
		
		btnObjetivo.setOnMouseClicked(e->{
			irPagina("http://www.google.com");		
		});
		
		btnAtras.setOnMouseClicked(e->{
			contenedor2.toBack();
		});
		
		
	    video1.getEngine().load(
	      "https://www.youtube.com/embed/Da-2h2B4faU"
	    );
	    
	    video2.getEngine().load(
	  	      "https://www.youtube.com/embed/Da-2h2B4faU"
	  	    );
	    //video1.setPrefSize(640, 390);
		
		
		/*myHyperlink.setOnAction(e -> {
		    if(Desktop.isDesktopSupported())
		    {
		        try {
		            Desktop.getDesktop().browse(new URI("http://www.google.com"));
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (URISyntaxException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
	}*/

	}
	
	public static void irPagina(String pagina) {
		if(Desktop.isDesktopSupported())
	    {
	        try {
	            Desktop.getDesktop().browse(new URI(pagina));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } catch (URISyntaxException e1) {
	            e1.printStackTrace();
	        }
	    }
	
	}
}
