package es.udc.jcastedo.NosaTenda.model.reserva;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("reservaDao")
public class ReservaDaoHibernate extends GenericDaoHibernate<Reserva, Long> 
								implements ReservaDao {

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas(Long clienteId) {
		
		try {
		
			return (List<Reserva>) getSession().createQuery("SELECT r " +
															"FROM Reserva r " +
															"WHERE (r.cliente.id = :cliente_id) " +
															"ORDER BY r.fecha")
															.setParameter("cliente_id", clienteId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		
		try {
			
			return (List<Reserva>) getSession().createQuery("SELECT r " +
															"FROM Reserva r " +
															"ORDER BY r.fecha")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentas() {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT r.producto.id as productoId, COALESCE(SUM(r.unidades),0) as unidadesVendidas " +
																"FROM Reserva r " +
																"WHERE r.estado = 'ENTREGADA' " +
																"GROUP BY r.producto.id")
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	public Long getVentasByProductoId(Long productoId) {
		
		try {
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(r.unidades),0) " +
													"FROM Reserva r " +
													"WHERE r.producto.id = :producto_id " +
													"AND r.estado = 'ENTREGADA' ")
													.setParameter("producto_id", productoId)
													.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByProductoIdByRango(Long productoId, Calendar fecha_inicio, Calendar fecha_fin) {
		
		try {
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(r.unidades),0) " +
													"FROM Reserva r " +
													"WHERE r.producto.id = :producto_id " +
													"AND r.estado = 'ENTREGADA' " +
													"AND r.fecha BETWEEN :fecha_inicio AND :fecha_fin ")
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
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT r.producto.id as productoId, COALESCE(SUM(r.unidades),0) as unidadesVendidas " +
																"FROM Reserva r " +
																"WHERE r.estado = 'ENTREGADA' " +
																"AND r.producto.tienda.id = :tienda_id " +
																"GROUP BY r.producto.id")
																.setParameter("tienda_id", tiendaId)
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByTiendaId(Long tiendaId) {
		
		try {
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(r.unidades),0) " +
													"FROM Reserva r " +
													"WHERE r.producto.tienda.id = :tienda_id " +
													"AND r.estado = 'ENTREGADA' ")
													.setParameter("tienda_id", tiendaId)
													.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentasByCategoriaIdDesglose(Long categoriaId) {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT r.producto.id as productoId, COALESCE(SUM(r.unidades),0) as unidadesVendidas " +
																"FROM Reserva r JOIN r.producto.categorias g " +
																"WHERE g.id = :categoria_id " +
																"AND r.estado = 'ENTREGADA' " +
																"GROUP BY r.producto.id")
																.setParameter("categoria_id", categoriaId)
																.setResultTransformer(new AliasToBeanResultTransformer(UnidadesBlock.class))
																.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Long getVentasByCategoriaId(Long categoriaId) {
		
		try {
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(r.unidades),0) " +
													"FROM Reserva r JOIN r.producto.categorias g " +
													"WHERE g.id = :categoria_id " +
													"AND r.estado = 'ENTREGADA' ")
													.setParameter("categoria_id", categoriaId)
													.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesBlock> getVentasByCategoriaIdByTiendaIdDesglose(Long categoriaId, Long tiendaId) {
		
		try {
			
			return (List<UnidadesBlock>) getSession().createQuery("SELECT r.producto.id as productoId, COALESCE(SUM(r.unidades),0) as unidadesVendidas " +
																"FROM Reserva r JOIN r.producto.categorias g " +
																"WHERE g.id = :categoria_id " +
																"AND r.producto.tienda.id = :tienda_id " +
																"AND r.estado = 'ENTREGADA' " +
																"GROUP BY r.producto.id")
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
			
			return (Long) getSession().createQuery("SELECT COALESCE(SUM(r.unidades),0) " +
													"FROM Reserva r JOIN r.producto.categorias g " +
													"WHERE g.id = :categoria_id " +
													"AND r.producto.tienda.id = :tienda_id " +
													"AND r.estado = 'ENTREGADA' ")
													.setParameter("categoria_id", categoriaId)
													.setParameter("tienda_id", tiendaId)
													.uniqueResult();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

}
