package es.udc.jcastedo.NosaTenda.model;

import java.util.Calendar;

public class CompraTO {

	public enum RecogidaStateTO {RECOGIDA, NO_RECOGIDA};
	
	private Long id;
	private Long unidades;
	private RecogidaStateTO estadoRecogida;
	private Calendar fecha;
	private Double precio_noiva;
	private Double precio;
	private Double total;
	private String idPaypal;
	private String estadoPaypal;
	private String formaPago;
	private String currency;
	private Double tax_amount;
	private Double tax_percentage;
	private Long num_factura;
	private ProductoTO producto;
	private ClienteTO cliente;
	
	//since we override equals, we override hashCode too to maintain the
	//general contract between them, that means that if two objects are equals,
	//the hashCode they generate are equal too
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result
				+ ((estadoPaypal == null) ? 0 : estadoPaypal.hashCode());
		result = prime * result
				+ ((estadoRecogida == null) ? 0 : estadoRecogida.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((formaPago == null) ? 0 : formaPago.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idPaypal == null) ? 0 : idPaypal.hashCode());
		result = prime * result
				+ ((num_factura == null) ? 0 : num_factura.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result
				+ ((precio_noiva == null) ? 0 : precio_noiva.hashCode());
		result = prime * result
				+ ((producto == null) ? 0 : producto.hashCode());
		result = prime * result
				+ ((tax_amount == null) ? 0 : tax_amount.hashCode());
		result = prime * result
				+ ((tax_percentage == null) ? 0 : tax_percentage.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result
				+ ((unidades == null) ? 0 : unidades.hashCode());
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
		CompraTO other = (CompraTO) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (estadoPaypal == null) {
			if (other.estadoPaypal != null)
				return false;
		} else if (!estadoPaypal.equals(other.estadoPaypal))
			return false;
		if (estadoRecogida != other.estadoRecogida)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (formaPago == null) {
			if (other.formaPago != null)
				return false;
		} else if (!formaPago.equals(other.formaPago))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idPaypal == null) {
			if (other.idPaypal != null)
				return false;
		} else if (!idPaypal.equals(other.idPaypal))
			return false;
		if (num_factura == null) {
			if (other.num_factura != null)
				return false;
		} else if (!num_factura.equals(other.num_factura))
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
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
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
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (unidades == null) {
			if (other.unidades != null)
				return false;
		} else if (!unidades.equals(other.unidades))
			return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUnidades() {
		return unidades;
	}
	
	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}
	
	public RecogidaStateTO getEstadoRecogida() {
		return estadoRecogida;
	}
	
	public void setEstadoRecogida(RecogidaStateTO estadoRecogida) {
		this.estadoRecogida = estadoRecogida;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getIdPaypal() {
		return idPaypal;
	}
	
	public void setIdPaypal(String idPaypal) {
		this.idPaypal = idPaypal;
	}
	
	public String getEstadoPaypal() {
		return estadoPaypal;
	}
	
	public void setEstadoPaypal(String estadoPaypal) {
		this.estadoPaypal = estadoPaypal;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
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
	
	public Long getNum_factura() {
		return num_factura;
	}
	
	public void setNum_factura(Long num_factura) {
		this.num_factura = num_factura;
	}
	
	public ProductoTO getProducto() {
		return producto;
	}
	
	public void setProducto(ProductoTO producto) {
		this.producto = producto;
	}
	
	public ClienteTO getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}
	
}
