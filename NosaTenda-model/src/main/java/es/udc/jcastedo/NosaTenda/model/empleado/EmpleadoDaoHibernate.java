package es.udc.jcastedo.NosaTenda.model.empleado;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.model.empleado.Empleado.Roles_Empleado;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

@Repository("empleadoDao")
public class EmpleadoDaoHibernate extends GenericDaoHibernate<Empleado, Long>
								implements EmpleadoDao {


	public Empleado findByCorreo(String correo) throws InstanceNotFoundException {
		
		try {
			
			Empleado empleado = (Empleado) getSession().createQuery("SELECT e FROM Empleado e " +
																	"WHERE e.correo = :correo")
																	.setParameter("correo", correo)
																	.uniqueResult();
			
			if (empleado == null) {
				throw new InstanceNotFoundException(empleado, EmpleadoDaoHibernate.class.getName());
			} else {
				return empleado;
			}
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Empleado> getEmpleados() {
		
		try {
			
			return (List<Empleado>) getSession().createQuery("SELECT e " +
															"FROM Empleado e " +
															"ORDER BY e.correo")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> getEmpleadosByTienda(Long tiendaId) {
		
		try {
			
			return (List<Empleado>) getSession().createQuery("SELECT e " +
															"FROM Empleado e JOIN e.trabaja t " +
															"WHERE (t.id = :tiendaId) " +
															"ORDER BY e.correo")
															.setParameter("tiendaId", tiendaId)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Empleado> getEmpleadosByRole(Roles_Empleado role) {
		
		try {
			
			return (List<Empleado>) getSession().createQuery("SELECT e " +
															"FROM Empleado e " +
															"WHERE (e.role = :role) " +
															"ORDER BY e.correo")
															.setParameter("role", role)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

}
