package es.udc.jcastedo.NosaTenda.model.producto;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.model.productoService.ProductoBlock;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("productoHistoricoDao")
public class ProductoHistoricoDaoHibernate extends GenericDaoHibernate<ProductoHistorico, Long>
		implements ProductoHistoricoDao {

	@SuppressWarnings("unchecked")
	public List<ProductoBlock> getFechasVentaByProducto(Long productoId) {
		
		try {
			
			return (List<ProductoBlock>) getSession().createQuery("SELECT p.version as version, p.fecha_puesta_venta as fecha_puesta_venta, p.fecha_retirada as fecha_retirada, p.precio as precio, p.stock_inicial as stock_inicial " +
																"FROM ProductoHistorico p " +
																"WHERE p.id = :producto_id " +
																"ORDER BY p.version")
																.setParameter("producto_id", productoId)
																.setResultTransformer(new AliasToBeanResultTransformer(ProductoBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProductoHistorico> getHistoricos(Long productoId) {
		
		try {
			
			return (List<ProductoHistorico>) getSession().createQuery("SELECT p " +
																		"FROM ProductoHistorico p " +
																		"WHERE p.id = :producto_id " +
																		"ORDER BY p.version")
																		.setParameter("producto_id", productoId)
																		.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

}
