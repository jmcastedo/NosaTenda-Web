package es.udc.jcastedo.NosaTenda.model.userService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoDataTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.RoleTO;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoDao;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoData;
import es.udc.jcastedo.NosaTenda.model.role.RoleDao;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.model.util.ClienteTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.EmpleadoTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.RoleTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectPasswordException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Transactional(readOnly = true)
	public ClienteTO findClienteById(Long clienteId) throws InstanceNotFoundException {
		
		ClienteTO clienteTO = ClienteTypeConversor.toClienteTO(clienteDao.find(clienteId));
		
		// no queremos devolver la contraseña
		clienteTO.setPassword("");
		
		return clienteTO;
	}
	
	public Long newCliente(String correo, String password) throws DuplicateInstanceException {
		
		try {
			clienteDao.findByCorreo(correo);
			throw new DuplicateInstanceException(correo, Cliente.class.getName());
		} catch (InstanceNotFoundException e) {
			Cliente cliente = new Cliente(correo, password);
			
			clienteDao.save(cliente);
			
			return cliente.getId();
		}
		
		
	}
	
	public Long newCliente(String nombre, String correo, String password) throws DuplicateInstanceException {
		
		
		try {
			clienteDao.findByCorreo(correo);
			throw new DuplicateInstanceException(correo, Cliente.class.getName()); 
		} catch (InstanceNotFoundException e) {
			Cliente cliente = new Cliente(correo, password);
			clienteDao.save(cliente);
			
			cliente.getClienteData().setNombre(nombre);
			
			// Cascade.PERSIST
			clienteDao.save(cliente);
			
			return cliente.getId();
		}
		
	}
	
	@Transactional(readOnly = true)
	public Long loginCliente(String correo, String password)
			throws InstanceNotFoundException, IncorrectPasswordException {

		//Cliente cliente = clienteDao.find(correo, password);
		
		Cliente cliente = clienteDao.findByCorreo(correo);
		String storedPassword = cliente.getPassword();
		
		// TODO cuando encriptemos la password aqui habra que hacer dos comprobaciones
		if (!password.equals(storedPassword)) {
			throw new IncorrectPasswordException(correo);
		}
		
		return cliente.getId();
	}

	@Transactional(readOnly = true)
	public Long loginEmpleado(String correo, String password)
			throws InstanceNotFoundException, IncorrectPasswordException {
		
		Empleado empleado = empleadoDao.findByCorreo(correo);
		String storedPassword = empleado.getPassword();
		
		// TODO cuando encriptemos la password aqui habra que hacer dos comprobaciones
		if(!password.equals(storedPassword)) {
			throw new IncorrectPasswordException(correo);
		}
		
		return empleado.getId();
	}

	public Long newEmpleado(String correo, String password) throws DuplicateInstanceException {
		
		
		try {
			empleadoDao.findByCorreo(correo);
			throw new DuplicateInstanceException(correo, Empleado.class.getName());
		} catch (InstanceNotFoundException e) {
			Empleado empleado = new Empleado(correo, password);
			
			empleadoDao.save(empleado);
			
			return empleado.getId();
		}
		
	}

	public Boolean deleteEmpleado(String correo)
			throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.findByCorreo(correo);
		
		empleadoDao.remove(empleado.getId());
		
		return true;
	}
	
	public Boolean deleteEmpleado(Long empleadoId)
			throws InstanceNotFoundException {

		empleadoDao.remove(empleadoId);
		
		return true;
	}

	public Boolean activateEmpleado(String correo)
			throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.findByCorreo(correo);
		
		empleado.setActivado(true);
		empleadoDao.save(empleado);
		
		return true;
	}
	
	public Boolean activateEmpleado(Long empleadoId)
			throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		empleado.setActivado(true);
		empleadoDao.save(empleado);
		
		return true;
	}

	public Boolean deactivateEmpleado(String correo)
			throws InstanceNotFoundException {

		Empleado empleado = empleadoDao.findByCorreo(correo);
		
		empleado.setActivado(false);
		empleadoDao.save(empleado);
		
		return true;
	}

	public Boolean deactivateEmpleado(Long empleadoId)
			throws InstanceNotFoundException {

		Empleado empleado = empleadoDao.find(empleadoId);
		
		empleado.setActivado(false);
		empleadoDao.save(empleado);
		
		return true;
	}

	public Boolean editEmpleado(String correo, String role,
			Boolean activado, String nif, String nombre, String apellidos,
			String direccion, String localidad, String provincia, String cp,
			String phone1, String phone2)
					throws InstanceNotFoundException, BadFormatRequestException {
		
		Empleado empleado = empleadoDao.findByCorreo(correo);
		
		empleado.setRole(EmpleadoTypeConversor.getRole(role));
		empleado.setActivado(activado);
		empleado.setNif(nif);
		
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		
		empleadoData.setNombre(nombre);
		empleadoData.setApellidos(apellidos);
		empleadoData.setDireccion(direccion);
		empleadoData.setLocalidad(localidad);
		empleadoData.setProvincia(provincia);
		empleadoData.setCp(cp);
		empleadoData.setPhone1(phone1);
		empleadoData.setPhone2(phone2);
		
		// Cascade.PERSIST
		empleadoDao.save(empleado);
		
		return true;
	}
	
	public Boolean editEmpleado(String correo,
			String nif, String nombre, String apellidos,
			String direccion, String localidad, String provincia, String cp,
			String phone1, String phone2)
					throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.findByCorreo(correo);
		
		empleado.setNif(nif);
		
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		
		empleadoData.setNombre(nombre);
		empleadoData.setApellidos(apellidos);
		empleadoData.setDireccion(direccion);
		empleadoData.setLocalidad(localidad);
		empleadoData.setProvincia(provincia);
		empleadoData.setCp(cp);
		empleadoData.setPhone1(phone1);
		empleadoData.setPhone2(phone2);
		
		// Cascade.PERSIST
		empleadoDao.save(empleado);
		
		return true;
	}

	public Boolean editEmpleadoRole(Long empleadoId, String role)
			throws InstanceNotFoundException, BadFormatRequestException {
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		empleado.setRole(EmpleadoTypeConversor.getRole(role));
		
		return true;
	}
	
	public Boolean editEmpleadoPassword(String correo, String password,
			String passwordNew) throws InstanceNotFoundException,
			IncorrectPasswordException {
		
		Long empleadoId = loginEmpleado(correo, password);
		
		Empleado empleado = empleadoDao.find(empleadoId);
		empleado.setPassword(passwordNew);
		
		empleadoDao.save(empleado);
		
		return true;
	}
	
	public Boolean editEmpleadoCorreo(String correo, String correoNew)
			throws InstanceNotFoundException, DuplicateInstanceException {
		
		try {
			empleadoDao.findByCorreo(correoNew);
			throw new DuplicateInstanceException(correoNew, Empleado.class.getName());
		} catch (InstanceNotFoundException e) {
			Empleado empleado = empleadoDao.findByCorreo(correo);
			
			empleado.setCorreo(correoNew);
			
			empleadoDao.save(empleado);
			
			return true;
		}
		
	}

	@Transactional(readOnly = true)
	public List<EmpleadoTO> getEmpleados() throws BadFormatRequestException {
		
		List<EmpleadoTO> empleadoTOs = EmpleadoTypeConversor.toEmpleadoTO(empleadoDao.getEmpleados());
		
		return empleadoTOs;
	}
	
	@Transactional(readOnly = true)
	public List<EmpleadoTO> getEmpleadosByTienda(Long tiendaId) throws BadFormatRequestException {
		
		List<EmpleadoTO> empleadoTOs = EmpleadoTypeConversor.toEmpleadoTO(empleadoDao.getEmpleadosByTienda(tiendaId));
		
		return empleadoTOs;
	}

	@Transactional(readOnly = true)
	public List<EmpleadoTO> getEmpleadosByRole(String role) throws BadFormatRequestException {
		
		List<EmpleadoTO> empleadoTOs = EmpleadoTypeConversor.toEmpleadoTO(empleadoDao.getEmpleadosByRole(EmpleadoTypeConversor.getRole(role)));
		
		return empleadoTOs;
	}

	@Transactional(readOnly = true)
	public EmpleadoDataTO getEmpleadoData(Long empleadoId) throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		return EmpleadoTypeConversor.toEmpleadoDataTO(empleado.getEmpleadoData());
	}

	@Transactional(readOnly = true)
	public EmpleadoTO getEmpleado(String correo)
			throws InstanceNotFoundException, BadFormatRequestException {
		
		EmpleadoTO empleadoTO = EmpleadoTypeConversor.toEmpleadoTO(empleadoDao.findByCorreo(correo));
		
		return empleadoTO;
	}

	@Transactional(readOnly = true)
	public List<RoleTO> getRoles() {
		
		List<RoleTO> roleTOs = RoleTypeConversor.toRoleTO(roleDao.getRoles());
		
		return roleTOs;
	}

	

	


}
