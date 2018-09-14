package application;

import java.io.File;
import java.io.IOException;
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

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		  TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
	      fuerzas.setMinWidth(150);
	      TableColumn angulo = new TableColumn("Angulo");
	      angulo.setMinWidth(150);
	      
	      tablaDatos.getColumns().addAll(fuerzas,angulo);
	      tablaDatos.resizeColumn(fuerzas, 0);
	      data=FXCollections.observableArrayList();
	        
	      fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("fuerzas"));
	      angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("angulo"));
	       
	      tablaDatos.setItems(data);      
		
		
		
		
		
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
                    int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length;            		
            		try {
						Path temp = Files.move(Paths.get(texto.getText()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
                    success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
		
		
		
		texto1.getStyleClass().add("textBoard");
		texto2.getStyleClass().add("textBoard");
		indicacionLbl.getStyleClass().add("textForm");

		board.getOnDragDropped();
		
		board.getStyleClass().add("board");
		formData.getStyleClass().add("board");
		
		
		try {
		numVect.setOnKeyPressed(e->{
			//if(e.getCode().equals("DIGIT1")) {
				
			
			if(!verificarExp(e)) return;

			if((numVect.getText().equals(""))){
				numFilasAnt=0;
			}else {
			numFilasAnt= Integer.parseInt(numVect.getText());
			}
			
		});
		}catch(NumberFormatException e) {
			numFilasAnt= tablaDatos.getItems().size();
		}
		
		
		numVect.setOnKeyReleased(e->{
			
			//numFilasAnt= tablaDatos.getItems().size()-1;
			if(!verificarExp(e)) return;			
			int vects;			

			if((numVect.getText().equals(""))){
				vects=0;
			}else {
				vects = Integer.parseInt(numVect.getText());
				if(vects>=10 || numFilasAnt>=10) return;
			}

			//System.out.println(numVect.getText());
			//int lastNumber= numVect.getText().charAt(numVect.getText().length());
			if((numFilasAnt-vects)>0) {
				for(int i=0;i<(numFilasAnt-vects);i++) {
					eliminarVector();				
				}
			}else {				
				for(int i=0;i<vects;i++) {
					agregarVector();				
				}
				
			}			
			System.out.println(e.getCode());
			//if(e.getCode().equals("BACK_SPACE")) {
				
			//}
		
			
		});

		
		//cajaPrincipal.setPrefSize(580, 1100);
		
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
		
		
		//file.renameTo(new File("\\images\\imgEjercicios\\Ejer3.png"));  "/images/imgEjercicios/Ejer3.png"
		//"../Ejer3.png" -----Apunta al archivo del proyecto
		//  "src/images/imgEjercicios/Ejer3.png" 
		
		//int maxFiles= new File("..\\SistFuerzasFiles\\imgEjercicios").listFiles().length;
		
		int maxFiles= new File(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios").listFiles().length;
		
		Path temp = Files.move(Paths.get(file.getPath()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+2)+".png"),StandardCopyOption.REPLACE_EXISTING);
		System.out.println(temp.getParent());
		//JOptionPane.showMessageDialog(null,"El archivo se ha guardado en "+new File("..\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+1)+".png").getAbsolutePath());
		
		//if(temp!=null) {
			//JOptionPane.showMessageDialog(null,"Se a registrado con exito");				
		//}else {
			//System.out.println("Algo salio mal");
		//}
		
		
		System.out.println(file.getAbsolutePath());
	}
	
	
	

}
