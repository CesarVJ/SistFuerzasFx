package application;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Recursos implements Initializable {
	
	@FXML
	AnchorPane recursos,contenedor2,contenedor1,editorRecursos,visorDeRecursos;
	@FXML
	VBox btnApuntes,btnVideos,btnWikis,btnEjemplos,btnCuestionarios,btnObjetivo,btnPropuestos,btnAgregar,formulario,listaDeRecursos,contenedorRecursos;
	@FXML
	ImageView imgApuntes,imgVideos,imgWikis,imgEjemplos,imgCuestionarios,imgObjetivo,imgPropuestos,btnAtras,imgAgregar,salirEditor,salirDeVisor;
	@FXML
	Label txtApuntes,txtVideos,txtWikis,txtEjemplos,txtCuestionarios,txtObjetivo,txtPropuestos,txtAgregar;
	@FXML
	Hyperlink myHyperlink;
	@FXML
	WebView video1,video2;
	@FXML
	HBox urlBox,nombreBox,categoriaBox;
	@FXML
	TextField URL,nombre;
	@FXML
	ChoiceBox<String> categoria;
	@FXML
	Button btnValidar;
	@FXML
	Label txtTitle;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		recursos.getStyleClass().add("backgrounds");
		recursos.getStyleClass().add("profileBackground");
		
		btnApuntes.getStyleClass().addAll("profileBox","resourcesBox");
		btnWikis.getStyleClass().addAll("profileBox","resourcesBox");
		btnEjemplos.getStyleClass().addAll("profileBox","resourcesBox");
		btnCuestionarios.getStyleClass().addAll("profileBox","resourcesBox");
		btnVideos.getStyleClass().addAll("profileBox","resourcesBox");
		btnPropuestos.getStyleClass().addAll("profileBox","resourcesBox");
		btnObjetivo.getStyleClass().addAll("profileBox","resourcesBox");
		btnAgregar.getStyleClass().addAll("profileBox","resourcesBox");

		txtApuntes.getStyleClass().add("textForm");
		txtVideos.getStyleClass().add("textForm");
		txtWikis.getStyleClass().add("textForm");
		txtEjemplos.getStyleClass().add("textForm");
		txtCuestionarios.getStyleClass().add("textForm");
		txtPropuestos.getStyleClass().add("textForm");
		txtObjetivo.getStyleClass().add("textForm");
		txtAgregar.getStyleClass().add("textForm");

		formulario.setSpacing(30);
		urlBox.setSpacing(15);
		nombreBox.setSpacing(15);
		categoriaBox.setSpacing(15);
		
		categoria.getItems().addAll("Apuntes","Videos","Ejemplos","Cuestionarios","Wikis","Ejercicios");
		categoria.getStylesheets().add(Tabs.class.getResource("/view/Estilos.css").toExternalForm());
		categoria.setTooltip(new Tooltip("Selecciona en donde se añadira el recurso"));
		categoria.getSelectionModel().select(0);
		
		btnValidar.getStyleClass().add("btnGuardar");

		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
	    btnApuntes.setEffect(ef);
		btnApuntes.setSpacing(10);
		
		btnVideos.setEffect(ef);
		btnVideos.setSpacing(10);
		
		btnWikis.setEffect(ef);
		btnWikis.setSpacing(10);
		
		btnEjemplos.setEffect(ef);
		btnEjemplos.setSpacing(10);
		
		btnCuestionarios.setEffect(ef);
		btnCuestionarios.setSpacing(10);
		
		btnObjetivo.setEffect(ef);
		btnObjetivo.setSpacing(10);
		
		btnAgregar.setEffect(ef);
		btnAgregar.setSpacing(10);
		
		btnPropuestos.setEffect(ef);
		btnPropuestos.setSpacing(10);
		txtPropuestos.setWrapText(true);
		txtObjetivo.setText("Objetivo de\naprendizaje");
		txtPropuestos.setText(" Ejercicios\nPropuestos");
		txtAgregar.setText(" Añadir Recursos");	
		
		imgApuntes.setImage(new Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true, true));
		imgApuntes.getStyleClass().add("photoProfile");
		
		imgVideos.setImage(new Image(getClass().getResourceAsStream("/images/videos.png"), 100, 100, true, true));
		imgVideos.getStyleClass().add("photoProfile");
		
		imgWikis.setImage(new Image(getClass().getResourceAsStream("/images/wikis.png"), 100, 100, true, true));
		imgWikis.getStyleClass().add("photoProfile");
		
		imgCuestionarios.setImage(new Image(getClass().getResourceAsStream("/images/cuestionarios.png"), 100, 100, true, true));
		imgCuestionarios.getStyleClass().add("photoProfile");
		
		imgObjetivo.setImage(new Image(getClass().getResourceAsStream("/images/objetivo.png"), 85, 85, true, true));
		imgObjetivo.getStyleClass().add("photoProfile");
		
		imgEjemplos.setImage(new Image(getClass().getResourceAsStream("/images/ejemplos.png"), 85, 85, true, true));
		imgEjemplos.getStyleClass().add("photoProfile");
		
		imgPropuestos.setImage(new Image(getClass().getResourceAsStream("/images/propuestos.png"), 85, 85, true, true));
		imgPropuestos.getStyleClass().add("photoProfile");
		
		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile","boton");

		imgAgregar.setImage(new Image(getClass().getResourceAsStream("/images/addResources.png"), 100, 100, true, true));
		imgAgregar.getStyleClass().addAll("photoProfile");
		
		salirEditor.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		salirEditor.getStyleClass().addAll("photoProfile","boton");
		
		salirDeVisor.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		salirDeVisor.getStyleClass().addAll("photoProfile","boton");
	
		btnApuntes.setAlignment(Pos.CENTER);
		btnVideos.setAlignment(Pos.CENTER);
		btnWikis.setAlignment(Pos.CENTER);
		btnEjemplos.setAlignment(Pos.CENTER);
		btnCuestionarios.setAlignment(Pos.CENTER);
		btnPropuestos.setAlignment(Pos.CENTER);
		btnObjetivo.setAlignment(Pos.CENTER);
		btnAgregar.setAlignment(Pos.CENTER);		
		
		contenedor2.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		contenedor2.getStyleClass().add("backgrounds");
		contenedor1.getStyleClass().add("backgrounds");
		contenedor1.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());


		contenedor2.setPrefWidth(1100);
		contenedor2.setPrefHeight(580);
		
		
		editorRecursos.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		editorRecursos.getStyleClass().add("backgrounds");
		editorRecursos.setPrefWidth(1100);
		editorRecursos.setPrefHeight(580);
		
		visorDeRecursos.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		visorDeRecursos.getStyleClass().add("backgrounds");
		visorDeRecursos.setPrefWidth(1100);
		visorDeRecursos.setPrefHeight(580);
		
		listaDeRecursos.setSpacing(10);
		listaDeRecursos.setAlignment(Pos.CENTER);
		contenedorRecursos.setAlignment(Pos.CENTER);


		
		contenedor1.toFront();
		//Links
	
		btnVideos.setOnMouseClicked(e->{			
			//ventanaProfile.toFront();
			contenedor2.toFront();
			
		});
		
		btnApuntes.setOnMouseClicked(e->{			
			visorDeRecursos.toFront();
			cargarRecursos("Apuntes");
		});
		btnWikis.setOnMouseClicked(e->{
			visorDeRecursos.toFront();
			cargarRecursos("Wikis");	
		});
		btnEjemplos.setOnMouseClicked(e->{
			visorDeRecursos.toFront();
			cargarRecursos("Ejemplos");	
		});
		
		btnCuestionarios.setOnMouseClicked(e->{
			visorDeRecursos.toFront();
			cargarRecursos("Cuestionarios");	
		});
		
		btnPropuestos.setOnMouseClicked(e->{
			irPagina("http://www.google.com");					
		});
		
		btnObjetivo.setOnMouseClicked(e->{
			irPagina("http://www.google.com");		
		});
		
		btnAgregar.setOnMouseClicked(e->{
			editorRecursos.toFront();
		});
		
		btnAtras.setOnMouseClicked(e->{
			contenedor2.toBack();
		});
		
		salirEditor.setOnMouseClicked(e->{
			editorRecursos.toBack();
		});
		
		salirDeVisor.setOnMouseClicked(e->{
			visorDeRecursos.toBack();
		});
		
		
	    video1.getEngine().load(
	      "https://www.youtube.com/embed/Da-2h2B4faU"
	    );
	    
	    video2.getEngine().load(
	  	      "https://www.youtube.com/embed/Da-2h2B4faU"
	  	    );
	    //video1.setPrefSize(640, 390);
		/*myHyperlink.setOnAction(e -> {
		    if(Desktop.isDesktopSupported())
		    {
		        try {
		            Desktop.getDesktop().browse(new URI("http://www.google.com"));
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (URISyntaxException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
	}*/

	}
	
	public void cargarRecursos(String categoria) {
		try {
			verificarLista();
			enlistarRecursos(categoria);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void verificarLista() {
		boolean listaVacia=listaDeRecursos.getChildren().isEmpty();
		if(!listaVacia) {
			limpiarLista();
		}
	}
	
	public void limpiarLista() {
		listaDeRecursos.getChildren().clear();
	}
	
	public void enlistarRecursos(String categoriaAEnlistar) throws IOException {
		txtTitle.setText(categoriaAEnlistar);
		RandomAccessFile archivoDeRecursos =crearArchivo();
		Recurso recursoActual=null;
		long indicador = 0;
		archivoDeRecursos.seek(indicador);
		while(archivoDeRecursos.getFilePointer()<archivoDeRecursos.length()) {
			String nombre=archivoDeRecursos.readLine();
			String categoria= archivoDeRecursos.readLine();
			String enlace=archivoDeRecursos.readLine();
			if(categoriaAEnlistar.equals(categoria)) {
				recursoActual= new Recurso(nombre,categoria,enlace);
				posicionarRecurso(recursoActual);
			}
		}
		archivoDeRecursos.close();
	}
	
	public void posicionarRecurso(Recurso recursoNuevo) {
		//nuevoElemento.setLayoutY(Y);

		Label nombre = new Label(recursoNuevo.getNombre());
		HBox nuevoElemento= new HBox();
		nuevoElemento.getChildren().add(nombre);
		nuevoElemento.setPrefHeight(18);
		nuevoElemento.setPrefWidth(600);

		agregarEfecto(nuevoElemento);		
		
		listaDeRecursos.getChildren().add(nuevoElemento);
		definirHipervinculo(nuevoElemento,recursoNuevo.getEnlace());
	
	}
	
	public void agregarEfecto(HBox nuevoElemento) {
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(1);
	    ef.setOffsetY(1);
	    ef.setRadius(1);	    
	    nuevoElemento.getStyleClass().addAll("cajaRecurso","titlesText");
	    nuevoElemento.setEffect(ef);
	    txtTitle.getStyleClass().addAll("textBoard");	   	    
	    
	}
	
	public void definirHipervinculo(HBox nuevoElemento,String enlace) {
		nuevoElemento.setOnMouseClicked(e->{
			irPagina(enlace);					
		});
	}
	
	
	
	public void validarRecurso() {
		RandomAccessFile datosRecursos= crearArchivo();
		escribirDatos(datosRecursos);
	}
	public RandomAccessFile crearArchivo() {
		try {
			return crearParaWindows();
		}catch(Exception e) {
			return crearParaLinux();
		}	
	}
	
	public RandomAccessFile crearParaWindows() throws FileNotFoundException {
		return new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\recursos.dat", "rw");
	}
	
	public RandomAccessFile crearParaLinux(){
		try {
			return  new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/recursos.dat", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void escribirDatos(RandomAccessFile datosRecursos) {
		try {
			datosRecursos.seek(datosRecursos.length());
			Recurso recursoActual= recuperarDatos();		
			datosRecursos.writeBytes(recursoActual.getNombre()+"\n");
			datosRecursos.writeBytes(recursoActual.getCategoria()+"\n");
			datosRecursos.writeBytes(recursoActual.getEnlace()+"\n");
			datosRecursos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Recurso recuperarDatos() {
		Recurso actualResource = new Recurso();
		String nombreActual=nombre.getText();
		String categoriaActual=categoria.getSelectionModel().getSelectedItem();
		String URLActual=URL.getText();
		actualResource.setNombre(nombreActual);
		actualResource.setCategoria(categoriaActual);
		actualResource.setEnlace(URLActual);
		return actualResource;		
	}
	
	public static void irPagina(String pagina) {
		if(Desktop.isDesktopSupported())
	    {
	        try {
	            Desktop.getDesktop().browse(new URI(pagina));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } catch (URISyntaxException e1) {
	            e1.printStackTrace();
	        }
	    }
	
	}
}
