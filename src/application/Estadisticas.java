package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Usuario;

public class Estadisticas implements Initializable {
	@FXML
	AnchorPane ventanaEstadist;
	@FXML
	 AnchorPane vistaEstadisticos,vistaUsuarios;
	@FXML
	ImageView btnAtras;
	@FXML	
	TableView datosAlumno,datosEjercicios1;
	@FXML
	TableView datosEjercicios2;
	ObservableList<ContenidoTabla> data;
	ObservableList<ContenidoTabla> dataEjercicios1;
	ObservableList<ContenidoTabla> dataEjercicios2;
	
	
	final CategoryAxis xAxis = new CategoryAxis ();
    final NumberAxis  yAxis = new NumberAxis ();
    
    
    
    final NumberAxis xAxis2 = new NumberAxis();
    final NumberAxis yAxis2 = new NumberAxis();
    
	final LineChart<String, Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);	
	final LineChart<Number,Number> lineChart2 = new LineChart<Number,Number>(xAxis2,yAxis2);	


	
	
	static ArrayList btnPerfiles= new ArrayList<VBox>();
	static ArrayList imgPerfiles= new ArrayList<ImageView>();

	static float initialLayoutX = 90;
	
	static float initialLayoutY = 70;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		if (!vistaEstadisticos.getChildren().contains(btnAtras)) {
			vistaEstadisticos.getChildren().add(btnAtras);

		}
		

		
		

		//datosAlumno.setItems(data);
		
		TableColumn noControlColumn = new TableColumn("Usuario");
		noControlColumn.setCellValueFactory(new PropertyValueFactory<>("noControl"));
		noControlColumn.setMinWidth(0);
		
		TableColumn nombreColumn = new TableColumn("Nombre");
		//nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<ContenidoTabla, TextField>("nombre"));

		nombreColumn.setMinWidth(0);
		
		TableColumn carreraColumn = new TableColumn("Correo");
		carreraColumn.setCellValueFactory(new PropertyValueFactory<>("carrera"));
		carreraColumn.setMinWidth(0);
		
		TableColumn visitasColumn = new TableColumn("Visitas");
		visitasColumn.setCellValueFactory(new PropertyValueFactory<>("visitas"));
		visitasColumn.setMinWidth(0);
		
		datosAlumno.getColumns().addAll(noControlColumn,nombreColumn,carreraColumn,visitasColumn);
		datosAlumno.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		
		datosAlumno.resizeColumn(noControlColumn, 20);
		datosAlumno.resizeColumn(nombreColumn, 54);
		datosAlumno.resizeColumn(carreraColumn, 30);
		datosAlumno.resizeColumn(visitasColumn, 8);
		
		nombreColumn.setStyle( "-fx-alignment: CENTER;");
		carreraColumn.setStyle( "-fx-alignment: CENTER;");
		visitasColumn.setStyle( "-fx-alignment: CENTER;");
		noControlColumn.setStyle( "-fx-alignment: CENTER;");
		//============================================================================

		//data.add(1, new ContenidoTabla("1111","1111","111","111"));
		//TableColumn<String,String> nombreColumn = new TableColumn<>("Nombre");
		//nombreColumn.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("nombre"));
		
		//TableColumn<String,String> carreraColumn = new TableColumn<>("Carrera");
		//carreraColumn.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("carrera"));
		
		//TableColumn <String,String>visitasColumn = new TableColumn<>("Visitas");
		//visitasColumn.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("visitas"));
		
		//noControlColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
		//nombreColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
		//carreraColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));		
		//visitasColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

		/*datosAlumno.getColumns().addAll(noControlColumn,nombreColumn,carreraColumn,visitasColumn);
		 //ObservableList<String> items1 = FXCollections.observableArrayList("17011277","Cesar Valdez","Sistemas","12");
		 ObservableList<String> items1 = FXCollections.observableArrayList("Sistemas");
		 ObservableList<String> items2 = FXCollections.observableArrayList("1000");
		 datosAlumno.setItems(items1);*/			
			//===========================================================================

		
		/*
		TableColumn numEjercicio = new TableColumn("No. Ejercicio");
		//numEjercicio.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,String>("noEjercicio"));
		numEjercicio.setCellValueFactory(param -> new ReadOnlyStringWrapper(((StringExpression) param).getValue()));

		
		TableColumn intentos = new TableColumn("Intentos");
		intentos.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,String>("intentos"));
		
		TableColumn errores = new TableColumn("Errores");
		intentos.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,String>("errores"));
		
		datosEjercicios1.getColumns().addAll(numEjercicio,intentos,errores);

		datosEjercicios1.resizeColumn(numEjercicio, 33);
		datosEjercicios1.resizeColumn(intentos, 33);
		datosEjercicios1.resizeColumn(errores, 33);
		
		
		TableColumn<String,String> numEjercicio2 = new TableColumn<>("No. Ejercicio");
		//numEjercicio2.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("noEjercicio"));
		
		TableColumn <String,String>intentos2 = new TableColumn<>("Intentos");
		//intentos.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("intentos"));
		
		TableColumn<String,String> errores2 = new TableColumn<>("Errores");
		//intentos.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,Label>("errores"));
		
		
		numEjercicio2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
		intentos2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
		errores2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

		datosEjercicios2.getColumns().addAll(numEjercicio2,intentos2,errores2);

		datosEjercicios2.resizeColumn(numEjercicio2, 33);
		datosEjercicios2.resizeColumn(intentos2, 33);
		datosEjercicios2.resizeColumn(errores2, 33);
		
		//Ejemplo
        ObservableList<String> items = FXCollections.observableArrayList("1","5","4");

		datosEjercicios2.setItems(items);

	datosAlumno.resizeColumn(noControlColumn, 20);
		datosAlumno.resizeColumn(nombreColumn, 54);
		datosAlumno.resizeColumn(carreraColumn, 30);
		datosAlumno.resizeColumn(visitasColumn, 8);*/
		
		
		TableColumn numEjercicio = new TableColumn("No. Ejercicio");
		numEjercicio.setCellValueFactory(new PropertyValueFactory<>("numEjercicio"));
		numEjercicio.setMinWidth(0);
		
		TableColumn intentos = new TableColumn("Intentos");
		intentos.setCellValueFactory(new PropertyValueFactory<>("intentos"));
		intentos.setMinWidth(0);
		
		TableColumn errores = new TableColumn("Errores");
		errores.setCellValueFactory(new PropertyValueFactory<>("errores"));
		errores.setMinWidth(0);
		
		datosEjercicios1.resizeColumn(numEjercicio, 5);
		datosEjercicios1.resizeColumn(intentos, 5);
		datosEjercicios1.resizeColumn(errores, 5);
		
	
		
		datosEjercicios1.getColumns().addAll(numEjercicio,intentos,errores);
		datosEjercicios1.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

		/*dataEjercicios1 = FXCollections.observableArrayList(new ContenidoTabla("1", "5", "4"));
		dataEjercicios1.add(new ContenidoTabla("10","15","4"));

		datosEjercicios1.setItems(dataEjercicios1);*/
		
		numEjercicio.setStyle( "-fx-alignment: CENTER;");
		intentos.setStyle( "-fx-alignment: CENTER;");
		errores.setStyle( "-fx-alignment: CENTER;");
		//===============================================================================
		TableColumn numEjercicio2 = new TableColumn("No. Ejercicio");
		numEjercicio2.setCellValueFactory(new PropertyValueFactory<>("numEjercicio2"));
		numEjercicio2.setMinWidth(0);
		
		TableColumn intentos2 = new TableColumn("Intentos");
		//nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		intentos2.setCellValueFactory(new PropertyValueFactory<>("intentos2"));
		intentos2.setMinWidth(0);
		
		TableColumn errores2 = new TableColumn("Errores");
		errores2.setCellValueFactory(new PropertyValueFactory<>("errores2"));
		errores2.setMinWidth(0);
		
		datosEjercicios2.resizeColumn(numEjercicio2, 5);
		datosEjercicios2.resizeColumn(intentos2, 5);
		datosEjercicios2.resizeColumn(errores2, 5);
		
	
		
		datosEjercicios2.getColumns().addAll(numEjercicio2,intentos2,errores2);
		numEjercicio2.setStyle( "-fx-alignment: CENTER;");
		intentos2.setStyle( "-fx-alignment: CENTER;");
		errores2.setStyle( "-fx-alignment: CENTER;");

		datosEjercicios2.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());

	
		
		
		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile", "boton");
		
		ventanaEstadist.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		vistaEstadisticos.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		vistaEstadisticos.getStyleClass().add("backgrounds");

		vistaEstadisticos.setPrefWidth(1100);
		vistaEstadisticos.setPrefHeight(580);
		
		vistaUsuarios.setPrefHeight(580);
		vistaUsuarios.setPrefWidth(1100);
		ventanaEstadist.getStyleClass().add("backgrounds");
		ventanaEstadist.getStyleClass().add("profileBackground");
		
		
		vistaUsuarios.getStyleClass().add("backgrounds");
		vistaUsuarios.getStyleClass().add("profileBackground");
		vistaUsuarios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());

		
		vistaUsuarios.toFront();

		btnAtras.setOnMouseClicked(e -> {
			//vistaEstadisticos.getChildren().clear();
			vistaEstadisticos.toBack();
			vistaUsuarios.toFront();
			limpiarDatos();
		});	
		//crearRecuadros();
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
		ef.setHeight(20);
		ef.setOffsetX(0);
		ef.setOffsetY(0);
		ef.setRadius(10);
		
		
		initialLayoutX = 90;
		initialLayoutY = 70;
		RandomAccessFile file=null;

		try {
			
			if(System.getProperty("os.name").contains("Windows")) {
				file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			}else {
				file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");				
			}						
		} catch (FileNotFoundException e3) {
			System.out.print("Rutas erroneas");
		}
		
		
		
		
		
		
		
		try {

			int file1=0;
			for (int i = 0; i < btnPerfiles.size()+1 && file.getFilePointer()<file.length(); i++) {
				
				System.out.println("FilePointer= "+file.getFilePointer()+"  file.length= "+file.length());
				System.out.println("JAJAJAJA");

				imgPerfiles.add(i, new ImageView());
				btnPerfiles.add(i,new VBox());
				((ImageView) imgPerfiles.get(i)).getStyleClass().add("photoProfile");

					String nombre = "", usuario = "",correro="",especialidad="",semestre="",noControl="";
					int visitas=0;

					try {
						file = new RandomAccessFile(
								System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
						boolean band = false;
						int cont=0;
						while (!band && file.getFilePointer() < file.length()) {
							System.out.println("JEJEJEJE");
							band=i==cont;
							nombre=file.readLine();
							nombre+=file.readLine();
							file.readLine();
							usuario=file.readLine();
							file.readLine();
							correro=file.readLine();	
							file.readInt();
							file.readInt();
							visitas=file.readInt();

							cont++;
						}
						file1=(cont==1)?(int) file.getFilePointer():file1;
						/*((ImageView) imgPerfiles.get(i)).setImage(new Image("file:///" + System.getProperty("user.home")
										+ "\\SistFuerzasFiles\\imgEjercicios1\\Ejer3.png",
								150, 150, true, true));*/
						
						//((ImageView) imgPerfiles.get(i)).setImage(new Image("SistFuerzasFiles\\imgEjercicios1\\avatar.png",150,150,true,true));	
						
						
						File avatar=null;

						if(System.getProperty("os.name").contains("Windows")) {
							avatar = new File(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(usuario)+".png");
						}else {
							avatar = new File(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(usuario)+".png");
						}
						
						if(avatar.exists()) {
							if(System.getProperty("os.name").contains("Windows")) {
								System.out.println("La imagen se definio para "+usuario);
								((ImageView) imgPerfiles.get(i)).setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\avatares\\"+usuario+".png", 100, 100, true,true));// Ancho.alto
								//((ImageView) imgPerfiles.get(i)).setImage(new Image(getClass().getResourceAsStream(System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(usuario)+".png"), 100, 100, true, true));
							}else {
								((ImageView) imgPerfiles.get(i)).setImage(new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(usuario)+".png", 100, 100, true, true));// Ancho.alto
								//((ImageView) imgPerfiles.get(i)).setImage(new Image(getClass().getResourceAsStream("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/avatares/"+(usuario)+".png"), 100, 100, true, true));
							}
						}else {
							((ImageView) imgPerfiles.get(i)).setImage(new Image(getClass().getResourceAsStream("/images/avatar.png"), 100, 100, true, true));
						}
						


					} catch (Exception e1) {
						System.out.println("Error1");
						try {
							file = new RandomAccessFile(
									System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");
						} catch (FileNotFoundException e2) {
							System.out.println("Error20");

						}

					}

			

				((VBox) btnPerfiles.get(i)).setLayoutX(initialLayoutX);
				((VBox) btnPerfiles.get(i)).setLayoutY(initialLayoutY);
				((VBox) btnPerfiles.get(i)).setPrefHeight(180);
				((VBox) btnPerfiles.get(i)).setPrefWidth(180);
				((VBox) btnPerfiles.get(i)).getStyleClass().addAll("profileBox", "resourcesBox");

				((VBox) btnPerfiles.get(i)).setEffect(ef);
				((VBox) btnPerfiles.get(i)).setSpacing(10);
				((VBox) btnPerfiles.get(i)).setAlignment(Pos.CENTER);

				((VBox) btnPerfiles.get(i)).getChildren().add((ImageView) imgPerfiles.get(i));

				vistaUsuarios.getChildren().add((VBox) btnPerfiles.get(i));

				if (initialLayoutX <= 800) {
					initialLayoutX += 200;
				} else {
					initialLayoutY = 300;
					initialLayoutX = 90;

				}
				Text textNomb =new Text(usuario);
				textNomb.getStyleClass().add("textForm");
				((VBox) btnPerfiles.get(i)).getChildren().add(textNomb);
				//ventanaEstadist.toFront();

			}
			file.close();


			
		for (int j = 0; j < btnPerfiles.size(); j++) {
				
			((VBox) btnPerfiles.get(j)).setOnMouseClicked(er -> {
					String texto=((Text) ((VBox)(er.getSource())).getChildren().get(1)).getText();
					System.out.println(texto);
					
					vaciarTablas();
					abrirEstadisticos(texto);
					rellenarTablaEjer1(texto);
					rellenarTablaEjer2(texto);

				});
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

		

		
		
		//data = FXCollections.observableArrayList();
		//dataEjercicios1= FXCollections.observableArrayList();
        //datosEjercicios1.setItems(dataEjercicios1);       

	}
	
	public void vaciarTablas() {
		if(dataEjercicios1!=null) {
			dataEjercicios1.clear();
		}
		if(dataEjercicios2!=null) {
			dataEjercicios2.clear();
		}
		if(data!=null) {
			data.clear();
		}
	
	}
	
	public void limpiarDatos() {

		/*for (ContenidoTabla bean : data) {
			if (!bean.getNoControl().getText().isEmpty()) {
				//bean.getNoControl().setText("");
				//bean.getNombre().setText("");;
				//bean.getCarrera().setText("");;
				//bean.getVisitas().setText("");
			}

		}*/
		
	/*	for (ContenidoTabla bean : dataEjercicios1) {
			if (!bean.getNumEjercicio().getText().isEmpty()) {
				//bean.getNumEjercicio().setText("");
				//bean.getIntentos().setText("");;
				//bean.getErrores().setText("");;
			}

		}*/
		if(dataEjercicios2!=null) {
			for (ContenidoTabla bean : dataEjercicios2) {
				if (!bean.getNumEjercicio2().getText().isEmpty()) {
					bean.getNumEjercicio2().setText("");
					bean.getIntentos2().setText("");;
					bean.getErrores2().setText("");;
				}

			}
		}
		
		
	}
	
	public  void abrirEstadisticos(String nombUsuario) {

		//dataEjercicios1.add(new ContenidoTabla("Zlacko","ee","aaaa"));
		vistaEstadisticos.toFront();
		leerUsuario(nombUsuario);
		

	
		
		
		
	
	}
	public void leerUsuario(String user) {
		boolean band = false;
		String nombre="",apellido="",nacimiento="",usuario="",password="",mail="";
		int maxEjer1,maxEjer2,visitas=0;
		RandomAccessFile file=null;
		
		try {
			file=new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
			}catch(Exception eee) {
				try {
					file=new RandomAccessFile(
							 System.getProperty("user.home") + "/SistFuerzasFiles/users.dat", "rw");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
			try {
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
					visitas=file.readInt();

				}
				
				data = FXCollections.observableArrayList(new ContenidoTabla(usuario, nombre+" "+apellido,mail, visitas+""));
				datosAlumno.setItems(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	
	public void rellenarTablaEjer1(String user) {
		ArrayList<Integer> intentosChart= new ArrayList(),erroresChart= new ArrayList(),numEjercicio1Chart= new ArrayList();
		
		System.out.println("ESTE usuario= "+user);
		RandomAccessFile file=null;
		try {
			 file= new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\intentos1.dat", "rw");
		}catch(Exception e) {

			try {
				file= new RandomAccessFile(
						 System.getProperty("user.home") + "/SistFuerzasFiles/intentos1.dat", "rw");
			} catch (FileNotFoundException e1) {
			}
			
		}
			boolean band =false;
			String usuario="";
			int idEjer=0,intentos=0;
			boolean completado=false;
		try {
			int contador=0;
			while(file.getFilePointer()<file.length()) {
		
				usuario=file.readLine();
				idEjer=file.readInt();
				intentos=file.readInt();
				completado=file.readBoolean();

				band=(user.equals(usuario));	
				if(band) {
					
					if(contador==0) {
						dataEjercicios1 = FXCollections.observableArrayList(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
					}else {
						dataEjercicios1.add(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
					}
					numEjercicio1Chart.add(idEjer);
					intentosChart.add(((completado)?(intentos+1):(intentos)));
					erroresChart.add(intentos);
					
					//System.out.println(usuario+","+idEjer+", "+completado+","+intentos);
					

					contador++;
					
					//dataEjercicios1 = FXCollections.observableArrayList(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
			         //data.add(new ContenidoTabla("","","","",""));

				//	dataEjercicios1.add(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
					//datosEjercicios1.setItems(dataEjercicios1);

				}
			}	
			datosEjercicios1.setItems(dataEjercicios1);

			file.close();
			
			XYChart.Series series = new XYChart.Series();
			XYChart.Series series2 = new XYChart.Series();
			
			if(vistaEstadisticos.getChildren().contains(lineChart)) {
				lineChart.getData().clear();
				series.setName("Intentos");
				series2.setName("Errores");
			}

			
			for(int i=0;i<numEjercicio1Chart.size();i++) {
				series.getData().add(new XYChart.Data(numEjercicio1Chart.get(i)+"",intentosChart.get(i)));
				series2.getData().add(new XYChart.Data(numEjercicio1Chart.get(i)+"",erroresChart.get(i)));

			}
			
		
			lineChart.setTitle("Ejercicios Tipo 1");
			lineChart.getData().addAll(series,series2);
			lineChart.setLayoutY(20);
			lineChart.setLayoutX(650);
			lineChart.setPrefSize(380,250);
			//lineChart.setLegendSide(Side.RIGHT);
			//lineChart.setLegendVisible(false);
			vistaEstadisticos.getChildren().add(lineChart);
			series.setName("Intentos");
			series2.setName("Errores");

			yAxis.setMinorTickLength(0);
			yAxis.setMinorTickVisible(false);
			yAxis.setMinorTickCount(0);
			xAxis.setLabel("No. Ejercicio");


		}catch(Exception e2) {
			System.out.println("Error1");
		}	
	}
	public void rellenarTablaEjer2(String user) {
		ArrayList<Integer> intentosChart= new ArrayList(),erroresChart= new ArrayList(),numEjercicio2Chart= new ArrayList();

		System.out.println("ESTE usuario= "+user);
		RandomAccessFile file=null;
		try {
			 file= new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\intentos2.dat", "rw");
		}catch(Exception e) {

			try {
				file= new RandomAccessFile(
						 System.getProperty("user.home") + "/SistFuerzasFiles/intentos2.dat", "rw");
			} catch (FileNotFoundException e1) {
			}
			
		}
			boolean band =false;
			String usuario="";
			int idEjer=0,intentos=0;
			boolean completado=false;
		try {
			int contador=0;
			while(file.getFilePointer()<file.length()) {
				usuario=file.readLine();
				idEjer=file.readInt();
				intentos=file.readInt();
				completado=file.readBoolean();

				band=(user.equals(usuario));	
				if(band) {
					if(contador==0) {
						dataEjercicios2 = FXCollections.observableArrayList(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
					}else {
						dataEjercicios2.add(new ContenidoTabla(idEjer+"",((completado)?(intentos+1):(intentos))+"",intentos+""));
					}
					numEjercicio2Chart.add(idEjer);
					intentosChart.add(((completado)?(intentos+1):(intentos)));
					erroresChart.add(intentos);
					contador++;
				}
			}		
			datosEjercicios2.setItems(dataEjercicios2);

			file.close();

			XYChart.Series series = new XYChart.Series();
			XYChart.Series series2 = new XYChart.Series();


			
			for(int i=0;i<numEjercicio2Chart.size();i++) {
				series.getData().add(new XYChart.Data(numEjercicio2Chart.get(i),intentosChart.get(i)));
				series2.getData().add(new XYChart.Data(numEjercicio2Chart.get(i),erroresChart.get(i)));

			}
			
		if(vistaEstadisticos.getChildren().contains(lineChart2)) {
			lineChart2.getData().clear();
			series.setName("Intentos");
			series2.setName("Errores");
		}
			lineChart2.setTitle("Ejercicios Tipo 2");
			lineChart2.getData().addAll(series,series2);
			lineChart2.setLayoutY(280);
			lineChart2.setLayoutX(650);
			lineChart2.setPrefSize(380,250);
			vistaEstadisticos.getChildren().add(lineChart2);
			series.setName("Intentos");
			series2.setName("Errores");
			xAxis2.setLabel("No. Ejercicio");

			
			
		}catch(Exception e2) {
			System.out.println("Error2");
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	

}
