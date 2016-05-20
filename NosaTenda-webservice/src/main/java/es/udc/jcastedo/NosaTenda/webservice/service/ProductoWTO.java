package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.Calendar;

public class ProductoWTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private String imagen;
	private Double precio_noiva;
	private Double precio;
	private Long stock;
	private Double tax_amount;
	private Double tax_percentage;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private TiendaWTO tienda;
	
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
	public Double getPrecio_noiva() {
		return precio_noiva;
	}
	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
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
	public TiendaWTO getTienda() {
		return tienda;
	}
	public void setTienda(TiendaWTO tienda) {
		this.tienda = tienda;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((fecha_puesta_venta == null) ? 0 : fecha_puesta_venta
						.hashCode());
		result = prime * result
				+ ((fecha_retirada == null) ? 0 : fecha_retirada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result
				+ ((precio_noiva == null) ? 0 : precio_noiva.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result
				+ ((tax_amount == null) ? 0 : tax_amount.hashCode());
		result = prime * result
				+ ((tax_percentage == null) ? 0 : tax_percentage.hashCode());
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
		ProductoWTO other = (ProductoWTO) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
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
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
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
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
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
		return true;
	}
	
	
}
