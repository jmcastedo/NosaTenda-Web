package es.udc.jcastedo.NosaTenda.model.cliente;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import es.udc.jcastedo.NosaTenda.model.compra.Compra;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

@Entity
public class Cliente {
	
	private final String defaultRole = "ROLE_CLIENTE";
	private final Boolean defaultActivado = true;

	private Long id;
	private String correo;
	private String password;
	private String role = defaultRole;
	private Boolean activado = defaultActivado;
	private String nif;
	private ClienteData clienteData;
	private Set<Tienda> favoritas = new HashSet<Tienda>();
	private Set<Compra> compras = new HashSet<Compra>();
	private Set<Reserva> reservas = new HashSet<Reserva>();
	
	public Cliente () {}
	
	public Cliente (String correo, String password) {
		
		this.correo = correo;
		this.password = password;
		this.role = defaultRole;
		this.activado = defaultActivado;

	}

	@Id
	@Column(name = "cliente_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="ClienteIdGenerator",
			sequenceName="cliente_cliente_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="ClienteIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cliente_correo")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "cliente_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "cliente_role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "cliente_activado")
	public Boolean getActivado() {
		return activado;
	}

	public void setActivado(Boolean activado) {
		this.activado = activado;
	}

	@Column(name = "cliente_nif")
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public ClienteData getClienteData() {
		if (clienteData == null) {
			clienteData = new ClienteData(id);
		}
		return clienteData;
	}

	public void setClienteData(ClienteData clienteData) {
		this.clienteData = clienteData;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "cliente_tienda",
				joinColumns = {@JoinColumn(name="cliente_id")},
				inverseJoinColumns = {@JoinColumn(name="tienda_id")})
	public Set<Tienda> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(Set<Tienda> favoritas) {
		this.favoritas = favoritas;
	}
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
}
