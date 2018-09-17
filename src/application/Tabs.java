/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Tabs extends Application{
    Stage ventana;
    Scene escena1,escena2;
    TextField VResultante;
    public static Usuario user=null;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
		String username = System.getProperty("user.home");
	    new File (username+"\\SistFuerzasFiles\\imgEjercicios1").mkdirs();
	    new File (username+"\\SistFuerzasFiles\\imgEjercicio2").mkdirs();


        
        //Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        
        //Ventana del programa
        ventana = primaryStage;
        
        
        //----DISEÑO DEL INICIO DE SESION--//
        
        Label titulo = new Label("\t    SISTEMA DE FUERZAS - ¡BIENVENIDO!");
        
        
        //Estilo del boton Salir
        Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e-> ventana.close());
            btnSalir.getStyleClass().add("button-raised");
            
            
        //Estilo del boton Entrar
        Button btnEntrar = new Button("Entrar");
        btnEntrar.getStyleClass().add("button-raised");
        btnEntrar.setOnAction(e-> {
            ventana.setScene(escena2);
        });
        
        
        
        //Creando caja del titulo
        HBox cajaTitulo = new HBox(40);
        cajaTitulo.getChildren().addAll(titulo);        
        cajaTitulo.getStyleClass().add("title");//Añadiendole una clase para el css


        
       
        //Creando la el field de password y mail
        PasswordField pass = new PasswordField();
        TextField mail = new TextField();
        
        
        HBox cajaBotones = new HBox(40);  //Eliminar-----------   
        
        VBox cajaFields = new VBox(10);//Almacenara los textFields de correo,contraseña y el boton de iniciar sesion
        
        
        //Diseñando el boton de iniciar sesion
        Button btnIniciar = new Button("Comenzar");
        btnIniciar.setOnAction(e-> ventana.setScene(escena2));//Al oprimir abrira la ventantana de ejercicio1
        btnIniciar.getStyleClass().add("button-raised");//Estilo del css        
        btnIniciar.setPrefSize(100,40);//Tamaño
        btnIniciar.getStyleClass().add("btnLogin");//Estilo css      
        
        //Añadiendo los fileds al VBox
        cajaFields.getChildren().addAll(mail,pass,btnIniciar);
        
        
        cajaBotones.getChildren().addAll(btnSalir,btnEntrar);//----Eliminar
        cajaBotones.setPadding(new Insets(1,5,5,5));//ELiminar

        
        //Creando la caja principal, almacena todo el login
        VBox menu = new VBox(30);
        menu.getChildren().addAll(cajaTitulo,cajaFields,cajaBotones);
        menu.setPadding(new Insets(15,15,15,15));
        
        
        //Añadiendo clases para CSS
        pass.getStyleClass().add("pass");
        mail.getStyleClass().add("mail");
        
        
        //Añadiendo el placeHolder a los fields
        pass.setPromptText("password");
        mail.setPromptText("example@hotmail.com");
        
        //Modificando tamaños de fields
        pass.setPrefHeight(50);
        mail.setPrefHeight(50);
        
        pass.setPrefWidth(10);
        mail.setPrefWidth(10);
        
        

        btnIniciar.setOnAction(e->{
        	user=null;
        	user = new Usuario();        	
        	String passwordTxt =new String( pass.getText());
        	
        	if(!pass.getText().equals("") && !mail.getText().equals("")) {
        		
        		user.setUsuario(mail.getText());
        		user.setPassword(pass.getText());
        		
        		try {
					if(ingresar(user) || (pass.getText().equals("root")&&mail.getText().equals("root"))) {
						
						//ventana.setScene(escena2);//===============PAra utilizar el otro estilo
						Window win = new Window(user);
						win.crearVentana(e);
					}else {
						//Datos incorrectos
						Alert incorrect = new Alert(Alert.AlertType.WARNING);
						incorrect.setTitle("Datos incorrectos");
						incorrect.setContentText("Sus datos son incorrectos o el usuario no existe");
					    ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
					    incorrect.setGraphic(error);
					    incorrect.setHeaderText(null);
					    incorrect.showAndWait();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        	}else {
        		//Campos vacios
        		Alert camposVacios = new Alert(Alert.AlertType.WARNING);
        		camposVacios.setTitle("Campos vacios");
        		camposVacios.setContentText("Por favor rellene ambos campos para poder ingresar");
    			ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
                camposVacios.setGraphic(error);
                camposVacios.setHeaderText(null);
                camposVacios.showAndWait();

        	}
        	
        });

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //..CREACION DEL TABPANE-----
        TabPane login = new TabPane();
        Tab signUp= new Tab("Registrar");
        Tab signIn=new Tab("Iniciar sesión");
        

        signUp.setClosable(false);
        signIn.setClosable(false);
        login.getTabs().addAll(signUp,signIn);
        
        //Estilo CSS
        signUp.getStyleClass().add("tabs");
        signIn.getStyleClass().add("tabs");  
        
        //Añadiendo a pestaña 2 el contenido de "menu"(login)
        signIn.setContent(menu);
        login.getStyleClass().add("login");
        
        
        //Añadiendo el formulario de registro a pestaña 1
        Parent formulario = FXMLLoader.load(getClass().getResource("/view/FXMLForm.fxml"));        
        signUp.setContent(formulario);
        
        
        
        escena1 = new Scene(login,510,465);//tamaño del login de inicio
        escena1.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());        
       
        
        
        //---ESCENA 2----DISEÑO DE SEGUNDA VENTANA
        //escena2
       TabPane layout = new TabPane();
       Tab tab1 = new Tab("Ejercicios");       
       Tab tab2 = new Tab("Calculador");
       Tab tab3 = new Tab("Ejercicios....");
       
       
       tab1.setClosable(false);
       tab2.setClosable(false);
       layout.getTabs().addAll(tab1,tab2,tab3);  
        
        StackPane menu2 = new StackPane();   
        
        Button sigEjercicio = new Button("Siguiente ejercicio");
        sigEjercicio.setOnAction(e->{
            
        });
        
        Button btnRegresar = new Button("Regresar");
        btnRegresar.setOnAction(e->{
        ventana.setScene(escena1);
        });
        
        HBox cajaBtn = new HBox(10);
        cajaBtn.setPadding(new Insets(0,0,10,995));
        cajaBtn.getChildren().addAll(btnRegresar);
        
        VBox cajaMayor = new VBox();
        //cajaMayor.getChildren().addAll(root,cajaBtn);//Root es el tipo ejercicio1
        menu2.getChildren().addAll(cajaMayor);
        tab1.setContent(menu2);
        
        
        
        
        
        

       //Añadiendo a la segunda pestaña la calculadora
        Parent calculadora = FXMLLoader.load(getClass().getResource("/view/FXMLCalculator.fxml"));
       tab2.setContent(calculadora);
        
        
        escena2 = new Scene(layout,1100,580);//1050,550//970,580
        ventana.setScene(escena1);
        ventana.setTitle("SISTEMA DE FUERZAS");
        ventana.setResizable(false);
        ventana.show();
    }
    
    public boolean ingresar(Usuario user) throws FileNotFoundException {
    	boolean band = false;
		String nombre="",apellido="",nacimiento="",usuario="",password="",mail="";
		int maxEjer1=0,maxEjer2=0;
		RandomAccessFile file = new RandomAccessFile(
				 System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			try {
				while(!band && file.getFilePointer()<file.length()) {
					nombre = file.readLine();				
					apellido = file.readLine();
					nacimiento = file.readLine();
					usuario = file.readLine();
					password = file.readLine();
					band = usuario.equals(user.getUsuario()) && password.equals(user.getPassword());
					mail = file.readLine();
					maxEjer1=file.readInt();
					maxEjer2=file.readInt();

				}
				
				if(band) {
				user.setNombre(nombre);
				user.setApellidos(apellido);
				user.setNacimiento(java.sql.Date.valueOf(nacimiento));
				user.setCorreo(mail);
				user.setMaxEjer1(maxEjer1);
				user.setMaxEjer2(maxEjer2);
				System.out.println("Ejercicios max:"+user.getMaxEjer1());
				}
		
				
				
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		
		return band;
    	
    	
    }

    public static void main(String[] args) {
        launch(args);
    }

   
}
