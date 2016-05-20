package es.udc.jcastedo.NosaTenda.webservice.service;

public class EntryTO {

	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private String imagen = null;
	private Double precio = null;
	private String nombreTienda = null;
	private String direccionTienda = null;
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getNombreTienda() {
		return nombreTienda;
	}
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	public String getDireccionTienda() {
		return direccionTienda;
	}
	public void setDireccionTienda(String direccionTienda) {
		this.direccionTienda = direccionTienda;
	}
	
}
