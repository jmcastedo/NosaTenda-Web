package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.List;

public class EmpleadoWTO {

	private Long id;
	private String correo;
	private String password;
	private String role;
	private Boolean activado;
	private String nif;
	private List<TiendaWTO> tiendas;
	
	public EmpleadoWTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActivado() {
		return activado;
	}

	public void setActivado(Boolean activado) {
		this.activado = activado;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public List<TiendaWTO> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<TiendaWTO> tiendas) {
		this.tiendas = tiendas;
	}
	
	
}
