package es.udc.jcastedo.NosaTenda.test.model.compraService;

import static es.udc.jcastedo.NosaTenda.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.jcastedo.NosaTenda.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.CompraTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.compra.Compra;
import es.udc.jcastedo.NosaTenda.model.compra.CompraDao;
import es.udc.jcastedo.NosaTenda.model.compra.Compra.RecogidaState;
import es.udc.jcastedo.NosaTenda.model.compraNotVerified.CompraNotVerified;
import es.udc.jcastedo.NosaTenda.model.compraNotVerified.CompraNotVerifiedDao;
import es.udc.jcastedo.NosaTenda.model.compraService.CompraService;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.model.util.CompraTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.test.model.util.DbUtil;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE})
//prueba base de datos de produccion
//@ContextConfiguration (locations = {SPRING_CONFIG_FILE})
@Transactional
public class CompraServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(CompraServiceTest.class);
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private CompraDao compraDao;
	
	@Autowired
	private CompraNotVerifiedDao compraNotVerifiedDao;
	
	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	
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
	public void testCompraDaoHibernate() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testCompraDaoHibernate");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId2());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Compra compra = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4I", "approved", "paypal", "EUR", producto, cliente);
		
		compraDao.save(compra);
		
		Compra compraExpected = compraDao.find(compra.getId());
		
		assertEquals(compra, compraExpected);
	}
	
	@Test
	public void testCompraNotVerifiedDaoHibernate() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testCompraNotVerifiedDaoHibernate");
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId2());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		CompraNotVerified compraNotVerified = new CompraNotVerified(new Long(1), Calendar.getInstance(), 0.99,
				0.99, "PAY-90N36598CR826520HKTXAC4I", "paypal", "paypal", producto, cliente);
		
		compraNotVerifiedDao.save(compraNotVerified);
		
		CompraNotVerified compraNVExpected = compraNotVerifiedDao.find(compraNotVerified.getId());
		
		assertEquals(compraNotVerified, compraNVExpected);
	}
	
	@Test
	public void testCompraDaoHibernateGetComprasByClienteId() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testCompraDaoHibernateGetComprasByClienteId");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId3());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Compra compra1 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4", "approved", "paypal", "EUR", producto1, cliente);
		
		compraDao.save(compra1);
		
		Compra compra2 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4I", "approved", "paypal", "EUR", producto2, cliente);
		
		compraDao.save(compra2);
		
		List<Compra> listCompras = new ArrayList<Compra>();
		listCompras.add(compra1);
		listCompras.add(compra2);
		
		List<Compra> listExpected = compraDao.getComprasByClienteId(clienteId);
		
		assertEquals(listCompras.get(0), listExpected.get(0));
		assertEquals(listCompras.get(1), listExpected.get(1));
	}
	
	@Test
	public void testCompraDaoHibernateGetComprasByTiendaId() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testCompraDaoHibernateGetComprasByTiendaId");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		// no podemos usar el producto1 de DbUtil porque su stock es 0
		Producto producto1 = new Producto(new Double(10), new Long(10), tienda1);
		productoDao.save(producto1);
		
		producto1.getProductoData().setNombre("producto1bis");
		producto1.getProductoData().setDescripcion("test de GetComprasByTiendaId");
		producto1.getProductoData().setImagen("uri_imagen1");
		productoDao.save(producto1);
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		Producto producto3 = productoDao.find(DbUtil.getTestProductoId3());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Compra compra1 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4", "approved", "paypal", "EUR", producto1, cliente);
		
		compraDao.save(compra1);
		
		Compra compra2 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4I", "approved", "paypal", "EUR", producto2, cliente);
		
		compraDao.save(compra2);
		
		Compra compra3 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC", "approved", "paypal", "EUR", producto3, cliente);
		
		compraDao.save(compra3);
		
		// buscamos las compras de productos de la tienda 1, así que deben aparecer los productos 1 y 2 pero no el 3, que es de la tienda 2
		List<Compra> listComprasTienda1 = new ArrayList<Compra>();
		listComprasTienda1.add(compra1);
		listComprasTienda1.add(compra2);
		
		List<Compra> listExpected = compraDao.getComprasByTiendaId(tienda1.getId());
		
		assertEquals(listComprasTienda1.get(0), listExpected.get(0));
		assertEquals(listComprasTienda1.get(1), listExpected.get(1));
		assertTrue(listExpected.size()==2);
		
		// ahora al revés, sólo nos debe aparecer la compra del producto 3, que procede de la tienda 2
		List<Compra> listComprasTienda2 = new ArrayList<Compra>();
		listComprasTienda2.add(compra3);
		
		List<Compra> listExpected2 = compraDao.getComprasByTiendaId(tienda2.getId());
		
		assertEquals(listComprasTienda2.get(0), listExpected2.get(0));
		assertTrue(listExpected2.size()==1);
	}
	
	@Test
	public void testCompraDaoHibernateGetCompras() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testCompraDaoHibernateGetCompras");
		
		Producto producto1 = productoDao.find(DbUtil.getTestProductoId3());
		Producto producto2 = productoDao.find(DbUtil.getTestProductoId2());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Compra compra1 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4", "approved", "paypal", "EUR", producto1, cliente);
		
		compraDao.save(compra1);
		
		Compra compra2 = new Compra(new Long(1), RecogidaState.NO_RECOGIDA, Calendar.getInstance(), 0.99, 0.99,
				"PAY-90N36598CR826520HKTXAC4I", "approved", "paypal", "EUR", producto2, cliente);
		
		compraDao.save(compra2);
		
		List<Compra> listCompras = new ArrayList<Compra>();
		listCompras.add(compra1);
		listCompras.add(compra2);
		
		List<Compra> listExpected = compraDao.getCompras();
		
		assertEquals(listCompras.get(0), listExpected.get(0));
		assertEquals(listCompras.get(1), listExpected.get(1));
	}
	
	@Test
	public void testCompraDaoHibernateGetComprasEmpty() throws InstanceNotFoundException {
		
		logger.debug("testCompraDaoHibernateGetComprasEmpty");
		
		List<Compra> list = compraDao.getCompras();
		
		assertTrue(list.isEmpty());
	}
	
	
	@Test
	public void testGetComprasByCliente() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetComprasByCliente");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long compraId1 = compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		Long compraId2 = compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		Long compraId3 = compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		List<Compra> listExpected = new ArrayList<Compra>();

		listExpected.add(compraDao.find(compraId1));
		listExpected.add(compraDao.find(compraId2));
		listExpected.add(compraDao.find(compraId3));
		
		List<CompraTO> listExpectedTO = CompraTypeConversor.toCompraTO(listExpected);

		List<CompraTO> listCompraTO = compraService.getComprasByCliente(clienteId);
		
		assertEquals(listExpectedTO, listCompraTO);
	}
	
	@Test
	public void testGetComprasByTienda() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetComprasByTienda");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long compraId1 = compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId); // tienda1
		Long compraId2 = compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId); // tienda1
		Long compraId3 = compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId); // tienda2
		
		// tienda1
		List<Compra> listExpected1 = new ArrayList<Compra>();

		listExpected1.add(compraDao.find(compraId1));
		listExpected1.add(compraDao.find(compraId2));
		
		List<CompraTO> listExpectedTO1 = CompraTypeConversor.toCompraTO(listExpected1);

		List<CompraTO> listCompraTO1 = compraService.getComprasByTienda(DbUtil.getTestTiendaId1());
		
		assertEquals(listExpectedTO1, listCompraTO1);
		
		// tienda2
		List<Compra> listExpected2 = new ArrayList<Compra>();

		listExpected2.add(compraDao.find(compraId3));
		
		List<CompraTO> listExpectedTO2 = CompraTypeConversor.toCompraTO(listExpected2);

		List<CompraTO> listCompraTO2 = compraService.getComprasByTienda(DbUtil.getTestTiendaId2());
		
		assertEquals(listExpectedTO2, listCompraTO2);
	}
	
	@Test
	public void testGetComprasById() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetComprasById");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long compraId1 = compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		CompraTO compraTO = compraService.getCompraById(compraId1);
		
		CompraTO compraTOexpected = CompraTypeConversor.toCompraTO(compraDao.find(compraId1));
		
		assertEquals(compraTO, compraTOexpected);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testGetComprasByIdNotFound() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetComprasByIdNotFound");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long compraId1 = compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		CompraTO compraTO = compraService.getCompraById(NON_EXISTENT_ID);
		
		CompraTO compraTOexpected = CompraTypeConversor.toCompraTO(compraDao.find(compraId1));
		
		assertEquals(compraTO, compraTOexpected);
	}
	
	@Test
	public void testGetCompras() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetCompras");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long compraId1 = compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		Long compraId2 = compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		Long compraId3 = compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		List<Compra> listExpected = new ArrayList<Compra>();

		listExpected.add(compraDao.find(compraId1));
		listExpected.add(compraDao.find(compraId2));
		listExpected.add(compraDao.find(compraId3));
		
		List<CompraTO> listExpectedTO = CompraTypeConversor.toCompraTO(listExpected);

		List<CompraTO> listCompraTO = compraService.getCompras();
		
		assertEquals(listExpectedTO, listCompraTO);
	}
	
	@Test
	public void testGetComprasEmpty() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetComprasById");

		List<CompraTO> listCompraTO = compraService.getCompras();
		
		assertTrue(listCompraTO.isEmpty());
	}
	
	@Test
	public void testGuardarCompra() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGuardarCompra");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		Long productoId = DbUtil.getTestProductoId2();
		Long unidades = new Long(2);
		RecogidaStateTO estadoRecogida = RecogidaStateTO.NO_RECOGIDA;
		Calendar fecha = Calendar.getInstance();
		Double precio = productoDao.find(productoId).getPrecio();
		Double total = unidades * precio;
		String idPaypal = "PAY-4U864232GH7274642KTW7JWI";
		String estadoPaypal = "approved";
		String formaPago = "paypal";
		String currency = "EUR";
		
		Long compraId = compraService.guardarCompra(
				unidades,estadoRecogida, fecha, precio, total,
				idPaypal, estadoPaypal, formaPago, currency,
				productoId, clienteId);
		
		Compra compra = compraDao.find(compraId);
		
		CompraTO compraTO = CompraTypeConversor.toCompraTO(compra);
		
		assertEquals(compraTO.getCliente().getId(), clienteId);
		assertEquals(compraTO.getProducto().getId(), productoId);
		assertEquals(compraTO.getUnidades(), unidades);
		assertEquals(compraTO.getEstadoRecogida(), estadoRecogida);
		assertEquals(compraTO.getFecha(), fecha);
		assertEquals(compraTO.getPrecio(), precio);
		assertEquals(compraTO.getTotal(), total);
		assertEquals(compraTO.getIdPaypal(), idPaypal);
		assertEquals(compraTO.getEstadoPaypal(), estadoPaypal);
		assertEquals(compraTO.getFormaPago(), formaPago);
		assertEquals(compraTO.getCurrency(), currency);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testGuardarCompraInstanceNotFound() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGuardarCompraInstanceNotFound");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		Long productoId = NON_EXISTENT_ID;
		Long unidades = new Long(2);
		RecogidaStateTO estadoRecogida = RecogidaStateTO.NO_RECOGIDA;
		Calendar fecha = Calendar.getInstance();
		Double precio = productoDao.find(productoId).getPrecio();
		Double total = unidades * precio;
		String idPaypal = "PAY-4U864232GH7274642KTW7JWI";
		String estadoPaypal = "approved";
		String formaPago = "paypal";
		String currency = "EUR";
		
		Long compraId = compraService.guardarCompra(
				unidades,estadoRecogida, fecha, precio, total,
				idPaypal, estadoPaypal, formaPago, currency,
				productoId, clienteId);
		
		Compra compra = compraDao.find(compraId);
		
		CompraTO compraTO = CompraTypeConversor.toCompraTO(compra);
		
		assertEquals(compraTO.getCliente().getId(), clienteId);
		assertEquals(compraTO.getProducto().getId(), productoId);
	}
	
	@Test
	public void testGetVentasByProductoIdDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByProductoIdDaoHibernate");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(5), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long comprasProducto2 = compraDao.getVentasByProductoId(DbUtil.getTestProductoId2());
		assertTrue(comprasProducto2 == 3);
		
		Long comprasProducto3 = compraDao.getVentasByProductoId(DbUtil.getTestProductoId3());
		assertTrue(comprasProducto3 == 5);
		
		Long comprasProducto1 = compraDao.getVentasByProductoId(DbUtil.getTestProductoId1());
		assertTrue(comprasProducto1 == 0);
		
	}
	
	@Test
	public void testGetVentasByProductoIdByRangoDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByProductoIdByRangoDaoHibernate");
		
		Calendar fecha_inicio = Calendar.getInstance();
		Calendar fecha_fin = Calendar.getInstance();
		
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		// prueba sin compras
		Long ventasCompras = compraDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasCompras == 0);
		
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
				new Long(1), RecogidaStateTO.NO_RECOGIDA, fecha_compra2,
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		// crear una compra con fecha de hace 5 días
		Calendar fecha_compra3 = Calendar.getInstance();
		fecha_compra3.add(Calendar.DAY_OF_MONTH, -5);
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, fecha_compra3,
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		// testear con un día de margen
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -1);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +1);
		logger.debug("fecha_inicio: " + fecha_inicio.getTime());
		logger.debug("fecha_fin: " + fecha_fin.getTime());
		
		ventasCompras = compraDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasCompras == 1);
		
		// testear con tres día de margen
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -2);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +2);
		
		ventasCompras = compraDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasCompras == 2);
		
		// testear un margen en el que no haya ninguna venta
		fecha_inicio.add(Calendar.DAY_OF_MONTH, +8);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +8);
		
		ventasCompras = compraDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasCompras == 0);
	}
	
	@Test
	public void testGetVentasByTiendaIdDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByTiendaIdDaoHibernate");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(4), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long comprasTienda1 = compraDao.getVentasByTiendaId(DbUtil.getTestTiendaId1());
		assertTrue(comprasTienda1 == 3);
		
		Long comprasTienda2 = compraDao.getVentasByTiendaId(DbUtil.getTestTiendaId2());
		assertTrue(comprasTienda2 == 4);
		
	}
	
	@Test
	public void testGetVentasByCategoriaIdDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByCategoriaIdDaoHibernate");
		
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
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long comprasCategoria1 = compraDao.getVentasByCategoriaId(categoria.getId());
		assertTrue(comprasCategoria1 == 6);
		
		Long comprasCategoria2 = compraDao.getVentasByCategoriaId(categoria2.getId());
		assertTrue(comprasCategoria2 == 3);
		
		Long comprasCategoria3 = compraDao.getVentasByCategoriaId(categoria3.getId());
		assertTrue(comprasCategoria3 == 0);
		
	}
	
	@Test
	public void testGetVentasByCategoriaIdByTiendaIdDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByCategoriaIdByTiendaIdDaoHibernate");
		
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
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(1), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		Long comprasCategoria1Tienda1 = compraDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(comprasCategoria1Tienda1 == 3);
		
		Long comprasCategoria1Tienda2 = compraDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(comprasCategoria1Tienda2 == 3);
		
		Long comprasCategoria2Tienda1 = compraDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(comprasCategoria2Tienda1 == 3);
		
		Long comprasCategoria2Tienda2 = compraDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(comprasCategoria2Tienda2 == 0);
		
	}
	
	@Test
	public void testGetVentasDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasDaoHibernate");
		
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
		
		List<UnidadesBlock> ventasCompras = compraDao.getVentas();
		assertTrue(ventasCompras.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasCompras = compraDao.getVentas();
		assertTrue(ventasCompras.size() == 1);
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasCompras.contains(expectedBlock));
		
		compraService.guardarCompra(
				new Long(5), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		ventasCompras = compraDao.getVentas();
		assertTrue(ventasCompras.size() == 2);
		
		expectedBlock.setUnidadesVendidas(new Long(7));
		assertTrue(ventasCompras.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(3));
		assertTrue(ventasCompras.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByTiendaIdDesgloseDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByTiendaIdDesgloseDaoHibernate");
		
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
		
		List<UnidadesBlock> ventasComprasTienda1 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasComprasTienda2 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasTienda1.isEmpty());
		assertTrue(ventasComprasTienda2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasComprasTienda1 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		ventasComprasTienda2 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasTienda1.size() == 1);
		assertTrue(ventasComprasTienda2.isEmpty());
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasComprasTienda1.contains(expectedBlock));
		
		compraService.guardarCompra(
				new Long(5), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		ventasComprasTienda1 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		ventasComprasTienda2 = compraDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasTienda1.size() == 1);
		assertTrue(ventasComprasTienda2.size() == 1);
		
		expectedBlock.setUnidadesVendidas(new Long(7));
		assertTrue(ventasComprasTienda1.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(3));
		assertTrue(ventasComprasTienda2.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByCategoriaIdDesgloseDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByCategoriaIdDesgloseDaoHibernate");
		
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
		
		List<UnidadesBlock> ventasComprasCategoria1 = compraDao.getVentasByCategoriaIdDesglose(categoria.getId());
		List<UnidadesBlock> ventasComprasCategoria2 = compraDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		
		assertTrue(ventasComprasCategoria1.isEmpty());
		assertTrue(ventasComprasCategoria2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasComprasCategoria1 = compraDao.getVentasByCategoriaIdDesglose(categoria.getId());
		ventasComprasCategoria2 = compraDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		
		assertTrue(ventasComprasCategoria1.size() == 1);
		assertTrue(ventasComprasCategoria2.size() == 1);
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasComprasCategoria1.contains(expectedBlock));
		assertTrue(ventasComprasCategoria2.contains(expectedBlock));
		
		compraService.guardarCompra(
				new Long(5), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		ventasComprasCategoria1 = compraDao.getVentasByCategoriaIdDesglose(categoria.getId());
		ventasComprasCategoria2 = compraDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		
		assertTrue(ventasComprasCategoria1.size() == 2);
		assertTrue(ventasComprasCategoria2.size() == 1);
		
		expectedBlock.setUnidadesVendidas(new Long(7));
		assertTrue(ventasComprasCategoria1.contains(expectedBlock));
		assertTrue(ventasComprasCategoria2.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(3));
		assertTrue(ventasComprasCategoria1.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByCategoriaIdByTiendaIdDesgloseDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByCategoriaIdByTiendaIdDesgloseDaoHibernate");
		
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
		
		List<UnidadesBlock> ventasComprasCategoria1Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasComprasCategoria1Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		List<UnidadesBlock> ventasComprasCategoria2Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		List<UnidadesBlock> ventasComprasCategoria2Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasCategoria1Tienda1.isEmpty());
		assertTrue(ventasComprasCategoria1Tienda2.isEmpty());
		assertTrue(ventasComprasCategoria2Tienda1.isEmpty());
		assertTrue(ventasComprasCategoria2Tienda2.isEmpty());
		
		compraService.guardarCompra(
				new Long(2), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(10), new Double(20), "PAY-1", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		
		ventasComprasCategoria1Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasComprasCategoria1Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasComprasCategoria2Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasComprasCategoria2Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasCategoria1Tienda1.size() == 1);
		assertTrue(ventasComprasCategoria1Tienda2.isEmpty());
		assertTrue(ventasComprasCategoria2Tienda1.size() == 1);
		assertTrue(ventasComprasCategoria2Tienda2.isEmpty());
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasComprasCategoria1Tienda1.contains(expectedBlock));
		assertTrue(ventasComprasCategoria2Tienda1.contains(expectedBlock));
		
		compraService.guardarCompra(
				new Long(5), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(20), new Double(20), "PAY-2", "approved", "paypal", "EUR",
				DbUtil.getTestProductoId2(), clienteId);
		compraService.guardarCompra(
				new Long(3), RecogidaStateTO.NO_RECOGIDA, Calendar.getInstance(),
				new Double(30), new Double(90), "PAY-3", "approved", "tarjeta", "EUR",
				DbUtil.getTestProductoId3(), clienteId);
		
		ventasComprasCategoria1Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasComprasCategoria1Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasComprasCategoria2Tienda1 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasComprasCategoria2Tienda2 = compraDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasComprasCategoria1Tienda1.size() == 1);
		assertTrue(ventasComprasCategoria1Tienda2.size() == 1);
		assertTrue(ventasComprasCategoria2Tienda1.size() == 1);
		assertTrue(ventasComprasCategoria2Tienda2.isEmpty());
		
		expectedBlock.setUnidadesVendidas(new Long(7));
		assertTrue(ventasComprasCategoria1Tienda1.contains(expectedBlock));
		assertTrue(ventasComprasCategoria2Tienda1.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(3));
		assertTrue(ventasComprasCategoria1Tienda2.contains(expectedBlock2));
	}
}
