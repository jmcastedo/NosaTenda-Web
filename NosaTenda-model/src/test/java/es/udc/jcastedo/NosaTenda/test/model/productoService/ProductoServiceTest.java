package es.udc.jcastedo.NosaTenda.test.model.productoService;

import static es.udc.jcastedo.NosaTenda.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.jcastedo.NosaTenda.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.model.ProductoHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.model.VentaHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.compraService.CompraService;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.localidad.LocalidadDao;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoData;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoHistorico;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoHistoricoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoBlock;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva;
import es.udc.jcastedo.NosaTenda.model.reserva.ReservaDao;
import es.udc.jcastedo.NosaTenda.model.reservaService.ReservaService;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.tiendaService.TiendaService;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.model.util.CategoriaTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.LocalidadTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.ProductoHistoricoTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.ProductoTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.TiendaTypeConversor;
import es.udc.jcastedo.NosaTenda.test.model.util.DbUtil;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE})
//prueba base de datos de produccion
//@ContextConfiguration (locations = {SPRING_CONFIG_FILE})
@Transactional
public class ProductoServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoServiceTest.class);
	
	private final Long NON_EXISTENT_ID = Long.valueOf(-1);

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private TiendaService tiendaService;
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private LocalidadDao localidadDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ReservaDao reservaDao;
	
	@Autowired
	private ProductoHistoricoDao productoHistoricoDao;
	
	@BeforeClass
	public static void populateDb() throws Throwable {
		
		logger.info("populateDb: llamando a DbUtil");
		DbUtil.populateDb();
	}
	
	@AfterClass
	public static void cleanDb() throws Throwable {
		
		logger.info("cleanDb: llamando a DbUtil");
		DbUtil.cleanDb();
	}

	@Test
	public void testVoid() 
			throws InstanceNotFoundException {
		
		logger.debug("testVoid");
		
		Long testTiendaId = DbUtil.getTestTiendaId1();
		
		TiendaTO testTienda = tiendaService.getTiendaById(testTiendaId);
		
		String expectedString = "tienda1";
		
		assertEquals(testTienda.getNombre() , expectedString);
	}
	
	@Test
	public void testGetProductosCount() {
		
		logger.debug("testGetProductosCount");
		
		Integer count = productoService.getProductos().size();
		
		Integer expectedInt = 3;
		
		assertEquals(count, expectedInt);
	}
	
	@Test
	public void testGetProductos() 
			throws InstanceNotFoundException {
		
		logger.debug("testGetProductos");
		
		List<Producto> listExpected = new ArrayList<Producto>();
		
		Producto producto1 = new Producto(new Double(1), new Long(100), 
				TiendaTypeConversor.toTienda(tiendaService.getTiendaById(DbUtil.getTestTiendaId1())));
		Producto producto2 = new Producto(new Double(2), new Long(100),
				TiendaTypeConversor.toTienda(tiendaService.getTiendaById(DbUtil.getTestTiendaId1())));
		Producto producto3 = new Producto(new Double(3), new Long(100),
				TiendaTypeConversor.toTienda(tiendaService.getTiendaById(DbUtil.getTestTiendaId1())));
		
		productoDao.save(producto1);
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		producto1.getProductoData().setNombre("producto1test");
		producto1.getProductoData().setDescripcion("descripcion1test");
		producto1.getProductoData().setImagen("uri1test");
		
		producto2.getProductoData().setNombre("producto2test");
		producto2.getProductoData().setDescripcion("descripcion2test");
		producto2.getProductoData().setImagen("uri2test");
		
		producto3.getProductoData().setNombre("producto3test");
		producto3.getProductoData().setDescripcion("descripcion3test");
		producto3.getProductoData().setImagen("uri3test");
		
		productoDao.save(producto1);
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		listExpected.add(productoDao.find(DbUtil.getTestProductoId1()));
		listExpected.add(productoDao.find(DbUtil.getTestProductoId2()));
		listExpected.add(productoDao.find(DbUtil.getTestProductoId3()));
		listExpected.add(producto1);
		listExpected.add(producto2);
		listExpected.add(producto3);
		
		List<ProductoTO> listExpectedTO = ProductoTypeConversor.toProductoTO(listExpected);
		
		List<ProductoTO> listProductoTO = productoService.getProductos();
		
		assertEquals(listExpectedTO, listProductoTO);

	}
		
	@Test
	public void testGetProductosEmptyList()
			throws InstanceNotFoundException {
		
		logger.debug("testGetProductosEmpyList");
		
		productoDao.remove(DbUtil.getTestProductoId1());
		productoDao.remove(DbUtil.getTestProductoId2());
		productoDao.remove(DbUtil.getTestProductoId3());
		
		List<ProductoTO> listProductoTOs = productoService.getProductos();
		
		assertTrue(listProductoTOs.isEmpty());
	}
	
	@Test
	public void testNewProducto()
			throws InstanceNotFoundException {
		
		logger.debug("testNewProducto");
		
		Double testPrecio = new Double(5);
		Long testStock = new Long(25);
		Long testTiendaId1 = DbUtil.getTestTiendaId1();
		
		ProductoTO productoTO = productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId1);
		
		Producto producto = productoDao.find(productoTO.getId());
		
		ProductoTO productoExpectedTO = ProductoTypeConversor.toProductoTO(producto);
		
		assertEquals(producto.getProductoData().getNombre(), "nombre_producto");
		assertEquals(producto.getProductoData().getDescripcion(), "descripcion_producto");
		assertEquals(producto.getProductoData().getImagen(), "URL_producto");
		assertEquals(producto.getPrecio(), testPrecio);
		assertEquals(producto.getStock(), testStock);
		assertEquals(producto.getTienda().getId(), testTiendaId1);
		
		assertEquals(productoTO, productoExpectedTO);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testNewProductoInstanceNotFound()
			throws InstanceNotFoundException {
		
		logger.debug("testNewProductoInstanceNotFound");
		
		Double testPrecio = new Double(5);
		Long testStock = new Long(25);
		// creamos un tienda id inexistente
		Long testTiendaId1 = NON_EXISTENT_ID;
		
		ProductoTO productoTO = productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId1);
		
		Producto producto = productoDao.find(productoTO.getId());
		
		assertEquals(producto.getProductoData().getNombre(), "nombre_producto");
		assertEquals(producto.getProductoData().getDescripcion(), "descripcion_producto");
		assertEquals(producto.getProductoData().getImagen(), "URL_producto");
		assertEquals(producto.getPrecio(), testPrecio);
		assertEquals(producto.getStock(), testStock);
		assertEquals(producto.getTienda().getId(), testTiendaId1);
	}
	
	@Test
	public void testEditProducto() throws InstanceNotFoundException {
		
		logger.debug("testEditProducto");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId1());
		
		String nuevo_nombre = "nombre_producto_diferente";
		String nueva_descripcion = "descripcion_producto_diferente";
		String nueva_imagen = "imagen_producto_diferente";
		Double nuevo_precio = new Double(1400);
		Long nuevo_stock = new Long(5000);
		Tienda nueva_tienda = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		producto.getProductoData().setNombre(nuevo_nombre);
		producto.getProductoData().setDescripcion(nueva_descripcion);
		producto.getProductoData().setImagen(nueva_imagen);
		producto.setPrecio(nuevo_precio);
		producto.setStock(nuevo_stock);
		producto.setTienda(nueva_tienda);
		
		ProductoTO edited_productoTO = productoService.editProducto(
				DbUtil.getTestProductoId1(), nuevo_nombre, nueva_descripcion,
				nueva_imagen, nuevo_precio, nuevo_stock, DbUtil.getTestTiendaId2());
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), edited_productoTO);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testEditProductoNotFoundProducto() throws InstanceNotFoundException {
		
		logger.debug("testEditProductoNotFoundProducto");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId1());
		
		String nuevo_nombre = "nombre_producto_diferente";
		String nueva_descripcion = "descripcion_producto_diferente";
		String nueva_imagen = "imagen_producto_diferente";
		Double nuevo_precio = new Double(1400);
		Long nuevo_stock = new Long(5000);
		Tienda nueva_tienda = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		producto.getProductoData().setNombre(nuevo_nombre);
		producto.getProductoData().setDescripcion(nueva_descripcion);
		producto.getProductoData().setImagen(nueva_imagen);
		producto.setPrecio(nuevo_precio);
		producto.setStock(nuevo_stock);
		producto.setTienda(nueva_tienda);
		
		ProductoTO edited_productoTO = productoService.editProducto(
				NON_EXISTENT_ID, nuevo_nombre, nueva_descripcion,
				nueva_imagen, nuevo_precio, nuevo_stock, DbUtil.getTestTiendaId2());
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), edited_productoTO);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testEditProductoNotFoundTienda() throws InstanceNotFoundException {
		
		logger.debug("testEditProductoNotFoundTienda");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId1());
		
		String nuevo_nombre = "nombre_producto_diferente";
		String nueva_descripcion = "descripcion_producto_diferente";
		String nueva_imagen = "imagen_producto_diferente";
		Double nuevo_precio = new Double(1400);
		Long nuevo_stock = new Long(5000);
		Tienda nueva_tienda = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		producto.getProductoData().setNombre(nuevo_nombre);
		producto.getProductoData().setDescripcion(nueva_descripcion);
		producto.getProductoData().setImagen(nueva_imagen);
		producto.setPrecio(nuevo_precio);
		producto.setStock(nuevo_stock);
		producto.setTienda(nueva_tienda);
		
		ProductoTO edited_productoTO = productoService.editProducto(
				DbUtil.getTestProductoId1(), nuevo_nombre, nueva_descripcion,
				nueva_imagen, nuevo_precio, nuevo_stock, NON_EXISTENT_ID);
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), edited_productoTO);	
	}
	
	@Test
	public void testEditPrecio() throws InstanceNotFoundException {
		
		logger.debug("testEditPrecio");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId1());
		
		Double nuevo_precio = new Double(1400);
		
		producto.setPrecio(nuevo_precio);
		
		ProductoTO edited_productoTO = productoService.editPrecio(
				DbUtil.getTestProductoId1(), nuevo_precio);
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), edited_productoTO);
		
	}
	
	@Test
	public void testPonerEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testPonerEnVenta");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId1());
		
		Double nuevo_precio = new Double(700);
		Long nuevo_stock = new Long(255);
		Calendar nueva_fecha_venta = Calendar.getInstance();
		nueva_fecha_venta.add(Calendar.DAY_OF_MONTH, 3);
		Calendar nueva_fecha_retirada = Calendar.getInstance();
		nueva_fecha_retirada.add(Calendar.DAY_OF_MONTH, 6);
		
		ProductoTO productoTO = productoService.ponerEnVenta(
				producto.getId(), nuevo_precio, nuevo_stock, nueva_fecha_venta, nueva_fecha_retirada);
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), productoTO);
		
		List<ProductoHistorico> productosHistoricos = productoHistoricoDao.getHistoricos(producto.getId());
		assertTrue(productosHistoricos.size() == 1);
		
		ProductoHistorico historico1 = productosHistoricos.get(0);
		assertEquals(historico1.getId(), producto.getId());
		assertEquals(historico1.getVersion(), new Long(1));
		assertEquals(historico1.getPrecio(), nuevo_precio);
		assertEquals(historico1.getStock_inicial(), nuevo_stock);
		assertEquals(historico1.getFecha_puesta_venta(), nueva_fecha_venta);
		assertEquals(historico1.getFecha_retirada(), nueva_fecha_retirada);
		
		Double nuevo_precio2 = new Double(800);
		Long nuevo_stock2 = new Long(555);
		Calendar nueva_fecha_venta2 = Calendar.getInstance();
		nueva_fecha_venta2.add(Calendar.DAY_OF_MONTH, 9);
		Calendar nueva_fecha_retirada2 = Calendar.getInstance();
		nueva_fecha_retirada2.add(Calendar.DAY_OF_MONTH, 12);
		
		ProductoTO productoTO2 = productoService.ponerEnVenta(
				producto.getId(), nuevo_precio2, nuevo_stock2, nueva_fecha_venta2, nueva_fecha_retirada2);
		
		assertEquals(ProductoTypeConversor.toProductoTO(producto), productoTO2);
		
		productosHistoricos = productoHistoricoDao.getHistoricos(producto.getId());
		assertTrue(productosHistoricos.size() == 2);
		
		ProductoHistorico historico2 = productosHistoricos.get(1);
		assertEquals(historico2.getId(), producto.getId());
		assertEquals(historico2.getVersion(), new Long(2));
		assertEquals(historico2.getPrecio(), nuevo_precio2);
		assertEquals(historico2.getStock_inicial(), nuevo_stock2);
		assertEquals(historico2.getFecha_puesta_venta(), nueva_fecha_venta2);
		assertEquals(historico2.getFecha_retirada(), nueva_fecha_retirada2);
	}
	
	@Test
	public void testFindProductoById() throws InstanceNotFoundException {
		
		logger.debug("testFindProductoById");
		
		ProductoTO productoTOexpected = ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId1()));
		
		ProductoTO productoTO = productoService.findProductoById(DbUtil.getTestProductoId1());
		
		assertEquals(productoTOexpected, productoTO);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testFindProductoByIdNotFoundProducto() throws InstanceNotFoundException {
		
		logger.debug("testFindProductoByIdNotFoundProducto");
		
		ProductoTO productoTOexpected = ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId1()));
		
		ProductoTO productoTO = productoService.findProductoById(NON_EXISTENT_ID);
		
		assertEquals(productoTOexpected, productoTO);
	}
	
	@Test
	public void testSearchProductos() {
		
		logger.debug("testSearchProductos");
		
		// vamos a usar los tres productos definidos en DbUtil
		List<ProductoTO> productoTOs = productoService.searchProductos("pro", null);
		assertEquals(productoTOs.size(), 3);
		
		productoTOs = productoService.searchProductos(null, new Long(0));
		assertEquals(productoTOs.size(), 1);
		assertEquals(productoTOs.get(0).getId(), DbUtil.getTestProductoId1());

		productoTOs = productoService.searchProductos("to3", new Long(99));
		assertEquals(productoTOs.size(), 0);

		productoTOs = productoService.searchProductos("to3", new Long(100));
		assertEquals(productoTOs.size(), 1);
		assertEquals(productoTOs.get(0).getId(), DbUtil.getTestProductoId3());
		
	}
	
	@Test
	public void testDeleteProducto() throws InstanceNotFoundException {
		
		logger.debug("testDeleteProducto");
		
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		Producto productoConCategoria = productoDao.find(DbUtil.getTestProductoId1());
		productoConCategoria.getCategorias().add(categoria);
		productoDao.save(productoConCategoria);
		
		categoria.getProductos().add(productoConCategoria);
		categoriaDao.save(categoria);
		
		productoService.deleteProducto(DbUtil.getTestProductoId1());
		productoService.deleteProducto(DbUtil.getTestProductoId2());
		productoService.deleteProducto(DbUtil.getTestProductoId3());
		
		List<ProductoTO> listProductoTOs = productoService.getProductos();
		
		assertTrue(listProductoTOs.isEmpty());
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testDeleteProductoInstanceNotFound() throws InstanceNotFoundException {
		
		logger.debug("testDeleteProductoInstanceNotFound");
		
		productoService.deleteProducto(DbUtil.getTestProductoId1());
		// intentamos borrar otra vez el mismo producto
		productoService.deleteProducto(DbUtil.getTestProductoId1());
		productoService.deleteProducto(DbUtil.getTestProductoId3());
		
		List<ProductoTO> listProductoTOs = productoService.getProductos();
		
		assertTrue(listProductoTOs.isEmpty());
	}
	
	@Test
	public void testProductoDaoHibernateSearch() {
		
		logger.debug("testProductoDaoHibernateSearch");
		
		// vamos a usar los tres productos definidos en DbUtil
		List<Producto> productos = productoDao.searchProductos("pro", null);
		assertEquals(productos.size(), 3);
		
		productos = productoDao.searchProductos(null, new Long(0));
		assertEquals(productos.size(), 1);
		assertEquals(productos.get(0).getId(), DbUtil.getTestProductoId1());

		productos = productoDao.searchProductos("to3", new Long(99));
		assertEquals(productos.size(), 0);

		productos = productoDao.searchProductos("to3", new Long(100));
		assertEquals(productos.size(), 1);
		assertEquals(productos.get(0).getId(), DbUtil.getTestProductoId3());
		
	}
	
	@Test
	public void testActualizarStockUp()
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testActualizarStockUp");
		
		Long productoId = DbUtil.getTestProductoId2();
		
		Producto producto = productoDao.find(productoId);
		
		Long stockOriginal = producto.getStock();
		
		Long cantidadUp = new Long(2);
		
		Long stock = productoService.actualizarStock(productoId, cantidadUp, "up");
		
		Long stockExpected = stockOriginal + cantidadUp;
		
		assertEquals(stock, stockExpected);
	}
	
	@Test
	public void testActualizarStockDown()
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testActualizarStockDown");
		
		Long productoId = DbUtil.getTestProductoId2();
		
		Producto producto = productoDao.find(productoId);
		
		Long stockOriginal = producto.getStock();
		
		Long cantidadDown = new Long(2);
		
		Long stock = productoService.actualizarStock(productoId, cantidadDown, "down");
		
		Long stockExpected = stockOriginal - cantidadDown;
		
		assertEquals(stock, stockExpected);
	}
	
	@Test(expected = InsufficientStockException.class)
	public void testActualizarStockInsufficientStock()
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testActualizarStockInsufficientStock");
		
		// stock de testProducto1 = 0
		Long productoId = DbUtil.getTestProductoId1();
		
		Producto producto = productoDao.find(productoId);
		
		Long stockOriginal = producto.getStock();
		
		Long cantidadDown = new Long(2);
		
		Long stock = productoService.actualizarStock(productoId, cantidadDown, "down");
		
		Long stockExpected = stockOriginal - cantidadDown;
		
		assertEquals(stock, stockExpected);
	}
	
	@Test(expected = BadFormatRequestException.class)
	public void testActualizarStockBadRequest()
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testActualizarStockBadRequest");
		
		Long productoId = DbUtil.getTestProductoId2();
		
		Producto producto = productoDao.find(productoId);
		
		Long stockOriginal = producto.getStock();
		
		Long cantidadDown = new Long(2);
		
		Long stock = productoService.actualizarStock(productoId, cantidadDown, "wrong");
		
		Long stockExpected = stockOriginal - cantidadDown;
		
		assertEquals(stock, stockExpected);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testActualizarStockInstanceNotFound()
			throws InsufficientStockException, InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testActualizarStockInstanceNotFound");
		
		Long productoId = NON_EXISTENT_ID;
		
		Producto producto = productoDao.find(productoId);
		
		Long stockOriginal = producto.getStock();
		
		Long cantidadDown = new Long(2);
		
		Long stock = productoService.actualizarStock(productoId, cantidadDown, "up");
		
		Long stockExpected = stockOriginal - cantidadDown;
		
		assertEquals(stock, stockExpected);
	}
	
	@Test
	public void testHayStock() throws InstanceNotFoundException {
		
		logger.debug("testHayStock");
		
		Long cantidadRequerida1 = new Long(0);
		Long cantidadRequerida2 = new Long(1);
		Long cantidadRequerida3 = new Long(49);
		Long cantidadRequerida4 = new Long(51);
		
		// stock = 0
		Long productoId1 = DbUtil.getTestProductoId1();
		// stock = 50
		Long productoId2 = DbUtil.getTestProductoId2();
		
		assertTrue(productoService.hayStock(productoId1, cantidadRequerida1));  // 0 = 0 => true
		assertTrue(!productoService.hayStock(productoId1, cantidadRequerida2)); // 0 < 1 => false
		assertTrue(productoService.hayStock(productoId2, cantidadRequerida3));  // 50 > 49 => true
		assertTrue(!productoService.hayStock(productoId2, cantidadRequerida4)); // 50 < 51 => false
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testHayStockInstanceNotFound() throws InstanceNotFoundException {
		
		logger.debug("testHayStockInstanceNotFound");
		
		Long cantidadRequerida1 = new Long(0);
		Long cantidadRequerida2 = new Long(1);
		Long cantidadRequerida3 = new Long(49);
		Long cantidadRequerida4 = new Long(51);
		
		// stock = 0
		Long productoId1 = DbUtil.getTestProductoId1();
		// stock = 50
		Long productoId2 = NON_EXISTENT_ID;
		
		assertTrue(productoService.hayStock(productoId1, cantidadRequerida1));  // 0 = 0 => true
		assertTrue(!productoService.hayStock(productoId1, cantidadRequerida2)); // 0 < 1 => false
		assertTrue(productoService.hayStock(productoId2, cantidadRequerida3));  // 50 > 49 => true
		assertTrue(!productoService.hayStock(productoId2, cantidadRequerida4)); // 50 < 51 => false
	}
	
	@Test
	public void testGetLocalidadesLocalidadDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testGetLocalidadesLocalidadDaoHibernate");
		
		Localidad localidad = localidadDao.find(DbUtil.getTestLocalidadId());
		
		Localidad localidad2 = new Localidad("Lugo");
		
		localidadDao.save(localidad2);
		
		List<Localidad> localidadListExpected = new ArrayList<Localidad>();
		localidadListExpected.add(localidad);
		localidadListExpected.add(localidad2);
		
		List<Localidad> localidadList = localidadDao.getLocalidades();
		
		assertEquals(localidadListExpected, localidadList);
	}
	
	@Test
	public void testGetProductosByLocalidadProductoDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByLocalidadProductoDaoHibernate");
		
		Localidad localidadTest = new Localidad("Lugo");
		
		localidadDao.save(localidadTest);
		
		Tienda tiendaTest = new Tienda("tienda1test", "direccion1test", "00000003", "tienda3@test.com", "539295939", localidadTest);
		
		tiendaDao.save(tiendaTest);
		
		Long testTiendaId = tiendaTest.getId();
		
		// creamos un par de productos en la nueva tienda en la nueva localidad Lugo
		Double testPrecio = new Double(5);
		Long testStock = new Long(25);
		
		Producto producto = ProductoTypeConversor.toProducto(productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId));
		
		Producto producto2 = ProductoTypeConversor.toProducto(productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId));
		
		List<Producto> productosExpected = new ArrayList<Producto>();
		productosExpected.add(producto);
		productosExpected.add(producto2);
		
		List<Producto> productosPorLocalidad = productoDao.getProductosByLocalidad(localidadTest.getId());
		
		assertTrue(productosPorLocalidad.size()==2);
		assertEquals(productosExpected.get(0).getId(), productosPorLocalidad.get(0).getId());
		assertEquals(productosExpected.get(1).getId(), productosPorLocalidad.get(1).getId());
		
		List<Producto> productosPorLocalidad2 = productoDao.getProductosByLocalidad(DbUtil.getTestLocalidadId());
		
		assertTrue(productosPorLocalidad2.size()==3);
		assertEquals(DbUtil.getTestProductoId1(), productosPorLocalidad2.get(0).getId());
		assertEquals(DbUtil.getTestProductoId2(), productosPorLocalidad2.get(1).getId());
		assertEquals(DbUtil.getTestProductoId3(), productosPorLocalidad2.get(2).getId());
	}
	
	@Test
	public void testGetLocalidades() throws InstanceNotFoundException {
		
		logger.debug("testGetLocalidades");
		
		Localidad lugo = new Localidad("Lugo");
		Localidad santiago = new Localidad("Santiago");
		
		localidadDao.save(lugo);
		localidadDao.save(santiago);
		
		List<Localidad> listExpected = new ArrayList<Localidad>();
		listExpected.add(localidadDao.find(DbUtil.getTestLocalidadId()));
		listExpected.add(lugo);
		listExpected.add(santiago);
		
		List<LocalidadTO> listExpectedTO = LocalidadTypeConversor.toLocalidadTO(listExpected);
		
		List<LocalidadTO> listlocalidadesTO = productoService.getLocalidades();
		
		assertEquals(listlocalidadesTO, listExpectedTO);
	}
	
	@Test
	public void testGetProductosByLocalidad() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByLocalidad");
		
		Localidad localidadTest = new Localidad("Lugo");
		
		localidadDao.save(localidadTest);
		
		Tienda tiendaTest = new Tienda("tienda1test", "direccion1test", "00000003", "tienda3@test.com", "539295939", localidadTest);
		
		tiendaDao.save(tiendaTest);
		
		Long testTiendaId = tiendaTest.getId();
		
		// creamos un par de productos en la nueva tienda en la nueva localidad Lugo
		Double testPrecio = new Double(5);
		Long testStock = new Long(25);
		
		Producto producto = ProductoTypeConversor.toProducto(productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId));
		
		Producto producto2 = ProductoTypeConversor.toProducto(productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId));
		
		List<Producto> productosExpected = new ArrayList<Producto>();
		productosExpected.add(producto);
		productosExpected.add(producto2);
		
		List<ProductoTO> productosExpectedTO = ProductoTypeConversor.toProductoTO(productosExpected);
		
		List<ProductoTO> productosPorLocalidadTO = productoService.getProductosByLocalidad(localidadTest.getId());
		
		assertTrue(productosPorLocalidadTO.size()==2);
		assertEquals(productosExpectedTO.get(0).getId(), productosPorLocalidadTO.get(0).getId());
		assertEquals(productosExpectedTO.get(1).getId(), productosPorLocalidadTO.get(1).getId());
		
		List<ProductoTO> productosPorLocalidadTO2 = productoService.getProductosByLocalidad(DbUtil.getTestLocalidadId());
		
		assertTrue(productosPorLocalidadTO2.size()==3);
		assertEquals(DbUtil.getTestProductoId1(), productosPorLocalidadTO2.get(0).getId());
		assertEquals(DbUtil.getTestProductoId2(), productosPorLocalidadTO2.get(1).getId());
		assertEquals(DbUtil.getTestProductoId3(), productosPorLocalidadTO2.get(2).getId());
	}
	
	@Test
	public void testGetProductosByLocalidadEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByLocalidadEnVenta");
		
		Localidad localidadTest = new Localidad("Lugo");
		
		localidadDao.save(localidadTest);
		
		Tienda tiendaTest = new Tienda("tienda1test", "direccion1test", "00000003", "tienda3@test.com", "539295939", localidadTest);
		
		tiendaDao.save(tiendaTest);
		
		Long testTiendaId = tiendaTest.getId();
		
		// creamos un par de productos en la nueva tienda en la nueva localidad Lugo
		Double testPrecio = new Double(5);
		Long testStock = new Long(25);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 4);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 2);
		
		ProductoTO productoTO = productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId);
		
		Producto producto = productoDao.find(productoTO.getId());
		
		producto.setFecha_puesta_venta(fecha_puesta_venta);
		producto.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto);
		
		ProductoTO productoTO2 = productoService.newProducto("nombre_producto",
															"descripcion_producto",
															"URL_producto",
															testPrecio,
															testStock,
															testTiendaId);
		
		Producto producto2 = productoDao.find(productoTO2.getId());
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto2);
		
		List<Producto> productosExpected = new ArrayList<Producto>();
		productosExpected.add(producto2);
		productosExpected.add(producto);
		
		List<ProductoTO> productosExpectedTO = ProductoTypeConversor.toProductoTO(productosExpected);
		
		List<ProductoTO> productosPorLocalidadTO = productoService.getProductosByLocalidadEnVenta(localidadTest.getId());
		
		assertTrue(productosPorLocalidadTO.size()==2);
		assertEquals(productosExpectedTO.get(0).getId(), productosPorLocalidadTO.get(0).getId());
		assertEquals(productosExpectedTO.get(1).getId(), productosPorLocalidadTO.get(1).getId());
		
		// la localidad con los tres productos de dbutil
		List<ProductoTO> productosPorLocalidadTO2 = productoService.getProductosByLocalidadEnVenta(DbUtil.getTestLocalidadId());
		// pero no estan en venta
		assertTrue(productosPorLocalidadTO2.isEmpty());
		
	}
	
	@Test
	public void testCategoriaDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testCategoriaDaoHibernate");
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		producto1.getCategorias().add(categoria);
		producto1.getCategorias().add(categoria2);
		
		producto2.getCategorias().add(categoria);
		
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto1);
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		List<Producto> listExpected1 = new ArrayList<Producto>();
		listExpected1.add(producto1);
		listExpected1.add(producto2);
		listExpected1.add(producto3);
		
		List<Producto> productos = productoDao.getProductosByCategoria(categoria);
		
		assertEquals(listExpected1, productos);
		
		List<Producto> listExpected2 = new ArrayList<Producto>();
		listExpected2.add(producto1);

		productos = productoDao.getProductosByCategoria(categoria2);
		
		assertEquals(listExpected2, productos);
		
		productos = productoDao.getProductosByCategoria(categoria3);
		
		assertTrue(productos.isEmpty());
		
	}
	
	@Test
	public void testGetCategorias() {
		
		logger.debug("testGetCategorias");
		
		List<CategoriaTO> categoriaTOs = new ArrayList<CategoriaTO>();
				
		categoriaTOs = productoService.getCategorias();
		
		assertTrue(categoriaTOs.isEmpty());
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		List<CategoriaTO> expectedCategoriaTOs = new ArrayList<CategoriaTO>();
		expectedCategoriaTOs.add(CategoriaTypeConversor.toCategoriaTO(categoria));
		expectedCategoriaTOs.add(CategoriaTypeConversor.toCategoriaTO(categoria2));
		expectedCategoriaTOs.add(CategoriaTypeConversor.toCategoriaTO(categoria3));
		
		categoriaTOs = productoService.getCategorias();
		
		// dado que la lista de categorias no tiene un orden predecible, las comparamos así
		assertTrue(categoriaTOs.size() == expectedCategoriaTOs.size());
		assertTrue(categoriaTOs.containsAll(expectedCategoriaTOs));
		
	}
	
	@Test
	public void testGetProductosByCategoria() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByCategoria");
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		List<Long> categoriaIds = new ArrayList<Long>();
		
		// la categoria no existe (no deben saltar excepciones por ello)
		categoriaIds.add(NON_EXISTENT_ID);
		
		productoTOs = productoService.getProductosByCategoria(categoriaIds);
		
		assertTrue(productoTOs.isEmpty());
		
		// la categoria exite pero no hay ninguno producto en ella
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		categoriaIds.clear();
		categoriaIds.add(categoria.getId());
		
		productoTOs = productoService.getProductosByCategoria(categoriaIds);
		
		assertTrue(productoTOs.isEmpty());
		
		// categorias con y sin productos
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		producto1.getCategorias().add(categoria);
		producto1.getCategorias().add(categoria2);
		
		
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto1);
		productoDao.save(producto3);
		
		categoriaIds.clear();
		categoriaIds.add(categoria.getId());
		categoriaIds.add(categoria2.getId());
		categoriaIds.add(categoria3.getId());
		
		productoTOs = productoService.getProductosByCategoria(categoriaIds);
		
		List<ProductoTO> expectedProductoTOs = new ArrayList<ProductoTO>();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		
		assertTrue(productoTOs.size() == expectedProductoTOs.size());
		assertTrue(productoTOs.containsAll(expectedProductoTOs));
		
		
		// otro caso más
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		producto2.getCategorias().add(categoria3);
		productoDao.save(producto2);
		
		categoriaIds.clear();
		categoriaIds.add(categoria2.getId());
		categoriaIds.add(categoria3.getId());
		
		expectedProductoTOs.clear();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto2));
		
		productoTOs = productoService.getProductosByCategoria(categoriaIds);
		
		assertTrue(productoTOs.size() == expectedProductoTOs.size());
		assertTrue(productoTOs.containsAll(expectedProductoTOs));
	}
	
	@Test
	public void testGetProductosByCategoriaEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByCategoriaEnVenta");
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		List<Long> categoriaIds = new ArrayList<Long>();
		
		// la categoria no existe (no deben saltar excepciones por ello)
		categoriaIds.add(NON_EXISTENT_ID);
		
		productoTOs = productoService.getProductosByCategoriaEnVenta(categoriaIds);
		
		assertTrue(productoTOs.isEmpty());
		
		// la categoria exite pero no hay ninguno producto en ella
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		categoriaIds.clear();
		categoriaIds.add(categoria.getId());
		
		productoTOs = productoService.getProductosByCategoriaEnVenta(categoriaIds);
		
		assertTrue(productoTOs.isEmpty());
		
		// categorias con y sin productos
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		// los productos deben estar en venta (con o sin stock) y ordenados por fecha de retirada
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		// producto 2 no está en venta todavía y no debe aparecer
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, 2);
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 4);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 1);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		
		producto1.getCategorias().add(categoria);
		producto1.getCategorias().add(categoria2);
		
		producto2.getCategorias().add(categoria);
		producto2.getCategorias().add(categoria2);
		
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto1);
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		categoriaIds.clear();
		categoriaIds.add(categoria.getId());
		categoriaIds.add(categoria2.getId());
		categoriaIds.add(categoria3.getId());
		
		productoTOs = productoService.getProductosByCategoriaEnVenta(categoriaIds);
		
		List<ProductoTO> expectedProductoTOs = new ArrayList<ProductoTO>();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		
		assertEquals(productoTOs, expectedProductoTOs);
		
		
		// otro caso más
		producto2.getCategorias().add(categoria3);
		productoDao.save(producto2);
		
		categoriaIds.clear();
		categoriaIds.add(categoria2.getId());
		categoriaIds.add(categoria3.getId());
		
		expectedProductoTOs.clear();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		
		productoTOs = productoService.getProductosByCategoriaEnVenta(categoriaIds);
		
		assertEquals(productoTOs, expectedProductoTOs);
	}
	
	@Test
	public void testGetProductosByCategoriaTienda() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByCategoriaTienda");
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		
		// la categoria no existe (no deben saltar excepciones por ello)
		productoTOs = productoService.getProductosByCategoriaTienda(NON_EXISTENT_ID);
		
		assertTrue(productoTOs.isEmpty());
		
		// la categoria exite pero no hay ninguno producto en ella
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		productoTOs = productoService.getProductosByCategoriaTienda(categoria.getId());
		
		assertTrue(productoTOs.isEmpty());
		
		
		
		// tienda1 con una categoria
		Tienda tienda = tiendaDao.find(DbUtil.getTestTiendaId1());
		tienda.getCategorias().add(categoria);
		
		tiendaDao.save(tienda);
		
		// la tienda tiene dos productos (DbUtil)
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		List<ProductoTO> expectedProductoTOs = new ArrayList<ProductoTO>();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto2));
		
		productoTOs = productoService.getProductosByCategoriaTienda(categoria.getId());
		
		assertTrue(productoTOs.size() == expectedProductoTOs.size());
		assertTrue(productoTOs.containsAll(expectedProductoTOs));
		
		
		
		// tienda2 con misma categoría que tienda1
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		tienda2.getCategorias().add(categoria);
		
		tiendaDao.save(tienda2);
		
		// tienda2 tiene un producto
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		//al buscar por categoria de tiendas, deben salir los productos de ambas tiendas, tres en total
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		
		productoTOs = productoService.getProductosByCategoriaTienda(categoria.getId());
		
		assertTrue(productoTOs.size() == expectedProductoTOs.size());
		assertTrue(productoTOs.containsAll(expectedProductoTOs));
		
		
		
		// otro caso
		Categoria categoria2 = new Categoria("Omega");
		categoriaDao.save(categoria2);
		
		tienda2.getCategorias().add(categoria2);
		tiendaDao.save(tienda2);
		
		productoTOs = productoService.getProductosByCategoriaTienda(categoria2.getId());
		
		expectedProductoTOs.clear();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		
		assertTrue(productoTOs.size() == expectedProductoTOs.size());
		assertTrue(productoTOs.containsAll(expectedProductoTOs));
	}
	
	@Test
	public void testGetProductosByCategoriaTiendaEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByCategoriaTiendaEnVenta");
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		
		// la categoria no existe (no deben saltar excepciones por ello)
		productoTOs = productoService.getProductosByCategoriaTiendaEnVenta(NON_EXISTENT_ID);
		
		assertTrue(productoTOs.isEmpty());
		
		// la categoria exite pero no hay ninguno producto en ella
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		productoTOs = productoService.getProductosByCategoriaTiendaEnVenta(categoria.getId());
		
		assertTrue(productoTOs.isEmpty());
		
		
		
		// tienda1 con una categoria
		Tienda tienda = tiendaDao.find(DbUtil.getTestTiendaId1());
		tienda.getCategorias().add(categoria);
		
		tiendaDao.save(tienda);
		
		// la tienda tiene dos productos en venta (DbUtil)
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		// los productos deben estar en venta (con o sin stock) y ordenados por fecha de retirada
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 3);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto1);
		productoDao.save(producto2);
		
		List<ProductoTO> expectedProductoTOs = new ArrayList<ProductoTO>();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto2));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		
		productoTOs = productoService.getProductosByCategoriaTiendaEnVenta(categoria.getId());
		
		assertEquals(productoTOs, expectedProductoTOs);
		assertTrue(productoTOs.equals(expectedProductoTOs));
		
		
		// tienda2 con misma categoría que tienda1
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		tienda2.getCategorias().add(categoria);
		
		tiendaDao.save(tienda2);
		
		// tienda2 tiene un producto en venta
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 2);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		productoDao.save(producto3);
		
		// al buscar por categoria de tiendas, deben salir los productos de ambas tiendas
		// tres en total, por orden de fecha de retirada
		expectedProductoTOs.clear();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto2));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto1));
		
		productoTOs = productoService.getProductosByCategoriaTiendaEnVenta(categoria.getId());
		
		assertEquals(productoTOs, expectedProductoTOs);
		assertTrue(productoTOs.equals(expectedProductoTOs));
		
		
		// otro caso
		Categoria categoria2 = new Categoria("Omega");
		categoriaDao.save(categoria2);
		
		tienda2.getCategorias().add(categoria2);
		tiendaDao.save(tienda2);
		
		productoTOs = productoService.getProductosByCategoriaTiendaEnVenta(categoria2.getId());
		
		expectedProductoTOs.clear();
		expectedProductoTOs.add(ProductoTypeConversor.toProductoTO(producto3));
		
		assertEquals(productoTOs, expectedProductoTOs);
		assertTrue(productoTOs.equals(expectedProductoTOs));
	}
	
	@Test
	public void testGetProductosByTiendaDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTiendaDaoHibernate");
		
		List<Producto> productos = productoDao.getProductosByTienda(DbUtil.getTestTiendaId1());
		
		List<Producto> productosExpected = new ArrayList<Producto>();
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId1()));
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId2()));
		
		assertTrue(productos.equals(productosExpected));
		
		productos = productoDao.getProductosByTienda(DbUtil.getTestTiendaId2());
		
		productosExpected.clear();
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId3()));
		
		assertTrue(productos.equals(productosExpected));
	}
	
	@Test
	public void testGetProductosByTiendaFavDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTiendaFavDaoHibernate");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		clienteDao.save(cliente);
		
		cliente.getClienteData().setNombre("cliente1");
		clienteDao.save(cliente);
		
		List<Producto> productos = productoDao.getProductosByTiendasFav(cliente.getId());
		
		// cliente no tiene tiendas favoritas
		assertTrue(productos.isEmpty());
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		cliente.getFavoritas().add(tienda1);
		clienteDao.save(cliente);
		
		productos = productoDao.getProductosByTiendasFav(cliente.getId());
		
		List<Producto> productosExpected = new ArrayList<Producto>();
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId1()));
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId2()));
		
		// cliente tiene como favorita tienda 1
		// deben salir los productos 1 y 2
		assertTrue(productos.equals(productosExpected));
		
		
		
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);
		
		productos = productoDao.getProductosByTiendasFav(cliente.getId());
		
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId3()));
		
		// cliente tiene como favorita tienda 1 y tienda 2
		// deben salir los productos 1, 2 y 3
		assertTrue(productos.equals(productosExpected));
		
		
		cliente.getFavoritas().remove(tienda1);
		clienteDao.save(cliente);
		
		productos = productoDao.getProductosByTiendasFav(cliente.getId());
		
		productosExpected.clear();
		productosExpected.add(productoDao.find(DbUtil.getTestProductoId3()));
		
		// cliente tiene como favorita tienda 2
		// deben salir el producto 3
		assertTrue(productos.equals(productosExpected));
	}
	
	@Test
	public void testGetProductosByTienda() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTienda");
		
		List<ProductoTO> productoTOs = productoService.getProductosByTienda(DbUtil.getTestTiendaId1());
		
		List<ProductoTO> productoTOsExpected = new ArrayList<ProductoTO>();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId1())));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId2())));
		
		assertTrue(productoTOs.equals(productoTOsExpected));
		
		productoTOs = productoService.getProductosByTienda(DbUtil.getTestTiendaId2());

		productoTOsExpected.clear();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId3())));

		assertTrue(productoTOs.equals(productoTOsExpected));
	}
	
	@Test
	public void testGetProductosByTiendaFav() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTiendaFav");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		clienteDao.save(cliente);
		
		cliente.getClienteData().setNombre("cliente1");

		List<ProductoTO> productoTOs = productoService.getProductosByTiendaFav(cliente.getId());
		
		// cliente no tiene tiendas favoritas
		assertTrue(productoTOs.isEmpty());
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		cliente.getFavoritas().add(tienda1);
		clienteDao.save(cliente);

		productoTOs = productoService.getProductosByTiendaFav(cliente.getId());

		List<ProductoTO> productoTOsExpected = new ArrayList<ProductoTO>();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId1())));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId2())));
		
		// cliente tiene como favorita tienda 1
		// deben salir los productos 1 y 2
		assertTrue(productoTOs.equals(productoTOsExpected));
		
		
		
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);

		productoTOs = productoService.getProductosByTiendaFav(cliente.getId());

		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId3())));
		
		// cliente tiene como favorita tienda 1 y tienda 2
		// deben salir los productos 1, 2 y 3
		assertTrue(productoTOs.equals(productoTOsExpected));
		
		
		
		cliente.getFavoritas().remove(tienda1);
		clienteDao.save(cliente);
		
		productoTOs = productoService.getProductosByTiendaFav(cliente.getId());

		productoTOsExpected.clear();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId3())));
		
		// cliente tiene como favorita tienda 2
		// deben salir el producto 3
		assertTrue(productoTOs.equals(productoTOsExpected));
	}
	
	@Test
	public void testGetProductosByTiendaFavEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTiendaFavEnVenta");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		clienteDao.save(cliente);
		
		cliente.getClienteData().setNombre("cliente1");

		List<ProductoTO> productoTOs = productoService.getProductosByTiendaFavEnVenta(cliente.getId());
		
		// cliente no tiene tiendas favoritas
		assertTrue(productoTOs.isEmpty());
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		cliente.getFavoritas().add(tienda1);
		clienteDao.save(cliente);

		productoTOs = productoService.getProductosByTiendaFavEnVenta(cliente.getId());
		
		// cliente tiene tienda favorita, pero no hay productos en venta
		assertTrue(productoTOs.isEmpty());
		
		
		
		// la tienda tiene dos productos en venta (DbUtil)
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 3);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto1);
		productoDao.save(producto2);

		List<ProductoTO> productoTOsExpected = new ArrayList<ProductoTO>();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto2));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto1));
		
		productoTOs = productoService.getProductosByTiendaFavEnVenta(cliente.getId());
		
		// cliente tiene como favorita tienda 1
		// deben salir los productos 1 y 2
		assertTrue(productoTOs.equals(productoTOsExpected));
		
		
		
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);
		
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 5);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		productoDao.save(producto3);

		productoTOs = productoService.getProductosByTiendaFavEnVenta(cliente.getId());

		productoTOsExpected.clear();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto2));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto1));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto3));
		
		// cliente tiene como favorita tienda 1 y tienda 2
		// deben salir los productos 2, 1 y 3 por orden de fecha de retirada
		assertTrue(productoTOs.equals(productoTOsExpected));
		
		
		
		cliente.getFavoritas().remove(tienda1);
		clienteDao.save(cliente);
		
		productoTOs = productoService.getProductosByTiendaFavEnVenta(cliente.getId());

		productoTOsExpected.clear();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(producto3));
		
		// cliente tiene como favorita tienda 2
		// deben salir el producto 3
		assertTrue(productoTOs.equals(productoTOsExpected));
	}
	
	@Test
	public void testGetProductosByTiendaEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosByTiendaEnVenta");
		
		// lista vacia
		List<ProductoTO> productoTOs = productoService.getProductosByTiendaEnVenta(DbUtil.getTestTiendaId1());
		assertTrue(productoTOs.isEmpty());
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		// producto 1 a la venta, sin stock
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto1);
		
		// producto 2 a la venta
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto2);
		
		// deben aparecer ambos productos, primero el 2, luego el 1, por orden de retirada
		productoTOs = productoService.getProductosByTiendaEnVenta(DbUtil.getTestTiendaId1());
		
		List<ProductoTO> productoTOsExpected = new ArrayList<ProductoTO>();
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId2())));
		productoTOsExpected.add(ProductoTypeConversor.toProductoTO(productoDao.find(DbUtil.getTestProductoId1())));
		
		assertTrue(productoTOs.equals(productoTOsExpected));
	}
	
	@Test
	public void testGetProductosEnVenta() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosEnVenta");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		// producto 1 a la venta, pero no tiene stock así que no sale
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto1);
		
		// producto 2 a la venta, fecha de retirada mas tarde que producto3
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto2);
		
		// producto 3 a la venta
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		productoDao.save(producto3);
		
		List<ProductoTO> productoListExpected = new ArrayList<ProductoTO>();
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto3));
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto2));
		
		List<ProductoTO> productoList = productoService.getProductosEnVenta();
		
		assertEquals(productoList, productoListExpected);
	}
	
	@Test
	public void testGetProductosEnVentaAgotados() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosEnVentaAgotados");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		// producto 1 a la venta, no tiene stock
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto1);
		
		// producto 2 a la venta, pero tiene stock
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto2);
		
		// producto 3 a la venta, pero tiene stock
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		productoDao.save(producto3);
		
		List<ProductoTO> productoListExpected = new ArrayList<ProductoTO>();
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto1));
		
		List<ProductoTO> productoList = productoService.getProductosEnVentaAgotados();
		
		assertEquals(productoList, productoListExpected);
	}
	
	@Test
	public void testGetProductosEnVentaYAgostados() throws InstanceNotFoundException {
		
		logger.debug("testGetProductosEnVentaYAgostados");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		// producto 1 a la venta, sin stock
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 3);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		productoDao.save(producto1);
		
		// producto 2 a la venta, fecha de retirada mas tarde que producto3
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -1);
		
		producto2.setFecha_puesta_venta(fecha_puesta_venta2);
		producto2.setFecha_retirada(fecha_retirada2);
		
		productoDao.save(producto2);
		
		// producto 3 a la venta
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -2);
		
		producto3.setFecha_puesta_venta(fecha_puesta_venta3);
		producto3.setFecha_retirada(fecha_retirada3);
		
		productoDao.save(producto3);
		
		List<ProductoTO> productoListExpected = new ArrayList<ProductoTO>();
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto3));
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto2));
		productoListExpected.add(ProductoTypeConversor.toProductoTO(producto1));
		
		List<ProductoTO> productoList = productoService.getProductosEnVentaYAgotados();
		
		assertEquals(productoList, productoListExpected);
	}
	
	@Test
	public void testGetUnidadesVendidasByProducto() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByProducto");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		Long unidadesVendidas = productoService.getUnidadesVendidasByProducto(DbUtil.getTestProductoId2());
		
		assertTrue(unidadesVendidas == 2);
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		unidadesVendidas = productoService.getUnidadesVendidasByProducto(DbUtil.getTestProductoId2());
		
		assertTrue(unidadesVendidas == 5);
	}
	
	@Test
	public void testGetUnidadesVendidasByTienda() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByTienda");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		Long unidadesVendidasTienda1 = productoService.getUnidadesVendidasByTienda(DbUtil.getTestTiendaId1());
		
		assertTrue(unidadesVendidasTienda1 == 2);
		
		Long unidadesVendidasTienda2 = productoService.getUnidadesVendidasByTienda(DbUtil.getTestTiendaId2());
		
		assertTrue(unidadesVendidasTienda2 == 0);
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		unidadesVendidasTienda1 = productoService.getUnidadesVendidasByTienda(DbUtil.getTestTiendaId1());
		
		assertTrue(unidadesVendidasTienda1 == 5);
	}
	
	@Test
	public void testGetUnidadesVendidasByCategoria() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByCategoria");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		producto2.getCategorias().add(categoria);
		producto2.getCategorias().add(categoria2);
		
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long unidadesVendidasCategoria1 = productoService.getUnidadesVendidasByCategoria(categoria.getId());
		
		assertTrue(unidadesVendidasCategoria1 == 2);
		
		Long unidadesVendidasCategoria2 = productoService.getUnidadesVendidasByCategoria(categoria2.getId());
		
		assertTrue(unidadesVendidasCategoria2 == 0);
		
		Long unidadesVendidasCategoria3 = productoService.getUnidadesVendidasByCategoria(categoria3.getId());
		
		assertTrue(unidadesVendidasCategoria3 == 0);
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		unidadesVendidasCategoria1 = productoService.getUnidadesVendidasByCategoria(categoria.getId());
		assertTrue(unidadesVendidasCategoria1 == 5);
		
		unidadesVendidasCategoria2 = productoService.getUnidadesVendidasByCategoria(categoria2.getId());
		assertTrue(unidadesVendidasCategoria2 == 3);
		
		unidadesVendidasCategoria3 = productoService.getUnidadesVendidasByCategoria(categoria3.getId());
		assertTrue(unidadesVendidasCategoria3 == 0);
		
	}
	
	@Test
	public void testGetUnidadesVendidasByCategoriaByTienda() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByCategoriaByTienda");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		Categoria categoria3 = new Categoria("Gamma");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		categoriaDao.save(categoria3);
		
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		producto2.getCategorias().add(categoria);
		producto2.getCategorias().add(categoria2);
		
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long unidadesVendidasCategoria1Tienda1 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(unidadesVendidasCategoria1Tienda1 == 0);
		
		Long unidadesVendidasCategoria1Tienda2 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(unidadesVendidasCategoria1Tienda2 == 2);
		
		Long unidadesVendidasCategoria2Tienda1 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(unidadesVendidasCategoria2Tienda1 == 0);
		
		Long unidadesVendidasCategoria2Tienda2 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(unidadesVendidasCategoria2Tienda2 == 0);
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		unidadesVendidasCategoria1Tienda1 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(unidadesVendidasCategoria1Tienda1 == 3);
		
		unidadesVendidasCategoria1Tienda2 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(unidadesVendidasCategoria1Tienda2 == 2);
		
		unidadesVendidasCategoria2Tienda1 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(unidadesVendidasCategoria2Tienda1 == 3);
		
		unidadesVendidasCategoria2Tienda2 = productoService.getUnidadesVendidasByCategoriaByTienda(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(unidadesVendidasCategoria2Tienda2 == 0);
		
	}
	
	@Test
	public void testGetHistoricoByProducto() throws InstanceNotFoundException {
		
		logger.debug("testGetHistoricoByProducto");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		// TODO pruebas preliminares, a falta de usar un mecanismo automático para
		// crear los historicos y sus versiones
		ProductoHistorico productoHistorico1 = new ProductoHistorico(
				producto1.getId(),
				new Long(1),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		ProductoHistorico productoHistorico2 = new ProductoHistorico(
				producto1.getId(),
				new Long(2),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		ProductoHistorico productoHistorico3 = new ProductoHistorico(
				producto1.getId(),
				new Long(3),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		producto1.getHistorico().add(productoHistorico1);
		producto1.getHistorico().add(productoHistorico2);
		producto1.getHistorico().add(productoHistorico3);
		
		// cascade ALL, deberían guardarse los historicos
		productoDao.save(producto1);
		
		assertTrue(producto1.getHistorico().size() == 3);
		
		
		List<ProductoHistoricoTO> productoHistoricoTOs = productoService.getHistoricoByProducto(producto1.getId());
		
		List<ProductoHistoricoTO> productoHistoricoTOsExpected = new ArrayList<ProductoHistoricoTO>();
		productoHistoricoTOsExpected.add(ProductoHistoricoTypeConversor.toProductoHistoricoTO(productoHistorico1));
		productoHistoricoTOsExpected.add(ProductoHistoricoTypeConversor.toProductoHistoricoTO(productoHistorico2));
		productoHistoricoTOsExpected.add(ProductoHistoricoTypeConversor.toProductoHistoricoTO(productoHistorico3));
		
		assertTrue(productoHistoricoTOs.containsAll(productoHistoricoTOsExpected));
		
		logger.debug("testGetHistoricoByProducto Fecha: " + productoHistoricoTOs.get(0).getFecha_puesta_venta());
	}
	
	@Test
	public void testGetFechasVentasByProducto() throws InstanceNotFoundException {
		
		logger.debug("testGetFechasVentasByProducto");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());
		
		Calendar fecha_retirada = Calendar.getInstance();
		fecha_retirada.add(Calendar.DAY_OF_MONTH, 2);
		
		Calendar fecha_puesta_venta = Calendar.getInstance();
		fecha_puesta_venta.add(Calendar.DAY_OF_MONTH, -1);
		
		producto1.setFecha_puesta_venta(fecha_puesta_venta);
		producto1.setFecha_retirada(fecha_retirada);
		
		// TODO pruebas preliminares, a falta de usar un mecanismo automático para
		// crear los historicos y sus versiones
		ProductoHistorico productoHistorico1 = new ProductoHistorico(
				producto1.getId(),
				new Long(1),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		ProductoHistorico productoHistorico2 = new ProductoHistorico(
				producto1.getId(),
				new Long(2),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		ProductoHistorico productoHistorico3 = new ProductoHistorico(
				producto1.getId(),
				new Long(3),
				producto1.getFecha_puesta_venta(),
				producto1.getFecha_retirada(),
				producto1.getPrecio(),
				producto1.getStock());
		
		producto1.getHistorico().add(productoHistorico1);
		producto1.getHistorico().add(productoHistorico2);
		producto1.getHistorico().add(productoHistorico3);
		
		// cascade ALL, deberían guardarse los historicos
		productoDao.save(producto1);
		
		assertTrue(producto1.getHistorico().size() == 3);
		
		List<ProductoBlock> productoBlocks = productoService.getFechasVentaByProducto(producto1.getId());
		
		assertTrue(productoBlocks.size() == 3);
		
		assertEquals(productoBlocks.get(0).getVersion(), new Long(1));
		assertEquals(productoBlocks.get(1).getVersion(), new Long(2));
		assertEquals(productoBlocks.get(2).getVersion(), new Long(3));
	}
	
	@Test
	public void testGetUnidadesVendidas() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidas");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		List<UnidadesBlock> ventasBlock = productoService.getUnidadesVendidas();
		assertTrue(ventasBlock.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasBlock = productoService.getUnidadesVendidas();
		assertTrue(ventasBlock.size() == 1);
		
		UnidadesBlock unidadBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasBlock.contains(unidadBlock));
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		ventasBlock = productoService.getUnidadesVendidas();
		assertTrue(ventasBlock.size() == 2);
		
		unidadBlock.setUnidadesVendidas(new Long(4));
		assertTrue(ventasBlock.contains(unidadBlock));
		
		UnidadesBlock unidadBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(1));
		assertTrue(ventasBlock.contains(unidadBlock2));
	}
	
	@Test
	public void testGetUnidadesVendidasByProductoByRango() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByProductoByRango");
		
		Calendar fecha_inicio = Calendar.getInstance();
		Calendar fecha_fin = Calendar.getInstance();
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		// coger un producto de dbutil, hacer una prueba, debe devolver cero
		Long productoId = DbUtil.getTestProductoId2();
		
		UnidadesBlock ventas = productoService.getUnidadesVendidasByProductoByRango(productoId, fecha_inicio, fecha_fin);
		assertTrue(ventas.getUnidadesVendidas() == 0);
		
		// crear dos reservas y dos compras de un mismo producto, con un determinado número de ventas cada una
		// crear una compra con fecha de hoy
		Calendar fecha_compra = Calendar.getInstance();
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, fecha_compra,
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		// crear una compra con fecha de hace tres días
		Calendar fecha_compra2 = Calendar.getInstance();
		fecha_compra2.add(Calendar.DAY_OF_MONTH, -3);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, fecha_compra2,
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		// crear una reserva entregada con fecha de hoy
		Long reserva1long = reservaService.reserveProducto(new Long(5), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		// crear una reserva entregada con fecha de hace tres días
		Long reserva2long = reservaService.reserveProducto(new Long(7), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Calendar fecha_reserva = Calendar.getInstance();
		fecha_reserva.add(Calendar.DAY_OF_MONTH, -3);
		
		Reserva reserva2 = reservaDao.find(reserva2long);
		reserva2.setFecha(fecha_reserva);
		reservaDao.save(reserva2);
		
		// meter también una venta y una compra de otro producto, por verificación, no deben salir nunca
		Calendar fecha_compra3 = Calendar.getInstance();
		fecha_compra3.add(Calendar.DAY_OF_MONTH, -3);
		compraService.guardarCompra(
				new Long(13), RecogidaStateTO.NO_RECOGIDA, fecha_compra3,
				new Double(20), new Double(20), "PAY-3", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long reserva3long = reservaService.reserveProducto(new Long(17), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		// marcamos como entregadas las dos reservas
		reservaService.entregarReserva(reserva1long);
		reservaService.entregarReserva(reserva2long);
		reservaService.entregarReserva(reserva3long);
		
		// hacer varias pruebas con varios intervalos de tiempo, cogiendo alternativamente las ventas y viendo que coincide
		// testear con un día de margen
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -1);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +1);
		logger.debug("fecha_inicio = " + fecha_inicio.getTime());
		logger.debug("fecha_fin = " + fecha_fin.getTime());
		UnidadesBlock unidadBlock = productoService.getUnidadesVendidasByProductoByRango(productoId, fecha_inicio, fecha_fin);
		logger.debug("unidadesVendidas = " + unidadBlock.getUnidadesVendidas());
		assertTrue(unidadBlock.getUnidadesVendidas() == 6);
		
		// testear con tres días de margen
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -2);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +2);
		unidadBlock = productoService.getUnidadesVendidasByProductoByRango(productoId, fecha_inicio, fecha_fin);
		assertTrue(unidadBlock.getUnidadesVendidas() == 16);
		
		// testear un margen donde no haya nada
		fecha_inicio.add(Calendar.DAY_OF_MONTH, +8);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +5);
		unidadBlock = productoService.getUnidadesVendidasByProductoByRango(productoId, fecha_inicio, fecha_fin);
		assertTrue(unidadBlock.getUnidadesVendidas() == 0);
		
	}
	
	@Test
	public void testGetUnidadesVendidasByTiendaDesglose() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByTiendaDesglose");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		List<UnidadesBlock> ventasBlockTienda1 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasBlockTienda2 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockTienda1.isEmpty());
		assertTrue(ventasBlockTienda2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasBlockTienda1 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId1());
		ventasBlockTienda2 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockTienda1.size() == 1);
		assertTrue(ventasBlockTienda2.isEmpty());
		
		UnidadesBlock unidadBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasBlockTienda1.contains(unidadBlock));
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		ventasBlockTienda1 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId1());
		ventasBlockTienda2 = productoService.getUnidadesVendidasByTiendaDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockTienda1.size() == 1);
		assertTrue(ventasBlockTienda2.size() == 1);
		
		unidadBlock.setUnidadesVendidas(new Long(4));
		assertTrue(ventasBlockTienda1.contains(unidadBlock));
		
		UnidadesBlock unidadBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(1));
		assertTrue(ventasBlockTienda2.contains(unidadBlock2));
	}
	
	@Test
	public void testGetUnidadesVendidasByCategoriaDesglose() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByCategoriaDesglose");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		producto2.getCategorias().add(categoria);
		producto2.getCategorias().add(categoria2);
		
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		List<UnidadesBlock> ventasBlockCategoria1 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria.getId());
		List<UnidadesBlock> ventasBlockCategoria2 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria2.getId());
		
		assertTrue(ventasBlockCategoria1.isEmpty());
		assertTrue(ventasBlockCategoria2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasBlockCategoria1 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria.getId());
		ventasBlockCategoria2 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria2.getId());
		
		assertTrue(ventasBlockCategoria1.size() == 1);
		assertTrue(ventasBlockCategoria2.size() == 1);
		
		UnidadesBlock unidadBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasBlockCategoria1.contains(unidadBlock));
		assertTrue(ventasBlockCategoria2.contains(unidadBlock));
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		ventasBlockCategoria1 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria.getId());
		ventasBlockCategoria2 = productoService.getUnidadesVendidasByCategoriaDesglose(categoria2.getId());
		
		assertTrue(ventasBlockCategoria1.size() == 2);
		assertTrue(ventasBlockCategoria2.size() == 1);
		
		unidadBlock.setUnidadesVendidas(new Long(4));
		assertTrue(ventasBlockCategoria1.contains(unidadBlock));
		assertTrue(ventasBlockCategoria2.contains(unidadBlock));
		
		UnidadesBlock unidadBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(1));
		assertTrue(ventasBlockCategoria1.contains(unidadBlock2));
	}
	
	@Test
	public void testGetUnidadesVendidasByCategoriaByTiendaDesglose() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetUnidadesVendidasByCategoriaByTiendaDesglose");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Omega");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		producto2.getCategorias().add(categoria);
		producto2.getCategorias().add(categoria2);
		
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		producto3.getCategorias().add(categoria);
		
		productoDao.save(producto2);
		productoDao.save(producto3);
		
		List<UnidadesBlock> ventasBlockCategoria1Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasBlockCategoria1Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		List<UnidadesBlock> ventasBlockCategoria2Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasBlockCategoria2Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockCategoria1Tienda1.isEmpty());
		assertTrue(ventasBlockCategoria1Tienda2.isEmpty());
		assertTrue(ventasBlockCategoria2Tienda1.isEmpty());
		assertTrue(ventasBlockCategoria2Tienda2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasBlockCategoria1Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasBlockCategoria1Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasBlockCategoria2Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasBlockCategoria2Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockCategoria1Tienda1.size() == 1);
		assertTrue(ventasBlockCategoria1Tienda2.isEmpty());
		assertTrue(ventasBlockCategoria2Tienda1.size() == 1);
		assertTrue(ventasBlockCategoria2Tienda2.isEmpty());
		
		UnidadesBlock unidadBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasBlockCategoria1Tienda1.contains(unidadBlock));
		assertTrue(ventasBlockCategoria2Tienda1.contains(unidadBlock));
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		
		ventasBlockCategoria1Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasBlockCategoria1Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasBlockCategoria2Tienda1 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasBlockCategoria2Tienda2 = productoService.getUnidadesVendidasByCategoriaByTiendaDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasBlockCategoria1Tienda1.size() == 1);
		assertTrue(ventasBlockCategoria1Tienda2.size() == 1);
		assertTrue(ventasBlockCategoria2Tienda1.size() == 1);
		assertTrue(ventasBlockCategoria2Tienda2.isEmpty());
		
		unidadBlock.setUnidadesVendidas(new Long(4));
		assertTrue(ventasBlockCategoria1Tienda1.contains(unidadBlock));
		assertTrue(ventasBlockCategoria2Tienda1.contains(unidadBlock));
		
		UnidadesBlock unidadBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(1));
		assertTrue(ventasBlockCategoria1Tienda2.contains(unidadBlock2));
	}
	
	@Test
	public void testProductoLazy() throws InstanceNotFoundException {
		
		logger.debug("testProductoLazy");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId1());

		//LAZY
		assertTrue(!Hibernate.isInitialized(producto1.getHistorico()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(producto1.getCategorias()));
		//EAGER
		assertTrue(Hibernate.isPropertyInitialized(producto1, "productoData"));
		//LAZY
		//assertTrue(!Hibernate.isPropertyInitialized(producto1, "tienda"));
		
		// la categoria exite pero no hay ninguno producto en ella
		Categoria categoria = new Categoria("Alpha");
		categoriaDao.save(categoria);
		
		producto1.getCategorias().add(categoria);
		
		productoDao.save(producto1);
		
		//initialized
		assertTrue(Hibernate.isInitialized(producto1.getCategorias()));
		//EAGER
		assertTrue(Hibernate.isInitialized(categoria.getProductos()));
		//EAGER
		assertTrue(Hibernate.isInitialized(categoria.getTiendas()));
	}
	
	@Test
	public void testGetProductoData() throws InstanceNotFoundException {
		
		logger.debug("testGetProductoData");
		
		String testnombre = "testnombre";
		String testdescripcion = "testdescripcion";
		String testimagen = "testimagen";
		Double testprecio = new Double(0.99);
		Long teststock = new Long(10);
		Long tienda1 = DbUtil.getTestTiendaId1();
		
		ProductoTO productoTO = productoService.newProducto(testnombre, testdescripcion, testimagen, testprecio, teststock, tienda1);
		
		Long productoId = productoTO.getId();
		
		Producto producto = productoDao.find(productoId);
		ProductoData productoData = producto.getProductoData();
		
		assertEquals(productoData.getNombre(), testnombre);
		assertEquals(productoData.getDescripcion(), testdescripcion);
		assertEquals(productoData.getImagen(), testimagen);
		assertEquals(productoData.getId(), productoId);
		
	}
	
	@Test
	public void testGetHistoricoVentasByProducto() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetHistoricoVentasByProducto");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId2());
		
		producto1.setStock(new Long(100));
		productoDao.save(producto1);
		
		Long productoId = DbUtil.getTestProductoId2();
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		// creamos tres historicos para un producto del dbutil
		// TODO pruebas preliminares, a falta de usar un mecanismo automático para
		// crear los historicos y sus versiones
		Calendar fecha_puesta_venta1 = Calendar.getInstance();
		fecha_puesta_venta1.add(Calendar.DAY_OF_MONTH, -20);
		
		Calendar fecha_retirada1 = Calendar.getInstance();
		fecha_retirada1.add(Calendar.DAY_OF_MONTH, -15);
		
		ProductoHistorico productoHistorico1 = new ProductoHistorico(
				producto1.getId(),
				new Long(1),
				fecha_puesta_venta1,
				fecha_retirada1,
				producto1.getPrecio(),
				new Long(10));
		
		Calendar fecha_puesta_venta2 = Calendar.getInstance();
		fecha_puesta_venta2.add(Calendar.DAY_OF_MONTH, -12);
		
		Calendar fecha_retirada2 = Calendar.getInstance();
		fecha_retirada2.add(Calendar.DAY_OF_MONTH, -9);
		
		ProductoHistorico productoHistorico2 = new ProductoHistorico(
				producto1.getId(),
				new Long(2),
				fecha_puesta_venta2,
				fecha_retirada2,
				producto1.getPrecio(),
				new Long(20));
		
		Calendar fecha_puesta_venta3 = Calendar.getInstance();
		fecha_puesta_venta3.add(Calendar.DAY_OF_MONTH, -5);
		
		Calendar fecha_retirada3 = Calendar.getInstance();
		fecha_retirada3.add(Calendar.DAY_OF_MONTH, -2);
		
		ProductoHistorico productoHistorico3 = new ProductoHistorico(
				producto1.getId(),
				new Long(3),
				fecha_puesta_venta3,
				fecha_retirada3,
				producto1.getPrecio(),
				new Long(60));
		
		producto1.getHistorico().add(productoHistorico1);
		producto1.getHistorico().add(productoHistorico2);
		producto1.getHistorico().add(productoHistorico3);
		
		// cascade ALL, deberían guardarse los historicos
		productoDao.save(producto1);
		
		// para el primer historico no metemos ninguna venta
		
		// para el segundo historico incluimos una reserva y una compra, con varias unidades
		Calendar fecha_compra1 = Calendar.getInstance();
		fecha_compra1.add(Calendar.DAY_OF_MONTH, -10);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, fecha_compra1,
				new Double(20), new Double(20), "PAY-1", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		Long reserva1long = reservaService.reserveProducto(new Long(7), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Calendar fecha_reserva = Calendar.getInstance();
		fecha_reserva.add(Calendar.DAY_OF_MONTH, -11);
		
		Reserva reserva1 = reservaDao.find(reserva1long);
		reserva1.setFecha(fecha_reserva);
		reservaDao.save(reserva1);
		
		reservaService.entregarReserva(reserva1long);
		
		// para el tercer historico varias compras
		Calendar fecha_compra2 = Calendar.getInstance();
		fecha_compra2.add(Calendar.DAY_OF_MONTH, -3);
		compraService.guardarCompra(
				new Long(13), RecogidaStateTO.NO_RECOGIDA, fecha_compra2,
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		Calendar fecha_compra3 = Calendar.getInstance();
		fecha_compra3.add(Calendar.DAY_OF_MONTH, -4);
		compraService.guardarCompra(
				new Long(17), RecogidaStateTO.NO_RECOGIDA, fecha_compra3,
				new Double(20), new Double(20), "PAY-3", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		Calendar fecha_compra4 = Calendar.getInstance();
		fecha_compra4.add(Calendar.DAY_OF_MONTH, -4);
		compraService.guardarCompra(
				new Long(21), RecogidaStateTO.NO_RECOGIDA, fecha_compra4,
				new Double(20), new Double(20), "PAY-4", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		// pedimos los ventahistorico y comprobamos si los datos coinciden para cada uno
		List<VentaHistoricoTO> ventaHistoricoTOs = productoService.getHistoricoVentasByProducto(productoId);
		
		// historico 1
		assertTrue(ventaHistoricoTOs.get(0).getFecha_puesta_venta().equals(fecha_puesta_venta1));
		assertTrue(ventaHistoricoTOs.get(0).getFecha_retirada().equals(fecha_retirada1));
		assertTrue(ventaHistoricoTOs.get(0).getStock_inicial() == 10);
		assertTrue(ventaHistoricoTOs.get(0).getVentas() == 0);
		
		// historico 2
		assertTrue(ventaHistoricoTOs.get(1).getFecha_puesta_venta().equals(fecha_puesta_venta2));
		assertTrue(ventaHistoricoTOs.get(1).getFecha_retirada().equals(fecha_retirada2));
		assertTrue(ventaHistoricoTOs.get(1).getStock_inicial() == 20);
		assertTrue(ventaHistoricoTOs.get(1).getVentas() == 10);
		
		// historico 3
		assertTrue(ventaHistoricoTOs.get(2).getFecha_puesta_venta().equals(fecha_puesta_venta3));
		assertTrue(ventaHistoricoTOs.get(2).getFecha_retirada().equals(fecha_retirada3));
		assertTrue(ventaHistoricoTOs.get(2).getStock_inicial() == 60);
		assertTrue(ventaHistoricoTOs.get(2).getVentas() == 51);
	}
}
