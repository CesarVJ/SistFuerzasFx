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
   private TextField fuerzas,angulo; 
	public Label getCarrera() {
	return carrera;
}
	
	

public void setCarrera(Label carrera) {
	this.carrera = carrera;
}

public Label getVisitas() {
	return visitas;
}



public void setVisitas(Label visitas) {
	this.visitas = visitas;
}

	private Label nombres,nombre,noControl, carrera, visitas,numEjercicio,intentos,errores,numEjercicio2,intentos2,errores2;
    public Label getNumEjercicio2() {
		return numEjercicio2;
	}



	public void setNumEjercicio2(Label numEjercicio2) {
		this.numEjercicio2 = numEjercicio2;
	}



	public Label getIntentos2() {
		return intentos2;
	}



	public void setIntentos2(Label intentos2) {
		this.intentos2 = intentos2;
	}



	public Label getErrores2() {
		return errores2;
	}



	public void setErrores2(Label errores2) {
		this.errores2 = errores2;
	}



	public Label getNumEjercicio() {
		return numEjercicio;
	}



	public void setNumEjercicio(Label numEjercicio) {
		this.numEjercicio = numEjercicio;
	}



	public Label getIntentos() {
		return intentos;
	}



	public void setIntentos(Label intentos) {
		this.intentos = intentos;
	}



	public Label getErrores() {
		return errores;
	}



	public void setErrores(Label errores) {
		this.errores = errores;
	}

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
        
        
        this.numEjercicio=new Label(nombres);
        this.intentos=new Label(fuerzas);
        this.errores=new Label(angulo);
        
        
        this.numEjercicio2=new Label(nombres);
        this.intentos2=new Label(fuerzas);
        this.errores2=new Label(angulo);

        
        

}
    
    public ContenidoTabla(String noControl,String nombre,String carrera,String visitas){
  
        this.nombre=new Label(nombre);
        this.noControl=new Label(noControl);
        this.carrera=new Label(carrera);
        this.visitas=new Label(visitas);

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
	
	
	  public Label getNombre() {
			return nombre;
		}

		public void setNombre(Label nombre) {
			this.nombre = nombre;
		}
		
		  public Label getNoControl() {
				return noControl;
			}

			public void setNoControl(Label noControl) {
				this.noControl = noControl;
			}
    
}
