package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileSystemView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import model.Formulario;
import model.Usuario;

public class Window implements Initializable{

	
	@FXML
	AnchorPane ventanaPrincipal,contenedor,VentanaCalc,ventanaEjer,titleBar,ventanaAdd;
	 @FXML
	 private Label lblText;
	static ActionEvent eventLog;
	@FXML
	private HBox main;
	@FXML
	private VBox VBMenu,VBMenu2,menuPrincipal,VBSub,exit;
	@FXML
	private Button btnRegresar,btnPerfil,btnEjercicios,btnCalculador,btnCerrar,btnMin,btnAgregar,btnEjercicios1,btnEjercicios2;
	static Stage stage=null;
	
	@FXML
	Image imgSistema2;
	Usuario usuario;
	
	public Window(Usuario user) {
		usuario=user;
	}
	public Window() {
	}
	
	public void crearVentana(ActionEvent event) {
		
		
			eventLog=event;
		Parent root;
		
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FXMLWindow.fxml"));
            stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 1250, 580));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            
            // Para ocultar la ventana anterior
            ((Node)(event.getSource())).getScene().getWindow().hide();
    
        }
        catch (IOException e) {
            e.printStackTrace();
        }  
        
	}
	
	
	
	public void regresarLogin(ActionEvent event) throws IOException {
		 //((Node)(eventLog.getSource())).getScene().getWindow().setFocused(true);
		 Tabs program = new Tabs();
		   ((Node)(event.getSource())).getScene().getWindow().hide();
		   usuario=null;
		   program.user=null;
		   ventanaEjer=null;
		 program.start(new Stage());
 
	}
	
	public void agregarEjercicio() {
		VBSub.getChildren().remove(btnEjercicios1);
		VBSub.getChildren().remove(btnEjercicios2);		
		ventanaAdd.toFront();

	}
	
	public void abrirEjercicio() {
		//root3.getChildren().get(0).toFront();	
		//ventanaEjer.toFront();
		try {
		VBSub.getChildren().addAll(btnEjercicios1,btnEjercicios2);
		}catch(Exception e) {
			VBSub.getChildren().remove(btnEjercicios1);
			VBSub.getChildren().remove(btnEjercicios2);
		}
	}
	
	public void abrirEjercicio1() {
		//root3.getChildren().get(0).toFront();	
		ventanaEjer.toFront();		
	}
	
	public void abrirCalculador() {
		VBSub.getChildren().remove(btnEjercicios1);
		VBSub.getChildren().remove(btnEjercicios2);
		VentanaCalc.toFront();

		
	}
	
	public void abrirPerfil() {
		VBSub.getChildren().remove(btnEjercicios1);
		VBSub.getChildren().remove(btnEjercicios2);


	}
	
	@FXML
	public void minVentana() {
		stage.setIconified(true);

	}
	
	
	public static double x,y;		
	@FXML
	public void pressed(MouseEvent event) {
		x=event.getSceneX();
		y=event.getSceneY();		
	}
	
	@FXML
	public void dragged(MouseEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);
	}	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("");
		
		
		
		btnCalculador.setText("Calculador\nde fuerzas");
		btnCalculador.setWrapText(true);
		
		//btnCalculador.textProperty().setValue("Calculador de fuerzas");
		
		VBSub.getChildren().remove(btnEjercicios1);
		VBSub.getChildren().remove(btnEjercicios2);

		
		File[] paths;

		paths = File.listRoots();
		
		String username = System.getProperty("user.home");
	    System.out.println("username = " + username);
	    new File (username+"\\SistFuerzasFiles\\imgEjercicios1").mkdirs();
	    
		//imgSistema2 = new Image("file:///"+username+"\\SistFuerzasFiles\\imgEjercicios\\Ejer1.png", 500, 355, true, true);// Ancho.alto
//C:\Users\alberto\SistFuerzasFiles\imgEjercicios

	    /*
		for(File path:paths)
		{
		    System.out.println("Drive Name: "+path);
		    new File (path+"Users\\alberto\\SistFuerzasFiles2").mkdir();
		    new File (path+"Users\\alberto\\SistFuerzasFiles2.txt");
		   System.out.println(new File(path+"Users\\alberto\\SistFuerzasFiles2").exists());
		    break;
		    
		}*/
		
		
		
		


		btnAgregar.setDisable(false);
		
		
		titleBar.setPrefWidth(1250);
		titleBar.setPrefHeight(40);
		titleBar.toFront();
		
		titleBar.setOnMousePressed(e->{
			pressed(e);
			
		});
		
		titleBar.setOnMouseDragged(e->{
			dragged(e);
			
		});
		
		

		
		btnMin.setPrefSize(40, 39);
		btnCerrar.setPrefSize(40, 39);


		
		btnMin.getStyleClass().add("titleButtons");
		btnCerrar.getStyleClass().add("titleButtons");
		
		btnCerrar.setGraphic(new ImageView("/images/close2.png"));
		btnMin.setGraphic(new ImageView("/images/min2.png"));

		
		
		titleBar.getStyleClass().add("titleBar");

		contenedor.getStyleClass().add("backgrounds");
		contenedor.setPrefWidth(1100);
		contenedor.setPrefHeight(580);
		
		btnRegresar.setPrefHeight(60);
		btnRegresar.setPrefWidth(150);
		
		btnAgregar.setPrefHeight(60);
		btnAgregar.setPrefWidth(150);
		
		
		btnPerfil.setPrefHeight(60);
		btnPerfil.setPrefWidth(150);
		
		btnEjercicios.setPrefHeight(60);
		btnEjercicios.setPrefWidth(150);
		
		btnEjercicios1.setPrefHeight(40);
		btnEjercicios1.setPrefWidth(150);
		
		btnEjercicios2.setPrefHeight(40);
		btnEjercicios2.setPrefWidth(150);
		
		btnCalculador.setPrefHeight(60);
		btnCalculador.setPrefWidth(150);
		
	
		
		
		VBMenu.setPrefHeight(580);
		VBMenu.setPrefWidth(150);
		
		

		VBMenu.getStyleClass().add("VBMenu");
		
		btnAgregar.getStyleClass().add("btns");		
		btnRegresar.getStyleClass().add("btns");
		btnPerfil.getStyleClass().add("btns");
		btnEjercicios.getStyleClass().add("btns");
		btnEjercicios1.getStyleClass().addAll("btns","btnEjercicios1");
		btnEjercicios2.getStyleClass().addAll("btns","btnEjercicios2");
		btnCalculador.getStyleClass().addAll("btns","btnCalc");
		
		btnRegresar.setPadding(Insets.EMPTY);
		btnPerfil.setPadding(Insets.EMPTY);
	//	btnEjercicios.setPadding(Insets.EMPTY);

	
		btnPerfil.setGraphic(new ImageView("/images/userLogo1.png"));
		btnRegresar.setGraphic(new ImageView("/images/salir.png"));
		btnEjercicios.setGraphic(new ImageView("/images/ejercicios.png"));
		btnEjercicios1.setGraphic(new ImageView("/images/vector.png"));
		btnEjercicios2.setGraphic(new ImageView("/images/dcl.png"));
		btnCalculador.setGraphic(new ImageView("/images/calculador.png"));
		btnAgregar.setGraphic(new ImageView("/images/add.png"));
		
		try {
			Tabs valid = new Tabs();		
			if(!valid.user.getUsuario().equals("root")) {
				//btnAgregar.setDisable(true);
				exit.getChildren().remove(0);				
			}
			}catch(Exception e) {
				Formulario form = new Formulario();
				if(!form.cuenta.getUsuario().equals("root")) {
					//btnAgregar.setDisable(true);
					exit.getChildren().remove(0);				

				}
				
				
			}

		
		

		ventanaPrincipal.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		contenedor.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
	    
	    
	    try {
	    	ventanaEjer = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
	    	VentanaCalc = FXMLLoader.load(getClass().getResource("/view/FXMLCalculator.fxml"));
	    	ventanaAdd = FXMLLoader.load(getClass().getResource("/view/FXMLAdd.fxml"));

			
			contenedor.getChildren().addAll(ventanaAdd);
			contenedor.getChildren().addAll(VentanaCalc);
			contenedor.getChildren().addAll(ventanaEjer);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    
	

	}
}
