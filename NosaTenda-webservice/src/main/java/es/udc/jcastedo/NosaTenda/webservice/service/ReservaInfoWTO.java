package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.Calendar;

public class ReservaInfoWTO {

	public enum ReservaStateWTO {PENDIENTE, ENTREGADA, CANCELADA};
	
	private Long id;
	private Long unidades;
	private ReservaStateWTO estado;
	private Calendar fecha;
	private Calendar fecha_limite;
	private Double precio_noiva;
	private Double precio;
	private Double total;
	private Double tax_amount;
	private Double tax_percentage;
	private ProductoWTO producto;
	
	
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
	public ReservaStateWTO getEstado() {
		return estado;
	}
	public void setEstado(ReservaStateWTO estado) {
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
	public ProductoWTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoWTO productoWTO) {
		this.producto = productoWTO;
	}
	
}
