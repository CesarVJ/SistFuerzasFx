package application;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PanelEjercicios implements Initializable {
	@FXML
	AnchorPane ventanaPanel,ejerciciosT1,ejerciciosT2, ejercicios, editorEjercicios;
	@FXML
	ScrollPane ejerciciosT1Scroll,ejerciciosT2Scroll;
	@FXML
	VBox titlePanel, photoBox, picture, titlePanel2, photoBox2, picture2, formData,adicionales;
	@FXML
	HBox pesoBox;
	@FXML
	Label textTitle, textTitle2, indicacionLbl, vectLbl,txtEjercicio1,totalEjer1,totalEjer2,txtEjercicio2;
	@FXML
	ImageView photoProfile, photoProfile2, btnAtras, btnAtrasEditor,btnAtras2,imgEjercicio;
	@FXML
	TextField numVect,peso;
	@FXML
	CheckBox esCociente;
	@FXML
	private TableView tablaDatos;
	@FXML
	TextArea txtDescripcion;
	@FXML
	ObservableList<ContenidoTabla> data;
	@FXML
	Button btnGuardar;

	static int EjercicioAct=0,numVectActual=0;
	int contadorFilas = 0;
	int numFilasAnt = 0;
	int vects = 0;

	int size;
	VBox btnEjercicios[];
	ImageView imgEjercicios[];
	float initialLayoutX = 90;
	float initialLayoutY = 70;
	static int idEjer;
	
	
	int size2;
	VBox btnEjercicios2[];
	ImageView imgEjercicios2[];
	float initialLayoutX2 = 90;
	float initialLayoutY2 = 70;
	static int idEjer2;
	
	static int idEjercicioAModificar;
	String rutaArchivoViejo="";


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		ejerciciosT1Scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		ejerciciosT1Scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		ejerciciosT1Scroll.setPannable(true);
		

		ejerciciosT2Scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		ejerciciosT2Scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		ejerciciosT2Scroll.setPannable(true);
		
		
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
		ef.setHeight(20);
		ef.setOffsetX(0);
		ef.setOffsetY(0);
		ef.setRadius(10);

		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile", "boton");

		btnAtras2.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras2.getStyleClass().addAll("photoProfile", "boton");
		btnAtrasEditor.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtrasEditor.getStyleClass().addAll("photoProfile", "boton");
		//txtEjercicio1.getStyleClass().add("textForm");
		totalEjer1.getStyleClass().add("textForm");  
		totalEjer2.getStyleClass().add("textForm");    

		
		

		txtEjercicio1.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		txtEjercicio2.setFont(Font.font("Verdana", FontWeight.BOLD, 17));

		

		ventanaPanel.getStyleClass().add("backgrounds");
		ventanaPanel.getStyleClass().add("profileBackground");
		titlePanel.getStyleClass().add("titlesProf");
		textTitle.getStyleClass().add("titlesText");

		photoBox.setAlignment(Pos.CENTER);
		photoBox.setSpacing(18);

		photoProfile.setImage(
				new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 350, 350, true, true));
		photoProfile.getStyleClass().add("photoProfile");
		picture.getStyleClass().addAll("profileBox", "resourcesBox2");

		titlePanel2.getStyleClass().add("titlesProf");
		textTitle2.getStyleClass().add("titlesText");

		photoBox2.setAlignment(Pos.CENTER);
		photoBox2.setSpacing(18);

		photoProfile2.setImage(
				new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer2.png"), 300, 250, true, true));
		photoProfile2.getStyleClass().add("photoProfile");
		picture2.getStyleClass().addAll("profileBox", "resourcesBox2");

		ejerciciosT1.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT1.getStyleClass().add("backgrounds");

		ejerciciosT1.setPrefWidth(1100);
		ejerciciosT1.setPrefHeight(2000);
		
		
		ejerciciosT1Scroll.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT1Scroll.getStyleClass().add("backgrounds");

		ejerciciosT1Scroll.setPrefWidth(1100);
		ejerciciosT1Scroll.setPrefHeight(600);
		
		
		ejerciciosT2Scroll.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT2Scroll.getStyleClass().add("backgrounds");

		ejerciciosT2Scroll.setPrefWidth(1100);
		ejerciciosT2Scroll.setPrefHeight(600);
		
		
		ejerciciosT2.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT2.getStyleClass().add("backgrounds");

		ejerciciosT2.setPrefWidth(1100);
		ejerciciosT2.setPrefHeight(2000);
		
		
		

		editorEjercicios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		editorEjercicios.getStyleClass().add("backgrounds");

		editorEjercicios.setPrefWidth(1100);
		editorEjercicios.setPrefHeight(580);

		ejercicios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejercicios.getStyleClass().add("backgrounds");

		ejercicios.setPrefWidth(1100);
		ejercicios.setPrefHeight(580);
		
		int total1  = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicios1").listFiles().length
				+ 1;
		totalEjer1.setText(totalEjer1.getText()+total1);
		
		int total2  = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicio2").listFiles().length;
		totalEjer2.setText(totalEjer2.getText()+total2);

		/*
		 * btnEjer.getStyleClass().addAll("profileBox","resourcesBox");
		 * 
		 * btnEjer.setOnMouseClicked(e->{ System.out.println("Hello");
		 * //irPagina("http://www.google.com"); });
		 * 
		 * imgEjer.setImage(new
		 * Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true,
		 * true)); imgEjer.getStyleClass().add("photoProfile");
		 * 
		 * for(int i=0;i<3;i++) {
		 * 
		 * }
		 */
		// =============================

		ejercicios.toFront();
		picture.setEffect(ef);
		picture.setSpacing(10);

		picture2.setEffect(ef);
		picture2.setSpacing(10);


		picture.setOnMouseClicked(e -> {
			// ventanaProfile.toFront();
			// ===================Listando ejercicios tipo
			// 1===================================
			// Crear btnEjer(VBox)
			// Crear imgEjer(ImageView)
			if (!ejerciciosT1.getChildren().contains(btnAtras)) {
				ejerciciosT1.getChildren().add(btnAtras);

			}

			size = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicios1").listFiles().length
					+ 1;
			btnEjercicios = new VBox[size];
			imgEjercicios = new ImageView[size];
			initialLayoutX = 90;
			initialLayoutY = 70;

			for (int i = 0; i < size; i++) {
				btnEjercicios[i] = new VBox();
				imgEjercicios[i] = new ImageView();
				imgEjercicios[i].getStyleClass().add("photoProfile");
				Label text = new Label("Ejercicio");
				text.getStyleClass().add("textForm");

				if (i != 0) {
					RandomAccessFile file = null;
					int id = 0, tipo = 0, vects1 = 0;
					String nameImage = "", descripcion = "";

					try {
						file = new RandomAccessFile(
								System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
						boolean band = false;

						while (!band && file.getFilePointer() < file.length()) {
							id = file.readInt();
							band = (i + 1) == id ? true : false;
							nameImage = file.readLine();
							tipo = file.readInt();
							vects1 = file.readInt();
							float angulos[] = new float[vects1];
							float fuerzas[] = new float[vects1];

							for (int n = 0; n < angulos.length; n++) {
								angulos[n] = file.readFloat();
								fuerzas[n] = file.readFloat();
							}
							descripcion = file.readLine();
						}
						file.close();
						System.out.println(nameImage);
						imgEjercicios[i].setImage(new Image(
								"file:///" + System.getProperty("user.home")
										+ "\\SistFuerzasFiles\\imgEjercicios1\\Ejer" + id + ".png",
								150, 150, true, true));

						// imgEjercicios[i].setImage(new
						// Image(getClass().getResourceAsStream(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer3.png"),
						// 100, 100, true, true));

					} catch (Exception e1) {
						System.out.println("Error1");
						try {
							file = new RandomAccessFile(
									System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat", "rw");
						} catch (FileNotFoundException e2) {
							System.out.println("Error2");

						}

					}

				} else {
					imgEjercicios[i].setImage(new Image(
							getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 100, 100, true, true));
				}

				btnEjercicios[i].setLayoutX(initialLayoutX);
				btnEjercicios[i].setLayoutY(initialLayoutY);
				btnEjercicios[i].setPrefHeight(180);
				btnEjercicios[i].setPrefWidth(180);
				btnEjercicios[i].getStyleClass().addAll("profileBox", "resourcesBox");

				btnEjercicios[i].setEffect(ef);
				btnEjercicios[i].setSpacing(10);
				btnEjercicios[i].setAlignment(Pos.CENTER);

				btnEjercicios[i].getChildren().add(imgEjercicios[i]);

				ejerciciosT1.getChildren().add(btnEjercicios[i]);

				if (initialLayoutX <= 800) {
					initialLayoutX += 200;
				} else {
					initialLayoutY += 230;
					initialLayoutX = 90;

				}
				text.setText(text.getText() + " " + (i + 1));
				btnEjercicios[i].getChildren().add(text);

			}
			ejerciciosT1Scroll.toFront();

			for (idEjer = 0; idEjer < btnEjercicios.length; idEjer++) {
				
				btnEjercicios[idEjer].setOnMouseClicked(er -> {
					//String texto=((Label)btnEjercicios[idEjer].getChildren().get(1)).getText();
					String texto=((Label)((VBox)(er.getSource())).getChildren().get(1)).getText();				
					int num= Integer.parseInt(texto.substring(10));
					abrirEditor(num,1);
				});
			}
			
			
			//System.out.println("ID DEL NODO= "+ ((Label)btnEjercicios[3].getChildren().get(1)).getText());

		});// ??
		
		
		picture2.setOnMouseClicked(e -> {

			if (!ejerciciosT2.getChildren().contains(btnAtras2)) {
				ejerciciosT2.getChildren().add(btnAtras2);

			}

			size2 = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicio2").listFiles().length
					;
			btnEjercicios2 = new VBox[size2];
			imgEjercicios2 = new ImageView[size2];
			initialLayoutX2 = 90;
			initialLayoutY2 = 70;

			for (int i = 0; i < size2; i++) {
				btnEjercicios2[i] = new VBox();
				imgEjercicios2[i] = new ImageView();
				imgEjercicios2[i].getStyleClass().add("photoProfile");
				Label text = new Label("Ejercicio");
				text.getStyleClass().add("textForm");

				
					RandomAccessFile file = null;
					int id = 0, tipo = 0, vects1 = 0;
					String nameImage = "", descripcion = "";

					try {
						file = new RandomAccessFile(
								System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2.dat", "rw");
						boolean band = false;

						while (!band && file.getFilePointer() < file.length()) {
							id = file.readInt();
							band = (i + 1) == id ? true : false;
							nameImage = file.readLine();
							tipo = file.readInt();
							vects1 = file.readInt();
							float angulos[] = new float[vects1];
							float fuerzas[] = new float[vects1];

							for (int n = 0; n < angulos.length; n++) {
								angulos[n] = file.readFloat();
								fuerzas[n] = file.readFloat();
							}
							descripcion = file.readLine();
						}
						file.close();
						System.out.println(nameImage);
						imgEjercicios2[i].setImage(new Image(
								"file:///" + System.getProperty("user.home")
										+ "\\SistFuerzasFiles\\imgEjercicio2\\Ejer" + id + ".png",
								150, 150, true, true));

						// imgEjercicios[i].setImage(new
						// Image(getClass().getResourceAsStream(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer3.png"),
						// 100, 100, true, true));

					} catch (Exception e1) {
						System.out.println("Error1");
						try {
							file = new RandomAccessFile(
									System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo2.dat", "rw");
						} catch (FileNotFoundException e2) {
							System.out.println("Error2");

						}

					}

		

				btnEjercicios2[i].setLayoutX(initialLayoutX2);
				btnEjercicios2[i].setLayoutY(initialLayoutY2);
				btnEjercicios2[i].setPrefHeight(180);
				btnEjercicios2[i].setPrefWidth(180);
				btnEjercicios2[i].getStyleClass().addAll("profileBox", "resourcesBox");

				btnEjercicios2[i].setEffect(ef);
				btnEjercicios2[i].setSpacing(10);
				btnEjercicios2[i].setAlignment(Pos.CENTER);

				btnEjercicios2[i].getChildren().add(imgEjercicios2[i]);

				ejerciciosT2.getChildren().add(btnEjercicios2[i]);

				if (initialLayoutX2 <= 800) {
					initialLayoutX2 += 200;
				} else {
					initialLayoutY2 += 230;
					initialLayoutX2 = 90;

				}
				text.setText(text.getText() + " " + (i + 1));
				btnEjercicios2[i].getChildren().add(text);

			}
			
			ejerciciosT2Scroll.toFront();


			for (idEjer2 = 0; idEjer2 < btnEjercicios2.length; idEjer2++) {
				
				btnEjercicios2[idEjer2].setOnMouseClicked(er -> {
					//String texto=((Label)btnEjercicios[idEjer].getChildren().get(1)).getText();
					String texto=((Label)((VBox)(er.getSource())).getChildren().get(1)).getText();				
					int num= Integer.parseInt(texto.substring(10));
					abrirEditor(num,2);
				});
			}
			
			
			//System.out.println("ID DEL NODO= "+ ((Label)btnEjercicios[3].getChildren().get(1)).getText());

		});
		
			btnAtrasEditor.setOnMouseClicked(e -> {
			// editorEjercicios.getChildren().clear();
			
			editorEjercicios.toBack();
			//editorEjercicios.getChildren().clear();
			limpiarDatos();
			for(int i=0;i<Integer.parseInt(numVect.getText());i++) {
				eliminarVector();
			}
			
			numVect.clear();
			txtDescripcion.clear();	
		});

		btnAtras.setOnMouseClicked(e -> {
			ejerciciosT1.getChildren().clear();
			ejerciciosT1Scroll.toBack();
		});
		btnAtras2.setOnMouseClicked(e -> {
			ejerciciosT2.getChildren().clear();
			ejerciciosT2Scroll.toBack();
		});
		
		
		crearEditor();	
	}
	
	public void guardarCambios() {
		int  tipoEjercicio = adicionales.getChildren().contains(pesoBox)?2:1; 
		RandomAccessFile archivoViejo = null;
		int maxFiles = 0;
		try {
			if(tipoEjercicio==1) {
				archivoViejo= abrirArchivo(tipoEjercicio);
				maxFiles=obtenerNumDeArchivos(1);
				modificarDatos(archivoViejo,tipoEjercicio);	
			}else if(tipoEjercicio==2) {			
				archivoViejo= abrirArchivo(tipoEjercicio);
				maxFiles=obtenerNumDeArchivos(2);
				modificarDatos(archivoViejo,tipoEjercicio);	
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	public RandomAccessFile abrirArchivo(int tipo) throws FileNotFoundException {
		RandomAccessFile file=null;
		String homePath=System.getProperty("user.home");				
		String rutaDatosGraficasWindows ="\\SistFuerzasFiles\\graficasInfo";
		String rutaDatosGraficaOtros ="/SistFuerzasFiles/graficasInfo";		
		
		if(System.getProperty("os.name").contains("Windows")) {
			if(tipo!=1) {
				file=new RandomAccessFile(homePath+rutaDatosGraficasWindows+tipo+".dat","rw");
				rutaArchivoViejo=rutaDatosGraficasWindows+tipo+".dat";
			}else {
				file=new RandomAccessFile(homePath+rutaDatosGraficasWindows+".dat","rw");
				rutaArchivoViejo=rutaDatosGraficasWindows+".dat";

			}
		}else {
			if(tipo!=1) {
				file=new RandomAccessFile(homePath+rutaDatosGraficaOtros+tipo+".dat","rw");
				rutaArchivoViejo=rutaDatosGraficaOtros+tipo+".dat";

			}else {
				file=new RandomAccessFile(homePath+rutaDatosGraficaOtros+".dat","rw");
				rutaArchivoViejo=rutaDatosGraficaOtros+".dat";

			}
		}		
		return file;
	}
	
	public int obtenerNumDeArchivos(int tipo) {
		String homePath=System.getProperty("user.home");				
		String rutaWindows1="\\SistFuerzasFiles\\imgEjercicios1";
		String rutaWindows2="\\SistFuerzasFiles\\imgEjercicio2";		
		String rutaOtros1="/SistFuerzasFiles/imgEjercicios1";
		String rutaOtros2="/SistFuerzasFiles/imgEjercicio2";
		int numArchivos=0;
		if(System.getProperty("os.name").contains("Windows")) {
			if(tipo!=1) {
				numArchivos= new File(homePath +rutaWindows1).listFiles().length;
			}else {
				numArchivos= new File(homePath +rutaWindows2).listFiles().length;
			}
		}else {
			if(tipo!=1) {
				numArchivos= new File(homePath +rutaOtros1).listFiles().length;
			}else {
				numArchivos= new File(homePath +rutaOtros2).listFiles().length;
			}
		}
		return numArchivos;
	}
	
	
	public void modificarDatos(RandomAccessFile archivoViejo,int tipoEjercicio) throws IOException {
		RandomAccessFile archivoNuevo=null;
		String rutaArchivoNuevo="";
		if(System.getProperty("os.name").contains("Windows")) {
			if(tipoEjercicio==1) {
				archivoNuevo= new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfoTemporal.dat","rw");
				rutaArchivoNuevo="\\SistFuerzasFiles\\graficasInfoTemporal.dat";
			}else {
				archivoNuevo=new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2Temporal.dat", "rw");
				rutaArchivoNuevo="\\SistFuerzasFiles\\graficasInfo2Temporal.dat";
			}
		}else {
			if(tipoEjercicio==1) {
				archivoNuevo= new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfoTemporal.dat","rw");
				rutaArchivoNuevo="/SistFuerzasFiles/graficasInfoTemporal.dat";

			}else {
				archivoNuevo= new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo2Temporal.dat","rw");
				rutaArchivoNuevo="/SistFuerzasFiles/graficasInfo2Temporal.dat";

			}
		}
		
		boolean isEditado = false;
		int vects2=0;
		int id=0,tipo=0;
		float peso1=0;
		String descripcion="",nameFile="";
		float[] angulos2=null,fuerzas2=null;
		long apuntador=0;
		
	
		 
	
		while(archivoViejo.getFilePointer()<archivoViejo.length()) { 
			id=archivoViejo.readInt();
			isEditado=(id)==idEjercicioAModificar?true:false;
			nameFile=archivoViejo.readLine(); 
			tipo=archivoViejo.readInt();//tipo
			vects2=archivoViejo.readInt();
			angulos2= new float[vects2]; 
			fuerzas2= new float[vects2];		  
			for(int n=0;n<angulos2.length;n++) {
				angulos2[n]=archivoViejo.readFloat();
		 		fuerzas2[n]=archivoViejo.readFloat(); 
		  	}
			if(tipoEjercicio==2) {
				peso1=archivoViejo.readFloat();
			}
			descripcion=archivoViejo.readLine(); 
			
		
			
			if(!isEditado) {
				archivoNuevo.seek(archivoNuevo.length());
				archivoNuevo.writeInt(id);
				archivoNuevo.writeBytes(nameFile+"\n");				
				archivoNuevo.writeInt(tipo);				
				archivoNuevo.writeInt(vects2);
				for(int n=0;n<vects2;n++) {
					archivoNuevo.writeFloat(angulos2[n]);
					archivoNuevo.writeFloat(fuerzas2[n]);
				}				
				if(tipoEjercicio==2) {
					archivoNuevo.writeFloat(peso1);
			  	}
				archivoNuevo.writeBytes(descripcion+"\n");
			}else {
				//Obteniendo datos nuevos
				float[] angulosNuevos= new float[vects2];
				float[] fuerzasNuevas= new float[vects2];
				int ii=0;
				for (ContenidoTabla bean : data) {
					if (!bean.getFuerzas().getText().isEmpty()) {
						angulosNuevos[ii] = Float.parseFloat(bean.getAngulo().getText());
						fuerzasNuevas[ii] = Float.parseFloat(bean.getFuerzas().getText());
					}
					ii++;
				}//Datos obtenidos
								
				archivoNuevo.seek(archivoNuevo.length());
				archivoNuevo.writeInt(id);
				archivoNuevo.writeBytes(nameFile+"\n");
				if(tipoEjercicio==2) {
					archivoNuevo.writeInt(esCociente.isSelected()?2:1);
				}else {
					archivoNuevo.writeInt(tipo);
				}				
				archivoNuevo.writeInt(vects2);
				for(int n=0;n<vects2;n++) {
					archivoNuevo.writeFloat(angulosNuevos[n]);
					archivoNuevo.writeFloat(fuerzasNuevas[n]);
				}					
				if(tipoEjercicio==2) {
					archivoNuevo.writeFloat(Float.parseFloat(peso.getText()));
			  	}
				archivoNuevo.writeBytes(txtDescripcion.getText()+"\n");	
			}
			isEditado=false;
		}
		archivoNuevo.close();
		archivoViejo.close();
		
		reemplazarArchivos(rutaArchivoViejo,rutaArchivoNuevo);
	}
	
	public void reemplazarArchivos(String antiguo,String nuevo) {
		System.out.print("Intercambiando archivos");
		File archivoAntiguo= new File(System.getProperty("user.home") + antiguo);
		File archivoNuevo= new File(System.getProperty("user.home") + nuevo);
		
		archivoAntiguo.delete();
		archivoNuevo.renameTo(new File(System.getProperty("user.home")+antiguo));

	}
	
	
	
	
	
	
	
	public void guardarCambios2() {
		int  tipoEjercicio = adicionales.getChildren().contains(pesoBox)?2:1; 
		RandomAccessFile file = null;
		int maxFiles = 0;
		try {
			if(tipoEjercicio==1) {
				file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
				maxFiles = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicios1")
						.listFiles().length;
			}else if(tipoEjercicio ==2) {
				file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2.dat", "rw");
				maxFiles = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicio2")
						.listFiles().length;
				
			}	
		} catch (Exception op) {
			try {				
				if(tipoEjercicio==1) {
					file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat",
							"rw");
					maxFiles = new File(System.getProperty("user.home") + "/SistFuerzasFiles/imgEjercicios1")
							.listFiles().length;
				}else if(tipoEjercicio==2) {
					file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo2.dat",
							"rw");
					maxFiles = new File(System.getProperty("user.home") + "/SistFuerzasFiles/imgEjercicio2")
							.listFiles().length;
				}
				

			} catch (FileNotFoundException e) {

			}
		}
		
		
		boolean band = false;
		int vects2=0;
		int id=0,tipo=0;
		float peso1=0;
		String descripcion2="",nameFile="";
		float[] angulos2=null,fuerzas2=null;
		long apuntador=0;
		 
			try {
				while(!band && file.getFilePointer()<file.length()) { 
					apuntador=file.getFilePointer();					
					id=file.readInt();
					  band=(id)==idEjercicioAModificar?true:false;
				  		nameFile=file.readLine(); 
				  		tipo=file.readInt();//tipo
				  		vects2=file.readInt();
				  		 angulos2= new float[vects2]; 
				  		 fuerzas2= new float[vects2];		  
				  		for(int n=0;n<angulos2.length;n++) {
				  			angulos2[n]=file.readFloat();
				  			fuerzas2[n]=file.readFloat(); 
				  		}
				  	if(tipoEjercicio==2) {
				  		peso1=file.readFloat();
				  	}
				  descripcion2=file.readLine(); 
				  }
				
				float[] angulosNuevos= new float[vects2];
				float[] fuerzasNuevas= new float[vects2];
				int ii=0;
				for (ContenidoTabla bean : data) {
					if (!bean.getFuerzas().getText().isEmpty()) {
						angulosNuevos[ii] = Float.parseFloat(bean.getAngulo().getText());
						fuerzasNuevas[ii] = Float.parseFloat(bean.getFuerzas().getText());
					}
					System.out.println(fuerzasNuevas[ii] + "," + angulosNuevos[ii]);
					ii++;
					
					
				}

				file.seek(apuntador);
				file.writeInt(id);
				file.writeBytes(nameFile+"\n");
				if(tipoEjercicio==2) {
					file.writeInt(esCociente.isSelected()?2:1);
				}else {
					file.writeInt(tipo);
				}				
				file.writeInt(vects2);
				for(int n=0;n<vects2;n++) {
					file.writeFloat(angulosNuevos[n]);
					file.writeFloat(fuerzasNuevas[n]);
				}	
				
				if(tipoEjercicio==2) {
					file.writeFloat(Float.parseFloat(peso.getText()));
			  	}
				file.writeBytes(txtDescripcion.getText()+"\n");
				file.close();
	
				
			} catch (IOException e) {

			}
		  
	
		
	}
	
	
	public void crearEditor() {
		
		
		txtDescripcion.setWrapText(true);
		TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
		fuerzas.setMinWidth(160);
		TableColumn angulo = new TableColumn("Angulo");
		angulo.setMinWidth(160);
		tablaDatos.getColumns().addAll(fuerzas, angulo);
		tablaDatos.resizeColumn(fuerzas, 0);
		data = FXCollections.observableArrayList();

		fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("fuerzas"));
		angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("angulo"));

		tablaDatos.setItems(data);

		tablaDatos.resizeColumn(fuerzas, 28);
		tablaDatos.resizeColumn(angulo, 28);

		// Tabla para equilibrio
		TableColumn fuerzas2 = new TableColumn("Fuerzas(Nw)");
		fuerzas.setMinWidth(160);

		TableColumn showFuerza = new TableColumn("Visible");
		fuerzas.setMinWidth(160);

		TableColumn showAngulo = new TableColumn("Angulo");
		angulo.setMinWidth(160);

		TableColumn angulo2 = new TableColumn("Angulo");
		angulo.setMinWidth(160);

		indicacionLbl.setWrapText(true);
		indicacionLbl.getStyleClass().add("textForm");
		formData.getStyleClass().add("formData");

		try {
			numVect.setOnKeyPressed(e -> {
				System.out.println("KeyReleased()" + numVect.getText());

				// if(e.getCode().equals("DIGIT1")) {
				if (!verificarExp(e))
					return;

				if ((numVect.getText().equals(""))) {
					numFilasAnt = 0;
					System.out.println("KeyReleased(Textto vacio)");

				} else {
					try {
						numFilasAnt = Integer.parseInt(numVect.getText());
						System.out.println("KeyReleased(TRY)");

					} catch (Exception ex) {
						numFilasAnt = tablaDatos.getItems().size();
						System.out.println("KeyReleased(Catch)");

					}
				}

			});
		} catch (NumberFormatException e) {
			numFilasAnt = tablaDatos.getItems().size();
			System.out.println("KeyReleased(Catch 2)");

		}

		numVect.setOnKeyReleased(e -> {
			System.out.println("KeyReleased(Inicio)");
			// numFilasAnt= tablaDatos.getItems().size()-1;
			if (!verificarExp(e))
				return;
			System.out.println("KeyReleased(Medio)");

			if ((numVect.getText().equals(""))) {
				vects = 0;
			} else {
				try {
					vects = Integer.parseInt(numVect.getText());
					System.out.println("KeyReleased(Exito)");

				} catch (Exception ex) {
					vects = tablaDatos.getItems().size();
					System.out.println("KeyReleased(Error)");

				}
				if (vects >= 10 || numFilasAnt >= 10)
					return;
			}

			// System.out.println(numVect.getText());
			// int lastNumber= numVect.getText().charAt(numVect.getText().length());
			if ((numFilasAnt - vects) >= 0) {
				for (int in = 0; in < (numFilasAnt - vects); in++) {
					eliminarVector();
				}
				System.out.println("KeyReleased(Eliminando): " + (numFilasAnt) + " " + vects);

			} else {
				for (int j = 0; j < vects; j++) {
					agregarVector();
				}
				System.out.println("KeyReleased(Agregando)");

			}
			System.out.println(e.getCode());
			// if(e.getCode().equals("BACK_SPACE")) {
		});
		
	}

	public void abrirEditor(int i, int tipoEjercicio) {
		EjercicioAct=i;
		
		if(tipoEjercicio==2) {
			if(!adicionales.getChildren().contains(pesoBox)) {
				adicionales.getChildren().remove(btnGuardar);
				adicionales.getChildren().add(pesoBox);
				adicionales.getChildren().add(btnGuardar);
			}
		}else {
			if(adicionales.getChildren().contains(pesoBox)) {
				adicionales.getChildren().remove(pesoBox);
			}
		}
		System.out.println("ID del ejercicio: "+i);
		editorEjercicios.toFront();
		
		if (!editorEjercicios.getChildren().contains(btnAtrasEditor)) {
			editorEjercicios.getChildren().add(btnAtrasEditor);
		}
		rellenarDatos(i,tipoEjercicio);
	}
	

	public void rellenarDatos(int idE, int tipoEjercicio) {
		numVect.requestFocus();	
		//rellenarDatos(idEjer);
		RandomAccessFile file = null;
		int maxFiles = 0;
		try {
			if(tipoEjercicio==1) {
				file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
				maxFiles = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicios1")
						.listFiles().length;
			}else if(tipoEjercicio ==2) {
				file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2.dat", "rw");
				maxFiles = new File(System.getProperty("user.home") + "\\SistFuerzasFiles\\imgEjercicio2")
						.listFiles().length;
				
			}
			
		} catch (Exception op) {
			try {
				
				if(tipoEjercicio==1) {
					file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat",
							"rw");
					maxFiles = new File(System.getProperty("user.home") + "/SistFuerzasFiles/imgEjercicios1")
							.listFiles().length;
				}else if(tipoEjercicio==2) {
					file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo2.dat",
							"rw");
					maxFiles = new File(System.getProperty("user.home") + "/SistFuerzasFiles/imgEjercicio2")
							.listFiles().length;
				}
				

			} catch (FileNotFoundException e) {

			}
		}
		
		
		boolean band = false;
		int vects2=0;
		int id=0,tipo=0;
		float peso1=0;
		String descripcion2="";
		float[] angulos2=null,fuerzas2=null;
		  try {
			while(!band && file.getFilePointer()<file.length()) { 
				  id=file.readInt();
				  band=(id)==idE?true:false;
			  		file.readLine(); 
			  		tipo=file.readInt();//tipo
			  		vects2=file.readInt();
			  		 angulos2= new float[vects2]; 
			  		 fuerzas2= new float[vects2];		  
			  		for(int n=0;n<angulos2.length;n++) {
			  			angulos2[n]=file.readFloat();
			  			fuerzas2[n]=file.readFloat(); 
			  		}
			  	if(tipoEjercicio==2) {
			  		peso1=file.readFloat();
			  	}
			  descripcion2=file.readLine(); 
			  }
			
			idEjercicioAModificar=id;
			
			if(tipoEjercicio==1) {
				imgEjercicio.setImage(new Image(
						"file:///" + System.getProperty("user.home")
								+ "\\SistFuerzasFiles\\imgEjercicios1\\Ejer" + id + ".png",
						400, 300, false, false));
			}else {
				imgEjercicio.setImage(new Image(
						"file:///" + System.getProperty("user.home")
								+ "\\SistFuerzasFiles\\imgEjercicio2\\Ejer" + id + ".png",
						300, 300, false, false));
			}
			
			  file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  teclearVectores(vects2);
		  numVectActual=vects2;
		  //=====================
		  
		  System.out.println("La tabla esta: "+tablaDatos.getItems().size());
		 
			ObservableList<ContenidoTabla> dataRows = FXCollections.observableArrayList();
			int ip=0;
			for (ContenidoTabla bean : data) {
				if (bean.getFuerzas().getText().isEmpty()) {
					TextField ang = new TextField(angulos2[ip]+"");
					TextField fuer = new TextField(fuerzas2[ip]+"");
					bean.setAngulo(ang);
					bean.setFuerzas(fuer);
					dataRows.add(bean);

					}
				ip++;
			}
			
			txtDescripcion.setText(descripcion2);
			peso.setText(peso1+"");
			if(tipoEjercicio==2) {
				if(tipo==1)
					esCociente.fire();
			}
			
	}
	
	
	
	
	public void teclearVectores(int vects3) {
		
			System.out.println("Vectores: "+vects3);
			for(int i=0;i<vects3;i++) agregarVector();
			numVect.setText(vects3+"");
			contadorFilas=vects3;
		//try {
			/*Robot r = new Robot();
			switch(vects3) {
			case 0:
				r.keyPress(com.sun.glass.events.KeyEvent.VK_0);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_0);
				break;
			case 1:
				r.keyPress(com.sun.glass.events.KeyEvent.VK_1);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_1);
				break;
			case 2:
				r.keyPress(com.sun.glass.events.KeyEvent.VK_2);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_2);
				break;
			case 3: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_3);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_3);
				break;
			case 4: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_4);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_4);
				break;
			case 5: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_5);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_5);
				break;
			case 6: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_6);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_6);
				break;
			case 7: 
				//r.keyPress(com.sun.glass.events.KeyEvent.VK_7);
				//r.keyRelease(com.sun.glass.events.KeyEvent.VK_7);
				numVect.setText("7");
				for(int i=0;i<7;i++) agregarVector();break;
			case 8: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_8);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_8);
				break;
			case 9: 
				r.keyPress(com.sun.glass.events.KeyEvent.VK_9);
				r.keyRelease(com.sun.glass.events.KeyEvent.VK_9);
				break;
			}*/
			

			
		//} catch (AWTException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		//}
		
	}

	public boolean verificarExp(KeyEvent e) {
		String n = e.getCode() + "";
		Pattern expression = Pattern.compile("^*DIGIT" + "[0-9]");
		Matcher match = expression.matcher(n);
		return (match.find() || n.equals("BACK_SPACE"));
	}

	private void agregarVector() {
		contadorFilas++;
		data.add(new ContenidoTabla("", ""));
	}

	private void eliminarVector() {
		data.remove(--contadorFilas);
		tablaDatos.getItems().removeAll(tablaDatos.getSelectionModel().getSelectedItems());
	}
	
	public void limpiarDatos() {

		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				bean.getAngulo().clear();
				bean.getFuerzas().clear();
			}

		}
	}

}
