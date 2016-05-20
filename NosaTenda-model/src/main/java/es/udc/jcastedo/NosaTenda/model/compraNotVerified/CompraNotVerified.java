package es.udc.jcastedo.NosaTenda.model.compraNotVerified;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class CompraNotVerified {

	private Long id;
	private Long unidades;
	private Calendar fecha;
	private Double subtotal;
	private Double total;
	private String idPaypal;
	private String formaPago;
	private String currency;
	private Double tax;
	private Producto producto;
	private Cliente cliente;
	
	public CompraNotVerified() {}
	
	public CompraNotVerified(
			Long unidades, Calendar fecha, Double subtotal,
			Double total, String idPaypal, String formaPago,
			String currency, Producto producto, Cliente cliente) {
		
		this.unidades = unidades;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total = total;
		this.idPaypal = idPaypal;
		this.formaPago = formaPago;
		this.currency = currency;
		this.tax = new Double(0);
		this.producto = producto;
		this.cliente = cliente;
	}
	
	public CompraNotVerified(
			Long unidades, Calendar fecha, Double subtotal,
			Double total, String idPaypal, String formaPago,
			String currency, Double tax,
			Producto producto, Cliente cliente) {
		
		this.unidades = unidades;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total = total;
		this.idPaypal = idPaypal;
		this.formaPago = formaPago;
		this.currency = currency;
		this.tax = tax;
		this.producto = producto;
		this.cliente = cliente;
	}

	@Id
	@Column(name="compra_not_verified_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="CompraNotVerifiedIdGenerator",
			sequenceName="compra_not_verified_compra_not_verified_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="CompraNotVerifiedIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "compra_not_verified_unidades")
	public Long getUnidades() {
		return unidades;
	}

	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "compra_not_verified_fecha")
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Column(name = "compra_not_verified_subtotal")
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "compra_not_verified_total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "compra_not_verified_id_paypal")
	public String getIdPaypal() {
		return idPaypal;
	}

	public void setIdPaypal(String idPaypal) {
		this.idPaypal = idPaypal;
	}

	@Column(name = "compra_not_verified_forma_pago")
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Column(name = "compra_not_verified_currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "compra_not_verified_tax")
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
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
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cliente_id")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
