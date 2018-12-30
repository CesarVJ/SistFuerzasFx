package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Usuario;

public class TipoEjercicio2 implements Initializable{
	
	@FXML
	private AnchorPane ejercicios2;
	@FXML
	private HBox mainBox,cajaResultante,cajaBotones;
	@FXML
	private VBox cajaIzquierda,cajaDerecha;
	@FXML
	private ImageView imgCuerpo;
	@FXML
	private Label textoInferior;
	@FXML
	private TextField tanB,tanA,sumFx,sumFy;
	@FXML
	private Label tituloEjercicio2,fxTxt,fyTxt;
	@FXML
	private Button btnAnterior,BtnSiguiente;
	private static int vects=0;
	private static float[] fuerzas,angulos;
	private static float peso=0;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//imgCuerpo.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true));
		if(leerEjercicio()) {
			imgCuerpo.setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer1.png", 500, 355, false, false));// Ancho.alto
		}

		//imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

		//imgCuerpo.getStyleClass().add("photoProfile");
		ejercicios2.getStyleClass().add("backgrounds");
		
		
		cajaIzquierda.setPadding(new Insets(10, 10, 0, 10));
		cajaIzquierda.setSpacing(10);
		
		cajaDerecha.setPadding(new Insets(10, 10, 0, 2));
		cajaDerecha.setSpacing(20);
		
		tituloEjercicio2.setText(
				"EJERCICIO EQUILIBRIO \nDado este sistema de fuerzas...................................................bla bla"
						+ " y realiza tus operaciones en un papel con ayuda de tu calculadora.(Anotar solo 3 decimales)");

		tituloEjercicio2.setWrapText(true);		
		
		fxTxt.setText("\u03A3"+"Fx = 0 ");
		fxTxt.getStyleClass().add("textForm");
		sumFx.setPrefHeight(40);
		sumFx.setPrefWidth(200);
		Label promptFx=new Label("Escribe la ecuacion correspondiente. Ej. ACos"+"\u03B8"+" + BSen"+"\u03B8"+" - W");
		promptFx.getStyleClass().add("textForm");
		
		sumFx.setPromptText(promptFx.getText()+"");
		sumFx.getStyleClass().add("textForm");

		
		
		fyTxt.setText("\u03A3"+"Fy = 0 ");
		fyTxt.getStyleClass().add("textForm");
		sumFy.setPrefHeight(40);
		
		sumFy.setPromptText(promptFx.getText()+"");
		sumFy.getStyleClass().add("textForm");

		

	}
	@FXML
	public static boolean leerEjercicio() {
	
		int id=0,tipo=0;

		try {
			RandomAccessFile file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2.dat", "rw");
			try {
				if(file.length()==0) {
					System.out.println("Sin ejercicios");
					return false;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    try {
		    	boolean band=false;
		    	
		    	while(file.getFilePointer()<file.length()) {
		    		id=file.readInt();System.out.println(id);
		    		//band = id==idEjer ? true:false;
		    		String name= file.readLine();System.out.println(name);
		    		tipo= file.readInt();System.out.println(tipo);
		    		vects = file.readInt();System.out.println(vects);
		    		angulos= new float[vects];
		    		fuerzas= new float[vects];

					for(int n=0;n<vects;n++) {
						float angulo= file.readFloat();
						float fuerza= file.readFloat();
						angulos[n]=angulo;
						fuerzas[n]=fuerza;
						System.out.println("Angulo: "+angulo+"=> Fuerza: "+fuerza);
					}
					peso=file.readFloat();
					System.out.println("Peso = "+peso);
					//oficiales.setSumFx(fuerzas, angulos, vects);
					//oficiales.setSumFy(fuerzas, angulos, vects);
					//System.out.println("Resultados oficiales: SUmFx= "+oficiales.getSumFx()+" SumFy= "+oficiales.getSumFy());

		    	}
				
				
				
				file.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
		
		
	}
	@FXML
	public void ejercicioAnterior2() {
		
	}
	@FXML
	private void siguienteEjercicio2(ActionEvent event) {
		
	}
	
	@FXML
	private void verificarDatos2(ActionEvent event) {
		
	}
	/*
	
	public static int leerEjercicio(int idEjer) {
		if(idEjer==1) {
			return 3;
		}
		
		int id=0,tipo=0;
		try {
			RandomAccessFile file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
			
			//file.seek(file.length());
		    try {
		    	boolean band=false;
		    	
		    	while(file.getFilePointer()<file.length() || !band) {
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
					oficiales.setSumFx(fuerzas, angulos, vects);
					oficiales.setSumFy(fuerzas, angulos, vects);
					System.out.println("Resultados oficiales: SUmFx= "+oficiales.getSumFx()+" SumFy= "+oficiales.getSumFy());

		    	}
				
				
				
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
	}*/
	
	//==================================

	
	
	/*
	 * @FXML
	private void siguienteEjercicio(ActionEvent event) {
		System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer1());


		btnAnterior.setDisable(false);
		numEjercicio++;
		
		

		//if (numEjercicio == ejercicioMax || ejercicioMax == 1) {
			//ejercicioMax++;
		//}
		if(numEjercicio==ejercicioMax || numEjercicio==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length+1) {
			btnSiguiente.setDisable(true);
		}
		imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		// grafica1.setImage(new
		// Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer"+numEjercicio+".png"),500,400,true,true));
		grafica1.setImage(imgSistema);

		tituloEjercicio1.setText("Estes es otro ejercicio");
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
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length);


	}
*/
	
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
