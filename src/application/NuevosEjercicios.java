package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.*;

public class NuevosEjercicios implements Initializable{
	
	@FXML
	AnchorPane add;
	
	@FXML
	Button btnAgregar,btnValidar;
	@FXML
	Label titulo,texto,texto1,texto2,indicacionLbl,tipoLbl,vectLbl;
	@FXML
	HBox boxName;
	@FXML
	VBox  board,formData;
	@FXML
	ImageView icoImg;
	@FXML
	private ChoiceBox<String> tipoEjercicio;
	@FXML
	private TextField numVect;
	@FXML 
	private TableView tablaDatos;
	@FXML
	ObservableList<ContenidoTabla> data;
	
	int contadorFilas=0;
	int numFilasAnt=0;
	int vects=0;	
	String ruta="",nameFile="";

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
			
		
		
		
		
		
		indicacionLbl.setWrapText(true);
		tipoEjercicio.getItems().addAll("Vectores por componentes","Diagramas de CL");
		tipoEjercicio.getSelectionModel().select(0);
		
		texto1.setText("Arrastre la imagen aqu�.");
		texto2.setText("O click para seleccionarlo...");
		texto1.setWrapText(true);
		btnAgregar.getStyleClass().add("filesButtons");
		btnValidar.getStyleClass().add("filesButtons");

		
		board.setSpacing(20);
		board.setCenterShape(true);
		board.setAlignment(Pos.CENTER);
		
		icoImg.setImage(new Image(getClass().getResourceAsStream("/images/up.png"), 100, 100, true, true));
		icoImg.getStyleClass().add("imageViewIco");
		board.setOnDragOver(new EventHandler<DragEvent>() { 
	            public void handle(DragEvent event) {
            if (event.getGestureSource() != board && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        }
    });
		
		
		board.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    texto.setText(db.getFiles().get(0).getAbsolutePath());
                    int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length;            		
            		try {
						//Path temp = Files.move(Paths.get(texto.getText()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);
						ruta= texto.getText();
						nameFile = db.getFiles().get(0).getName();
					} catch (Exception e) {
						e.printStackTrace();
					}
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });
		
		
		
		texto1.getStyleClass().add("textBoard");
		texto2.getStyleClass().add("textBoard");
		indicacionLbl.getStyleClass().add("textForm");

		board.getOnDragDropped();
		
		board.getStyleClass().add("board");
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
				for(int i=0;i<(numFilasAnt-vects);i++) {
					eliminarVector();				
				}
				System.out.println("KeyReleased(Eliminando): "+ (numFilasAnt)+" "+vects);

			}else {				
				for(int i=0;i<vects;i++) {
					agregarVector();				
				}
				System.out.println("KeyReleased(Agregando)");

				
			}			
			System.out.println(e.getCode());
			//if(e.getCode().equals("BACK_SPACE")) {		
		});

		
		
		add.getStyleClass().add("backgrounds");				
		add.getStylesheets().add(NuevosEjercicios.class.getResource("/view/Estilos.css").toExternalForm());
	}
	
	 private void agregarVector(){
         contadorFilas++;
         data.add(new ContenidoTabla("",""));
   }
     private void eliminarVector(){
         data.remove(--contadorFilas);          
         tablaDatos.getItems().removeAll(tablaDatos.getSelectionModel().getSelectedItems());
   }

     
     public boolean verificarExp(KeyEvent e) {
    	 
    		String n= e.getCode()+"";			
			Pattern expression = Pattern.compile("^*DIGIT"+"[0-9]");
			Matcher match = expression.matcher(n);
			return (match.find() || n.equals("BACK_SPACE"));

			
     }
	
	public void agregarGrafica() throws IOException {
		FileChooser select = new FileChooser();
		select.setTitle("Elegir imagen");
		Stage stage = (Stage)add.getScene().getWindow();
		
		File file = select.showOpenDialog(stage);
		file.setWritable(true);
		
		int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length;
		
		Path temp = Files.move(Paths.get(file.getPath()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);
		System.out.println(temp.getParent());
		//JOptionPane.showMessageDialog(null,"El archivo se ha guardado en "+new File("..\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+1)+".png").getAbsolutePath());
				
		System.out.println(file.getAbsolutePath());
	}
	
	public void validarEjercicio() throws FileNotFoundException {
		//Numero de vectores es vects
		//tipo de ejercicio 
		//Nombre de imagen
		//Fuerzas
		//Angulos
		int tipo =0;
		if(tipoEjercicio.getSelectionModel().getSelectedIndex()==1) {
			tipo =1;
		}
		
		ObservableList<ContenidoTabla> dataRows = FXCollections.observableArrayList();
		int i = 0;
		float[] angulos = new float[vects];
		float[] fuerzas = new float[vects];


		for (ContenidoTabla bean : data) {
			if (!bean.getFuerzas().getText().isEmpty()) {
				dataRows.add(bean);
				angulos[i] = Float.parseFloat(bean.getAngulo().getText());
				fuerzas[i] = Float.parseFloat(bean.getFuerzas().getText());

			}
			i++;

		}
		String strAngulos="",strFuerzas="";
		for(int j=0;j<vects;j++) {
			strAngulos=angulos[j]+"";
			strFuerzas=fuerzas[j]+"";
			System.out.println(strFuerzas+":"+strAngulos);

		}	
		
		RandomAccessFile file = new RandomAccessFile(System.getProperty("user.home") + "\\SistFuerzasFiles\\graficasInfo.dat", "rw");
        int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1").listFiles().length;            		

		int id = maxFiles+2;
		try {
		    file.seek(file.length());
		    file.writeInt(id);
			file.writeBytes(nameFile+"\n");
			file.writeInt(tipo);
			file.writeInt(vects);
			for(int n=0;n<vects;n++) {
				file.writeFloat(angulos[n]);
				file.writeFloat(fuerzas[n]);
			}
			
			
			
			file.close();
			Files.move(Paths.get(texto.getText()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios1\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

}
