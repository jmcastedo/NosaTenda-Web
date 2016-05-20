package es.udc.jcastedo.NosaTenda.model;

public class EmpleadoTO {

	public enum Roles_EmpleadoTO {ROLE_USUARIO, ROLE_ADMIN, ROLE_ADMIN_TIENDA}
	
	private Long id;
	private String correo;
	private String password;
	private Roles_EmpleadoTO role;
	private Boolean activado;
	private String nif;
	
	//since we override equals, we override hashCode too to maintain the
	//general contract between them, that means that if two objects are equals,
	//the hashCode they generate are equal too
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activado == null) ? 0 : activado.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	
	//we override equals so it will not only evaluate the objects references when comparing them,
	//but also the content of their properties.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoTO other = (EmpleadoTO) obj;
		if (activado == null) {
			if (other.activado != null)
				return false;
		} else if (!activado.equals(other.activado))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
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
	public Roles_EmpleadoTO getRole() {
		return role;
	}
	public void setRole(Roles_EmpleadoTO role) {
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
}
