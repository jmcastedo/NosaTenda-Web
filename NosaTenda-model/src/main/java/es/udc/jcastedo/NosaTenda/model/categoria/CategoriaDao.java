package es.udc.jcastedo.NosaTenda.model.categoria;

import java.util.Set;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface CategoriaDao extends GenericDao<Categoria, Long> {

	public Set<Categoria> getCategorias();
	
	public Set<Categoria> getCategoriasByTienda(Long tiendaId);
}
