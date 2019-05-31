package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private HBox mainBox,cajaResultante,cajaBotones,cajaTabla;
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
	private static float[] fuerzas;
	private static float[] angulos;
	private static float peso=0;
	private static boolean isCociente=false;

	private static String ecuacionSumX1="",ecuacionSumX2="",ecuacionSumY1="",ecuacionSumY2="";
	@FXML
	private TableView tablaTensiones;
	@FXML
	ObservableList<ContenidoTabla> data;
	@FXML
	private AnchorPane ejercicios1;
	private int contadorFilas;
	private char letra='A';



	
	Image imgCuerpoContent;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		//imgCuerpo.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 500, 500, true, true));
		if(leerEjercicio(1)) {
			if(System.getProperty("os.name").contains("Windows")) {
			imgCuerpo.setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer1.png", 500, 400, false, false));// Ancho.alto
			}else {
				imgCuerpo.setImage(new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2/Ejer1.png", 500, 400, false, false));// Ancho.alto
			}
		}
		ejercicioMax = getUserName().getMaxEjer2();
		numEjercicio=1;
		if(ejercicioMax==1) {
			BtnSiguiente.setDisable(true);
		}
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);

		}
		tablaTensiones.setMaxWidth(430);
		tablaTensiones.setPrefWidth(430);
		cajaTabla.setAlignment(Pos.CENTER);

		  TableColumn nombres = new TableColumn("Tension");
		  TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
	      fuerzas.setMinWidth(160);
	      TableColumn angulo = new TableColumn("Angulo");
	      angulo.setMinWidth(160);
	      
	      nombres.setMaxWidth(50);
	      nombres.setStyle( "-fx-alignment: CENTER;");

	  
	      tablaTensiones.getColumns().addAll(nombres,fuerzas,angulo);
	      tablaTensiones.resizeColumn(fuerzas, 0);
	      tablaTensiones.resizeColumn(angulo, 0);

	      data=FXCollections.observableArrayList();
	        
	      fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("fuerzas"));
	      angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("angulo"));
	      nombres.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("nombres"));

	       
	      tablaTensiones.setItems(data); 	      
	      tablaTensiones.resizeColumn(fuerzas, 24);
	      tablaTensiones.resizeColumn(angulo, 24);
	      tablaTensiones.resizeColumn(nombres, 2);

		  

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
		Label promptFx=new Label("Escribe la ecuacion correspondiente. Ej. ACos"+"\u03B8"+" + BSen"+"\u03B8");
		promptFx.getStyleClass().add("textForm");
		
		sumFx.setPromptText(promptFx.getText()+"");
		sumFx.getStyleClass().add("textForm");

		
		
		fyTxt.setText("\u03A3"+"Fy = 0 ");
		fyTxt.getStyleClass().add("textForm");
		sumFy.setPrefHeight(40);
		
		sumFy.setPromptText(promptFx.getText()+" - W");
		sumFy.getStyleClass().add("textForm");

		
		
		contadorFilas=tablaTensiones.getItems().size();		
		int sizeTab=tablaTensiones.getItems().size();
		if(vects>sizeTab) {
			for(int i=0;i<(vects-sizeTab);i++) {
				agregarVector();				
			}
		}else if(vects<sizeTab){
			for(int i=0;i<(sizeTab-vects);i++) {
				eliminarVector();				
			}
		}
		

	}
	@FXML
	public  boolean leerEjercicio(int idEjer) {
	
		int id=0,tipo=0;

		try {
			
			RandomAccessFile file=null;
			try {
			file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo2.dat", "rw");
			}catch(Exception e) {
				file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo2.dat", "rw");
			}
			
			
			
			
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
				tituloEjercicio2.setText(descripcion);

				
				
				
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
		
		 if(System.getProperty("os.name").contains("Windows")) {
		imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		}else {
			imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto
		}
		
		
		imgCuerpo.setImage(imgCuerpoContent);
		if (numEjercicio == 1) {
			btnAnterior.setDisable(true);
		}
		BtnSiguiente.setDisable(false);
		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		
		try {
		System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length);
		}catch(Exception e ) {
			System.out.println(new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2").listFiles().length);
		}
		
		
		
		
		leerEjercicio(numEjercicio);
		
		contadorFilas=tablaTensiones.getItems().size();		
		int sizeTab=tablaTensiones.getItems().size();
		if(vects>sizeTab) {
			for(int i=0;i<(vects-sizeTab);i++) {
				agregarVector();				
			}
		}else if(vects<sizeTab){
			for(int i=0;i<(sizeTab-vects);i++) {
				eliminarVector();				
			}
		}

		
		
		
		
	}
	@FXML
	private void siguienteEjercicio2(ActionEvent event) {
		btnAnterior.setDisable(false);
		numEjercicio++;
		int leng=0;

		 if(System.getProperty("os.name").contains("Windows")) {
			 leng=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length;
		 }else {
			 leng=new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2").listFiles().length;
		 }

		
		
		
		
		if(numEjercicio==ejercicioMax || numEjercicio==leng) {
			BtnSiguiente.setDisable(true);
		}
		
		 if(System.getProperty("os.name").contains("Windows")) {
				imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2\\Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

		 }else {
				imgCuerpoContent = new Image("file:///"+System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2/Ejer"+numEjercicio+".png", 500, 355, false, false);// Ancho.alto

		 }
		
		
		
		
		
		
		
		imgCuerpo.setImage(imgCuerpoContent);
		tituloEjercicio2.setText("Estes es otro ejercicio..........");

		System.out.println("Maximos:" + ejercicioMax+" Actual "+numEjercicio);
		
		
		
		 if(System.getProperty("os.name").contains("Windows")) {
				System.out.println(new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length);

		 }else {
				System.out.println(new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2").listFiles().length);

		 }

		leerEjercicio(numEjercicio);
		
		
		//limpiarDatos();
		
		
		contadorFilas=tablaTensiones.getItems().size();

		
		int sizeTab=tablaTensiones.getItems().size();
		if(vects>sizeTab) {
			for(int i=0;i<(vects-sizeTab);i++) {
				agregarVector();				
			}
		}else if(vects<sizeTab){
			for(int i=0;i<(sizeTab-vects);i++) {
				eliminarVector();				
			}
		}
		

	}
	
	 private void agregarVector(){
         contadorFilas++;
         data.add(new ContenidoTabla((letra++)+"","",""));
   }
     private void eliminarVector(){
         data.remove(--contadorFilas);          
         tablaTensiones.getItems().removeAll(tablaTensiones.getSelectionModel().getSelectedItems());
         letra--;
   }
	
     
     
     public boolean compareTables(float[] angu, float[] fuer) {
    	 
    	
    	 for(int i=0;i<angulos.length;i++) {
    		 
			 System.out.println(fuerzas[i]+" "+angulos[i]);
			 //System.out.println(fuerzas[i+1]+" "+angulos[i+1]);
			 if(angulos[i]>=0 && angulos[i]<=90) {
				 
			 }else if(angulos[i]>90 && angulos[i]<=180) {
				 angu[i]=180-angu[i];
			 }else if(angulos[i]>180 && angulos[i]<=270) {
				 angu[i]=angu[i]-180;
			 }else if(angulos[i]>270 && angulos[i]<=360) {
				 angu[i]=360-angu[i];
			 }
    		 if((Math.floor(angulos[i])!=Math.floor(angu[i])) || (Math.floor(fuerzas[i])!=Math.floor(fuer[i]))) {
    			 System.out.println("Incorrecta");
    			 return false;
    		 }
			 System.out.println("Correcta");

    	 }
    	 return true;
    	 
     }
	@FXML
	private void verificarDatos2(ActionEvent event) {
		//
		///
		////
		Alert resultado = new Alert(Alert.AlertType.WARNING);

		//==========RESPUESTA CORRECTA====================================================
		System.out.println("Sumatorio de X="+getEcuacionFx());//Tiene que dar cero
		getEcuacionFy();
		System.out.println("Ecuaciones de sumatoria de Fx:\n"+ecuacionSumX1+"\n"+ecuacionSumX2);
		System.out.println("Ecuaciones de sumatoria de Fy:\n"+ecuacionSumY1+"\n"+ecuacionSumY2);
		
		ObservableList<ContenidoTabla> dataRows = FXCollections.observableArrayList();

		int i = 0;
		float[] angu = new float[vects];
		float[] fuer = new float[vects];

		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				dataRows.add(bean);
				angu[i] = Float.parseFloat(bean.getAngulo().getText());
				fuer[i] = Float.parseFloat(bean.getFuerzas().getText());			
			}
			System.out.println(fuer[i] + "," + angu[i]);
			i++;
		}
		
		boolean iguales = compareTables(angu,fuer);
		
	
		
		
		
		
		
		
		
		
		
		int lenFile=0;
		try {
		lenFile = new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicio2").listFiles().length;
		}catch(Exception e) {
			lenFile = new File(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicio2").listFiles().length;
		}
		
		
		
		if(ecuacionSumX1.equalsIgnoreCase(sumFx.getText()) && ecuacionSumY1.equalsIgnoreCase(sumFy.getText()) && iguales) {
			
		if((ejercicioMax)== lenFile && numEjercicio==ejercicioMax) {
			BtnSiguiente.setDisable(true);
		}
		
	
		
		if(BtnSiguiente.isDisabled()) {
			if(ejercicioMax==numEjercicio && (ejercicioMax)<lenFile) {
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
			if((ejercicioMax)==lenFile) {
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
		
		registrarCompletado(getUserName().getUsuario(),numEjercicio);

		resultado.setTitle("Respuesta correcta");
		resultado.setContentText("Tus resultados han sido los correctos, puedes pasar al siguiente ejercicio.");
		ImageView correcta = new ImageView(
				new Image(getClass().getResourceAsStream("/images/respuesta_correcta.png"), 50, 50, true, true));
		resultado.setGraphic(correcta);
		resultado.setHeaderText(null);
    	resultado.showAndWait();
		
	}else {
		registrarIntento(getUserName().getUsuario(),numEjercicio);

		resultado.setTitle("Respuesta incorrecta");
		resultado.setContentText(
				"Alguno de tus resultados es incorrecto, verifica tus operaciones y vuelve a intentarlo");
		ImageView incorrecta = new ImageView(
			new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"), 50, 50, true, true));
		resultado.setGraphic(incorrecta);
    	new Shake(tablaTensiones).play();
    	resultado.setHeaderText(null);
    	resultado.showAndWait();
		System.out.println("Respuesta incorrecta");
    	return;

	}
		//==============================================================

	}
	
	public void aumentarMaximos(Usuario user,int max) throws IOException {
		
		
		RandomAccessFile file=null;
		try {
		file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\users.dat", "rw");
		}catch(Exception e ) {
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
			file.writeInt(maxEjer1);
			file.writeInt(max);
		}
		file.close();
	}
	
	
	public int getEcuacionFx() {
		int resultado=0;
		ecuacionSumX1="";
		ecuacionSumX2="";
		char tension='A';
		boolean positivo=true;
		
		for(int i=0;i<angulos.length;i++) {
			String ecuTemp1="",ecuTemp2="";
			if(angulos[i]>=0 && angulos[i]<=90) {
				ecuTemp1=tension+"Cos"+Math.round(angulos[i]);
				ecuTemp2=tension+"Sen"+(90-Math.round(angulos[i]));
				positivo=true;
				
				resultado+=fuerzas[i]*Math.cos(Math.toRadians(angulos[i]));
				
			}else if(angulos[i]>90 && angulos[i]<=180) {
				ecuTemp1=tension+"Cos"+(180-Math.round(angulos[i]));
				ecuTemp2=tension+"Sen"+(Math.round(angulos[i])-90);
				positivo=false;
				
				resultado-=fuerzas[i]*Math.cos(Math.toRadians(180-angulos[i]));

			}else if(angulos[i]>180 && angulos[i]<=270) {
				ecuTemp1=tension+"Cos"+(Math.round(angulos[i])-180);
				ecuTemp2=tension+"Sen"+(270-Math.round(angulos[i]));
				positivo=false;
				
				resultado-=fuerzas[i]*Math.cos(Math.toRadians(angulos[i]-180));

			}else if(angulos[i]>270 && angulos[i]<=360) {
				ecuTemp1=tension+"Cos"+(360-Math.round(angulos[i]));
				ecuTemp2=tension+"Sen"+(Math.round(angulos[i])-270);
				positivo=true;
				
				resultado+=fuerzas[i]*Math.cos(Math.toRadians(angulos[i]-270));

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
		
		return resultado;
		
	}
	
	
	public void getEcuacionFy() {
		ecuacionSumY1="";
		ecuacionSumY2="";
		char tension='A';
		boolean positivo=true;
		
		for(int i=0;i<angulos.length;i++) {
			String ecuTemp1="",ecuTemp2="";
			if(angulos[i]>=0 && angulos[i]<=90) {
				ecuTemp1=tension+"Sen"+Math.round(angulos[i]);
				ecuTemp2=tension+"Cos"+(90-Math.round(angulos[i]));
			}else if(angulos[i]>90 && angulos[i]<=180) {
				ecuTemp1=tension+"Sen"+(180-Math.round(angulos[i]));
				ecuTemp2=tension+"Cos"+(Math.round(angulos[i])-90);
			}else if(angulos[i]>180 && angulos[i]<=270) {
				ecuTemp1=tension+"Sen"+(Math.round(angulos[i])-180);
				ecuTemp2=tension+"Cos"+(270-Math.round((angulos[i])));
				positivo=false;
			}else if(angulos[i]>270 && angulos[i]<=360) {
				ecuTemp1=tension+"Sen"+(360-Math.round(angulos[i]));
				ecuTemp2=tension+"Cos"+(Math.round(angulos[i])-270);
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
						
			ecuacionSumY1+=ecuTemp1;
			ecuacionSumY2+=ecuTemp2;
			tension++;
			
		}
		ecuacionSumY1+="-"+Math.round(peso);
		ecuacionSumY2+="-"+Math.round(peso);
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
	
	public void registrarIntento(String user,int idEjercicio) {
		RandomAccessFile file=null;
		try {
			 file= new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\intentos2.dat", "rw");
		}catch(Exception e) {
			System.out.println("Error0");

			try {
				file= new RandomAccessFile(
						 System.getProperty("user.home") + "/SistFuerzasFiles/intentos2.dat", "rw");
			} catch (FileNotFoundException e1) {
				System.out.println("Error1");
			}
			
		}
			boolean band =false;
			String usuario="";
			int idEjer=0,intentos=0;
			long posInicial=0,posFinal=0;
			boolean completado=false;
		try {
			while(!band && file.getFilePointer()<file.length()) {

				posInicial=file.getFilePointer();
				usuario=file.readLine();
				idEjer=file.readInt();
				intentos=file.readInt();
				completado=file.readBoolean();

				band=(user.equals(usuario) && idEjer==idEjercicio);	
				System.out.println("["+usuario+","+idEjer+","+intentos+","+completado+"]");
			}
			
			if(band) {//Aumentar el num Intentos
				file.seek(posInicial);
				file.writeBytes(usuario+"\n");
				file.writeInt(idEjer);
				file.writeInt(intentos+1);
				file.writeBoolean(completado);
			}else {//primer intento
				file.seek(file.length());
				file.writeBytes(user+"\n");
				file.writeInt(idEjercicio);
				file.writeInt(1);	
				file.writeBoolean(false);
			}
			file.close();
			
			
			
		}catch(Exception e2) {
			System.out.println("Error2");
		}
	}
	
	public void registrarCompletado(String user,int idEjercicio) {
		
		RandomAccessFile file=null;
		try {
			 file= new RandomAccessFile(
					 System.getProperty("user.home") + "\\SistFuerzasFiles\\intentos2.dat", "rw");
		}catch(Exception e) {
			System.out.println("Error0");

			try {
				file= new RandomAccessFile(
						 System.getProperty("user.home") + "/SistFuerzasFiles/intentos2.dat", "rw");
			} catch (FileNotFoundException e1) {
				System.out.println("Error1");
			}
			
		}
			boolean band =false;
			String usuario="";
			int idEjer=0,intentos=0;
			long posInicial=0,posFinal=0;
			boolean completado=false;
		try {
			while(!band && file.getFilePointer()<file.length()) {

				posInicial=file.getFilePointer();
				usuario=file.readLine();
				idEjer=file.readInt();
				intentos=file.readInt();
				completado=file.readBoolean();

				band=(user.equals(usuario) && idEjer==idEjercicio);	
				System.out.println("["+usuario+","+idEjer+","+intentos+","+completado+"]");
			}
			
			if(band) {//Aumentar el num Intentos
				file.seek(posInicial);
				file.writeBytes(usuario+"\n");
				file.writeInt(idEjer);
				file.writeInt(intentos);
				file.writeBoolean(true);
			}else {//primer intento
				file.seek(file.length());
				file.writeBytes(user+"\n");
				file.writeInt(idEjercicio);
				file.writeInt(0);	
				file.writeBoolean(true);
			}
			file.close();
			
			
			
		}catch(Exception e2) {
			System.out.println("Error2");
		}
	
		
	}
	
	
}
