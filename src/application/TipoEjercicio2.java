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
	private  Label tituloEjercicio2;
	@FXML
	private Label fxTxt;
	@FXML
	private Label fyTxt;
	@FXML
	private Button btnAnterior,BtnSiguiente;
	private static int vects=0,numEjercicio=1,ejercicioMax = getUserName().getMaxEjer2();;
	private static float[] fuerzas,angulos;
	private static float peso=0;
	private static String ecuacionSumX1="",ecuacionSumX2="",ecuacionSumY1="",ecuacionSumY2="";

	
	Image imgCuerpoContent;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		//imgCuerpo.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true));
		if(leerEjercicio(1)) {
			imgCuerpo.setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer1.png", 500, 355, false, false));// Ancho.alto
		}
		ejercicioMax = getUserName().getMaxEjer2();
		numEjercicio=1;
		if(ejercicioMax==1) {
			BtnSiguiente.setDisable(true);
		}
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);

		}


		//imgSistema = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

		//imgCuerpo.getStyleClass().add("photoProfile");
		ejercicios2.getStyleClass().add("backgrounds");
		
		
		cajaIzquierda.setPadding(new Insets(10, 10, 0, 10));
		cajaIzquierda.setSpacing(10);
		
		cajaDerecha.setPadding(new Insets(10, 10, 0, 2));
		cajaDerecha.setSpacing(20);
		
		/*tituloEjercicio2.setText(
				"EJERCICIO EQUILIBRIO \nDado este sistema de fuerzas...................................................bla bla"
						+ " y realiza tus operaciones en un papel con ayuda de tu calculadora.(Anotar solo 3 decimales)");
*/
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
	public  boolean leerEjercicio(int idEjer) {
	
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
		    	String descripcion="";

		    	while(file.getFilePointer()<file.length() && !band) {
		    		id=file.readInt();System.out.println(id);
		    		band = id==idEjer ? true:false;
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
					descripcion=file.readLine();

					
					System.out.println("Peso = "+peso);
					//oficiales.setSumFx(fuerzas, angulos, vects);
					//oficiales.setSumFy(fuerzas, angulos, vects);
					//System.out.println("Resultados oficiales: SUmFx= "+oficiales.getSumFx()+" SumFy= "+oficiales.getSumFy());

		    	}
				tituloEjercicio2.setText("EJERCICIO EQUILIBRIO \n"+descripcion);

				
				
				
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
		numEjercicio -= 1;
		
		imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		imgCuerpo.setImage(imgCuerpoContent);
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);
		}
		BtnSiguiente.setDisable(false);
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length);
		leerEjercicio(numEjercicio);

		
		
		
		
	}
	@FXML
	private void siguienteEjercicio2(ActionEvent event) {
		btnAnterior.setDisable(false);
		numEjercicio++;
		if(numEjercicio==ejercicioMax || numEjercicio==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length) {
			BtnSiguiente.setDisable(true);
		}
		imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		imgCuerpo.setImage(imgCuerpoContent);
		tituloEjercicio2.setText("Estes es otro ejercicio..........");

		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length);
		leerEjercicio(numEjercicio);

	}
	
	@FXML
	private void verificarDatos2(ActionEvent event) {
		//
		///
		////
		//==========RESPUESTA CORRECTA====================================================
		getEcuacionFx();
		getEcuacionFy();
		System.out.println("Ecuaciones de sumatoria de Fx:\n"+ecuacionSumX1+"\n"+ecuacionSumX2);
		System.out.println("Ecuaciones de sumatoria de Fy:\n"+ecuacionSumY1+"\n"+ecuacionSumY2);

		

		if((ejercicioMax)==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length && numEjercicio==ejercicioMax) {
			BtnSiguiente.setDisable(true);
		}
		
	
		
		if(BtnSiguiente.isDisabled()) {
			if(ejercicioMax==numEjercicio && (ejercicioMax)<new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length) {
			BtnSiguiente.setDisable(false);
			ejercicioMax++;
			System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer2());
			getUserName().setMaxEjer2(ejercicioMax);
			System.out.println(getUserName().getCorreo()+" "+getUserName().getNacimiento()+" "+getUserName().getPassword()+" Max = "+getUserName().getMaxEjer2());
			try {
				aumentarMaximos(getUserName(),ejercicioMax);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		

			}
			//==============================
			if((ejercicioMax)==new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length) {
				ejercicioMax++;
				getUserName().setMaxEjer2(ejercicioMax);
				try {
					aumentarMaximos(getUserName(),ejercicioMax);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			//=====================================
		}	
		//==============================================================

	}
	
	public void aumentarMaximos(Usuario user,int max) throws IOException {
		
		
		RandomAccessFile file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
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
			file.writeInt(maxEjer1);
			file.writeInt(max);
		}
		file.close();
	}
	
	
	public void getEcuacionFx() {
		ecuacionSumX1="";
		ecuacionSumX2="";
		char tension='A';
		boolean positivo=true;
		
		for(int i=0;i<angulos.length;i++) {
			String ecuTemp1="",ecuTemp2="";
			if(angulos[i]>=0 && angulos[i]<=90) {
				ecuTemp1=tension+"Cos"+angulos[i];
				ecuTemp2=tension+"Sen"+(90-angulos[i]);
				positivo=true;
			}else if(angulos[i]>90 && angulos[i]<=180) {
				ecuTemp1=tension+"Cos"+(180-angulos[i]);
				ecuTemp2=tension+"Sen"+(angulos[i]-90);
				positivo=false;
			}else if(angulos[i]>180 && angulos[i]<=270) {
				ecuTemp1=tension+"Cos"+(angulos[i]-180);
				ecuTemp2=tension+"Sen"+(270-angulos[i]);
				positivo=false;
			}else if(angulos[i]>270 && angulos[i]<=360) {
				ecuTemp1=tension+"Cos"+(360-angulos[i]);
				ecuTemp2=tension+"Sen"+(angulos[i]-270);
				positivo=true;
			}
			if(i!=0) {
				ecuacionSumX1+=positivo?"+":"-";
				ecuacionSumX2+=positivo?"+":"-";	
			}else {
				ecuacionSumX1+=positivo?"":"-";
				ecuacionSumX2+=positivo?"":"-";	
			}
			//if(i==0 && ecuacionSum1.equals("+")); ecuacionSum1="";
			//if(i==0 && ecuacionSum1.equals("+")); ecuacionSum2="";

			
			
			ecuacionSumX1+=ecuTemp1;
			ecuacionSumX2+=ecuTemp2;
			tension++;
			
		}
		
	}
	
	
	public void getEcuacionFy() {
		ecuacionSumY1="";
		ecuacionSumY2="";
		char tension='A';
		boolean positivo=true;
		
		for(int i=0;i<angulos.length;i++) {
			String ecuTemp1="",ecuTemp2="";
			if(angulos[i]>=0 && angulos[i]<=90) {
				ecuTemp1=tension+"Sen"+angulos[i];
				ecuTemp2=tension+"Cos"+(90-angulos[i]);
			}else if(angulos[i]>90 && angulos[i]<=180) {
				ecuTemp1=tension+"Sen"+(180-angulos[i]);
				ecuTemp2=tension+"Cos"+(angulos[i]-90);
			}else if(angulos[i]>180 && angulos[i]<=270) {
				ecuTemp1=tension+"Sen"+(angulos[i]-180);
				ecuTemp2=tension+"Cos"+(270-angulos[i]);
				positivo=false;
			}else if(angulos[i]>270 && angulos[i]<=360) {
				ecuTemp1=tension+"Sen"+(360-angulos[i]);
				ecuTemp2=tension+"Cos"+(angulos[i]-270);
				positivo=false;
			}
			if(i!=0) {
				ecuacionSumY1+=positivo?"+":"-";
				ecuacionSumY2+=positivo?"+":"-";	
			}else {
				ecuacionSumY1+=positivo?"":"-";
				ecuacionSumY2+=positivo?"":"-";	
			}
			//if(i==0 && ecuacionSum1.equals("+")); ecuacionSum1="";
			//if(i==0 && ecuacionSum1.equals("+")); ecuacionSum2="";

			
			
			ecuacionSumY1+=ecuTemp1+"-"+peso;
			ecuacionSumY2+=ecuTemp2+"-"+peso;
			tension++;
			
		}
		
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
