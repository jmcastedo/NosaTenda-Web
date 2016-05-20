package es.udc.jcastedo.NosaTenda.model.localidad;

import java.util.List;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface LocalidadDao extends GenericDao<Localidad, Long> {

	public List<Localidad> getLocalidades();
}
