package es.udc.jcastedo.NosaTenda.model.metodoEnvio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

@Entity
public class MetodoEnvio {

	private Long id;
	private Double coste;
	private Long plazo;
	private String descripcion;
	private Tienda tienda;
	
	public MetodoEnvio() {}

	@Id
	@Column(name="metodoenvio_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="MetodoEnvioIdGenderator",
			sequenceName="metodoenvio_metodoenvio_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="MetodoEnvioIdGenderator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name= "metodoenvio_coste")
	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	@Column(name= "metodoenvio_plazo")
	public Long getPlazo() {
		return plazo;
	}

	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}

	@Column(name= "metodoenvio_descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="tienda_id")
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	
}
