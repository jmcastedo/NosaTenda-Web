package es.udc.jcastedo.NosaTenda.model.role;

import java.util.List;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface RoleDao extends GenericDao<Role, String> {

	public List<Role> getRoles();
}
