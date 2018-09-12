/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

/**
 *
 * @author Cesar
 */
public class Calculador extends Resultados implements Initializable{
    @FXML
    private Label titulo,lblResultados;
    @FXML
    private VBox cajaPrincipal;
    @FXML
    private HBox cajaTabla;
    @FXML 
    private TableView tablaDatos;
    @FXML
    private AnchorPane calc;
    
    @FXML
    ObservableList<ContenidoTabla> data;
   // @FXML
    //private ChoiceBox<String> numVectores = new ChoiceBox<>();
    @FXML
    private Button btnAgregar,btnEliminar;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	calc.getStyleClass().add("backgrounds");
        calc.getStylesheets().add(Calculador.class.getResource("/view/Estilos.css").toExternalForm());        

        lblResultados.getStylesheets().add(Calculador.class.getResource("/view/Estilos.css").toExternalForm());        
        cajaTabla.setSpacing(20);
       // for(int i=1;i<6;i++){
         //   numVectores.getItems().add(i+"");
        //}
        cajaPrincipal.setSpacing(10);
        cajaPrincipal.setPadding(new Insets(10,10,10,10));
        titulo.setText("En esta sección podras ingresar los datos de tus propios sistemas, proporcionando los datos\n"
           + "como el angulo de los vectores y sus fuerzas; obteninedo el vector resultante y su angulo en grados.");
        
        TableColumn fuerzas = new TableColumn("Fuerzas(Nw)");
        fuerzas.setMinWidth(150);
        TableColumn angulo = new TableColumn("Angulo");
        angulo.setMinWidth(150);
        ;
        tablaDatos.getColumns().addAll(fuerzas,angulo);
        tablaDatos.resizeColumn(fuerzas, 0);
        
    
    
    
    
                    data=FXCollections.observableArrayList();

                    
        //AGREGABA FILAS CON EL CHOISE BOX
    /* numVectores.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener <Number>(){
                public void changed(ObservableValue ov,Number value,Number new_value){
        //(Indice,newtons,angulo,Fx,Fy,VResultante,AN)
//        for(int i =0;i<new_value.intValue();i++){
                    data.add(new ContenidoTabla("",""));

  //      }
                tablaDatos.getSelectionModel().selectAll();
                tablaDatos.getItems().removeAll(tablaDatos.getSelectionModel().getSelectedItems());


                }
            });*/

    
    fuerzas.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("fuerzas"));
    angulo.setCellValueFactory(new PropertyValueFactory<ContenidoTabla,TextField>("angulo"));
   
            tablaDatos.setItems(data);        
    }
    int contadorFilas=0;
 @FXML
      private void agregarVector(){
          contadorFilas++;
          data.add(new ContenidoTabla("",""));

    }
      @FXML
      private void eliminarVector(){
          data.remove(--contadorFilas);          
          tablaDatos.getItems().removeAll(tablaDatos.getSelectionModel().getSelectedItems());

    }
      
      @FXML
      private void ObternerResultados(){
          ObservableList<ContenidoTabla>  dataRows = FXCollections.observableArrayList();
       Resultados obj = new Resultados();
       int i=0;
       float[] angulos = new float[contadorFilas];
       float[] fuerzas = new float[contadorFilas];
       for(ContenidoTabla bean :data){  
                if(!bean.getFuerzas().getText().isEmpty()){                  
                    dataRows.add(bean);
                    angulos[i]=Float.parseFloat(bean.getAngulo().getText());
                    fuerzas[i]=Float.parseFloat(bean.getFuerzas().getText());
                }
                //System.out.println(fuerzas[i]+","+angulos[i]);
                i++;

            }
       boolean angulosSonCorrectos=verificarAngulos(angulos);
       if(angulosSonCorrectos==false){
            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("¡Angulo inexistente!");
            mensaje.setContentText("Alguno de los angulos ingresados es mayor a 360 grados, por favor revisa y continua.");
            ImageView error = new ImageView(new Image(getClass().getResourceAsStream("/images/respuesta_incorrecta.png"),50,50,true,true));
            mensaje.setGraphic(error);
            mensaje.setHeaderText(null);
            mensaje.show();

       }else{
       obj.setSumFx(fuerzas, angulos, contadorFilas);
       obj.setSumFy(fuerzas, angulos, contadorFilas);
       obj.setVR();
       obj.setAnguloResult();
                   lblResultados.getStyleClass().add("lblResultados");
       lblResultados.setText("La sumatoria de Fx = "+String.format("%.3f",obj.getSumFx())+"\n"+"La sumatoria de Fy = "+String.format("%.3f",obj.getSumFy())
               +"\n"+"Vector resultante : "+String.format("%.3f",obj.getVR())+"\nAngulo resultante: "+String.format("%.3f",obj.getAnguloResult()));
        
      }
      }
      
      public boolean verificarAngulos(float angulos[]){
          for(int i=0;i<angulos.length;i++){
              if(angulos[i]>360){
                  return false;
              }
          }
          return true;
      }
    
}
