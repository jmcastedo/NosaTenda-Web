package es.udc.jcastedo.NosaTenda.model.producto;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;

@Repository("productoDao")
public class ProductoDaoHibernate extends GenericDaoHibernate<Producto, Long>
								implements ProductoDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoDaoHibernate.class);
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductos() {
		
		try {
		return (List<Producto>) getSession().createQuery("SELECT c " +
														"FROM Producto c " +
														"ORDER BY c.id")
														.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Producto> searchProductos(String nombre, Long stock) {
		
		logger.debug("entrando searchProductos");
		
		String query = "SELECT p FROM Producto p JOIN p.productoData d WHERE";
		
		Boolean twoParam = false;
		
		if (nombre != null) {
			query = query.concat(" UPPER(d.nombre) LIKE UPPER('%"+nombre+"%')");
			twoParam = true;
		}
		
		if (stock != null) {
			if (twoParam) query = query.concat(" AND");
			query = query.concat(" p.stock <= :stock");
		}
		
		query = query.concat(" ORDER BY p.id");
		
		logger.debug("antes del return");
		
		try {
			if (stock != null) {
				return getSession().createQuery(query)
						.setLong("stock", stock)
						.list();
			} else {
				return getSession().createQuery(query)
						.list();
				
			}
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByLocalidad(Long localidadId) {
		
		try {
		
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.localidad.id = :localidad_id) " +
															"ORDER BY p.id")
															.setParameter("localidad_id", localidadId)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByLocalidadEnVenta(Long localidadId) {
		
		try {
		
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.localidad.id = :localidad_id) " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("localidad_id", localidadId)
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByCategoria(Categoria categoria) {
		
		try {
															
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.categorias c " +
															"WHERE (c.id = :categoria_id) " +
															"ORDER BY p.id")
															.setParameter("categoria_id", categoria.getId())
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByCategoriaEnVenta(Categoria categoria) {
		
		try {
															
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.categorias c " +
															"WHERE (c.id = :categoria_id) " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("categoria_id", categoria.getId())
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByCategoriaTienda(Categoria categoria) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.tienda.categorias c " +
															"WHERE (c.id = :categoria_id) " +
															"ORDER BY p.id")
															.setParameter("categoria_id", categoria.getId())
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByCategoriaTiendaEnVenta(Categoria categoria) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.tienda.categorias c " +
															"WHERE (c.id = :categoria_id) " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("categoria_id", categoria.getId())
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTienda(Long tiendaId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.id = :tienda_id) " +
															"ORDER BY p.id")
															.setParameter("tienda_id", tiendaId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTiendaEnVenta(Long tiendaId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.id = :tienda_id) " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("tienda_id", tiendaId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTiendaEnVentaDisponibles(Long tiendaId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.id = :tienda_id) " +
															"AND p.stock > 0 " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("tienda_id", tiendaId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTiendaEnVentaAgotados(Long tiendaId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE (p.tienda.id = :tienda_id) " +
															"AND p.stock = 0 " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("tienda_id", tiendaId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTiendasFav(Long clienteId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.tienda.clientes c " +
															"WHERE (c.id = :cliente_id) " +
															"ORDER BY p.id")
															.setParameter("cliente_id", clienteId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByTiendasFavEnVenta(Long clienteId) {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p JOIN p.tienda.clientes c " +
															"WHERE (c.id = :cliente_id) " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.setParameter("cliente_id", clienteId)
															.list();
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosEnVenta() {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Producto> getProductosEnVentaDisponibles() {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE p.stock > 0 " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosEnVentaAgotados() {
		
		try {
			
			return (List<Producto>) getSession().createQuery("SELECT p " +
															"FROM Producto p " +
															"WHERE p.stock = 0 " +
															"AND LOCALTIMESTAMP BETWEEN p.fecha_puesta_venta AND p.fecha_retirada " +
															"ORDER BY p.fecha_retirada")
															.list();
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
}
