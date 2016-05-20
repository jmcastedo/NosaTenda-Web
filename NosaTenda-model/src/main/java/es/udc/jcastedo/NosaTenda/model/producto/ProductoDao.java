package es.udc.jcastedo.NosaTenda.model.producto;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface ProductoDao extends GenericDao<Producto, Long> {
	
	public List<Producto> getProductos();

	public List<Producto> searchProductos(String nombre, Long stock);
	
	public List<Producto> getProductosByLocalidad(Long localidadId);
	
	public List<Producto> getProductosByLocalidadEnVenta(Long localidadId);
	
	public List<Producto> getProductosByCategoria(Categoria categoria);
	
	public List<Producto> getProductosByCategoriaEnVenta(Categoria categoria);
	
	public List<Producto> getProductosByCategoriaTienda(Categoria categoria);
	
	public List<Producto> getProductosByCategoriaTiendaEnVenta(Categoria categoria);
	
	public List<Producto> getProductosByTienda(Long tiendaId);
	
	public List<Producto> getProductosByTiendaEnVenta(Long tiendaId);
	
	public List<Producto> getProductosByTiendaEnVentaDisponibles(Long tiendaId);
	
	public List<Producto> getProductosByTiendaEnVentaAgotados(Long tiendaId);
	
	public List<Producto> getProductosByTiendasFav(Long clienteId);
	
	public List<Producto> getProductosByTiendasFavEnVenta(Long clienteId);
	
	public List<Producto> getProductosEnVenta();
	
	public List<Producto> getProductosEnVentaDisponibles();
	
	public List<Producto> getProductosEnVentaAgotados();
	
}
