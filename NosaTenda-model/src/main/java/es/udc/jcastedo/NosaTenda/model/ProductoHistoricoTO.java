package es.udc.jcastedo.NosaTenda.model;

import java.util.Calendar;

public class ProductoHistoricoTO {

	private Long id;
	private Long version;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private Double precio;
	private Double precio_noiva;
	private Long stock_inicial;
	private Double tax_amount;
	private Double tax_percentage;
	
	//since we override equals, we override hashCode too to maintain the
	//general contract between them, that means that if two objects are equals,
	//the hashCode they generate are equal too
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
		ProductoHistoricoTO other = (ProductoHistoricoTO) obj;
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
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Calendar getFecha_puesta_venta() {
		return fecha_puesta_venta;
	}
	
	public void setFecha_puesta_venta(Calendar fecha_puesta_venta) {
		this.fecha_puesta_venta = fecha_puesta_venta;
	}
	
	public Calendar getFecha_retirada() {
		return fecha_retirada;
	}
	
	public void setFecha_retirada(Calendar fecha_retirada) {
		this.fecha_retirada = fecha_retirada;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Double getPrecio_noiva() {
		return precio_noiva;
	}
	
	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}
	
	public Long getStock_inicial() {
		return stock_inicial;
	}
	
	public void setStock_inicial(Long stock_inicial) {
		this.stock_inicial = stock_inicial;
	}
	
	public Double getTax_amount() {
		return tax_amount;
	}
	
	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}
	
	public Double getTax_percentage() {
		return tax_percentage;
	}
	
	public void setTax_percentage(Double tax_percentage) {
		this.tax_percentage = tax_percentage;
	}
	
	
}
