package es.udc.jcastedo.NosaTenda.model.compra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("compraDao")
public class CompraDaoHibernate extends GenericDaoHibernate<Compra, Long>
								implements CompraDao {

	@SuppressWarnings("unchecked")
	public List<Compra> getComprasByClienteId(Long clienteId) {
		
		try {
			
			return (List<Compra>) getSession().createQuery("SELECT c " +
															"FROM Compra c " +
															"WHERE (c.cliente.id = :cliente_id) " +
															"ORDER BY c.fecha")
															.setParameter("cliente_id", clienteId)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> getCompras() {
		
		try {
			
			return (List<Compra>) getSession().createQuery("SELECT c " +
															"FROM Compra c " +
															"ORDER BY c.fecha")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> getComprasByTiendaId(Long tiendaId) {
		
		try {
			
			// las dos formas están probadas y funcionan
			// INNER JOIN explícito
			return (List<Compra>) getSession().createQuery("SELECT DISTINCT c " +
															"FROM Compra c " +
															"JOIN c.producto p " +
															"WHERE p.tienda.id = :tienda_id " +
															"ORDER BY c.fecha")
															.setParameter("tienda_id", tiendaId)
															.list();
			// INNER JOIN implícito
			/*return (List<Compra>) getSession().createQuery("SELECT DISTINCT c " +
					"FROM Compra c, Producto p " +
					"WHERE c.producto = p AND p.tienda.id = :tienda_id " +
					"ORDER BY c.id")
					.setParameter("tienda_id", tiendaId)
					.list();*/
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentas() {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT c.producto.id as productoId, COALESCE(SUM(c.unidades),0) as unidadesVendidas " +
																"FROM Compra c " +
																"GROUP BY c.producto.id")
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	public Long getVentasByProductoId(Long productoId) {
		
		try {
		
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(c.unidades),0) " +
											"FROM Compra c " +
											"WHERE c.producto.id = :producto_id ")
											.setParameter("producto_id", productoId)
											.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
		
	}
	
	public Long getVentasByProductoIdByRango(Long productoId, Calendar fecha_inicio, Calendar fecha_fin) {
		
		try {
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(c.unidades),0) " +
													"FROM Compra c " +
													"WHERE c.producto.id = :producto_id " +
													"AND c.fecha BETWEEN :fecha_inicio AND :fecha_fin ")
													.setParameter("producto_id", productoId)
													.setParameter("fecha_inicio", fecha_inicio)
													.setParameter("fecha_fin", fecha_fin)
													.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentasByTiendaIdDesglose(Long tiendaId) {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT c.producto.id as productoId, COALESCE(SUM(c.unidades),0) as unidadesVendidas " +
																"FROM Compra c " +
																"WHERE c.producto.tienda.id = :tienda_id " +
																"GROUP BY c.producto.id")
																.setParameter("tienda_id", tiendaId)
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByTiendaId(Long tiendaId) {
		
		try {
		
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(c.unidades),0) " +
											"FROM Compra c " +
											"WHERE c.producto.tienda.id = :tienda_id ")
											.setParameter("tienda_id", tiendaId)
											.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentasByCategoriaIdDesglose(Long categoriaId) {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT c.producto.id as productoId, COALESCE(SUM(c.unidades),0) as unidadesVendidas " +
																"FROM Compra c JOIN c.producto.categorias g " +
																"WHERE g.id = :categoria_id " +
																"GROUP BY c.producto.id")
																.setParameter("categoria_id", categoriaId)
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByCategoriaId(Long categoriaId) {
		
		try {
		
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(c.unidades),0) " +
											"FROM Compra c JOIN c.producto.categorias g " +
											"WHERE g.id = :categoria_id ")
											.setParameter("categoria_id", categoriaId)
											.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentasByCategoriaIdByTiendaIdDesglose(Long categoriaId, Long tiendaId) {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT c.producto.id as productoId, COALESCE(SUM(c.unidades),0) as unidadesVendidas " +
																"FROM Compra c JOIN c.producto.categorias g " +
																"WHERE g.id = :categoria_id " +
																"AND c.producto.tienda.id = :tienda_id " +
																"GROUP BY c.producto.id")
																.setParameter("categoria_id", categoriaId)
																.setParameter("tienda_id", tiendaId)
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByCategoriaIdByTiendaId(Long categoriaId, Long tiendaId) {
		
		try {
		
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(c.unidades),0) " +
											"FROM Compra c JOIN c.producto.categorias g " +
											"WHERE g.id = :categoria_id " +
											"AND c.producto.tienda.id = :tienda_id ")
											.setParameter("categoria_id", categoriaId)
											.setParameter("tienda_id", tiendaId)
											.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
		
	}

}
