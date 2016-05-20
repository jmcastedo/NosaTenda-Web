package es.udc.jcastedo.NosaTenda.model.tienda;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.metodoEnvio.MetodoEnvio;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;

@Entity
public class Tienda {

	private Long id;
	private String nombre;
	private String direccion;
	private String provincia;
	private String cp;
	private String nif;
	private String correo;
	private String web;
	private String phone1;
	private String phone2;
	private String fax;
	private String imagen;
	private Double lat;
	private Double lon;
	private Localidad localidad;
	private Set<Categoria> categorias = new HashSet<Categoria>();
	private Set<Cliente> clientes = new HashSet<Cliente>();
	private Set<Empleado> empleados = new HashSet<Empleado>();
	private Set<Producto> productos = new HashSet<Producto>();
	private Set<MetodoEnvio> metodosEnvio = new HashSet<MetodoEnvio>();
	
	public Tienda () {}

	public Tienda (String nombre, String direccion, String nif,
			String correo, String phone1, Localidad localidad) {
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.web = "";
		this.provincia = "";
		this.cp = "";
		this.nif = nif;
		this.correo = correo;
		this.phone1 = phone1;
		this.phone2 = "";
		this.fax = "";
		this.imagen = "";
		this.lat = null;
		this.lon = null;
		this.localidad = localidad;
	}

	public Tienda(String nombre, String direccion, String provincia,
			String cp, String nif, String correo, String web,
			String phone1, String phone2, String fax,
			String imagen, Double lat, Double lon, Localidad localidad) {
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.provincia = provincia;
		this.cp = cp;
		this.nif = nif;
		this.correo = correo;
		this.web = web;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.imagen = imagen;
		this.lat = lat;
		this.lon = lon;
		this.localidad = localidad;
	}

	@Id
	@Column(name = "tienda_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="TiendaIdGenerator",
			sequenceName="tienda_tienda_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="TiendaIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tienda_nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "tienda_direccion")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "tienda_provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column(name = "tienda_cp")
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "tienda_nif")
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@Column(name = "tienda_correo")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "tienda_web")
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "tienda_phone1")
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "tienda_phone2")
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Column(name = "tienda_fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "tienda_imagen")
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column(name = "tienda_lat")
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "tienda_lon")
	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="localidad_id")
	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "tienda_categoria",
				joinColumns = {@JoinColumn(name="tienda_id")},
				inverseJoinColumns={@JoinColumn(name="categoria_id")})
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@ManyToMany(mappedBy="favoritas", fetch=FetchType.LAZY)
	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	@ManyToMany(mappedBy="trabaja", fetch=FetchType.LAZY)
	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	@OneToMany(mappedBy="tienda", fetch=FetchType.LAZY)
	public Set<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	@OneToMany(mappedBy="tienda", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<MetodoEnvio> getMetodosEnvio() {
		return metodosEnvio;
	}

	public void setMetodosEnvio(Set<MetodoEnvio> metodosEnvio) {
		this.metodosEnvio = metodosEnvio;
	}
	
}
