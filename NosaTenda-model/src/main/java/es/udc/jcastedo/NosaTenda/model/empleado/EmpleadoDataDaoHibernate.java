package es.udc.jcastedo.NosaTenda.model.empleado;

import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("empleadoDataDao")
public class EmpleadoDataDaoHibernate extends GenericDaoHibernate<EmpleadoData, Long>
		implements EmpleadoDataDao {


}
