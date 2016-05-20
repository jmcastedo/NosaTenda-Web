package es.udc.jcastedo.NosaTenda.model.metodoEnvio;

import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("metodoEnvioDao")
public class MetodoEnvioDaoHibernate extends GenericDaoHibernate<MetodoEnvio, Long>
		implements MetodoEnvioDao {


}
