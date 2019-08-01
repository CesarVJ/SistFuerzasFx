package application;

public class Recurso {
	
	private String nombre;
	private String categoria;
	private String enlace;
	private boolean habilitado;
	
	

	public Recurso() {
		this.nombre=null;
		this.categoria=null;
		this.enlace=null;
		this.habilitado=true;
	}
	public Recurso(String nombre,String categoria,String enlace,boolean habilitado) {
		this.nombre=nombre;
		this.categoria=categoria;
		this.enlace=enlace;
		this.habilitado=habilitado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
	

}
