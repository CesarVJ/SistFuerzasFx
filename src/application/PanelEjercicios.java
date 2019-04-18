package application;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;

public class PanelEjercicios implements Initializable {
	@FXML
	AnchorPane ventanaPanel,ejerciciosT1,ejercicios,editorEjercicios;
	@FXML
	VBox titlePanel,photoBox,picture,titlePanel2,photoBox2,picture2,formData;
	@FXML
	Label textTitle,textTitle2,indicacionLbl,vectLbl;
	@FXML
	ImageView photoProfile,photoProfile2,btnAtras,btnAtrasEditor;
	@FXML
	TextField numVect;
	@FXML
	private TableView tablaDatos;
	@FXML
	TextArea txtDescripcion;
	@FXML
	ObservableList<ContenidoTabla> data;
	
	int contadorFilas=0;
	int numFilasAnt=0;
	int vects=0;	
	
	int size;
	VBox btnEjercicios[];
	ImageView imgEjercicios[];
	float initialLayoutX=90;
	float initialLayoutY=70;
	static int idEjer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		DropShadow ef = new DropShadow();
		ef.setWidth(20);
	    ef.setHeight(20);
	    ef.setOffsetX(0);
	    ef.setOffsetY(0);
	    ef.setRadius(10);
		
		btnAtras.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtras.getStyleClass().addAll("photoProfile","boton");
		
		
		btnAtrasEditor.setImage(new Image(getClass().getResourceAsStream("/images/back.png"), 50, 50, true, true));
		btnAtrasEditor.getStyleClass().addAll("photoProfile","boton");
		
		ventanaPanel.getStyleClass().add("backgrounds");
		ventanaPanel.getStyleClass().add("profileBackground");	
		titlePanel.getStyleClass().add("titlesProf");
		textTitle.getStyleClass().add("titlesText");

		photoBox.setAlignment(Pos.CENTER);
		photoBox.setSpacing(12);
		
		photoProfile.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 330, 330, true, true));
		photoProfile.getStyleClass().add("photoProfile");		
		picture.getStyleClass().addAll("profileBox","resourcesBox2");

		
		

		titlePanel2.getStyleClass().add("titlesProf");
		textTitle2.getStyleClass().add("titlesText");

		photoBox2.setAlignment(Pos.CENTER);
		photoBox2.setSpacing(12);
		
		photoProfile2.setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer2.png"), 330, 330, true, true));
		photoProfile2.getStyleClass().add("photoProfile");		
		picture2.getStyleClass().addAll("profileBox","resourcesBox2");
		
		
		ejerciciosT1.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejerciciosT1.getStyleClass().add("backgrounds");
		
		ejerciciosT1.setPrefWidth(1100);
		ejerciciosT1.setPrefHeight(580);
		
		
		
		editorEjercicios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		editorEjercicios.getStyleClass().add("backgrounds");
		
		editorEjercicios.setPrefWidth(1100);
		editorEjercicios.setPrefHeight(580);
		
		
		
		ejercicios.getStylesheets().add(Window.class.getResource("/view/Estilos.css").toExternalForm());
		ejercicios.getStyleClass().add("backgrounds");
		
		ejercicios.setPrefWidth(1100);
		ejercicios.setPrefHeight(580);
		
		/*
		btnEjer.getStyleClass().addAll("profileBox","resourcesBox");
		
		btnEjer.setOnMouseClicked(e->{
			System.out.println("Hello");
			//irPagina("http://www.google.com");		
		});
		
		imgEjer.setImage(new Image(getClass().getResourceAsStream("/images/apuntes.png"), 100, 100, true, true));
		imgEjer.getStyleClass().add("photoProfile");
		
		for(int i=0;i<3;i++) {
			
		}*/
		//=============================
		
		ejercicios.toFront();
		picture.setEffect(ef);
		picture.setSpacing(10);
		
		picture2.setEffect(ef);
		picture2.setSpacing(10);
		//btnEjer.setEffect(ef);
		//btnEjer.setSpacing(10);
		//btnEjer.setAlignment(Pos.CENTER);
	
	
		
		picture.setOnMouseClicked(e->{			
			//ventanaProfile.toFront();
			//===================Listando ejercicios tipo 1===================================		
			//Crear btnEjer(VBox)
			//Crear imgEjer(ImageView)
			if(!ejerciciosT1.getChildren().contains(btnAtras)) {
				ejerciciosT1.getChildren().add(btnAtras);
				
			}
			
			 size=new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length+1; 
			 btnEjercicios= new VBox[size];
			 imgEjercicios = new ImageView[size];
			 initialLayoutX=90;
			 initialLayoutY=70;

			for(int i=0;i<size;i++) {
				btnEjercicios[i]= new VBox();
				imgEjercicios[i]= new ImageView();
				imgEjercicios[i].getStyleClass().add("photoProfile");
				Label text=new Label("Ejercicio");
				text.getStyleClass().add("textForm");
				
				
				if(i!=0) {
					RandomAccessFile file =null;
					int id=0,tipo=0,vects=0;
					String nameImage="",descripcion="";
					
					try {
						file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
						boolean band=false;
						
						while(!band && file.getFilePointer()<file.length()) {
							id=file.readInt();
							band=(i+1)==id?true:false;
							nameImage=file.readLine();
							tipo=file.readInt();
							vects=file.readInt();
							float angulos[]= new float[vects];
							float fuerzas[]= new float[vects];

							for(int n=0;n<angulos.length;n++) {
								angulos[n]=file.readFloat();
								fuerzas[n]=file.readFloat();
							}
							descripcion=file.readLine();						
						}
						file.close();						
						System.out.println(nameImage);
						imgEjercicios[i].setImage(new Image("file:///"+System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+id+".png", 150, 150, true, true));

						//imgEjercicios[i].setImage(new Image(getClass().getResourceAsStream(System.getProperty("user.home")+"/SistFuerzasFiles/imgEjercicios1/Ejer3.png"), 100, 100, true, true));
						
						
						
						
					} catch (Exception e1) {
						System.out.println("Error1");
						try {
							file = new RandomAccessFile(System.getProperty("user.home") + "/SistFuerzasFiles/graficasInfo.dat", "rw");
						} catch (FileNotFoundException e2) {
							System.out.println("Error2");

						}

					}
					
				}else {
					imgEjercicios[i].setImage(new Image(getClass().getResourceAsStream("/images/imgEjercicios/Ejer1.png"), 100, 100, true, true));
				}

				
				
				btnEjercicios[i].setLayoutX(initialLayoutX);
				btnEjercicios[i].setLayoutY(initialLayoutY);
				btnEjercicios[i].setPrefHeight(180);
				btnEjercicios[i].setPrefWidth(180);
				btnEjercicios[i].getStyleClass().addAll("profileBox","resourcesBox");
				
							
				btnEjercicios[i].setEffect(ef);
				btnEjercicios[i].setSpacing(10);
				btnEjercicios[i].setAlignment(Pos.CENTER);
				
				btnEjercicios[i].getChildren().add(imgEjercicios[i]);

				ejerciciosT1.getChildren().add(btnEjercicios[i]);
				
				if(initialLayoutX<=800) {
					initialLayoutX+=200;
				}else {
					initialLayoutY=300;
					initialLayoutX=90;

				}
				text.setText(text.getText()+" "+(i+1));
				btnEjercicios[i].getChildren().add(text);
				

			}
			ejerciciosT1.toFront();
			
			for(idEjer=0;idEjer<btnEjercicios.length;idEjer++) {
				btnEjercicios[idEjer].setOnMouseClicked(er->{
					abrirEditor(idEjer);
				});
			}
			
		});
		
		
		
		
		
		
		
		
		btnAtrasEditor.setOnMouseClicked(e->{
			//editorEjercicios.getChildren().clear();
			editorEjercicios.toBack();
		});
		
		btnAtras.setOnMouseClicked(e->{
			ejerciciosT1.getChildren().clear();
			ejerciciosT1.toBack();
		});

	}
	
	public void abrirEditor(int i) {
		
		
		if(!editorEjercicios.getChildren().contains(btnAtrasEditor)) {
			editorEjercicios.getChildren().add(btnAtrasEditor);			
		}
		
		
		
		txtDescripcion.setWrapText(true);
		
		  TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
	      fuerzas.setMinWidth(160);
	      TableColumn angulo = new TableColumn("Angulo");
	      angulo.setMinWidth(160);
	  
	      tablaDatos.getColumns().addAll(fuerzas,angulo);
	      tablaDatos.resizeColumn(fuerzas, 0);
	      data=FXCollections.observableArrayList();
	        
	      fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("fuerzas"));
	      angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("angulo"));
	       
	      tablaDatos.setItems(data); 
	      
	      tablaDatos.resizeColumn(fuerzas, 28);
		  tablaDatos.resizeColumn(angulo, 28);
		  
		  
		  //Tabla para equilibrio
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
			numVect.setOnKeyPressed(e->{
				System.out.println("KeyReleased()"+numVect.getText());

				//if(e.getCode().equals("DIGIT1")) {
					
				
				if(!verificarExp(e)) return;

				if((numVect.getText().equals(""))){
					numFilasAnt=0;
					System.out.println("KeyReleased(Textto vacio)");

				}else {
					try {
				numFilasAnt= Integer.parseInt(numVect.getText());
				System.out.println("KeyReleased(TRY)");

					}catch(Exception ex) {
						numFilasAnt=tablaDatos.getItems().size();
						System.out.println("KeyReleased(Catch)");

						
					}
				}
				
			});
			}catch(NumberFormatException e) {
				numFilasAnt= tablaDatos.getItems().size();
				System.out.println("KeyReleased(Catch 2)");

			}
			
			
			numVect.setOnKeyReleased(e->{
				System.out.println("KeyReleased(Inicio)");
				//numFilasAnt= tablaDatos.getItems().size()-1;
				if(!verificarExp(e)) return;		
				System.out.println("KeyReleased(Medio)");

						

				if((numVect.getText().equals(""))){
					vects=0;
				}else {
					try {
					vects = Integer.parseInt(numVect.getText());
					System.out.println("KeyReleased(Exito)");

					}catch(Exception ex) {
						vects=tablaDatos.getItems().size();
						System.out.println("KeyReleased(Error)");

					}
					if(vects>=10 || numFilasAnt>=10) return;
				}

				//System.out.println(numVect.getText());
				//int lastNumber= numVect.getText().charAt(numVect.getText().length());
				if((numFilasAnt-vects)>=0) {
					for(int in=0;in<(numFilasAnt-vects);in++) {
						eliminarVector();				
					}
					System.out.println("KeyReleased(Eliminando): "+ (numFilasAnt)+" "+vects);

				}else {				
					for(int j=0;j<vects;j++) {
						agregarVector();				
					}
					System.out.println("KeyReleased(Agregando)");

					
				}			
				System.out.println(e.getCode());
				//if(e.getCode().equals("BACK_SPACE")) {		
			});
		
		
		
		
		
		
		
		
		
		editorEjercicios.toFront();
		numVect.requestFocus();
		try {
			Robot r = new Robot();
			r.keyPress(com.sun.glass.events.KeyEvent.VK_3);
			r.keyRelease(com.sun.glass.events.KeyEvent.VK_3);

			
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
	}
    public boolean verificarExp(KeyEvent e) {
   	 
		String n= e.getCode()+"";			
		Pattern expression = Pattern.compile("^*DIGIT"+"[0-9]");
		Matcher match = expression.matcher(n);
		return (match.find() || n.equals("BACK_SPACE"));

		
 }
    
	 private void agregarVector(){
         contadorFilas++;
         data.add(new ContenidoTabla("",""));
   }
     private void eliminarVector(){
         data.remove(--contadorFilas);          
         tablaDatos.getItems().removeAll(tablaDatos.getSelectionModel().getSelectedItems());
   }

}
