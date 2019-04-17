package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelEjercicios implements Initializable {
	@FXML
	AnchorPane ventanaPanel,ejerciciosT1,ejercicios;
	@FXML
	VBox titlePanel,photoBox,picture,titlePanel2,photoBox2,picture2;
	@FXML
	Label textTitle,textTitle2;
	@FXML
	ImageView photoProfile,photoProfile2,btnAtras;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
		
		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile","boton");
		ventanaPanel.getStyleClass().add("backgrounds");
		ventanaPanel.getStyleClass().add("profileBackground");	
		titlePanel.getStyleClass().add("titlesProf");
		textTitle.getStyleClass().add("titlesText");

		photoBox.setAlignment(Pos.CENTER);
		photoBox.setSpacing(12);
		
		photoProfile.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 330, 330, true, true));
		photoProfile.getStyleClass().add("photoProfile");		
		picture.getStyleClass().addAll("profileBox","resourcesBox2");

		
		

		titlePanel2.getStyleClass().add("titlesProf");
		textTitle2.getStyleClass().add("titlesText");

		photoBox2.setAlignment(Pos.CENTER);
		photoBox2.setSpacing(12);
		
		photoProfile2.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer2.png"), 330, 330, true, true));
		photoProfile2.getStyleClass().add("photoProfile");		
		picture2.getStyleClass().addAll("profileBox","resourcesBox2");
		
		
		ejerciciosT1.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT1.getStyleClass().add("backgrounds");
		
		ejerciciosT1.setPrefWidth(1100);
		ejerciciosT1.setPrefHeight(580);
		
		ejercicios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejercicios.getStyleClass().add("backgrounds");
		
		ejercicios.setPrefWidth(1100);
		ejercicios.setPrefHeight(580);
		
		
		
	
		
		
		
		
		
		/*
		btnEjer.getStyleClass().addAll("profileBox","resourcesBox");
		
		btnEjer.setOnMouseClicked(e->{
			System.out.println("Hello");
			//irPagina("http://www.google.com");		
		});
		
		imgEjer.setImage(new Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true, true));
		imgEjer.getStyleClass().add("photoProfile");
		
		for(int i=0;i<3;i++) {
			
		}*/
		//=============================
		
		
		
		
		ejercicios.toFront();

		
		
		
	
		picture.setEffect(ef);
		picture.setSpacing(10);
		
		picture2.setEffect(ef);
		picture2.setSpacing(10);
		//btnEjer.setEffect(ef);
		//btnEjer.setSpacing(10);
		//btnEjer.setAlignment(Pos.CENTER);
		
		picture.setOnMouseClicked(e->{			
			//ventanaProfile.toFront();
			//===================Listando ejercicios tipo 1===================================		
			//Crear btnEjer(VBox)
			//Crear imgEjer(ImageView)
			if(!ejerciciosT1.getChildren().contains(btnAtras)) {
				ejerciciosT1.getChildren().add(btnAtras);
				
			}
			
			int size=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length+1; 
			VBox btnEjercicios[] = new VBox[size];
			ImageView imgEjercicios[] = new ImageView[size];
			float initialLayoutX=90;
			float initialLayoutY=70;

			for(int i=0;i<size;i++) {
				btnEjercicios[i]= new VBox();
				imgEjercicios[i]= new ImageView();
				imgEjercicios[i].getStyleClass().add("photoProfile");
				Label text=new Label("Ejercicio");
				text.getStyleClass().add("textForm");
				
				
				if(i!=0) {
					RandomAccessFile file =null;
					int id=0,tipo=0,vects=0;
					String nameImage="",descripcion="";
					
					try {
						file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
						boolean band=false;
						
						while(!band && file.getFilePointer()<file.length()) {
							id=file.readInt();
							band=(i+1)==id?true:false;
							nameImage=file.readLine();
							tipo=file.readInt();
							vects=file.readInt();
							float datos[]= new float[vects];
							for(int n=0;n<datos.length;n++) {
								file.readFloat();
								file.readFloat();
							}
							descripcion=file.readLine();						
						}
						file.close();						
						System.out.println(nameImage);
						imgEjercicios[i].setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+id+".png", 150, 150, true, true));

						//imgEjercicios[i].setImage(new Image(getClass().getResourceAsStream(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer3.png"), 100, 100, true, true));
						
						
						
						
					} catch (Exception e1) {
						System.out.println("Error1");
						try {
							file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat", "rw");
						} catch (FileNotFoundException e2) {
							System.out.println("Error2");

						}

					}
					
				}else {
					imgEjercicios[i].setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 100, 100, true, true));
				}

				
				
				btnEjercicios[i].setLayoutX(initialLayoutX);
				btnEjercicios[i].setLayoutY(initialLayoutY);
				btnEjercicios[i].setPrefHeight(180);
				btnEjercicios[i].setPrefWidth(180);
				btnEjercicios[i].getStyleClass().addAll("profileBox","resourcesBox");
				
							
				btnEjercicios[i].setEffect(ef);
				btnEjercicios[i].setSpacing(10);
				btnEjercicios[i].setAlignment(Pos.CENTER);
				
				btnEjercicios[i].getChildren().add(imgEjercicios[i]);

				ejerciciosT1.getChildren().add(btnEjercicios[i]);
				
				if(initialLayoutX<=800) {
					initialLayoutX+=200;
				}else {
					initialLayoutY=300;
					initialLayoutX=90;

				}
				text.setText(text.getText()+" "+(i+1));
				btnEjercicios[i].getChildren().add(text);
				

			}
			ejerciciosT1.toFront();
			
		});
		btnAtras.setOnMouseClicked(e->{
			ejerciciosT1.getChildren().clear();
			ejerciciosT1.toBack();
		});

	}

}
