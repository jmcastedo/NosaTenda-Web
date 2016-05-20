package es.udc.jcastedo.NosaTenda.model.compraNotVerified;

import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("compraNotVerifiedDao")
public class CompraNotVerifiedDaoHibernate extends GenericDaoHibernate<CompraNotVerified, Long>
											implements CompraNotVerifiedDao {

}
