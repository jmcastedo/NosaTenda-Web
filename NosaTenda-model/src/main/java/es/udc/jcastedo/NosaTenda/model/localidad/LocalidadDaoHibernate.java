package es.udc.jcastedo.NosaTenda.model.localidad;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("localidadDao")
public class LocalidadDaoHibernate extends GenericDaoHibernate<Localidad, Long>
									implements LocalidadDao {

	@SuppressWarnings("unchecked")
	public List<Localidad> getLocalidades() {
		
		try {
			
			return (List<Localidad>) getSession().createQuery("SELECT l " +
															"FROM Localidad l " +
															"ORDER BY l.nombre")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
}
