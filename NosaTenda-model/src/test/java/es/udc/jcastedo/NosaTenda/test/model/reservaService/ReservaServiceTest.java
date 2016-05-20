package es.udc.jcastedo.NosaTenda.test.model.reservaService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.ReservaTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva.ReservaState;
import es.udc.jcastedo.NosaTenda.model.reserva.ReservaDao;
import es.udc.jcastedo.NosaTenda.model.reservaService.ReservaService;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.model.util.ReservaTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectStateException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.test.model.util.DbUtil;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE})
//prueba base de datos de produccion
//@ContextConfiguration (locations = {SPRING_CONFIG_FILE})
@Transactional
public class ReservaServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ReservaServiceTest.class);
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservaDao reservaDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ProductoDao productoDao;
	
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
	public void testReserveProducto() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testReserveProducto");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reserva = reservaDao.find(reservaId);
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId2());
		
		assertEquals(reserva.getUnidades(), new Long(2));
		assertEquals(reserva.getEstado().toString(), "PENDIENTE");
		assertEquals(reserva.getPrecio(), new Double(7.2));
		assertEquals(reserva.getTotal(), new Double(14.4)); // 2*7.2
		assertEquals(reserva.getProducto(), producto);
		assertEquals(reserva.getCliente(), cliente);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testReserveProductoNoCliente() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testReserveProductoNoCliente");
		
		Long clienteId = NON_EXISTENT_ID;
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reserva = reservaDao.find(reservaId);
		
		Cliente cliente = clienteDao.find(reserva.getCliente().getId());
		
		Producto producto = productoDao.find(DbUtil.getTestProductoId2());
		
		assertEquals(reserva.getUnidades(), new Long(2));
		assertEquals(reserva.getEstado().toString(), "PENDIENTE");
		assertEquals(reserva.getPrecio(), new Double(7.2));
		assertEquals(reserva.getTotal(), new Double(14.4)); // 2*7.2
		assertEquals(reserva.getProducto(), producto);
		assertEquals(reserva.getCliente(), cliente);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testReserveProductoNoProducto() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testReserveProductoNoProducto");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long productoId = NON_EXISTENT_ID;
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , productoId, clienteId);
		
		Reserva reserva = reservaDao.find(reservaId);
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Producto producto = productoDao.find(reserva.getProducto().getId());
		
		assertEquals(reserva.getUnidades(), new Long(2));
		assertEquals(reserva.getEstado().toString(), "PENDIENTE");
		assertEquals(reserva.getPrecio(), new Double(7.2));
		assertEquals(reserva.getTotal(), new Double(14.4)); // 2*7.2
		assertEquals(reserva.getProducto(), producto);
		assertEquals(reserva.getCliente(), cliente);
	}
	
	@Test
	public void testReservaDaoHibernateFind() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testReservaDaoHibernateFind");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reservaId2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		List<Reserva> reservas = reservaDao.getReservas(clienteId);
		
		Reserva reserva1 = reservaDao.find(reservaId1);
		Reserva reserva2 = reservaDao.find(reservaId2);
		
		assertEquals(reservas.size(), 2);
		
		// comparacion de ids
		assertEquals(reservas.get(0).getId(), reservaId1);
		assertEquals(reservas.get(1).getId(), reservaId2);
		
		// comparacion de objetos
		assertEquals(reservas.get(0), reserva1);
		assertEquals(reservas.get(1), reserva2);
	}
	
	@Test
	public void testGetReservasById() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetReservasById");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reservaId2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reservaId3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		List<Reserva> listExpected = new ArrayList<Reserva>();
		
		listExpected.add(reservaDao.find(reservaId1));
		listExpected.add(reservaDao.find(reservaId2));
		listExpected.add(reservaDao.find(reservaId3));
		
		List<ReservaTO> listExpectedTO = ReservaTypeConversor.toReservaTO(listExpected);
		
		List<ReservaTO> listReservaTO = reservaService.getReservasById(clienteId);
		
		assertEquals(listExpectedTO, listReservaTO);
	}
	
	@Test
	public void testGetReservasByIdEmpty() throws InstanceNotFoundException, DuplicateInstanceException, BadFormatRequestException {
		
		logger.debug("testGetReservasByIdEmpty");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		List<ReservaTO> listReservaTO = reservaService.getReservasById(clienteId);
		
		assertTrue(listReservaTO.isEmpty());
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testGetReservasByIdNotFound() throws InstanceNotFoundException, DuplicateInstanceException, BadFormatRequestException {
		
		logger.debug("testGetReservasByIdNotFound");
		
		userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		List<ReservaTO> listReservaTO = reservaService.getReservasById(NON_EXISTENT_ID);
		
		assertTrue(listReservaTO.isEmpty());
	}
	
	@Test
	public void testCancelarReserva() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException, IncorrectStateException {
		
		logger.debug("testCancelarReserva");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reservaInicial = reservaDao.find(reservaId);
		assertEquals(reservaInicial.getEstado(), ReservaState.PENDIENTE);
		
		reservaService.cancelarReserva(reservaId);
		
		Reserva reservaExpected = reservaDao.find(reservaId);
		assertEquals(reservaExpected.getEstado(), ReservaState.CANCELADA);
		
		assertEquals(reservaInicial, reservaExpected);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testCancelarReservaNotFound() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException, IncorrectStateException {
		
		logger.debug("testCancelarReservaNotFound");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reservaInicial = reservaDao.find(reservaId);
		assertEquals(reservaInicial.getEstado(), ReservaState.PENDIENTE);
		
		reservaService.cancelarReserva(NON_EXISTENT_ID);
		
		Reserva reservaExpected = reservaDao.find(reservaId);
		assertEquals(reservaExpected.getEstado(), ReservaState.CANCELADA);
		
		assertEquals(reservaInicial, reservaExpected);
		
	}
	
	@Test(expected = IncorrectStateException.class)
	public void testCancelarReservaIncorrectState() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException, IncorrectStateException {
		
		logger.debug("testCancelarReservaNotFound");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.CANCELADA, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reservaInicial = reservaDao.find(reservaId);
		assertEquals(reservaInicial.getEstado(), ReservaState.CANCELADA);
		
		// al ya estar cancelada, aqui deberia saltar IncorrectStateException
		reservaService.cancelarReserva(reservaId);
		
		Reserva reservaExpected = reservaDao.find(reservaId);
		assertEquals(reservaExpected.getEstado(), ReservaState.CANCELADA);
		
		assertEquals(reservaInicial, reservaExpected);
		
	}
	
	@Test
	public void testGetReservas() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetReservas");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reservaId2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reservaId3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		List<Reserva> listExpected = new ArrayList<Reserva>();
		
		listExpected.add(reservaDao.find(reservaId1));
		listExpected.add(reservaDao.find(reservaId2));
		listExpected.add(reservaDao.find(reservaId3));
		
		List<ReservaTO> listExpectedTO = ReservaTypeConversor.toReservaTO(listExpected);
		
		List<ReservaTO> listReservaTO = reservaService.getReservas();
		
		assertEquals(listExpectedTO, listReservaTO);
	}
	
	@Test
	public void testGetReservasEmpty() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetReservasEmpty");
		
		List<ReservaTO> listReservaTO = reservaService.getReservas();
		
		assertTrue(listReservaTO.isEmpty());
	}
	
	@Test
	public void testEntregarReserva() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testEntregarReserva");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reservaInicial = reservaDao.find(reservaId);
		assertEquals(reservaInicial.getEstado(), ReservaState.PENDIENTE);
		
		reservaService.entregarReserva(reservaId);
		
		Reserva reservaExpected = reservaDao.find(reservaId);
		assertEquals(reservaExpected.getEstado(), ReservaState.ENTREGADA);
		
		assertEquals(reservaInicial, reservaExpected);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testEntregarReservaNotFound() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testEntregarReserva");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reservaInicial = reservaDao.find(reservaId);
		assertEquals(reservaInicial.getEstado(), ReservaState.PENDIENTE);
		
		reservaService.entregarReserva(NON_EXISTENT_ID);
		
		Reserva reservaExpected = reservaDao.find(reservaId);
		assertEquals(reservaExpected.getEstado(), ReservaState.ENTREGADA);
		
		assertEquals(reservaInicial, reservaExpected);
		
	}
	
	@Test
	public void testGetVentasByProductoIdDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetVentasByProductoIdDaoHibernate");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		Long reservasProducto2 = reservaDao.getVentasByProductoId(DbUtil.getTestProductoId2());
		assertTrue(reservasProducto2 == 0);
		
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		reservasProducto2 = reservaDao.getVentasByProductoId(DbUtil.getTestProductoId2());
		assertTrue(reservasProducto2 == 3);
		
		Long reservasProducto3 = reservaDao.getVentasByProductoId(DbUtil.getTestProductoId3());
		assertTrue(reservasProducto3 == 3);
		
		Long reservasProducto1 = reservaDao.getVentasByProductoId(DbUtil.getTestProductoId1());
		assertTrue(reservasProducto1 == 0);
	}
	
	@Test
	public void testGetVentasByProductoIdByRangoDaoHibernate() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByProductoIdByRangoDaoHibernate");
		
		Calendar fecha_inicio = Calendar.getInstance();
		Calendar fecha_fin = Calendar.getInstance();
		Calendar fecha_reserva = Calendar.getInstance();
		
		logger.debug("fecha_reserva: " + fecha_reserva.getTime());
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		// prueba sin reservas
		Long ventasReserva = reservaDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasReserva == 0);
		
		// crear una reserva entregada con fecha de hoy
		Long reserva1long = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		// crear una reserva entregada con fecha de hace tres días
		Long reserva2long = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		fecha_reserva.add(Calendar.DAY_OF_MONTH, -3);
		logger.debug("fecha_reserva: " + fecha_reserva.getTime());
		
		Reserva reserva2 = reservaDao.find(reserva2long);
		reserva2.setFecha(fecha_reserva);
		reservaDao.save(reserva2);
		
		// crear una reserva igual a alguna de las anteriores pero no entregada
		reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		// marcamos como entregadas las dos reservas
		reservaService.entregarReserva(reserva1long);
		reservaService.entregarReserva(reserva2long);
		
		// testear con un día de margen
		logger.debug("fecha_inicio: " + fecha_inicio.getTime());
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -1);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +1);
		logger.debug("fecha_inicio: " + fecha_inicio.getTime());
		logger.debug("fecha_fin: " + fecha_fin.getTime());
		logger.debug("reserva2: " + reserva2.getFecha().getTime());
		
		ventasReserva = reservaDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		logger.debug("ventasReserva: " + ventasReserva);
		assertTrue(ventasReserva == 1);
		
		// testear con tres días de margen
		fecha_inicio.add(Calendar.DAY_OF_MONTH, -2);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +2);
		ventasReserva = reservaDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasReserva == 2);
		
		// testear un margen en el que no haya ninguna venta
		fecha_inicio.add(Calendar.DAY_OF_MONTH, +8);
		fecha_fin.add(Calendar.DAY_OF_MONTH, +8);
		ventasReserva = reservaDao.getVentasByProductoIdByRango(DbUtil.getTestProductoId2(), fecha_inicio, fecha_fin);
		assertTrue(ventasReserva == 0);
	}
	
	@Test
	public void testGetVentasByTiendaIdDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
		logger.debug("testGetVentasByTiendaIdDaoHibernate");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		Long reservasTienda1 = reservaDao.getVentasByTiendaId(DbUtil.getTestTiendaId1());
		assertTrue(reservasTienda1 == 0);
		
		Long reservasTienda2 = reservaDao.getVentasByTiendaId(DbUtil.getTestTiendaId2());
		assertTrue(reservasTienda2 == 0);
		
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		reservasTienda1 = reservaDao.getVentasByTiendaId(DbUtil.getTestTiendaId1());
		assertTrue(reservasTienda1 == 3);
		
		reservasTienda2 = reservaDao.getVentasByTiendaId(DbUtil.getTestTiendaId2());
		assertTrue(reservasTienda2 == 3);

	}
	
	@Test
	public void testGetVentasByCategoriaIdDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		Long reservasCategoria1 = reservaDao.getVentasByCategoriaId(categoria.getId());
		assertTrue(reservasCategoria1 == 0);
		
		Long reservasCategoria2 = reservaDao.getVentasByCategoriaId(categoria2.getId());
		assertTrue(reservasCategoria2 == 0);
		
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		reservasCategoria1 = reservaDao.getVentasByCategoriaId(categoria.getId());
		assertTrue(reservasCategoria1 == 6);
		
		reservasCategoria2 = reservaDao.getVentasByCategoriaId(categoria2.getId());
		assertTrue(reservasCategoria2 == 3);
		
		Long reservasCategoria3 = reservaDao.getVentasByCategoriaId(categoria3.getId());
		assertTrue(reservasCategoria3 == 0);

	}
	
	@Test
	public void testGetVentasByCategoriaIdByTiendaIdDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(3), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		Long reservasCategoria1Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(reservasCategoria1Tienda1 == 0);
		
		Long reservasCategoria1Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(reservasCategoria1Tienda2 == 0);
		
		Long reservasCategoria2Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(reservasCategoria2Tienda1 == 0);
		
		Long reservasCategoria2Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(reservasCategoria2Tienda2 == 0);
		
		reservaService.entregarReserva(reserva1);
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		reservasCategoria1Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(reservasCategoria1Tienda1 == 3);
		
		reservasCategoria1Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(reservasCategoria1Tienda2 == 3);
		
		reservasCategoria2Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(reservasCategoria2Tienda1 == 3);
		
		reservasCategoria2Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaId(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(reservasCategoria2Tienda2 == 0);

	}
	
	@Test
	public void testGetVentasDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(4), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		List<UnidadesBlock> ventasReservas = reservaDao.getVentas();
		assertTrue(ventasReservas.isEmpty());
		
		reservaService.entregarReserva(reserva1);
		
		ventasReservas = reservaDao.getVentas();
		assertTrue(ventasReservas.size() == 1);
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasReservas.contains(expectedBlock));
		
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		ventasReservas = reservaDao.getVentas();
		assertTrue(ventasReservas.size() == 2);
		
		expectedBlock.setUnidadesVendidas(new Long(3));
		assertTrue(ventasReservas.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(4));
		assertTrue(ventasReservas.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByTiendaIdDesgloseDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(4), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		List<UnidadesBlock> ventasReservasTienda1 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		assertTrue(ventasReservasTienda1.isEmpty());
		
		List<UnidadesBlock> ventasReservasTienda2 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		assertTrue(ventasReservasTienda2.isEmpty());
		
		reservaService.entregarReserva(reserva1);
		
		ventasReservasTienda1 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		ventasReservasTienda2 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasReservasTienda1.size() == 1);
		assertTrue(ventasReservasTienda2.isEmpty());
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasReservasTienda1.contains(expectedBlock));
		
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		ventasReservasTienda1 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId1());
		ventasReservasTienda2 = reservaDao.getVentasByTiendaIdDesglose(DbUtil.getTestTiendaId2());
		
		assertTrue(ventasReservasTienda1.size() == 1);
		assertTrue(ventasReservasTienda2.size() == 1);
		
		expectedBlock.setUnidadesVendidas(new Long(3));
		assertTrue(ventasReservasTienda1.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(4));
		assertTrue(ventasReservasTienda2.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByCategoriaIdDesgloseDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(4), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		List<UnidadesBlock> ventasReservasCategoria1 = reservaDao.getVentasByCategoriaIdDesglose(categoria.getId());
		assertTrue(ventasReservasCategoria1.isEmpty());
		
		List<UnidadesBlock> ventasReservasCategoria2 = reservaDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		assertTrue(ventasReservasCategoria2.isEmpty());
		
		reservaService.entregarReserva(reserva1);
		
		ventasReservasCategoria1 = reservaDao.getVentasByCategoriaIdDesglose(categoria.getId());
		ventasReservasCategoria2 = reservaDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		
		assertTrue(ventasReservasCategoria1.size() == 1);
		assertTrue(ventasReservasCategoria2.size() == 1);
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasReservasCategoria1.contains(expectedBlock));
		assertTrue(ventasReservasCategoria2.contains(expectedBlock));
		
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		ventasReservasCategoria1 = reservaDao.getVentasByCategoriaIdDesglose(categoria.getId());
		ventasReservasCategoria2 = reservaDao.getVentasByCategoriaIdDesglose(categoria2.getId());
		
		assertTrue(ventasReservasCategoria1.size() == 2);
		assertTrue(ventasReservasCategoria2.size() == 1);
		
		expectedBlock.setUnidadesVendidas(new Long(3));
		assertTrue(ventasReservasCategoria1.contains(expectedBlock));
		assertTrue(ventasReservasCategoria2.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(4));
		assertTrue(ventasReservasCategoria1.contains(expectedBlock2));
	}
	
	@Test
	public void testGetVentasByCategoriaIdByTiendaIdDesgloseDaoHibernate() throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, DuplicateInstanceException {
		
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
		
		Long reserva1 = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		Long reserva3 = reservaService.reserveProducto(new Long(4), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId3(), clienteId);
		
		List<UnidadesBlock> ventasReservasCategoria1Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		assertTrue(ventasReservasCategoria1Tienda1.isEmpty());
		
		List<UnidadesBlock> ventasReservasCategoria1Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		assertTrue(ventasReservasCategoria1Tienda2.isEmpty());
		
		List<UnidadesBlock> ventasReservasCategoria2Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		assertTrue(ventasReservasCategoria2Tienda1.isEmpty());
		
		List<UnidadesBlock> ventasReservasCategoria2Tienda12 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		assertTrue(ventasReservasCategoria2Tienda12.isEmpty());
		
		reservaService.entregarReserva(reserva1);
		
		ventasReservasCategoria1Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasReservasCategoria1Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasReservasCategoria2Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasReservasCategoria2Tienda12 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasReservasCategoria1Tienda1.size() == 1);
		assertTrue(ventasReservasCategoria1Tienda2.isEmpty());
		assertTrue(ventasReservasCategoria2Tienda1.size() == 1);
		assertTrue(ventasReservasCategoria2Tienda12.isEmpty());
		
		UnidadesBlock expectedBlock = new UnidadesBlock(DbUtil.getTestProductoId2(), new Long(2));
		assertTrue(ventasReservasCategoria1Tienda1.contains(expectedBlock));
		assertTrue(ventasReservasCategoria2Tienda1.contains(expectedBlock));
		
		reservaService.entregarReserva(reserva2);
		reservaService.entregarReserva(reserva3);
		
		ventasReservasCategoria1Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId1());
		ventasReservasCategoria1Tienda2 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria.getId(), DbUtil.getTestTiendaId2());
		ventasReservasCategoria2Tienda1 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId1());
		ventasReservasCategoria2Tienda12 = reservaDao.getVentasByCategoriaIdByTiendaIdDesglose(categoria2.getId(), DbUtil.getTestTiendaId2());
		
		assertTrue(ventasReservasCategoria1Tienda1.size() == 1);
		assertTrue(ventasReservasCategoria1Tienda2.size() == 1);
		assertTrue(ventasReservasCategoria2Tienda1.size() == 1);
		assertTrue(ventasReservasCategoria2Tienda12.isEmpty());
		
		expectedBlock.setUnidadesVendidas(new Long(3));
		assertTrue(ventasReservasCategoria1Tienda1.contains(expectedBlock));
		assertTrue(ventasReservasCategoria2Tienda1.contains(expectedBlock));
		
		UnidadesBlock expectedBlock2 = new UnidadesBlock(DbUtil.getTestProductoId3(), new Long(4));
		assertTrue(ventasReservasCategoria1Tienda2.contains(expectedBlock2));
	}
	
	@Test
	public void testReservaLazy() throws DuplicateInstanceException, InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		logger.debug("testGetVentasByCategoriaIdByTiendaIdDesgloseDaoHibernate");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Long reservaId = reservaService.reserveProducto(new Long(2), ReservaStateTO.PENDIENTE, new Double(7.2) , DbUtil.getTestProductoId2(), clienteId);
		
		Reserva reserva = reservaDao.find(reservaId);
		
		//EAGER
		assertTrue(Hibernate.isPropertyInitialized(reserva, "producto"));
		//LAZY
		//assertTrue(!Hibernate.isPropertyInitialized(reserva, "cliente"));
	}
}
