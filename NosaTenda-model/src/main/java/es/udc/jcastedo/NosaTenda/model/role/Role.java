package es.udc.jcastedo.NosaTenda.model.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

	private String role_nombre;
	
	public Role() {}
	
	public Role(String role_nombre) {
		
		this.role_nombre = role_nombre;
	}

	@Id
	@Column(name= "role_nombre")
	public String getRole_nombre() {
		return role_nombre;
	}

	public void setRole_nombre(String role_nombre) {
		this.role_nombre = role_nombre;
	}
}
