package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.Calendar;

public class CompraWTO {

	public enum RecogidaStateWTO {RECOGIDA, NO_RECOGIDA};
	
	private Long id;
	private Long unidades;
	private RecogidaStateWTO estadoRecogida;
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
	private ProductoWTO producto;
	private ClienteInfoWTO cliente;
	
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
	public RecogidaStateWTO getEstadoRecogida() {
		return estadoRecogida;
	}
	public void setEstadoRecogida(RecogidaStateWTO estadoRecogida) {
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
	public ProductoWTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoWTO producto) {
		this.producto = producto;
	}
	public ClienteInfoWTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteInfoWTO cliente) {
		this.cliente = cliente;
	}
	
}
