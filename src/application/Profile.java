package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
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
	private VBox picture,accountDetails,titlePic,titleAccount,photoBox;
	@FXML
	private HBox info1,info2,titlesBox,namesBox;
	@FXML
	private Label textUser,textAccount,userName,firstNametxt,lastNametxt;
	@FXML
	private ImageView photoProfile;
	@FXML
	private ProgressBar progressEjer1,progressEjer2;
	@FXML
	TextField firstName,lastName;
	
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

		
		photoProfile.setImage(new Image(getClass().getResourceAsStream("/images/avatar.png"), 260, 200, true, true));
		photoProfile.getStyleClass().add("photoProfile");
		
		photoBox.setAlignment(Pos.CENTER);
		userName.getStyleClass().add("userName");
		userName.setText(getUserName().getUsuario());
		
		//ProgressBar bar = new ProgressBar(0);
		progressEjer1.setProgress(0.50);
		progressEjer2.setProgress(0.80);
		
		info1.setSpacing(5);
		info2.setSpacing(5);
		photoBox.setSpacing(7);
		info1.setAlignment(Pos.CENTER);
		info2.setAlignment(Pos.CENTER);
		titlesBox.setAlignment(Pos.CENTER);
		namesBox.setAlignment(Pos.CENTER);

		firstNametxt.getStyleClass().add("userName");
		lastNametxt.getStyleClass().add("userName");
		firstName.setPrefWidth(170);
//		progressEjer.setCursor(//10);
		lastName.setPrefWidth(170);
		
		firstName.setText(getUserName().getNombre());
		lastName.setText(getUserName().getApellidos());


		
		//FXTextField ad = new FXTextField();
		
		//		System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());
		//mainBox.setSpacing(30);
		
		//accountDetails.setPrefHeight(150);
	
		

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
