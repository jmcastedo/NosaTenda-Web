package es.udc.jcastedo.NosaTenda.model;

import java.util.Calendar;

public class ReservaTO {

	public enum ReservaStateTO {PENDIENTE, ENTREGADA, CANCELADA};
	
	private Long id;
	private Long unidades;
	private ReservaStateTO estado;
	private Calendar fecha;
	private Calendar fecha_limite;
	private Double precio_noiva;
	private Double precio;
	private Double total;
	private Double tax_amount;
	private Double tax_percentage;
	private ProductoTO productoTO;
	private ClienteTO clienteTO;
	
	
	//since we override equals, we override hashCode too to maintain the
	//general contract between them, that means that if two objects are equals,
	//the hashCode they generate are equal too
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clienteTO == null) ? 0 : clienteTO.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((fecha_limite == null) ? 0 : fecha_limite.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result
				+ ((precio_noiva == null) ? 0 : precio_noiva.hashCode());
		result = prime * result
				+ ((productoTO == null) ? 0 : productoTO.hashCode());
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
		ReservaTO other = (ReservaTO) obj;
		if (clienteTO == null) {
			if (other.clienteTO != null)
				return false;
		} else if (!clienteTO.equals(other.clienteTO))
			return false;
		if (estado != other.estado)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (fecha_limite == null) {
			if (other.fecha_limite != null)
				return false;
		} else if (!fecha_limite.equals(other.fecha_limite))
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
		if (productoTO == null) {
			if (other.productoTO != null)
				return false;
		} else if (!productoTO.equals(other.productoTO))
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
	
	public ReservaStateTO getEstado() {
		return estado;
	}
	
	public void setEstado(ReservaStateTO estado) {
		this.estado = estado;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public Calendar getFecha_limite() {
		return fecha_limite;
	}
	
	public void setFecha_limite(Calendar fecha_limite) {
		this.fecha_limite = fecha_limite;
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
	
	public ProductoTO getProductoTO() {
		return productoTO;
	}
	
	public void setProductoTO(ProductoTO productoTO) {
		this.productoTO = productoTO;
	}
	
	public ClienteTO getClienteTO() {
		return clienteTO;
	}
	
	public void setClienteTO(ClienteTO clienteTO) {
		this.clienteTO = clienteTO;
	}

	
}
