package es.udc.jcastedo.NosaTenda.webservice.service;

public class ClienteInfoWTO {

	private Long id;
	private String nombre;
	private String apellidos;
	private String correo;
	private String password;
	private String direccion;
	private String localidad;
	private String provincia;
	private String cp;
	private String nif;
	private String phone1;
	private String phone2;
	
	public ClienteInfoWTO() {}

	public ClienteInfoWTO(String nombre, String apellidos, String correo,
			String password, String direccion, String localidad,
			String provincia, String cp, String nif, String phone1,
			String phone2) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.cp = cp;
		this.nif = nif;
		this.phone1 = phone1;
		this.phone2 = phone2;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	
}
