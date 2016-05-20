package es.udc.jcastedo.NosaTenda.model.productoService.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.model.ProductoHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.VentaHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.compra.CompraDao;
import es.udc.jcastedo.NosaTenda.model.localidad.LocalidadDao;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoHistorico;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoHistoricoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoBlock;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.model.reserva.ReservaDao;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.util.CategoriaTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.LocalidadTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.ProductoHistoricoTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.ProductoTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private LocalidadDao localidadDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private ReservaDao reservaDao;
	
	@Autowired
	private CompraDao compraDao;
	
	@Autowired
	private ProductoHistoricoDao productoHistoricoDao;
	

	@Transactional(readOnly = true)
	public List<ProductoTO> getProductos() {
		
		logger.debug("productoDao: {}", productoDao);
		
		List<Producto> productos = productoDao.getProductos();
				
		List<ProductoTO> productoTOs = ProductoTypeConversor.toProductoTO(productos);
		
		return productoTOs;
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByLocalidad(Long localidadId) {
		
		List<Producto> productos = productoDao.getProductosByLocalidad(localidadId);
				
		List<ProductoTO> productoTOs = ProductoTypeConversor.toProductoTO(productos);
		
		return productoTOs;
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByLocalidadEnVenta(Long localidadId) {
		
		List<Producto> productos = productoDao.getProductosByLocalidadEnVenta(localidadId);
				
		List<ProductoTO> productoTOs = ProductoTypeConversor.toProductoTO(productos);
		
		return productoTOs;
	}
	
	@Transactional(readOnly = true)
	public List<LocalidadTO> getLocalidades() {
		
		List<LocalidadTO> localidades = LocalidadTypeConversor.toLocalidadTO(localidadDao.getLocalidades());
		
		return localidades;
	}

	public ProductoTO newProducto(String nombre, String descripcion,
			String imagen, Double precio, Long stock, Long tiendaId)
					throws InstanceNotFoundException {
		
		Tienda tienda = tiendaDao.find(tiendaId);
		
		Producto producto = new Producto(precio, stock, tienda);
		
		productoDao.save(producto);
		
		producto.getProductoData().setNombre(nombre);
		producto.getProductoData().setDescripcion(descripcion);
		producto.getProductoData().setImagen(imagen);
		
		productoDao.save(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	public ProductoTO newProducto(String nombre, String descripcion,
			String imagen, Double precio, Long stock, Double tax_percentage, Long tiendaId)
					throws InstanceNotFoundException {
		
		Tienda tienda = tiendaDao.find(tiendaId);
		
		Producto producto = new Producto(precio, stock, tienda);
		producto.setTax_percentage(tax_percentage);
		
		productoDao.save(producto);
		
		producto.getProductoData().setNombre(nombre);
		producto.getProductoData().setDescripcion(descripcion);
		producto.getProductoData().setImagen(imagen);
		
		productoDao.save(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	public ProductoTO editProducto(Long productoId, String nombre, String descripcion,
			String imagen, Double precio, Long stock, Long tiendaId)
					throws InstanceNotFoundException {
		
		Producto producto = productoDao.find(productoId);
		
		producto.getProductoData().setNombre(nombre);
		producto.getProductoData().setDescripcion(descripcion);
		producto.getProductoData().setImagen(imagen);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setTienda(tiendaDao.find(tiendaId));
		
		productoDao.save(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	public ProductoTO editProducto(Long productoId, String nombre, String descripcion,
			String imagen, Double precio, Long stock, Double tax_percentage, Long tiendaId)
					throws InstanceNotFoundException {
		
		Producto producto = productoDao.find(productoId);
		
		producto.getProductoData().setNombre(nombre);
		producto.getProductoData().setDescripcion(descripcion);
		producto.getProductoData().setImagen(imagen);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setTax_percentage(tax_percentage);
		producto.setTienda(tiendaDao.find(tiendaId));
		
		productoDao.save(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	public ProductoTO ponerEnVenta(Long productoId, Double precio, Long stock,
			Calendar fecha_venta, Calendar fecha_retirada)
					throws InstanceNotFoundException {

		Producto producto = productoDao.find(productoId);
		
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setFecha_puesta_venta(fecha_venta);
		producto.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto);
		
		guardarHistorico(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	private void guardarHistorico(Producto producto) {
		
		ProductoHistorico productoHistorico = new ProductoHistorico();
		
		productoHistorico.setId(producto.getId());
		productoHistorico.setPrecio(producto.getPrecio());
		productoHistorico.setPrecio_noiva(producto.getPrecio_noiva());
		productoHistorico.setStock_inicial(producto.getStock());
		productoHistorico.setTax_amount(producto.getTax_amount());
		productoHistorico.setTax_percentage(producto.getTax_percentage());
		productoHistorico.setFecha_puesta_venta(producto.getFecha_puesta_venta());
		productoHistorico.setFecha_retirada(producto.getFecha_retirada());
		
		ProductoHistorico lastProductoHistorico = getLastProductoHistorico(producto.getId());
		if (lastProductoHistorico == null) {
			productoHistorico.setVersion(new Long(1));
		} else {
			productoHistorico.setVersion(lastProductoHistorico.getVersion() + 1);
		}
		
		productoHistoricoDao.save(productoHistorico);
	}
	
	private ProductoHistorico getLastProductoHistorico(Long productoId) {
		
		List<ProductoHistorico> productoHistoricos = productoHistoricoDao.getHistoricos(productoId);
		
		int numHistoricos = productoHistoricos.size();
		
		if (numHistoricos == 0) {
			
			return null;
			
		} else {
			
			ProductoHistorico lastProductoHistorico = productoHistoricos.get(numHistoricos-1);
			return lastProductoHistorico;
					
		}
	}
	
	public ProductoTO editPrecio(Long productoId, Double precio)
					throws InstanceNotFoundException {
		
		Producto producto = productoDao.find(productoId);
		
		producto.setPrecio(precio);
		
		productoDao.save(producto);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	@Transactional(readOnly = true)
	public ProductoTO findProductoById(Long productoId) throws InstanceNotFoundException {
		
		Producto producto = productoDao.find(productoId);
		
		return ProductoTypeConversor.toProductoTO(producto);
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> searchProductos(String nombre, Long stock) {
		
		List<ProductoTO> productoTOs = ProductoTypeConversor.toProductoTO(
				productoDao.searchProductos(nombre, stock));
		
		return productoTOs;
	}
	
	public void deleteProducto(Long productoId)
			throws InstanceNotFoundException {
		
		productoDao.remove(productoId);
		
	}
	
	public Long actualizarStock(Long productoId, Long cantidad, String op)
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		Producto producto = productoDao.find(productoId);
		
		if(op.equals("up")) {
			producto.setStock(producto.getStock()+cantidad);
			productoDao.save(producto);
			return producto.getStock();
		}
		if(op.equals("down")) {
			
			if(producto.getStock() < cantidad) throw new InsufficientStockException(producto, Producto.class.getName());
			
			producto.setStock(producto.getStock()-cantidad);
			productoDao.save(producto);
			return producto.getStock();
		}
		
		throw new BadFormatRequestException(producto, Producto.class.getName());
	}
	
	@Transactional(readOnly = true)
	public Boolean hayStock(Long productoId, Long cantidad)
			throws InstanceNotFoundException {
		
		Producto producto = productoDao.find(productoId);
		
		if(producto.getStock() >= cantidad)
			return true;
		else return false;
	}
	
	@Transactional(readOnly = true)
	public List<CategoriaTO> getCategorias() {
		
		Set<CategoriaTO> categoriaSet = CategoriaTypeConversor.toCategoriaTO(categoriaDao.getCategorias());
		
		List<CategoriaTO> categoriaTOs = new ArrayList<CategoriaTO>(categoriaSet);
		
		return categoriaTOs;
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByCategoria(List<Long> categoriaIds) {
		
		// we use set as an easy way to eliminate duplicates
		Set<Producto> productosFinalSet = new HashSet<Producto>();
		
		for (Long id: categoriaIds) {
			try {
				
				Categoria categoria = categoriaDao.find(id);
				
				Set<Producto> productosSet = new HashSet<Producto>(productoDao.getProductosByCategoria(categoria));
				
				productosFinalSet.addAll(productosSet);
				
			} catch (InstanceNotFoundException e) {
				// nothing, we ignore it, just pass to the next id
			}
		}
		
		List<Producto> productosFinalList = new ArrayList<Producto>(productosFinalSet);
		
		return ProductoTypeConversor.toProductoTO(productosFinalList);
	}
	
	public class CustomComparator implements Comparator<ProductoTO> {

		public int compare(ProductoTO o1, ProductoTO o2) {
			return o1.getFecha_retirada().compareTo(o2.getFecha_retirada());
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByCategoriaEnVenta(List<Long> categoriaIds) {
		
		// we use set as an easy way to eliminate duplicates
		Set<Producto> productosFinalSet = new HashSet<Producto>();
		
		for (Long id: categoriaIds) {
			try {
				
				Categoria categoria = categoriaDao.find(id);
				
				Set<Producto> productosSet = new HashSet<Producto>(productoDao.getProductosByCategoriaEnVenta(categoria));
				
				productosFinalSet.addAll(productosSet);
				
			} catch (InstanceNotFoundException e) {
				// nothing, we ignore it, just pass to the next id
			}
		}
		
		List<Producto> productosFinalList = new ArrayList<Producto>(productosFinalSet);
		
		List<ProductoTO> productoTOsOrderedList = ProductoTypeConversor.toProductoTO(productosFinalList);
		
		Collections.sort(productoTOsOrderedList, new CustomComparator());
		
		return productoTOsOrderedList;
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByCategoriaTienda(Long categoriaId) {
		
		List<ProductoTO> productos = new ArrayList<ProductoTO>();
		
		try {
			
			Categoria categoria = categoriaDao.find(categoriaId);
			productos = ProductoTypeConversor.toProductoTO(productoDao.getProductosByCategoriaTienda(categoria));
			
		} catch (InstanceNotFoundException e) {
			return productos;
		}
		
		return productos;
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByCategoriaTiendaEnVenta(Long categoriaId) {
		
		List<ProductoTO> productos = new ArrayList<ProductoTO>();
		
		try {
			
			Categoria categoria = categoriaDao.find(categoriaId);
			productos = ProductoTypeConversor.toProductoTO(productoDao.getProductosByCategoriaTiendaEnVenta(categoria));
			
		} catch (InstanceNotFoundException e) {
			// si la categoria no existe, devolvemos una lista vacía
			return productos;
		}
		
		return productos;
	}

	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByTienda(Long tiendaId) {

		List<Producto> productos = productoDao.getProductosByTienda(tiendaId);
		
		return ProductoTypeConversor.toProductoTO(productos);
	}

	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByTiendaFav(Long clienteId) {
		
		List<Producto> productos = productoDao.getProductosByTiendasFav(clienteId);
		
		return ProductoTypeConversor.toProductoTO(productos);
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByTiendaFavEnVenta(Long clienteId) {
		
		List<Producto> productos = productoDao.getProductosByTiendasFavEnVenta(clienteId);
		
		return ProductoTypeConversor.toProductoTO(productos);
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosByTiendaEnVenta(Long tiendaId) {

		List<Producto> productos = productoDao.getProductosByTiendaEnVenta(tiendaId);
		
		return ProductoTypeConversor.toProductoTO(productos);
	}

	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosEnVenta() {
		
		List<Producto> productos = productoDao.getProductosEnVentaDisponibles();
		
		return ProductoTypeConversor.toProductoTO(productos);
	}

	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosEnVentaAgotados() {
		
		List<Producto> productos = productoDao.getProductosEnVentaAgotados();
		
		return ProductoTypeConversor.toProductoTO(productos);
	}
	
	@Transactional(readOnly = true)
	public List<ProductoTO> getProductosEnVentaYAgotados() {
		
		List<Producto> productos = productoDao.getProductosEnVenta();
		
		return ProductoTypeConversor.toProductoTO(productos);
	}
	
	@Transactional(readOnly = true)
	public List<UnidadesBlock> getUnidadesVendidas() {
		
		List<UnidadesBlock> unidadesVendidas = new ArrayList<UnidadesBlock>();
		
		// añadimos las unidades vendidas en forma de reservas entregadas
		unidadesVendidas.addAll(reservaDao.getVentas());
		
		// añadimos las unidades vendidas en forma de compras directas
		// comprobamos si ya existe un objecto con ese id
		// si ya existe una entrada con ese id, sumamos las unidades
		for (UnidadesBlock unidad: compraDao.getVentas()) {
			
			int index = findId(unidadesVendidas, unidad.getProductoId());
			if (index == -1) {
				unidadesVendidas.add(unidad);
			} else {
				Long unidadesTotales = unidad.getUnidadesVendidas() + unidadesVendidas.get(index).getUnidadesVendidas();
				unidadesVendidas.get(index).setUnidadesVendidas(unidadesTotales);
			}
		}
		
		return unidadesVendidas;
	}

	private int findId(List<UnidadesBlock> unidadesVendidas, Long productoId) {
		
		for (UnidadesBlock unidad: unidadesVendidas) {
			if (unidad.getProductoId().equals(productoId)) {
				return unidadesVendidas.indexOf(unidad);
			}
		}
		return -1;
	}

	@Transactional(readOnly = true)
	public Long getUnidadesVendidasByProducto(Long productoId) {
		
		Long unidadesVendidas = reservaDao.getVentasByProductoId(productoId) + compraDao.getVentasByProductoId(productoId);
		
		return unidadesVendidas;
	}
	
	@Transactional(readOnly = true)
	public List<UnidadesBlock> getUnidadesVendidasByProductoRangoActual(List<ProductoTO> productoTOs) {
		
		Long ventas;
		
		List<UnidadesBlock> unidadesBlock = new ArrayList<UnidadesBlock>();
		
		for (ProductoTO productoTO: productoTOs) {
			
			ventas = reservaDao.getVentasByProductoIdByRango(
						productoTO.getId(), productoTO.getFecha_puesta_venta(), productoTO.getFecha_retirada())
					+ compraDao.getVentasByProductoIdByRango(
						productoTO.getId(), productoTO.getFecha_puesta_venta(), productoTO.getFecha_retirada());
			
			UnidadesBlock unidadBlock = new UnidadesBlock(productoTO.getId(), ventas);
			
			unidadesBlock.add(unidadBlock);
		}
		
		return unidadesBlock;
	}
	
	@Transactional(readOnly = true)
	public UnidadesBlock getUnidadesVendidasByProductoByRango(Long productoId, Calendar fecha_inicio, Calendar fecha_fin) {
		
		Long ventas = reservaDao.getVentasByProductoIdByRango(
						productoId, fecha_inicio, fecha_fin)
					+ compraDao.getVentasByProductoIdByRango(
						productoId, fecha_inicio, fecha_fin);
		
		UnidadesBlock unidadesBlock = new UnidadesBlock(productoId, ventas);
		
		return unidadesBlock;
	}
	
	@Transactional(readOnly = true)
	public List<UnidadesBlock> getUnidadesVendidasByTiendaDesglose(Long tiendaId) {
		
		List<UnidadesBlock> unidadesVendidas = new ArrayList<UnidadesBlock>();
		
		// añadimos las unidades vendidas en forma de reservas entregadas
		unidadesVendidas.addAll(reservaDao.getVentasByTiendaIdDesglose(tiendaId));
		
		// añadimos las unidades vendidas en forma de compras directas
		// comprobamos si ya existe un objecto con ese id
		// si ya existe una entrada con ese id, sumamos las unidades
		for (UnidadesBlock unidad: compraDao.getVentasByTiendaIdDesglose(tiendaId)) {
			
			int index = findId(unidadesVendidas, unidad.getProductoId());
			if (index == -1) {
				unidadesVendidas.add(unidad);
			} else {
				Long unidadesTotales = unidad.getUnidadesVendidas() + unidadesVendidas.get(index).getUnidadesVendidas();
				unidadesVendidas.get(index).setUnidadesVendidas(unidadesTotales);
			}
		}
		
		return unidadesVendidas;
	}

	@Transactional(readOnly = true)
	public Long getUnidadesVendidasByTienda(Long tiendaId) {
		
		Long unidadesVendidas = reservaDao.getVentasByTiendaId(tiendaId) + compraDao.getVentasByTiendaId(tiendaId);
		
		return unidadesVendidas;
	}
	
	@Transactional(readOnly = true)
	public List<UnidadesBlock> getUnidadesVendidasByCategoriaDesglose(Long categoriaId) {
		
		List<UnidadesBlock> unidadesVendidas = new ArrayList<UnidadesBlock>();
		
		// añadimos las unidades vendidas en forma de reservas entregadas
		unidadesVendidas.addAll(reservaDao.getVentasByCategoriaIdDesglose(categoriaId));
		
		// añadimos las unidades vendidas en forma de compras directas
		// comprobamos si ya existe un objecto con ese id
		// si ya existe una entrada con ese id, sumamos las unidades
		for (UnidadesBlock unidad: compraDao.getVentasByCategoriaIdDesglose(categoriaId)) {
			
			int index = findId(unidadesVendidas, unidad.getProductoId());
			if (index == -1) {
				unidadesVendidas.add(unidad);
			} else {
				Long unidadesTotales = unidad.getUnidadesVendidas() + unidadesVendidas.get(index).getUnidadesVendidas();
				unidadesVendidas.get(index).setUnidadesVendidas(unidadesTotales);
			}
		}
		
		return unidadesVendidas;
	}
	
	@Transactional(readOnly = true)
	public Long getUnidadesVendidasByCategoria(Long categoriaId) {
		
		Long unidadesVendidas = reservaDao.getVentasByCategoriaId(categoriaId) + compraDao.getVentasByCategoriaId(categoriaId);
		
		return unidadesVendidas;
	}
	
	@Transactional(readOnly = true)
	public List<UnidadesBlock> getUnidadesVendidasByCategoriaByTiendaDesglose(Long categoriaId, Long tiendaId) {
		
		List<UnidadesBlock> unidadesVendidas = new ArrayList<UnidadesBlock>();
		
		// añadimos las unidades vendidas en forma de reservas entregadas
		unidadesVendidas.addAll(reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoriaId, tiendaId));
		
		// añadimos las unidades vendidas en forma de compras directas
		// comprobamos si ya existe un objecto con ese id
		// si ya existe una entrada con ese id, sumamos las unidades
		for (UnidadesBlock unidad: compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoriaId, tiendaId)) {
			
			int index = findId(unidadesVendidas, unidad.getProductoId());
			if (index == -1) {
				unidadesVendidas.add(unidad);
			} else {
				Long unidadesTotales = unidad.getUnidadesVendidas() + unidadesVendidas.get(index).getUnidadesVendidas();
				unidadesVendidas.get(index).setUnidadesVendidas(unidadesTotales);
			}
		}
		
		return unidadesVendidas;
	}
	
	@Transactional(readOnly = true)
	public Long getUnidadesVendidasByCategoriaByTienda(Long categoriaId, Long tiendaId) {
		
		Long unidadesVendidas = reservaDao.getVentasByCategoriaIdByTiendaId(categoriaId, tiendaId) + compraDao.getVentasByCategoriaIdByTiendaId(categoriaId, tiendaId);
		
		return unidadesVendidas;
	}

	@Transactional(readOnly = true)
	public List<ProductoHistoricoTO> getHistoricoByProducto(Long productoId) {
			//throws InstanceNotFoundException {
		
		//Producto producto = productoDao.find(productoId);
		
		//Set<ProductoHistoricoTO> productoHistoricoTOSet = ProductoHistoricoTypeConversor.toProductoHistoricoTO(producto.getHistorico());
		
		//List<ProductoHistoricoTO> productoHistoricoTOs = new ArrayList<ProductoHistoricoTO>(productoHistoricoTOSet);
		
		List<ProductoHistorico> productoHistoricos = productoHistoricoDao.getHistoricos(productoId);
		
		List<ProductoHistoricoTO> productoHistoricoTOs = ProductoHistoricoTypeConversor.toProductoHistoricoTO(productoHistoricos);
		
		return productoHistoricoTOs;
	}

	@Transactional(readOnly = true)
	public List<ProductoBlock> getFechasVentaByProducto(Long productoId) {
		
		List<ProductoBlock> productoBlocks = productoHistoricoDao.getFechasVentaByProducto(productoId);
		
		return productoBlocks;
	}

	@Transactional(readOnly = true)
	public List<VentaHistoricoTO> getHistoricoVentasByProducto(Long productoId) {
		
		List<VentaHistoricoTO> ventaHistoricos = new ArrayList<VentaHistoricoTO>();
		
		Long unidadesVendidasByRango;
		
		List<ProductoHistoricoTO> productoHistoricos = getHistoricoByProducto(productoId);
		
		for (ProductoHistoricoTO productoHistorico: productoHistoricos) {
			
			unidadesVendidasByRango = getUnidadesVendidasByProductoByRango(
					productoId, productoHistorico.getFecha_puesta_venta(), productoHistorico.getFecha_retirada())
					.getUnidadesVendidas();
			
			VentaHistoricoTO ventaHistorico = new VentaHistoricoTO();
			ventaHistorico.setId(productoHistorico.getId());
			ventaHistorico.setVersion(productoHistorico.getVersion());
			ventaHistorico.setFecha_puesta_venta(productoHistorico.getFecha_puesta_venta());
			ventaHistorico.setFecha_retirada(productoHistorico.getFecha_retirada());
			ventaHistorico.setPrecio(productoHistorico.getPrecio());
			ventaHistorico.setStock_inicial(productoHistorico.getStock_inicial());
			ventaHistorico.setVentas(unidadesVendidasByRango);
			
			ventaHistoricos.add(ventaHistorico);
			
		}
		
		return ventaHistoricos;
	}
	
}
