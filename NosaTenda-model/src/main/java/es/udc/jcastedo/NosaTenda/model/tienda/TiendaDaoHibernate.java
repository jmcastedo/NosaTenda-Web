package es.udc.jcastedo.NosaTenda.model.tienda;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("tiendaDao")
public class TiendaDaoHibernate extends GenericDaoHibernate<Tienda, Long> 
								implements TiendaDao {

	@SuppressWarnings("unchecked")
	public List<Tienda> getTiendas() {
		
		try {
			return (List<Tienda>) getSession().createQuery("SELECT t " +
															"FROM Tienda t " +
															"ORDER BY t.id")
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tienda> getTiendasFav(Long clienteId) {
		
		try {
			return (List<Tienda>) getSession().createQuery("SELECT t " +
															"FROM Tienda t JOIN t.clientes c " +
															"WHERE (c.id = :cliente_id) " +
															"ORDER BY t.id")
															.setParameter("cliente_id", clienteId)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
}
