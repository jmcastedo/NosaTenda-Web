package es.udc.jcastedo.NosaTenda.model.cliente;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

public interface ClienteDao extends GenericDao<Cliente, Long> {

	public Cliente find(String correo, String password) throws InstanceNotFoundException;

	public Cliente findByCorreo(String correo) throws InstanceNotFoundException;
}
