package es.udc.jcastedo.NosaTenda.model.localidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Localidad {

	private Long id;
	private String nombre;
	
	public Localidad() {}
	
	public Localidad(String nombre) {
		
		this.nombre = nombre;
	}

	@Id
	@Column(name = "localidad_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="LocalidadIdGenerator",
			sequenceName="localidad_localidad_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="LocalidadIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "localidad_nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
