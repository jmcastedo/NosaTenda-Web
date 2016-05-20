package es.udc.jcastedo.NosaTenda.webservice.service;

/**
 * Esta clase encapsula los parámetros de la petición POST para crear clientes,
 * y es usada por jackson para parsearla
 * @author jmcastedo
 *
 */
public class ClienteWTO {

	private String nombre;
	private String correo;
	private String password;
	
	public ClienteWTO() {}
	
	public ClienteWTO(String nombre, String correo, String password) {
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
}
