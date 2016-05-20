package es.udc.jcastedo.NosaTenda.model.tienda;

import java.util.List;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface TiendaDao extends GenericDao<Tienda, Long> {

	public List<Tienda> getTiendas();
	
	public List<Tienda> getTiendasFav(Long clienteId);
}
