package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.RoleTO;
import es.udc.jcastedo.NosaTenda.webservice.service.RoleWTO;

public class RoleWebserviceConversor {

	public RoleWebserviceConversor() {}
	
	public static RoleWTO toRoleWTO(RoleTO roleTO) {
		
		RoleWTO roleWTO = new RoleWTO();
		
		roleWTO.setNombre(roleTO.getRole_nombre());
		
		return roleWTO;
	}
	
	public static List<RoleWTO> toRoleWTO(List<RoleTO> roleTOs) {
		
		List<RoleWTO> roleWTOs = new ArrayList<RoleWTO>();
		
		for(RoleTO roleTO: roleTOs) {
			roleWTOs.add(toRoleWTO(roleTO));
		}
		
		return roleWTOs;
	}
}
