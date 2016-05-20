package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.RoleTO;
import es.udc.jcastedo.NosaTenda.model.role.Role;

public class RoleTypeConversor {

	public RoleTypeConversor() {}
	
	public static RoleTO toRoleTO(Role role) {
		
		RoleTO roleTO = new RoleTO();
		
		roleTO.setRole_nombre(role.getRole_nombre());
		
		return roleTO;
	}
	
	public static List<RoleTO> toRoleTO(List<Role> roles) {
		
		List<RoleTO> roleTOs = new ArrayList<RoleTO>();
		
		for (Role role: roles) {
			roleTOs.add(toRoleTO(role));
		}
		
		return roleTOs;
	}
}
