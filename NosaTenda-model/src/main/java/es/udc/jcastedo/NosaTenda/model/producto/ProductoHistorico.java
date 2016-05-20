package es.udc.jcastedo.NosaTenda.model.producto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class ProductoHistorico implements Serializable {

	private Long id;
	private Long version;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private Double precio;
	private Double precio_noiva;
	private Long stock_inicial;
	private Double tax_amount;
	private Double tax_percentage;
	
	public ProductoHistorico() {}
	
	public ProductoHistorico(Long id, Long version, Calendar fecha_puesta_venta, Calendar fecha_retirada,
			Double precio, Long stock_inicial) {
		
		this.id = id;
		this.version = version;
		this.fecha_puesta_venta = fecha_puesta_venta;
		this.fecha_retirada = fecha_retirada;
		this.precio = precio;
		this.stock_inicial = stock_inicial;
	}

	@Id
	@Column(name="productohistorico_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@Column(name="productohistorico_version")
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="productohistorico_fecha_puesta_venta")
	public Calendar getFecha_puesta_venta() {
		return fecha_puesta_venta;
	}

	public void setFecha_puesta_venta(Calendar fecha_puesta_venta) {
		this.fecha_puesta_venta = fecha_puesta_venta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="productohistorico_fecha_retirada")
	public Calendar getFecha_retirada() {
		return fecha_retirada;
	}

	public void setFecha_retirada(Calendar fecha_retirada) {
		this.fecha_retirada = fecha_retirada;
	}

	@Column(name = "productohistorico_precio")
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Column(name = "productohistorico_precio_noiva")
	public Double getPrecio_noiva() {
		return precio_noiva;
	}

	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}

	@Column(name = "productohistorico_stock_inicial")
	public Long getStock_inicial() {
		return stock_inicial;
	}

	public void setStock_inicial(Long stock_inicial) {
		this.stock_inicial = stock_inicial;
	}

	@Column(name = "productohistorico_tax_amount")
	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}

	@Column(name = "productohistorico_tax_percentage")
	public Double getTax_percentage() {
		return tax_percentage;
	}

	public void setTax_percentage(Double tax_percentage) {
		this.tax_percentage = tax_percentage;
	}

	// necesitamos hashCode y equals para implementar la clave primaria compuesta
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fecha_puesta_venta == null) ? 0 : fecha_puesta_venta
						.hashCode());
		result = prime * result
				+ ((fecha_retirada == null) ? 0 : fecha_retirada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result
				+ ((precio_noiva == null) ? 0 : precio_noiva.hashCode());
		result = prime * result
				+ ((stock_inicial == null) ? 0 : stock_inicial.hashCode());
		result = prime * result
				+ ((tax_amount == null) ? 0 : tax_amount.hashCode());
		result = prime * result
				+ ((tax_percentage == null) ? 0 : tax_percentage.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoHistorico other = (ProductoHistorico) obj;
		if (fecha_puesta_venta == null) {
			if (other.fecha_puesta_venta != null)
				return false;
		} else if (!fecha_puesta_venta.equals(other.fecha_puesta_venta))
			return false;
		if (fecha_retirada == null) {
			if (other.fecha_retirada != null)
				return false;
		} else if (!fecha_retirada.equals(other.fecha_retirada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (precio_noiva == null) {
			if (other.precio_noiva != null)
				return false;
		} else if (!precio_noiva.equals(other.precio_noiva))
			return false;
		if (stock_inicial == null) {
			if (other.stock_inicial != null)
				return false;
		} else if (!stock_inicial.equals(other.stock_inicial))
			return false;
		if (tax_amount == null) {
			if (other.tax_amount != null)
				return false;
		} else if (!tax_amount.equals(other.tax_amount))
			return false;
		if (tax_percentage == null) {
			if (other.tax_percentage != null)
				return false;
		} else if (!tax_percentage.equals(other.tax_percentage))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	
	
}
