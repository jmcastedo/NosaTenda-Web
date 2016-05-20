package es.udc.jcastedo.NosaTenda.webservice.service;

public class LocalidadWTO {

	private Long id;
	private String nombre;
	
	public LocalidadWTO() {}
	
	public LocalidadWTO(String nombre) {
		
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
