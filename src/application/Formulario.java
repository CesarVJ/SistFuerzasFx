package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;

public class Formulario implements Initializable {

	@FXML
	private HBox boxName, boxDate;
	@FXML
	private VBox boxUser;
	@FXML
	private Label lblTitulo, text, lblName, lblApellido, lblEdad, lblUser, lblPass;
	@FXML
	private PasswordField password, passwordConfirm;

	@FXML
	private Button btnRegistrarse;
	@FXML
	private VBox cajaPrincipal;

	@FXML
	private TextField nombre, apellido, edad, usuario, mail;

	@FXML
	private DatePicker birthDay;

	public static Usuario cuenta;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cajaPrincipal.setSpacing(20);
		lblTitulo.setText("Bienvenido a SistemaFuerzas, registrate para comenzar!");
		cajaPrincipal.setPadding(new Insets(10, 10, 10, 25));

		boxUser.setSpacing(20);
		boxName.setSpacing(10);
		btnRegistrarse.getStyleClass().add("btnLogin");
		btnRegistrarse.setId("btnRegistrarse");

		password.setPromptText("Contraseña");
		passwordConfirm.setPromptText("Repetir contraseña");

		usuario.setPromptText("Nombre de usuario");
		nombre.setPromptText("Nombre(s)");
		apellido.setPromptText("Apellidos");
		mail.setPromptText("email@example.com");
		birthDay.setPromptText("dd/mm/aaaa");
		// mail.setPrefWidth(200);
		boxDate.setSpacing(10);

		cajaPrincipal.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());
		// btnRegistrar.getStylesheets().add(Tabs.class.getResource("Estilos.css").toExternalForm());

	}

	public void registrarUsuarios(ActionEvent event) {

		try {
			
			String pass = new String(password.getText());
			String passConfirm = new String(passwordConfirm.getText());

			if (nombre.getText().equals("") || apellido.getText().equals("") || birthDay.getValue() == null
					|| usuario.getText().equals("") || password.getText().equals("") || mail.getText().equals("")) {
				Alert requerido = new Alert(Alert.AlertType.WARNING);
				requerido.setTitle("Datos requeridos");
				requerido.setContentText("Por favor verifica que todos tus datos esten correctos e intentalo de nuevo");
				ImageView error = new ImageView(new Image(
						getClass().getResourceAsStream("/images/respuesta_incorrecta.png"), 50, 50, true, true));
				requerido.setGraphic(error);
				requerido.setHeaderText(null);
				requerido.showAndWait();
				return;
			}
			
			

			if (pass.equals(passConfirm)) {
				boolean usuarioExistente = verificarExistencias(usuario.getText());
				if (!usuarioExistente) {
					if (!verificarEmail(mail.getText())) {
						Alert emailError = new Alert(Alert.AlertType.WARNING);
						emailError.setTitle("Email incorrecto");
						emailError.setContentText("El email que has proporcionado es incorrecto, verificalo");
						ImageView error = new ImageView(
								new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"), 50, 50,
										true, true));
						emailError.setGraphic(error);
						emailError.setHeaderText(null);
						emailError.showAndWait();
						return;
					}
					RandomAccessFile file = new RandomAccessFile(
							 System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");

					
					//ACA VA EL REGISTRO EN EL ARCHIVO y abrir la ventana
					cuenta = new Usuario();
					cuenta.setNombre(nombre.getText());
					cuenta.setApellidos(apellido.getText());
					cuenta.setNacimiento(java.sql.Date.valueOf(birthDay.getValue()));
					cuenta.setUsuario(usuario.getText());
					cuenta.setPassword(password.getText());
					cuenta.setCorreo(mail.getText());
					cuenta.setMaxEjer1(0);
					cuenta.setMaxEjer2(0);
					file.close();

					
					registrarUsuario(cuenta);
					//abrirVentana(event);
					Alert iniciaSes = new Alert(Alert.AlertType.WARNING);
					iniciaSes.setTitle("Registro exitoso");
					iniciaSes.setContentText(cuenta.getUsuario()+" inicia sesion para continuar.");
		            ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
		            iniciaSes.setGraphic(error);
		            iniciaSes.setHeaderText(null);
		            iniciaSes.showAndWait();
					return;

					
					
					
				}else {
					Alert existencia = new Alert(Alert.AlertType.WARNING);
					existencia.setTitle("El usuario ya existe");
					existencia.setContentText("El nombre de usuario ya esta registrado, intenta con otro");
		            ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
		            existencia.setGraphic(error);
		            existencia.setHeaderText(null);
		            existencia.showAndWait();																		
				}
			}else {
				Alert coincidencia = new Alert(Alert.AlertType.WARNING);
				coincidencia.setTitle("Confirmacion de contraseña");
				coincidencia.setContentText("Tus contraseñas no coinciden, intentalo de nuevo");
	            ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
	            coincidencia.setGraphic(error);
	            coincidencia.setHeaderText(null);
	            coincidencia.showAndWait();
				
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean verificarExistencias(String user) throws IOException {
		boolean band = false;
		String nombre,apellido,nacimiento,usuario,password,mail;
		int maxEjer1,maxEjer2;
		RandomAccessFile file = new RandomAccessFile(
				 System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			while(!band && file.getFilePointer()<file.length()) {
				nombre = file.readLine();				
				apellido = file.readLine();
				nacimiento = file.readLine();
				usuario = file.readLine();
				band = usuario.equals(user);
				password = file.readLine();
				mail = file.readLine();
				maxEjer1=file.readInt();
				maxEjer2=file.readInt();
	
			}
			file.close();
		

		
		return band;
	}
	
	
	

	public boolean verificarEmail(String correo) {
		Pattern expression = Pattern
				.compile("^[_a-zA-Z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Z-a-z0-9-]+(\\.[A-Za-z0-9]+)");

		Matcher match = expression.matcher(correo);

		return match.find();

	}
	
	
	public void registrarUsuario(Usuario user) throws IOException {
		try {
			RandomAccessFile file = new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			
			
			file.seek(file.length());
			file.writeBytes(user.getNombre()+"\n");
			file.writeBytes(user.getApellidos()+"\n");
			file.writeBytes(user.getNacimiento()+"\n");			
			file.writeBytes(user.getUsuario()+"\n");	
			file.writeBytes(user.getPassword()+"\n");			
			file.writeBytes(user.getCorreo()+"\n");		
			file.writeInt(1);
			file.writeInt(1);
			file.close();
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@FXML
	public void abrirVentana(ActionEvent event) {

		Window win = new Window();
		win.crearVentana(event);

	}

}
