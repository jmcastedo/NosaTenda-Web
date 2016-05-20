package es.udc.jcastedo.NosaTenda.model.empleado;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.empleado.Empleado.Roles_Empleado;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

public interface EmpleadoDao extends GenericDao<Empleado, Long> {

	public Empleado findByCorreo(String correo) throws InstanceNotFoundException;
	
	public List<Empleado> getEmpleados();
	
	public List<Empleado> getEmpleadosByTienda(Long tiendaId);
	
	public List<Empleado> getEmpleadosByRole(Roles_Empleado role);
	
}
