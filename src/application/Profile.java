package application;
import model.Zipper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Usuario;


public class Profile implements Initializable{
	
	@FXML
	private AnchorPane profile;
	//@FXML
	//private HBox mainBox;
	@FXML
	private VBox picture,accountDetails,titlePic,titleAccount,photoBox,exportBtns1,exportBtns2,importBtns;
	@FXML
	private HBox info1,info2,titlesBox,titlesBox2,namesBox,mailBox,titlesBox3,passBox,exportBtns;
	@FXML
	private Label textUser,textAccount,userName,firstNametxt,lastNametxt,txtBirthDay,txtMail,txtPassword,txtConfirmPass;
	@FXML
	private ImageView photoProfile,exportConf,exportApp,importConf;
	@FXML
	private ProgressBar progressEjer1,progressEjer2;
	@FXML
	TextField firstName,lastName,correoTF;
	@FXML
	DatePicker birthDay;
	@FXML
	PasswordField password,passwordConfirm;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		profile.getStyleClass().add("backgrounds");
		profile.getStyleClass().add("profileBackground");
		
		picture.getStyleClass().add("profileBox");
		accountDetails.getStyleClass().add("profileBox");
		titlePic.getStyleClass().add("titlesProf");
		titleAccount.getStyleClass().add("titlesProf");
		
		textUser.getStyleClass().add("titlesText");
		textAccount.getStyleClass().add("titlesText");
		
		exportApp.getStyleClass().add("boton");
		exportConf.getStyleClass().add("boton");

		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
		picture.setEffect(ef);
		accountDetails.setEffect(ef);
		accountDetails.setSpacing(10);
		picture.setSpacing(10);
		exportBtns.setSpacing(15);

		
		photoProfile.setImage(new Image(getClass().getResourceAsStream("/images/avatar.png"), 260, 200, true, true));
		photoProfile.getStyleClass().add("photoProfile");
		
		exportApp.setImage(new Image(getClass().getResourceAsStream("/images/exportApp.png"), 30, 30, true, true));
		exportApp.getStyleClass().add("photoProfile");
		
		exportConf.setImage(new Image(getClass().getResourceAsStream("/images/exportConf.png"), 30, 30, true, true));
		exportConf.getStyleClass().add("photoProfile");
		
		importConf.setImage(new Image(getClass().getResourceAsStream("/images/importConf.png"), 30, 30, true, true));
		importConf.getStyleClass().add("photoProfile");
		
		
		
		photoBox.setAlignment(Pos.CENTER);
		userName.getStyleClass().add("userName");
		userName.setText(getUserName().getUsuario());
		
		//ProgressBar bar = new ProgressBar(0);
		progressEjer1.setProgress(0.50);
		progressEjer2.setProgress(0.80);
		
		info1.setSpacing(5);
		info2.setSpacing(5);
		photoBox.setSpacing(12);
		info1.setAlignment(Pos.CENTER);
		info2.setAlignment(Pos.CENTER);
		titlesBox.setAlignment(Pos.CENTER);
		titlesBox2.setAlignment(Pos.CENTER);

		namesBox.setAlignment(Pos.CENTER);
		exportBtns.setAlignment(Pos.CENTER);
		mailBox.setAlignment(Pos.CENTER);
		titlesBox3.setAlignment(Pos.CENTER);
		passBox.setAlignment(Pos.CENTER);

		firstNametxt.getStyleClass().add("userName");
		lastNametxt.getStyleClass().add("userName");
		txtMail.getStyleClass().add("userName");
		txtBirthDay.getStyleClass().add("userName");
		txtPassword.getStyleClass().add("userName");
		txtConfirmPass.getStyleClass().add("userName");
		
		firstName.setPrefWidth(170);
//		progressEjer.setCursor(//10);
		lastName.setPrefWidth(170);
		correoTF.setPrefWidth(170);
		
		firstName.setText(getUserName().getNombre());
		lastName.setText(getUserName().getApellidos());
		correoTF.setText(getUserName().getCorreo());
		
		try {
		birthDay.setPromptText(getUserName().getNacimiento().getDay()+"/"+getUserName().getNacimiento().getMonth()+"/"+getUserName().getNacimiento().getYear());
		password.setText(getUserName().getPassword());
		}catch(Exception e){
			birthDay.setPromptText("dd/mm/aaaa");
			password.setPromptText("Password");
			
		}
				
		passwordConfirm.setPromptText("Confrima contraseña");
		
		exportConf.setOnMouseClicked(e->{
			//SistFuerzasFiles
			Path files= Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\users.dat");
			Path destino= Paths.get(System.getProperty("user.home")+"\\Desktop\\Export");			
			File fil = new File(System.getProperty("user.home")+"\\Desktop\\Export");
			
		     try {
		    	 	//Ubicacion destino del zip
		            Zipper z = new Zipper(new File(System.getProperty("user.home")+"\\Desktop\\Export\\FisicaFiles.zip"));
		            //z.zip(new File(System.getProperty("user.home")+"\\Desktop\\Export\\prueba"));
		            
		            //Ruta a zippear
		            z.zip(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\"));

		            System.out.println("Listo");
		        } catch (FileNotFoundException e2) {
		            e2.printStackTrace();
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }
			
			//fil.setWritable(true);
			//Files.setPosixFilePermissions(destino, PosixFilePermissions.fromString("rw-r--r--"));	
			//Files.copy(files, Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Export.rar"), StandardCopyOption.REPLACE_EXISTING);
			//Files.copy(Paths.get(texto.getText()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);


			
		});



		//setTooltip(new Tooltip("Selecciona el modo en el que desees ingresar los datos."));

		//FXTextField ad = new FXTextField();
		
		//		System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());
		//mainBox.setSpacing(30);
		
		//accountDetails.setPrefHeight(150);
	
		exportBtns.setAlignment(Pos.CENTER);
		exportBtns1.setAlignment(Pos.CENTER);
		exportBtns2.setAlignment(Pos.CENTER);
		importBtns.setAlignment(Pos.CENTER);
		
		
		if(!getUserName().getUsuario().equals("root")) {
			exportBtns.getChildren().removeAll(exportBtns1,exportBtns2);
		}else {
			exportBtns.getChildren().remove(importBtns);

		}
		
		

	}
	
	

	public static Usuario getUserName() {
		//try {
		Tabs user = new Tabs();
		//System.out.println("TU usuario es "+user.user.getUsuario());
		return user.user;
		//}catch(Exception e) {
			//Formulario user = new Formulario();
			//return user.cuenta;
		//}
	}

}
