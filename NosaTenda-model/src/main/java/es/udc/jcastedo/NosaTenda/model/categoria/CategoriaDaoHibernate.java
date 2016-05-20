package es.udc.jcastedo.NosaTenda.model.categoria;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("categoriaDao")
public class CategoriaDaoHibernate extends GenericDaoHibernate<Categoria, Long>
								implements CategoriaDao {

	@SuppressWarnings("unchecked")
	public Set<Categoria> getCategorias() {
		
		try {
			
			List<Categoria> categoriaList = (List<Categoria>) getSession().createQuery("SELECT c " +
																"FROM Categoria c " +
																"ORDER BY c.nombre")
																.list();
			Set<Categoria> categoriaSet = new HashSet<Categoria>(categoriaList);
			
			return categoriaSet;
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<Categoria> getCategoriasByTienda(Long tiendaId) {
		
		try {
			
			List<Categoria> categoriaList = (List<Categoria>) getSession().createQuery("SELECT c " +
																"FROM Categoria c JOIN c.tiendas t " +
																"WHERE (t.id = :tienda_id) " +
																"ORDER BY c.id")
																.setParameter("tienda_id", tiendaId)
																.list();
			
			Set<Categoria> categoriaSet = new HashSet<Categoria>(categoriaList);
			
			return categoriaSet;
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
}
