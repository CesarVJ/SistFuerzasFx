package model;

import java.sql.Date;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private Date nacimiento;
	private String correo;
	private String usuario;
	private String password;

	
	
	
	
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos=apellidos;
	}
	public String getApellidos() {
		return this.apellidos;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setUsuario(String usuario) {
		this.usuario=usuario;
	}
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setCorreo(String correo) {
		this.correo=correo;
	}
	public String getCorrero() {
		return this.correo;
	}
	

}
