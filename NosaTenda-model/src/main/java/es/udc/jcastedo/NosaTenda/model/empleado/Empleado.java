package es.udc.jcastedo.NosaTenda.model.empleado;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

@Entity
public class Empleado {
	
	public enum Roles_Empleado {ROLE_USUARIO, ROLE_ADMIN, ROLE_ADMIN_TIENDA}

	private final Roles_Empleado defaultRole = Roles_Empleado.ROLE_USUARIO;
	private final Boolean defaultActivado = false;
	
	private Long id;
	private String correo;
	private String password;
	private Roles_Empleado role = defaultRole;
	private Boolean activado = defaultActivado;
	private String nif;
	private EmpleadoData empleadoData;
	private Set<Tienda> trabaja = new HashSet<Tienda>();
	
	public Empleado() {}
	
	public Empleado(String correo, String password) {
		
		this.correo = correo;
		this.password = password;
		this.role = defaultRole;
		this.activado = defaultActivado;
		
	}
	
	public Empleado(String correo, String password, Roles_Empleado role,
			Boolean activado, String nif) {
		
		this.correo = correo;
		this.password = password;
		this.role = role;
		this.activado = activado;
		this.nif = nif;
	}

	@Id
	@Column(name = "empleado_id", insertable = false, updatable = false)
	@SequenceGenerator(
			name="EmpleadoIdGenerator",
			sequenceName="empleado_empleado_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="EmpleadoIdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "empleado_correo")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "empleado_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "empleado_role")
	public Roles_Empleado getRole() {
		return role;
	}

	public void setRole(Roles_Empleado role) {
		this.role = role;
	}

	@Column(name = "empleado_activado")
	public Boolean getActivado() {
		return activado;
	}

	public void setActivado(Boolean activado) {
		this.activado = activado;
	}

	@Column(name = "empleado_nif")
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public EmpleadoData getEmpleadoData() {
		if (empleadoData == null) {
			empleadoData = new EmpleadoData(id);
		}
		return empleadoData;
	}

	public void setEmpleadoData(EmpleadoData empleadoData) {
		this.empleadoData = empleadoData;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "empleado_tienda",
				joinColumns = {@JoinColumn(name="empleado_id")},
				inverseJoinColumns = {@JoinColumn(name="tienda_id")})
	public Set<Tienda> getTrabaja() {
		return trabaja;
	}

	public void setTrabaja(Set<Tienda> trabaja) {
		this.trabaja = trabaja;
	}
	
	
}
