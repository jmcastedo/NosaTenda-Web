package es.udc.jcastedo.NosaTenda.model.userService;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoDataTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.RoleTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectPasswordException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

public interface UserService {

	public ClienteTO findClienteById(Long clienteId) throws InstanceNotFoundException;
	
	public Long newCliente(String correo, String password) throws DuplicateInstanceException;
	
	public Long newCliente(String nombre, String correo, String password) throws DuplicateInstanceException;
	
	public Long loginCliente(String correo, String password)
			throws InstanceNotFoundException, IncorrectPasswordException;
	
	public Long loginEmpleado(String correo, String password)
			throws InstanceNotFoundException, IncorrectPasswordException;
	
	public Long newEmpleado(String correo, String password) throws DuplicateInstanceException;
	
	public Boolean deleteEmpleado(String correo) throws InstanceNotFoundException;
	
	public Boolean deleteEmpleado(Long empleadoId) throws InstanceNotFoundException;
	
	public Boolean editEmpleado(String correo, String role, Boolean activado,
			String nif, String nombre, String apellidos, String direccion, String localidad,
			String provincia, String cp, String phone1, String phone2)
					throws InstanceNotFoundException, BadFormatRequestException;
	
	public Boolean editEmpleado(String correo,
			String nif, String nombre, String apellidos, String direccion, String localidad,
			String provincia, String cp, String phone1, String phone2)
					throws InstanceNotFoundException;
	
	public Boolean editEmpleadoRole(Long empleadoId, String role) throws InstanceNotFoundException, BadFormatRequestException;
	
	public Boolean editEmpleadoPassword(String correo, String password, String passwordNew) throws InstanceNotFoundException, IncorrectPasswordException;
	
	public Boolean editEmpleadoCorreo(String correo, String correoNew) throws InstanceNotFoundException, DuplicateInstanceException;
	
	public Boolean activateEmpleado(String correo) throws InstanceNotFoundException;
	
	public Boolean activateEmpleado(Long empleadoId) throws InstanceNotFoundException;
	
	public Boolean deactivateEmpleado(String correo) throws InstanceNotFoundException;
	
	public Boolean deactivateEmpleado(Long empleadoId) throws InstanceNotFoundException;
	
	public List<EmpleadoTO> getEmpleados() throws BadFormatRequestException;
	
	public List<EmpleadoTO> getEmpleadosByTienda(Long tiendaId) throws BadFormatRequestException;
	
	public List<EmpleadoTO> getEmpleadosByRole(String role) throws BadFormatRequestException;
	
	public EmpleadoDataTO getEmpleadoData(Long empleadoId) throws InstanceNotFoundException;
	
	public EmpleadoTO getEmpleado(String correo) throws InstanceNotFoundException, BadFormatRequestException;
	
	public List<RoleTO> getRoles();
}
