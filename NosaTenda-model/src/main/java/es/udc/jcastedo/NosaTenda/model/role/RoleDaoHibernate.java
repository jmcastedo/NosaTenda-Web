package es.udc.jcastedo.NosaTenda.model.role;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("roleDao")
public class RoleDaoHibernate extends GenericDaoHibernate<Role, String> implements
		RoleDao {

	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		
		try {
			
			return (List<Role>) getSession().createQuery("SELECT r " +
														"FROM Role r " +
														"ORDER BY r.role_nombre")
														.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

}
