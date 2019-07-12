package application;
import model.Zipper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Unzipper;
import model.Usuario;


public class Profile implements Initializable{
	
	@FXML
	private AnchorPane profile;
	//@FXML
	//private HBox mainBox;
	@FXML
	private VBox picture,accountDetails,titlePic,titleAccount,photoBox,exportBtns1,importBtns,cajaNombre,cajaApellidos,cajaCorreo,cajaNacimiento,cajaContra,cajaContraConfirm,cajaDatos,VBoxPhoto;
	@FXML
	private HBox info1,info2,cajaH2,cajaH3,exportBtns,cajaBoton,cajaH1;
	@FXML
	private Label textUser,textAccount,userName,firstNametxt,lastNametxt,txtBirthDay,txtMail,txtPassword,txtConfirmPass,progressText2,progressText1;
	@FXML
	private ImageView photoProfile,exportConf,importConf;
	@FXML
	private ProgressBar progressEjer1,progressEjer2;
	@FXML
	TextField firstName,lastName,correoTF;
	@FXML
	DatePicker birthDay;
	@FXML
	PasswordField password,passwordConfirm;
	@FXML
	Button btnGuardar;
	static String rutaAvatar="",nameFileAvatar="";
	
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
		
		//exportApp.getStyleClass().add("boton");
		exportConf.getStyleClass().add("boton");
		importConf.getStyleClass().add("boton");
		
		btnGuardar.getStyleClass().add("btnGuardar");
		
		cajaBoton.setAlignment(Pos.CENTER);

		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
		picture.setEffect(ef);
		accountDetails.setEffect(ef);
		accountDetails.setSpacing(40);
		picture.setSpacing(10);
		exportBtns.setSpacing(15);

		File avatar=null;
		
		if(System.getProperty("os.name").contains("Windows")) {
			avatar = new File(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(getUserName().getUsuario())+".png");
		}else {
			avatar = new File(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(getUserName().getUsuario())+".png");
		}
		
		if(avatar.exists()) {
			if(System.getProperty("os.name").contains("Windows")) {
				photoProfile.setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\avatares\\"+(getUserName().getUsuario())+".png", 260, 200, false, false));// Ancho.alto
			}else {
				photoProfile.setImage(new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(getUserName().getUsuario())+".png", 260, 200, false, false));// Ancho.alto
			}
		}else {
			photoProfile.setImage(new Image(getClass().getResourceAsStream("/images/avatar.png"), 260, 200, true, true));
		}
		
		
		
		
		photoProfile.getStyleClass().add("photoProfile");
		VBoxPhoto.getStyleClass().add("VBoxPhoto");
		
		//exportApp.setImage(new Image(getClass().getResourceAsStream("/images/exportApp.png"), 30, 30, true, true));
		//exportApp.getStyleClass().add("photoProfile");
		
		exportConf.setImage(new Image(getClass().getResourceAsStream("/images/exportConf.png"), 30, 30, true, true));
		exportConf.getStyleClass().add("photoProfile");
		
		importConf.setImage(new Image(getClass().getResourceAsStream("/images/importConf.png"), 30, 30, true, true));
		importConf.getStyleClass().add("photoProfile");
		
		
		
		photoBox.setAlignment(Pos.CENTER);
		VBoxPhoto.setAlignment(Pos.CENTER);
		userName.getStyleClass().add("userName");
		userName.setText(getUserName().getUsuario());
		
		//ProgressBar bar = new ProgressBar(0);
		//progressEjer1.setProgress(0.50);
		//progressEjer2.setProgress(0.80);
		setMaxEjercicios();

		setProgressEjer();
		
		
		
		info1.setSpacing(5);
		info2.setSpacing(5);
		photoBox.setSpacing(12);
		info1.setAlignment(Pos.CENTER);
		info2.setAlignment(Pos.CENTER);
		
		cajaH1.setAlignment(Pos.CENTER);
		cajaH2.setAlignment(Pos.CENTER);
		cajaH3.setAlignment(Pos.CENTER);

		exportBtns.setAlignment(Pos.CENTER);
		
		cajaDatos.setAlignment(Pos.CENTER);
		cajaNombre.setAlignment(Pos.CENTER);
		cajaApellidos.setAlignment(Pos.CENTER);
		cajaCorreo.setAlignment(Pos.CENTER);
		cajaNacimiento.setAlignment(Pos.CENTER);
		cajaContra.setAlignment(Pos.CENTER);
		cajaContraConfirm.setAlignment(Pos.CENTER);

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
		passwordConfirm.setPrefWidth(170);
		password.setPrefWidth(170);
		birthDay.setPrefWidth(170);
		VBoxPhoto.setPrefWidth(150);

		
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
			Path files=null;
			
			Path destino= null;
			File fil =null;
			
			try {
			files= Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\users.dat");
			destino=Paths.get(System.getProperty("user.home")+"\\Desktop\\Export");			
			fil=new File(System.getProperty("user.home")+"\\Desktop\\Export");
			}catch(Exception ex) {
				files= Paths.get(System.getProperty("user.home")+"/SistFuerzasFiles/users.dat");
				destino=Paths.get(System.getProperty("user.home")+"/Desktop/Export");			
				fil=new File(System.getProperty("user.home")+"/Desktop/Export");
			}

			
			
		     try {
		    	 	//Ubicacion destino del zip
		    	 try {
		            Zipper z = new Zipper(new File(System.getProperty("user.home")+"\\Desktop\\Export\\FisicaFiles.zip"));
		            //z.zip(new File(System.getProperty("user.home")+"\\Desktop\\Export\\prueba"));		            
		            //Ruta a zippear
		            z.zip(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\"));
		    	 }catch(Exception ex2) {
		    		 Zipper z = new Zipper(new File(System.getProperty("user.home")+"/Desktop/Export/FisicaFiles.zip"));
			            //z.zip(new File(System.getProperty("user.home")+"\\Desktop\\Export\\prueba"));		            
			            //Ruta a zippear
			            z.zip(new File(System.getProperty("user.home")+"/SistFuerzasFiles/"));
		    	 }
		            
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

		     
		 	Alert resultado = new Alert(Alert.AlertType.WARNING);

			resultado.setTitle("Exportacion exitosa");
			resultado.setContentText("La configuracion de su aplicacion se ha realizado correctamente");
			ImageView correcta = new ImageView(
					new Image(getClass().getResourceAsStream("/images/respuesta_correcta.png"), 50, 50, true, true));
			resultado.setGraphic(correcta);
			resultado.setHeaderText(null);
	    	resultado.showAndWait();
		     
		     
		     

			
		});
		
		
		btnGuardar.setOnMouseClicked(e->{
			
			RandomAccessFile file=null;
			try {
			file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			}catch(Exception exp) {
				try {
					file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
			boolean band = false;
			String nombre="",apellido="",nacimiento="",usuario="",password1="",mail="";
			int maxEjer1=0,maxEjer2=0,visitas=0;
			long pointer=0;
		try {
			while(!band && file.getFilePointer()<file.length()) {
				pointer=file.getFilePointer();
				nombre = file.readLine();				
				apellido = file.readLine();
				nacimiento = file.readLine();
				usuario = file.readLine();
				password1 = file.readLine();
				band = usuario.equals(getUserName().getUsuario());
				mail = file.readLine();
				maxEjer1=file.readInt();
				maxEjer2=file.readInt();
				visitas=file.readInt();

			}
			
			
			if(band) {
				file.seek(pointer);
				file.writeBytes((firstName.getText().equals("")?nombre:firstName.getText())+"\n");
				file.writeBytes((lastName.getText().equals("")?apellido:lastName.getText())+"\n");
				file.writeBytes(nacimiento+"\n");			
				file.writeBytes(usuario+"\n");	
				file.writeBytes((!password.getText().equals("")&&password.getText().equals(passwordConfirm.getText())?password.getText():password1)+"\n");			
				file.writeBytes((correoTF.getText().equals("")?mail:correoTF.getText())+"\n");		
				file.writeInt(maxEjer1);
				file.writeInt(maxEjer2);
				file.writeInt(visitas);
			}
			file.close();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		});
		
		importConf.setOnMouseClicked(e->{
			
			FileChooser select = new FileChooser();
			select.setTitle("Elegir imagen");
			Stage stage = (Stage)profile.getScene().getWindow();
			
			File file = select.showOpenDialog(stage);
			file.setWritable(true);
			Unzipper unz= new Unzipper();
			try {
				unz.Descomprimir(file.getPath(),System.getProperty("user.home")+"\\SistFuerzasFiles");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				try {
					unz.Descomprimir(file.getPath(),System.getProperty("user.home")+"/SistFuerzasFiles");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			
			
			
			
			Alert resultado = new Alert(Alert.AlertType.WARNING);

			resultado.setTitle("Importacion exitosa");
			resultado.setContentText("Importación exitosa, vuelva a iniciar sesion para aplicar los cambios correctamente");
			ImageView correcta = new ImageView(
					new Image(getClass().getResourceAsStream("/images/respuesta_correcta.png"), 50, 50, true, true));
			resultado.setGraphic(correcta);
			resultado.setHeaderText(null);
	    	resultado.showAndWait();
			
			
			
		});
		
		
		
		
		
		
		
		



		//setTooltip(new Tooltip("Selecciona el modo en el que desees ingresar los datos."));

		//FXTextField ad = new FXTextField();
		
		//		System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());
		//mainBox.setSpacing(30);
		
		//accountDetails.setPrefHeight(150);
	
		exportBtns.setAlignment(Pos.CENTER);
		exportBtns1.setAlignment(Pos.CENTER);
		//exportBtns2.setAlignment(Pos.CENTER);
		importBtns.setAlignment(Pos.CENTER);
		
		
		if(!getUserName().getUsuario().equals("root")) {
			exportBtns.getChildren().removeAll(exportBtns1);
		}else {
			exportBtns.getChildren().remove(importBtns);

		}
		
		
		photoProfile.setOnMouseClicked(e->{cambiarAvatar();});
		
		

	}
	
	public void cambiarAvatar() {
		
		try {
		FileChooser select = new FileChooser();
		select.setTitle("Elegir imagen");
		Stage stage = (Stage)profile.getScene().getWindow();
		
		File file = select.showOpenDialog(stage);
		file.setWritable(true);
		
		rutaAvatar= file.getAbsolutePath();
		nameFileAvatar = file.getName();
	       

		System.out.println(rutaAvatar);
		
		try {
			Files.copy(Paths.get(rutaAvatar),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\avatares\\"+(getUserName().getUsuario())+".png"),StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception nm) {
				Files.copy(Paths.get(rutaAvatar),Paths.get(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(getUserName().getUsuario())+".png"),StandardCopyOption.REPLACE_EXISTING);
		}
		
		if(System.getProperty("os.name").contains("Windows")) {
			photoProfile.setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\avatares\\"+(getUserName().getUsuario())+".png", 260, 200, false, false));// Ancho.alto
		}else {
			photoProfile.setImage(new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(getUserName().getUsuario())+".png", 260, 200, false, false));// Ancho.alto
		}

		
		
		}catch(Exception ex) {
			System.out.println("No se selecciono ninguna imagen");
		}

	}
	
	
	
	
	
	
	
	public void setProgressEjer() {
		try {
			RandomAccessFile file =null;
			try {
			file= new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			}catch(Exception ee) {
				file= new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");

			}
	
			boolean band = false;
			String nombre="",apellido="",nacimiento="",usuario="",password="",mail="";
			int maxEjer1=0,maxEjer2=0,visitas=0;
			long pointer=0;
		while(!band && file.getFilePointer()<file.length()) {
			pointer=file.getFilePointer();
			nombre = file.readLine();				
			apellido = file.readLine();
			nacimiento = file.readLine();
			usuario = file.readLine();
			password = file.readLine();
			band = usuario.equals(getUserName().getUsuario());
			mail = file.readLine();
			maxEjer1=file.readInt();
			maxEjer2=file.readInt();
			visitas=file.readInt();

		}
		
		
		float porcentaje1=((maxEjer1-1)*100)/(Float.parseFloat(progressText1.getText()));
		float porcentaje2=((maxEjer2-1)*100)/(Float.parseFloat(progressText2.getText()));


		progressEjer1.setProgress(((float)(porcentaje1))/100);
		progressEjer2.setProgress(((float)(porcentaje2))/100);
		
		
		progressText1.setText((maxEjer1-1)+"/"+progressText1.getText());
		progressText2.setText((maxEjer2-1)+"/"+progressText2.getText());


		file.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public  void setMaxEjercicios() {
		
		
		int sizeFile1= 0;
		int sizeFile2= 0;
		
		try {
		sizeFile1=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length;	
		sizeFile2=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length;
		}catch(Exception eee) {
			if(!System.getProperty("os.name").contains("Windows")) {				
				sizeFile1=new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length;	
				sizeFile2=new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2").listFiles().length;
			}
		
		}

		progressText1.setText((sizeFile1+1)+"");
		progressText2.setText(sizeFile2+"");
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
