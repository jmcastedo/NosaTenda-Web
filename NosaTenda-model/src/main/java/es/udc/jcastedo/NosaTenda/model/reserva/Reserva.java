package es.udc.jcastedo.NosaTenda.model.reserva;

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
public class Reserva {

	public enum ReservaState {PENDIENTE, ENTREGADA, CANCELADA};
	
	private Long id;
	private Long unidades;
	private ReservaState estado;
	private Calendar fecha;
	private Calendar fecha_limite;
	private Double precio_noiva;
	private Double precio;
	private Double total;
	private Double tax_amount;
	private Double tax_percentage;
	private Producto producto;
	private Cliente cliente;
	
	public Reserva () {}
	
	public Reserva (
			Long unidades, ReservaState estado, Double precio,
			Producto producto, Cliente cliente) {
		
		this.unidades = unidades;
		this.estado = estado;
		this.fecha = Calendar.getInstance();
		//fecha.getTime();
		this.precio = precio;
		this.total = unidades * precio;
		//this.tax_amount = new Double(0);
		this.producto = producto;
		this.cliente = cliente;
	}
	
//	public Reserva (
//			Long unidades, ReservaState estado, Double subtotal,
//			Double tax, Producto producto, Cliente cliente) {
//		
//		this.unidades = unidades;
//		this.setEstado(estado);
//		this.fecha = Calendar.getInstance();
//		fecha.getTime();
//		this.precio = subtotal;
//		this.total = unidades * subtotal;
//		this.tax_amount = tax;
//		this.producto = producto;
//		this.cliente = cliente;
//	}

	@Id
	@Column(name = "reserva_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="ReservaIdGenerator",
			sequenceName="reserva_reserva_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="ReservaIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "reserva_unidades")
	public Long getUnidades() {
		return unidades;
	}

	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "reserva_estado")
	public ReservaState getEstado() {
		return estado;
	}

	public void setEstado(ReservaState estado) {
		this.estado = estado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reserva_fecha")
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reserva_fecha_limite")
	public Calendar getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(Calendar fecha_limite) {
		this.fecha_limite = fecha_limite;
	}
	
	@Column(name = "reserva_precio_noiva")
	public Double getPrecio_noiva() {
		return precio_noiva;
	}

	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}

	@Column(name = "reserva_precio")
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Column(name = "reserva_total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "reserva_tax_amount")
	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}
	
	@Column(name = "reserva_tax_percentage")
	public Double getTax_percentage() {
		return tax_percentage;
	}

	public void setTax_percentage(Double tax_percentage) {
		this.tax_percentage = tax_percentage;
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
