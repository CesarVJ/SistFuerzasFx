/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.Shake;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TipoEjercicio1 implements Initializable {

	@FXML
	private VBox caja = new VBox(20);
	@FXML
	private Label textoInferior, tituloEjercicio1;
	@FXML
	private Button btnSiguiente, btnAnterior;
	@FXML
	private TableView table;
	@FXML
	private HBox cajaBotones;
	@FXML
	private TextField VResultante;
	@FXML
	private TextField AnguloResultante;
	@FXML
	private ImageView grafica1;
	@FXML
	private VBox cajaIzquierda, cajaDerecha;
	@FXML
	private HBox cajaVResultante, cajaAnguloResultante;
	@FXML
	private ChoiceBox<String> cambiarModo;
	@FXML
	private AnchorPane ejercicios1;
	
    //int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length;            		


	Image imgSistema;

	Tabs obj = new Tabs();

	ObservableList<ContenidoTabla> data;
	static int numEjercicio = 1, ejercicioMax = 1;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ejercicios1.getStyleClass().add("backgrounds");

		cajaBotones.setSpacing(10);
		btnSiguiente.setDisable(true);
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);

		}
		cajaIzquierda.setPadding(new Insets(10, 10, 0, 10));
		cajaIzquierda.setSpacing(10);

		cajaDerecha.setPadding(new Insets(10, 10, 0, 2));
		cajaDerecha.setSpacing(10);

		cajaVResultante.setPadding(new Insets(10, 10, 10, 10));
		cajaAnguloResultante.setPadding(new Insets(10, 10, 10, 10));
		// cajaAnguloResultante.setPadding(new Insets(15,15,15,15));

		tituloEjercicio1.setText(
				"EJERCICIO 1 \nDado este sistema de fuerzas determina el Vector Resultante. Rellena la siguiente tabla con los datos requeridos"
						+ " y realiza tus operaciones en un papel con ayuda de tu calculadora.(Anotar solo 3 decimales)");

		tituloEjercicio1.setWrapText(true);		
		textoInferior.setText(
				"Al finalizar de rellenar la tabla realiza las operaciones correspondientes para obtener el angulo \ny el Vector resultante(Anota tus resultados y da click en 'calificar')");
		//============================================CAMBIAR ESTO PARA QUE LA IMAGEN CARGUE AL DARLE CLICK AL XML
		grafica1.setImage(
				new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true));

		// Definiendo columnas de la tabla
		TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
		// fuerzas.setMinWidth(0);
		TableColumn angulo = new TableColumn("Angulo");
		// angulo.setMinWidth(0);
		TableColumn Fx = new TableColumn("Fx");
		Fx.setMinWidth(0);
		TableColumn Fy = new TableColumn("Fy");
		Fy.setMinWidth(0);

		TableColumn funcion = new TableColumn("Funcion");
		funcion.setMinWidth(0);
		funcion.setCellValueFactory(new PropertyValueFactory<>("funcion"));

		TableColumn funcion2 = new TableColumn("Funcion");
		funcion2.setMinWidth(0);
		funcion2.setCellValueFactory(new PropertyValueFactory<>("funcion2"));

		table.getColumns().addAll(fuerzas, angulo, funcion, Fx, funcion2, Fy);
		table.resizeColumn(fuerzas, 10);
		table.resizeColumn(angulo, 10);
		table.resizeColumn(funcion, 10);
		table.resizeColumn(funcion2, 10);
		table.resizeColumn(Fx, 10);
		table.resizeColumn(Fy, 10);

		cambiarModo.getItems().addAll("Angulo real", "Angulo complementario");
		cambiarModo.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		tituloEjercicio1.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		table.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		ejercicios1.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		// CAMBIAR MODO
		cambiarModo.setTooltip(new Tooltip("Selecciona el modo en el que desees ingresar los datos."));
		cambiarModo.getSelectionModel().select(1);

		try {
			cambiarModo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				boolean band = true;

				public void changed(ObservableValue ov, Number value, Number new_value) {

					if (new_value.intValue() == 0) {
						// table.getColumns().remove(2);
						table.getColumns().remove(funcion);
						table.getColumns().remove(funcion2);

						table.resizeColumn(fuerzas, 45);
						table.resizeColumn(angulo, 45);
						table.resizeColumn(Fx, 45);
						table.resizeColumn(Fy, 45);

						band = false;

					} else if (new_value.intValue() == 1) {
						if (band == true) {

						} else {
							table.resizeColumn(fuerzas, -45);
							table.resizeColumn(angulo, -45);
							table.resizeColumn(Fx, -45);
							table.resizeColumn(Fy, -45);
							table.getColumns().add(2, funcion);
							table.getColumns().add(4, funcion2);

						}

					}

				}

			});
		} catch (Exception e) {
			System.out.println("Errrororoorr");
		}

		data = FXCollections.observableArrayList(
				// (Indice,newtons,angulo,Fx,Fy,VResultante,AN)
				new ContenidoTabla("", "", "", "", ""), new ContenidoTabla("", "", "", "", ""),
				new ContenidoTabla("", "", "", "", ""));

		fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("fuerzas"));
		angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("angulo"));

		// funcion.setCellFactory(new
		// PropertyValueFactory<ContenidoTabla,ChoiceBox>("funcion"));

		Fx.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("Fx"));
		Fy.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("Fy"));

		table.setItems(data);

	}

	
	@FXML
	private void siguienteEjercicio(ActionEvent event) {

		btnAnterior.setDisable(false);
		numEjercicio++;
		
		

		//if (numEjercicio == ejercicioMax || ejercicioMax == 1) {
			//ejercicioMax++;
		//}
		if(numEjercicio==ejercicioMax || numEjercicio==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length) {
			btnSiguiente.setDisable(true);
		}
		imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		// grafica1.setImage(new
		// Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer"+numEjercicio+".png"),500,400,true,true));
		grafica1.setImage(imgSistema);

		tituloEjercicio1.setText("Estes es otro ejercicio");
		limpiarDatos();
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length);


	}

	public void ejercicioAnterior() {
		numEjercicio -= 1;
		if(numEjercicio==1) {
			imgSistema= new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true);
		}else {
		imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		}
		// grafica1.setImage(new
		// Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer"+(numEjercicio)+".png"),500,400,true,true));
		grafica1.setImage(imgSistema);
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);
		}

		btnSiguiente.setDisable(false);
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length);


	}

	public void limpiarDatos() {

		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				// angulos[i]=Float.parseFloat(bean.getAngulo().getText());
				// fuerzas[i]=Float.parseFloat(bean.getFuerzas().getText());
				// fx[i]=Float.parseFloat(bean.getFx().getText());
				// fy[i]=Float.parseFloat(bean.getFy().getText());
				bean.getAngulo().clear();
				bean.getFuerzas().clear();
				bean.getFx().clear();
				bean.getFy().clear();
			}

		}
	}

	@FXML
	private void regresarInicio(ActionEvent event) {

	}

	@FXML
	private void verificarDatos(ActionEvent event) {
		
	
		
		ObservableList<ContenidoTabla> dataRows = FXCollections.observableArrayList();
		Resultados obj = new Resultados();
		int i = 0;
		float[] angulos = new float[3];
		float[] fuerzas = new float[3];
		float[] fx = new float[3];
		float[] fy = new float[3];

		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				dataRows.add(bean);
				angulos[i] = Float.parseFloat(bean.getAngulo().getText());
				fuerzas[i] = Float.parseFloat(bean.getFuerzas().getText());
				fx[i] = Float.parseFloat(bean.getFx().getText());
				fy[i] = Float.parseFloat(bean.getFy().getText());

			}
			System.out.println(fuerzas[i] + "," + angulos[i] + "  Fx = " + fx[i]);
			i++;

		}
		obj.setSumFx(fuerzas, angulos, 3);
		obj.setSumFy(fuerzas, angulos, 3);
		obj.setVR();
		obj.setAnguloResult();
		System.out.println("La sumatoria de Fx = " + obj.getSumFx());
		System.out.println("La sumatoria de Fy = " + obj.getSumFy());
		System.out.println("Vector resultante : " + String.format("%.3f", obj.getVR()));
		System.out.println("Angulo resultante: " + String.format("%.3f", obj.getAnguloResult()));

		Alert resultado = new Alert(Alert.AlertType.WARNING);

		if (VResultante.getText().equalsIgnoreCase(String.format("%.3f", obj.getVR()))
				&& AnguloResultante.getText().equalsIgnoreCase(String.format("%.3f", obj.getAnguloResult()))) {
			resultado.setTitle("Respuesta correcta");
			resultado.setContentText("Tus resultados han sido los correctos, puedes pasar al siguiente ejercicio.");
			ImageView correcta = new ImageView(
					new Image(getClass().getResourceAsStream("/images/respuesta_correcta.png"), 50, 50, true, true));
			resultado.setGraphic(correcta);
			
			
			
			
			
			System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
			
			if((ejercicioMax)==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length && numEjercicio==ejercicioMax) {
				btnSiguiente.setDisable(true);
			}
				if(btnSiguiente.isDisabled()) {
					if(ejercicioMax==numEjercicio && (ejercicioMax)<new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length+1 ) {
					btnSiguiente.setDisable(false);
					ejercicioMax++;
					}
				}	
				
				System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length);
			
		} else {
			resultado.setTitle("Respuesta incorrecta");
			resultado.setContentText(
					"Alguno de tus resultados es incorrecto, verifica tus operaciones y vuelve a intentarlo");
			ImageView incorrecta = new ImageView(
					new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"), 50, 50, true, true));
			resultado.setGraphic(incorrecta);
        	new Shake(table).play();

		}
		resultado.setHeaderText(null);
		resultado.showAndWait();

	}

}
