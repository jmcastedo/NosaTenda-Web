package es.udc.jcastedo.NosaTenda.model.compra;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;

@Entity
public class Compra {

	public enum RecogidaState {RECOGIDA, NO_RECOGIDA};
	
	private Long id;
	private Long unidades;
	private RecogidaState estadoRecogida;
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
	private Producto producto;
	private Cliente cliente;
	
	public Compra() {}
	
	public Compra(
			Long unidades, RecogidaState estadoRecogida, Calendar fecha,
			Double precio, Double total, String idPaypal,
			String estadoPaypal, String formaPago, String currency,
			Producto producto, Cliente cliente) {
		
		this.unidades = unidades;
		this.estadoRecogida = estadoRecogida;
		this.fecha = fecha;
		this.precio = precio;
		this.total = total;
		this.idPaypal = idPaypal;
		this.setEstadoPaypal(estadoPaypal);
		this.formaPago = formaPago;
		this.currency = currency;
		//this.tax_amount = new Double(0);
		this.producto = producto;
		this.cliente = cliente;
	}
	// TODO
//	public Compra(
//			Long unidades, RecogidaState estadoRecogida, Calendar fecha,
//			Double subtotal, Double total, String idPaypal,
//			String estadoPaypal, String formaPago, String currency,
//			Double tax, Producto producto, Cliente cliente) {
//		
//		this.unidades = unidades;
//		this.setEstadoRecogida(estadoRecogida);
//		this.fecha = fecha;
//		this.precio_noiva = subtotal;
//		this.total = total;
//		this.idPaypal = idPaypal;
//		this.setEstadoPaypal(estadoPaypal);
//		this.formaPago = formaPago;
//		this.currency = currency;
//		this.tax_amount = tax;
//		this.producto = producto;
//		this.cliente = cliente;
//	}

	@Id
	@Column(name="compra_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="CompraIdGenerator",
			sequenceName="compra_compra_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="CompraIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "compra_unidades")
	public Long getUnidades() {
		return unidades;
	}

	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "compra_estado_recogida")
	public RecogidaState getEstadoRecogida() {
		return estadoRecogida;
	}

	public void setEstadoRecogida(RecogidaState estadoRecogida) {
		this.estadoRecogida = estadoRecogida;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "compra_fecha")
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Column(name = "compra_precio_noiva")
	public Double getPrecio_noiva() {
		return precio_noiva;
	}

	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}

	@Column(name = "compra_precio")
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Column(name = "compra_total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "compra_id_paypal")
	public String getIdPaypal() {
		return idPaypal;
	}

	public void setIdPaypal(String idPaypal) {
		this.idPaypal = idPaypal;
	}

	@Column(name = "compra_estado_paypal")
	public String getEstadoPaypal() {
		return estadoPaypal;
	}

	public void setEstadoPaypal(String estadoPaypal) {
		this.estadoPaypal = estadoPaypal;
	}

	@Column(name = "compra_forma_pago")
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Column(name = "compra_currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "compra_tax_amount")
	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}

	@Column(name = "compra_tax_percentage")
	public Double getTax_percentage() {
		return tax_percentage;
	}

	public void setTax_percentage(Double tax_percentage) {
		this.tax_percentage = tax_percentage;
	}

	@Column(name="compra_num_factura", insertable = false, updatable = false)
	@SequenceGenerator(
			name="FacturaIdGenerator",
			sequenceName="compra_compra_num_factura_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="FacturaIdGenerator")
	public Long getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(Long num_factura) {
		this.num_factura = num_factura;
	}

	// Cascade NONE
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="producto_id")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	// Cascade NONE
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
