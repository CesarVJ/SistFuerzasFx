/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Cesar
 */
public class ContenidoTabla {
     TextField fuerzas,angulo;
 
	Label nombres;
    private ChoiceBox<String> funcion = new ChoiceBox<>();
    private ChoiceBox<String> funcion2 = new ChoiceBox<>();
    

    private TextField Fx,Fy;
    
    public ContenidoTabla(String fuerzas,String angulo,String funcion,String Fx,String Fy){
        this.fuerzas=new TextField(fuerzas);        
        this.funcion.getItems().add("Seno");
        this.funcion.getItems().add("Coseno");
        this.funcion2.getItems().add("Seno");
        this.funcion2.getItems().add("Coseno");
        this.Fx =  new TextField(Fx);
        this.Fy= new TextField(Fy);
        this.angulo=new TextField(angulo);
    }
    
    public ContenidoTabla(String fuerzas,String angulo){
                this.fuerzas=new TextField(fuerzas);  
                this.angulo=new TextField(angulo);

    }
    public ContenidoTabla(String nombres,String fuerzas,String angulo){
        this.fuerzas=new TextField(fuerzas);  
        this.angulo=new TextField(angulo);
        this.nombres=new Label(nombres);

}
    
    public void setFuerzas(TextField fuerzas){
        this.fuerzas= fuerzas;
    }
    public TextField getFuerzas(){
        return fuerzas;
    }
    public void setAngulo(TextField angulo){
        this.angulo= angulo;
    }
    public TextField getAngulo(){
        return angulo;
    }
    
    public void setFuncion(ChoiceBox funcion){
        this.funcion= funcion;
        //quantityOperational=Integer.parseInt(quantity);   
    }
    public ChoiceBox getFuncion(){
        return this.funcion;
    }
    
     public void setFuncion2(ChoiceBox funcion2){
        this.funcion2= funcion2;
        //quantityOperational=Integer.parseInt(quantity);   
    }
    public ChoiceBox getFuncion2(){
        return this.funcion2;
    }
    
    public void setFx(TextField Fx){
        this.Fx=Fx;
    }
    public TextField getFx(){
        return this.Fx;
    }
 
    public void setFy(TextField Fy){
        this.Fy=Fy;
    }
    public TextField getFy(){
        return this.Fy;
    }
    
    public Label getNombres() {
		return nombres;
	}

	public void setNombres(Label nombres) {
		this.nombres = nombres;
	}
    
}
