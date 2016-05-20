package es.udc.jcastedo.NosaTenda.model.producto;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

@Entity
public class Producto {

	private Long id;
	private Double precio;
	private Double precio_noiva;
	private Long stock;
	private Double tax_amount;
	private Double tax_percentage;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private ProductoData productoData;
	private Tienda tienda;
	private Set<Categoria> categorias = new HashSet<Categoria>();
	private Set<ProductoHistorico> historico = new HashSet<ProductoHistorico>();
	
	public Producto () {}
	
	public Producto (Double precio, Long stock, Tienda tienda) {
		
		this.precio = precio;
		this.stock = stock;
		//this.tax = new Double(0);
		this.tienda = tienda;
	}
	
//	public Producto (String nombre, String descripcion, String imagen,
//			Double precio, Long stock, Double tax, Tienda tienda) {
//		
//		this.precio = precio;
//		this.stock = stock;
//		this.tax = tax;
//		this.tienda = tienda;
//	}

	@Id
	@Column(name = "producto_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="ProductoIdGenerator",
			sequenceName="producto_producto_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="ProductoIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "producto_precio")
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Column(name = "producto_precio_noiva")
	public Double getPrecio_noiva() {
		return precio_noiva;
	}

	public void setPrecio_noiva(Double precio_noiva) {
		this.precio_noiva = precio_noiva;
	}

	@Column(name = "producto_stock")
	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Column(name = "producto_tax_amount")
	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}

	@Column(name = "producto_tax_percentage")
	public Double getTax_percentage() {
		return tax_percentage;
	}

	public void setTax_percentage(Double tax_percentage) {
		this.tax_percentage = tax_percentage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="producto_fecha_puesta_venta")
	public Calendar getFecha_puesta_venta() {
		return fecha_puesta_venta;
	}

	public void setFecha_puesta_venta(Calendar fecha_puesta_venta) {
		this.fecha_puesta_venta = fecha_puesta_venta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="producto_fecha_retirada")
	public Calendar getFecha_retirada() {
		return fecha_retirada;
	}

	public void setFecha_retirada(Calendar fecha_retirada) {
		this.fecha_retirada = fecha_retirada;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public ProductoData getProductoData() {
		if (productoData == null) {
			productoData = new ProductoData(id);
		}
		return productoData;
	}

	public void setProductoData(ProductoData productoData) {
		this.productoData = productoData;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="tienda_id")
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "producto_categoria",
				joinColumns = {@JoinColumn(name="producto_id")},
				inverseJoinColumns={@JoinColumn(name="categoria_id")})
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="productohistorico_id", referencedColumnName="producto_id")
	public Set<ProductoHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(Set<ProductoHistorico> historico) {
		this.historico = historico;
	}
	
	
}
