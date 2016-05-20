package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.EmpleadoDataTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO.Roles_EmpleadoTO;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoInfoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO;

public class EmpleadoWebserviceConversor {

	public EmpleadoWebserviceConversor() {}
	
	public static EmpleadoWTO toEmpleadoWTO(EmpleadoTO empleadoTO) {
		
		EmpleadoWTO empleadoWTO = new EmpleadoWTO();
		
		empleadoWTO.setId(empleadoTO.getId());
		empleadoWTO.setCorreo(empleadoTO.getCorreo());
		empleadoWTO.setRole(getRole(empleadoTO.getRole()));
		empleadoWTO.setPassword(empleadoTO.getPassword());
		empleadoWTO.setActivado(empleadoTO.getActivado());
		empleadoWTO.setNif(empleadoTO.getNif());
		
		return empleadoWTO;
	}
	
	public static List<EmpleadoWTO> toEmpleadoWTO(List<EmpleadoTO> empleadoTOs) {
		
		List<EmpleadoWTO> empleadoWTOs = new ArrayList<EmpleadoWTO>();
		
		for (EmpleadoTO empleadoTO: empleadoTOs) {
			EmpleadoWTO empleadoWTO = toEmpleadoWTO(empleadoTO);
			empleadoWTOs.add(empleadoWTO);
		}
		
		return empleadoWTOs;
	}

	private static String getRole(Roles_EmpleadoTO role) {
		
		return role.toString();
	}
	
	public static EmpleadoInfoWTO toEmpleadoInfoWTO(EmpleadoDataTO empleadoDataTO) {
		
		if (empleadoDataTO == null) {
			return null;
		} else {
			EmpleadoInfoWTO empleadoInfoWTO = new EmpleadoInfoWTO();
			
			empleadoInfoWTO.setId(empleadoDataTO.getId());
			empleadoInfoWTO.setNombre(empleadoDataTO.getNombre());
			empleadoInfoWTO.setApellidos(empleadoDataTO.getApellidos());
			empleadoInfoWTO.setDireccion(empleadoDataTO.getDireccion());
			empleadoInfoWTO.setLocalidad(empleadoDataTO.getLocalidad());
			empleadoInfoWTO.setProvincia(empleadoDataTO.getProvincia());
			empleadoInfoWTO.setCp(empleadoDataTO.getCp());
			empleadoInfoWTO.setPhone1(empleadoDataTO.getPhone1());
			empleadoInfoWTO.setPhone2(empleadoDataTO.getPhone2());
			
			return empleadoInfoWTO;
		}
	}
}
