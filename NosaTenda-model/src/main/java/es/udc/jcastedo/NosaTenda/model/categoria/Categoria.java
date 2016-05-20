package es.udc.jcastedo.NosaTenda.model.categoria;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

@Entity
public class Categoria {

	private Long id;
	private String nombre;
	private Set<Producto> productos = new HashSet<Producto>();
	private Set<Tienda> tiendas = new HashSet<Tienda>();
	
	public Categoria() {}
	
	public Categoria(String nombre) {
		
		this.nombre = nombre;
	}

	@Id
	@Column(name = "categoria_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="CategoriaIdGenerator",
			sequenceName="categoria_categoria_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="CategoriaIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "categoria_nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ManyToMany(mappedBy="categorias", fetch=FetchType.EAGER)
	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	@ManyToMany(mappedBy="categorias", fetch=FetchType.EAGER)
	public Set<Tienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(Set<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
}
