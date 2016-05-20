package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.EmpleadoDataTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO.Roles_EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoData;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado.Roles_Empleado;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;

public class EmpleadoTypeConversor {

	public EmpleadoTypeConversor() {}
	
	public static EmpleadoTO toEmpleadoTO(Empleado empleado) throws BadFormatRequestException {
		
		EmpleadoTO empleadoTO = new EmpleadoTO();
		
		empleadoTO.setId(empleado.getId());
		empleadoTO.setCorreo(empleado.getCorreo());
		empleadoTO.setPassword(empleado.getPassword());
		empleadoTO.setRole(getRoleTO(empleado.getRole()));
		empleadoTO.setActivado(empleado.getActivado());
		empleadoTO.setNif(empleado.getNif());
		
		return empleadoTO;
	}
	
	public static List<EmpleadoTO> toEmpleadoTO(List<Empleado> empleados) throws BadFormatRequestException {
		
		List<EmpleadoTO> empleadosTOs = new ArrayList<EmpleadoTO>();
		
		for (Empleado empleado: empleados) {
			empleadosTOs.add(EmpleadoTypeConversor.toEmpleadoTO(empleado));
		}
		
		return empleadosTOs;
	}
	
	public static Roles_Empleado getRole(String role) throws BadFormatRequestException {
		
		for (Roles_Empleado rol: Roles_Empleado.values()) {
			if (role.toString().equals(rol.toString())) {
				return rol;
			}
		}
		throw new BadFormatRequestException(role, EmpleadoTypeConversor.class.getName());
		
	}
	
	public static Roles_EmpleadoTO getRoleTO(Roles_Empleado role) throws BadFormatRequestException {
		
		for (Roles_EmpleadoTO rolTO: Roles_EmpleadoTO.values()) {
			if (role.toString().equals(rolTO.toString())) {
				return rolTO;
			}
		}
		throw new BadFormatRequestException(role, EmpleadoTypeConversor.class.getName());
		
	}
	
	public static EmpleadoDataTO toEmpleadoDataTO(EmpleadoData empleadoData) {
		
		EmpleadoDataTO empleadoDataTO = new EmpleadoDataTO();
		
		empleadoDataTO.setId(empleadoData.getId());
		empleadoDataTO.setNombre(empleadoData.getNombre());
		empleadoDataTO.setApellidos(empleadoData.getApellidos());
		empleadoDataTO.setDireccion(empleadoData.getDireccion());
		empleadoDataTO.setLocalidad(empleadoData.getLocalidad());
		empleadoDataTO.setProvincia(empleadoData.getProvincia());
		empleadoDataTO.setCp(empleadoData.getCp());
		empleadoDataTO.setPhone1(empleadoData.getPhone1());
		empleadoDataTO.setPhone2(empleadoData.getPhone2());
		
		return empleadoDataTO;
	}
}
