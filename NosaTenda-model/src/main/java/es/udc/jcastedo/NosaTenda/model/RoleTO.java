package es.udc.jcastedo.NosaTenda.model;

public class RoleTO {

	private String role_nombre;
	
	//since we override equals, we override hashCode too to maintain the
	//general contract between them, that means that if two objects are equals,
	//the hashCode they generate are equal too
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((role_nombre == null) ? 0 : role_nombre.hashCode());
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
		RoleTO other = (RoleTO) obj;
		if (role_nombre == null) {
			if (other.role_nombre != null)
				return false;
		} else if (!role_nombre.equals(other.role_nombre))
			return false;
		return true;
	}

	public String getRole_nombre() {
		return role_nombre;
	}

	public void setRole_nombre(String role_nombre) {
		this.role_nombre = role_nombre;
	}
	
	
}
