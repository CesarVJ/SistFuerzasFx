/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Cesar
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TipoEjercicio1 implements Initializable {

	@FXML
	private VBox caja = new VBox(20);
	@FXML
	private Label textoInferior;
	@FXML
	private   Label tituloEjercicio1;
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
	
	private int contadorFilas;
	private static int vects=3;
	
    //int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length;            		


	Image imgSistema;

	Tabs obj = new Tabs();

	ObservableList<ContenidoTabla> data;
	static int numEjercicio = 1, ejercicioMax = getUserName().getMaxEjer1();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ejercicioMax = getUserName().getMaxEjer1();
		numEjercicio=1;
		ejercicios1.getStyleClass().add("backgrounds");

		cajaBotones.setSpacing(10);
		if(ejercicioMax==1) {
			btnSiguiente.setDisable(true);
		}
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
		System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());


		btnAnterior.setDisable(false);
		numEjercicio++;
		
		

		//if (numEjercicio == ejercicioMax || ejercicioMax == 1) {
			//ejercicioMax++;
		//}
		
		
		
		int lengthFile=0;
		if(System.getProperty("os.name").contains("Windows")) {
			lengthFile=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length+1;
		}else {
			lengthFile=new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length+1;

		}
		
		
		
		if(numEjercicio==ejercicioMax || numEjercicio==lengthFile) {
			btnSiguiente.setDisable(true);
		}
		
		/*try {
		imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		}catch(Exception ex){
			System.out.println("IMAGE LINUX");
		imgSistema = new Image(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		}
		*/
		
		 if(System.getProperty("os.name").contains("Windows")) {
				imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		 }else {
				System.out.println("IMAGE LINUX");
				imgSistema = new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto				
		 }
		
		
		
		
		
		
		// grafica1.setImage(new
		// Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer"+numEjercicio+".png"),500,400,true,true));
		grafica1.setImage(imgSistema);

		//tituloEjercicio1.setText("Estes es otro ejercicio");
		limpiarDatos();
		
		
		contadorFilas=table.getItems().size();
		int numVects=leerEjercicio(numEjercicio);
		int sizeTab=table.getItems().size();
		if(numVects>sizeTab) {
			for(int i=0;i<(numVects-sizeTab);i++) {
				agregarVector();				
			}
		}else if(numVects<sizeTab){
			for(int i=0;i<(sizeTab-numVects);i++) {
				eliminarVector();				
			}
		}
		
		
		
		
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
	/*	try {
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length);
		}catch(Exception exp) {
			System.out.println(new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length);

		}*/
		
		
		
		 if(System.getProperty("os.name").contains("Windows")) {
				System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length);
         }else {
 			System.out.println(new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length);
         }


	}

	public void ejercicioAnterior() {
		numEjercicio -= 1;
		if(numEjercicio==1) {
			imgSistema= new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true);
		}else {
			
			
			/*try {
		imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
			}catch(Exception pp) {
				System.out.println("IMAGE LINUX");

				imgSistema = new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

			}*/
			
			 if(System.getProperty("os.name").contains("Windows")) {
					imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
			 }else {
					System.out.println("IMAGE LINUX");

					imgSistema = new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

			 }
			
			
			
			
		
		}
		// grafica1.setImage(new
		// Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer"+(numEjercicio)+".png"),500,400,true,true));
		grafica1.setImage(imgSistema);
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);
		}
		contadorFilas=table.getItems().size();
		int numVects=leerEjercicio(numEjercicio);
		int sizeTab=table.getItems().size();
		if(numVects>sizeTab) {
			for(int i=0;i<(numVects-sizeTab);i++) {
				agregarVector();				
			}
		}else if(numVects<sizeTab){
			for(int i=0;i<(sizeTab-numVects);i++) {
				eliminarVector();				
			}
		}
		btnSiguiente.setDisable(false);
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		
		try {
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length);
		}catch(Exception nop) {
			System.out.println(new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length);

		}


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
		Alert resultado = new Alert(Alert.AlertType.WARNING);

		int i = 0;
		float[] angulos = new float[vects];
		float[] fuerzas = new float[vects];
		float[] fx = new float[vects];
		float[] fy = new float[vects];
		int[] fun1 = new int[vects];
		int[] fun2= new int[vects];
		try {
		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				dataRows.add(bean);
				angulos[i] = Float.parseFloat(bean.getAngulo().getText());
				fuerzas[i] = Float.parseFloat(bean.getFuerzas().getText());
				fx[i] = Float.parseFloat(bean.getFx().getText());
				fy[i] = Float.parseFloat(bean.getFy().getText());
				if(cambiarModo.getSelectionModel().getSelectedIndex()==1) {
					fun1[i]=bean.getFuncion().getSelectionModel().getSelectedIndex();
					fun2[i]=bean.getFuncion2().getSelectionModel().getSelectedIndex();
					System.out.println("Funcion1: "+fun1[i]+"  Funcion2: "+fun2[i]);
					//Sen =0
					//Cos =1
				}

			}
			System.out.println(fuerzas[i] + "," + angulos[i] + "  Fx = " + fx[i]);
			i++;

		}
	
		
		if(cambiarModo.getSelectionModel().getSelectedIndex()==0) {
			obj.setSumFx(fuerzas, angulos, vects);
			obj.setSumFy(fuerzas, angulos, vects);
			obj.setVR();
			obj.setAnguloResult();
			
		}else {
			obj.setSumFxV2(fuerzas, angulos,fx,fun1 ,vects);
			obj.setSumFyV2(fuerzas, angulos,fy ,fun2,vects);
			obj.setVR();
			obj.setAnguloResult();
		}
		
		System.out.println("La sumatoria de Fx = " + obj.getSumFx());
		System.out.println("La sumatoria de Fy = " + obj.getSumFy());
		System.out.println("Vector resultante : " + String.format("%.3f", obj.getVR()));
		System.out.println("Angulo resultante: " + String.format("%.3f", obj.getAnguloResult()));


		//VResultante.getText().equalsIgnoreCase(String.format("%.3f", obj.getVR()))
		//AnguloResultante.getText().equalsIgnoreCase(String.format("%.3f", obj.getAnguloResult()))
		System.out.println("Resultado del usuario: "+Math.round(Float.parseFloat(VResultante.getText()))+" Resultado del algoritmo: "+Math.round(obj.getVR()));
		if (Math.round(Float.parseFloat(VResultante.getText()))==Math.round(obj.getVR())
				&& Math.round(Float.parseFloat(AnguloResultante.getText()))==Math.abs(Math.round(obj.getAnguloResult()))) {
			resultado.setTitle("Respuesta correcta");
			resultado.setContentText("Tus resultados han sido los correctos, puedes pasar al siguiente ejercicio.");
			ImageView correcta = new ImageView(
					new Image(getClass().getResourceAsStream("/images/respuesta_correcta.png"), 50, 50, true, true));
			resultado.setGraphic(correcta);
			
			
			
			
			
			System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
			
			
			
			int tam=0;
			try {
			tam=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length+1;
			}catch(Exception bb) {
				tam=new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1").listFiles().length+1;

			}
			
			if((ejercicioMax)==tam && numEjercicio==ejercicioMax) {
				btnSiguiente.setDisable(true);
			}
				if(btnSiguiente.isDisabled()) {
					if(ejercicioMax==numEjercicio && (ejercicioMax)<tam ) {
					btnSiguiente.setDisable(false);
					ejercicioMax++;
					System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());
					getUserName().setMaxEjer1(ejercicioMax);
					System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());
					try {
						
						aumentarMaximos(getUserName(),ejercicioMax);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

					}
					//========================
					if((ejercicioMax)==tam) {
						ejercicioMax++;
						getUserName().setMaxEjer1(ejercicioMax);
						aumentarMaximos(getUserName(),ejercicioMax);

					}
					//==========================================
				}	
				
				System.out.println(tam-1);
			
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
		
		}catch(Exception e){
			resultado.setTitle("Respuesta incorrecta");
			resultado.setContentText(
					"Alguno de tus resultados es incorrecto, verifica tus operaciones y vuelve a intentarlo");
			ImageView incorrecta = new ImageView(
				new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"), 50, 50, true, true));
			resultado.setGraphic(incorrecta);
        	new Shake(table).play();
        	resultado.setHeaderText(null);
        	resultado.showAndWait();
        	return;
			
		}

	}
	public void aumentarMaximos(Usuario user,int max) throws IOException {
		
		RandomAccessFile file=null;
		try {
		file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
		}catch(Exception exp) {
			file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");
		}
		
		
		
	  	boolean band = false;
			String nombre="",apellido="",nacimiento="",usuario="",password="",mail="";
			int maxEjer1=0,maxEjer2=0;
			long pointer=0;
		while(!band && file.getFilePointer()<file.length()) {
			pointer=file.getFilePointer();
			nombre = file.readLine();				
			apellido = file.readLine();
			nacimiento = file.readLine();
			usuario = file.readLine();
			password = file.readLine();
			band = usuario.equals(user.getUsuario());
			mail = file.readLine();
			maxEjer1=file.readInt();
			maxEjer2=file.readInt();

		}
		
		if(band) {
			file.seek(pointer);
			file.writeBytes(nombre+"\n");
			file.writeBytes(apellido+"\n");
			file.writeBytes(nacimiento+"\n");			
			file.writeBytes(usuario+"\n");	
			file.writeBytes(password+"\n");			
			file.writeBytes(mail+"\n");		
			file.writeInt(max);
			file.writeInt(maxEjer2);
		}
		file.close();

	}
	
	static Resultados oficiales= new Resultados(); 
	public  int leerEjercicio(int idEjer) {
		if(idEjer==1) {
			tituloEjercicio1.setText(
					"EJERCICIO 1 \nDado este sistema de fuerzas determina el Vector Resultante. Rellena la siguiente tabla con los datos requeridos"
							+ " y realiza tus operaciones en un papel con ayuda de tu calculadora.(Anotar solo 3 decimales)");
			return 3;

		}
		
		int id=0,tipo=0;
		try {
			RandomAccessFile file=null;
			try {
			file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
			}catch(Exception ej) {
				file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat", "rw");

			}
			//file.seek(file.length());
		    try {
		    	boolean band=false;
		    	String descripcion="";
		    	while(file.getFilePointer()<file.length() && !band) {
		    		id=file.readInt();System.out.println(id);
		    		band = id==idEjer ? true:false;
		    		String name= file.readLine();System.out.println(name);
		    		tipo= file.readInt();System.out.println(tipo);
		    		vects = file.readInt();System.out.println(vects);
		    		float[] angulos= new float[vects];
		    		float[] fuerzas= new float[vects];

					for(int n=0;n<vects;n++) {
						float angulo= file.readFloat();
						float fuerza= file.readFloat();
						angulos[n]=angulo;
						fuerzas[n]=fuerza;
						System.out.println("Angulo: "+angulo+"=> Fuerza: "+fuerza);
					}
					descripcion=file.readLine();
					
					oficiales.setSumFx(fuerzas, angulos, vects);
					oficiales.setSumFy(fuerzas, angulos, vects);
					System.out.println("Resultados oficiales: SUmFx= "+oficiales.getSumFx()+" SumFy= "+oficiales.getSumFy());

		    	}
				tituloEjercicio1.setText(descripcion);

				
				
				
				file.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vects;
	}
	
	
	 private void agregarVector(){
         contadorFilas++;
         data.add(new ContenidoTabla("","","","",""));
   }
     private void eliminarVector(){
         data.remove(--contadorFilas);          
         table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
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
