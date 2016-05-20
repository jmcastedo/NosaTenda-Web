package es.udc.jcastedo.NosaTenda.model.cliente;

import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("clienteDataDao")
public class ClienteDataDaoHibernate extends GenericDaoHibernate<ClienteData, Long>
		implements ClienteDataDao {

}
