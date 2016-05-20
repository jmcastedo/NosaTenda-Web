package es.udc.jcastedo.NosaTenda.model.producto;

import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("productoDataDao")
public class ProductoDataDaoHibernate extends GenericDaoHibernate<ProductoData, Long>
		implements ProductoDataDao {


}
