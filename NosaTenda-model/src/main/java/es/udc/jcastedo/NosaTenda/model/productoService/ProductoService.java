package es.udc.jcastedo.NosaTenda.model.productoService;

import java.util.Calendar;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.model.ProductoHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.VentaHistoricoTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;


public interface ProductoService {

	public List<ProductoTO> getProductos();

	public List<ProductoTO> getProductosByLocalidad(Long localidadId);
	
	public List<ProductoTO> getProductosByLocalidadEnVenta(Long localidadId);
	
	public List<LocalidadTO> getLocalidades();
	
	public ProductoTO newProducto(String nombre, String descripcion, String imagen, Double precio, Long stock, Long tiendaId) throws InstanceNotFoundException;
	
	public ProductoTO newProducto(String nombre, String descripcion, String imagen, Double precio, Long stock, Double tax_percentage, Long tiendaId) throws InstanceNotFoundException;
	
	public ProductoTO editProducto(Long productoId, String nombre, String descripcion, String imagen, Double precio, Long stock, Long tiendaId) throws InstanceNotFoundException;
	
	public ProductoTO editProducto(Long productoId, String nombre, String descripcion, String imagen, Double precio, Long stock, Double tax_percentage, Long tiendaId) throws InstanceNotFoundException;
	
	public ProductoTO editPrecio(Long productoId, Double precio) throws InstanceNotFoundException;
	
	public ProductoTO findProductoById(Long productoId) throws InstanceNotFoundException;
	
	public List<ProductoTO> searchProductos(String nombre, Long stock);
	
	public void deleteProducto(Long productoId) throws InstanceNotFoundException;

	public Long actualizarStock(Long productoId, Long cantidad, String op) throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException;
	
	public Boolean hayStock(Long productoId, Long cantidad) throws InstanceNotFoundException;
	
	public List<CategoriaTO> getCategorias();
	
	public List<ProductoTO> getProductosByCategoria(List<Long> categoriaIds);
	
	public List<ProductoTO> getProductosByCategoriaEnVenta(List<Long> categoriaIds);
	
	public List<ProductoTO> getProductosByCategoriaTienda(Long categoriaId);
	
	public List<ProductoTO> getProductosByCategoriaTiendaEnVenta(Long categoriaId);
	
	public List<ProductoTO> getProductosByTienda(Long tiendaId);
	
	public List<ProductoTO> getProductosByTiendaFav(Long clienteId);
	
	public List<ProductoTO> getProductosByTiendaFavEnVenta(Long clienteId);
	
	public List<ProductoTO> getProductosByTiendaEnVenta(Long tiendaId);
	
	public List<ProductoTO> getProductosEnVenta();
	
	public List<ProductoTO> getProductosEnVentaAgotados();
	
	public List<ProductoTO> getProductosEnVentaYAgotados();
	
	public List<UnidadesBlock> getUnidadesVendidas();
	
	public Long getUnidadesVendidasByProducto(Long productoId);
	
	public List<UnidadesBlock> getUnidadesVendidasByProductoRangoActual(List<ProductoTO> productoTOs);
	
	public UnidadesBlock getUnidadesVendidasByProductoByRango(Long productoId, Calendar fecha_inicio, Calendar fecha_fin);
	
	public List<UnidadesBlock> getUnidadesVendidasByTiendaDesglose(Long tiendaId);
	
	public Long getUnidadesVendidasByTienda(Long tiendaId);
	
	public List<UnidadesBlock> getUnidadesVendidasByCategoriaDesglose(Long categoriaId);
	
	public Long getUnidadesVendidasByCategoria(Long categoriaId);
	
	public List<UnidadesBlock> getUnidadesVendidasByCategoriaByTiendaDesglose(Long categoriaId, Long tiendaId);
	
	public Long getUnidadesVendidasByCategoriaByTienda(Long categoriaId, Long tiendaId);
	
	public List<ProductoHistoricoTO> getHistoricoByProducto(Long productoId);
	
	public List<ProductoBlock> getFechasVentaByProducto(Long productoId);
	
	public List<VentaHistoricoTO> getHistoricoVentasByProducto(Long productoId);
	
	public ProductoTO ponerEnVenta(Long productoId, Double precio, Long stock, Calendar fecha_venta, Calendar fecha_retirada) throws InstanceNotFoundException;
}
