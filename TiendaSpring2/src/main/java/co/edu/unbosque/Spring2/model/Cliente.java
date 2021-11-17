package co.edu.unbosque.Spring2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente {

	@Id
	private Long cedula;
	
	private String nombre_completo;
	
	private String direccion;
	
	private String telefono;
	
	private String correo;
	
	public Cliente() {
		
	}
	
	public Cliente(Long cedula, String nombre_completo, String direccion, String telefono, String correo) {
		
		super();
		this.cedula = cedula;
		this.nombre_completo = nombre_completo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombre_completo=" + nombre_completo + ", direccion=" + direccion + ", telefono=" + telefono 
				+ ", correo=" + correo + "]";
	}
	
}
