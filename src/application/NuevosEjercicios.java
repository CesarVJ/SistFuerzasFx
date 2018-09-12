package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
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
	Label titulo,texto,texto1,texto2;
	@FXML
	HBox boxName;
	@FXML
	VBox  board;
	@FXML
	ImageView icoImg;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		texto1.setText("Arrastre la imagen aquí.");
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
						Path temp = Files.move(Paths.get(texto.getText()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+1)+".png"),StandardCopyOption.REPLACE_EXISTING);
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

		board.getOnDragDropped();
		
		board.getStyleClass().add("board");
		
		//cajaPrincipal.setPrefSize(580, 1100);
		
		add.getStyleClass().add("backgrounds");				
		add.getStylesheets().add(NuevosEjercicios.class.getResource("/view/Estilos.css").toExternalForm());
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
		
		Path temp = Files.move(Paths.get(file.getPath()),Paths.get(System.getProperty("user.home")+"\\SistFuerzasFiles\\imgEjercicios\\Ejer"+(maxFiles+1)+".png"),StandardCopyOption.REPLACE_EXISTING);
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
