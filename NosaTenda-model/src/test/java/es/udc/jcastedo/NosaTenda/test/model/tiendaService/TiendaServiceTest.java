package es.udc.jcastedo.NosaTenda.test.model.tiendaService;

import static es.udc.jcastedo.NosaTenda.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.jcastedo.NosaTenda.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.MetodoEnvioTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoDao;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.localidad.LocalidadDao;
import es.udc.jcastedo.NosaTenda.model.metodoEnvio.MetodoEnvio;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.tiendaService.TiendaService;
import es.udc.jcastedo.NosaTenda.model.util.CategoriaTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.Haversine;
import es.udc.jcastedo.NosaTenda.model.util.MetodoEnvioTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.TiendaTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.test.model.util.DbUtil;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE})
//prueba base de datos de produccion
//@ContextConfiguration (locations = {SPRING_CONFIG_FILE})
@Transactional
public class TiendaServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(TiendaServiceTest.class);
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TiendaService tiendaService;
	
	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private LocalidadDao localidadDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EmpleadoDao empleadoDao;
	
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
	public void testGetTiendaById() 
			throws InstanceNotFoundException {
		
		logger.debug("testTiendaById");
		
		Long testTiendaId1 = DbUtil.getTestTiendaId1();
		
		TiendaTO testTiendaExpected = tiendaService.getTiendaById(testTiendaId1);
		TiendaTO testTienda = TiendaTypeConversor.toTiendaTO(tiendaDao.find(testTiendaId1));
		
		assertEquals(testTiendaExpected, testTienda);
	}
	
	@Test
	public void testGetTiendaByIdBis()
			throws InstanceNotFoundException {
		
		logger.debug("testGetTiendaByIdBis");
		
		Localidad localidadTest = localidadDao.find(DbUtil.getTestLocalidadId());
		
		Tienda tiendaTest = new Tienda("tienda1test", "direccion1test", "00000003", "tienda3@test.com", "539295939", localidadTest);
		
		tiendaDao.save(tiendaTest);
		
		Long testTiendaId = tiendaTest.getId();
		
		TiendaTO tiendaTOTestExpected = tiendaService.getTiendaById(testTiendaId);
		
		assertEquals(tiendaTOTestExpected, TiendaTypeConversor.toTiendaTO(tiendaTest));
	}

	
	@Test(expected = InstanceNotFoundException.class)
	public void testGetNonExistentTienda() 
			throws InstanceNotFoundException {
		
		logger.debug("testGetNonExistentTienda");
		
		Localidad localidadTest = localidadDao.find(DbUtil.getTestLocalidadId());
		
		Tienda tiendaTest = new Tienda("tienda1test", "direccion1test", "00000003", "tienda3@test.com", "539295939", localidadTest);
		
		tiendaDao.save(tiendaTest);
		
		Long testTiendaId = NON_EXISTENT_ID;
		
		TiendaTO tiendaTOTestExpected = tiendaService.getTiendaById(testTiendaId);
		
		assertEquals(tiendaTOTestExpected, TiendaTypeConversor.toTiendaTO(tiendaTest));
		
	}
	
	@Test
	public void testGetTiendas()
			throws InstanceNotFoundException {
		
		logger.debug("testGetTiendas");
		
		Localidad localidadTest = localidadDao.find(DbUtil.getTestLocalidadId());
		
		Tienda tienda1 = new Tienda("tienda1", "direccion1", "00000003", "tienda3@test.com", "539295939", localidadTest);
		Tienda tienda2 = new Tienda("tienda2", "direccion2", "00000004", "tienda4@test.com", "324586854", localidadTest);
		Tienda tienda3 = new Tienda("tienda3", "direccion3", "00000005", "tienda5@test.com", "697985848", localidadTest);
		
		tiendaDao.save(tienda1);
		tiendaDao.save(tienda2);
		tiendaDao.save(tienda3);
		
		List<Tienda> listExpected = new ArrayList<Tienda>();
		listExpected.add(tiendaDao.find(DbUtil.getTestTiendaId1()));
		listExpected.add(tiendaDao.find(DbUtil.getTestTiendaId2()));
		listExpected.add(tienda1);
		listExpected.add(tienda2);
		listExpected.add(tienda3);
		
		List<TiendaTO> listExpectedTO = TiendaTypeConversor.toTiendaTO(listExpected);
		
		List<TiendaTO> listTiendaTOs = tiendaService.getTiendas();
		
		assertEquals(listExpectedTO, listTiendaTOs);
	}
	
	@Test
	public void getTiendasEmptyList()
			throws InstanceNotFoundException {
		
		logger.debug("testGetTiendas");
		
		// borramos también productos para no violar constraint de clave foránea
		productoDao.remove(DbUtil.getTestProductoId1());
		productoDao.remove(DbUtil.getTestProductoId2());
		productoDao.remove(DbUtil.getTestProductoId3());
		
		tiendaDao.remove(DbUtil.getTestTiendaId1());
		tiendaDao.remove(DbUtil.getTestTiendaId2());
		
		List<TiendaTO> tiendaTOs = tiendaService.getTiendas();
		
		assertTrue(tiendaTOs.isEmpty());
	}
	
	@Test
	public void testGetTiendasCercanas() throws InstanceNotFoundException {
		
		logger.debug("testGetTiendasCercanas");
		
		Localidad localidadTest = localidadDao.find(DbUtil.getTestLocalidadId());
		
		Tienda tiendaTestCampus = new Tienda("tienda1test", "direccion1test", "", "", "00000003", "tienda3@test.com", "www.tiendaweb.es", "539295939", "539295940", "539295941", "http://i.imgur.com/q6DdE48.jpg", 43.369282, -8.419524, localidadTest);
		
		tiendaDao.save(tiendaTestCampus);
		
		Tienda tiendaTestFemenino = new Tienda("tienda2test", "direccion2test", "", "", "00000004", "tienda4@test.com", "www.tienda2web.es", "539295901", "539295902", "539295903", "http://i.imgur.com/q6DdE48.jpg", 43.368608, -8.407156, localidadTest);
		
		tiendaDao.save(tiendaTestFemenino);
		
		double dist = Haversine.distFrom(tiendaTestCampus.getLat(), tiendaTestCampus.getLon(), tiendaTestFemenino.getLat(), tiendaTestFemenino.getLon());
		
		logger.debug("dist = " + dist);
		
		assertTrue(dist < 1005);
		
		// punto intermedio, las dos tiendas en rango de menos de 1 km
		List<TiendaTO> tiendaTOs = tiendaService.getTiendasCercanas(43.367978, -8.410732, 1000.0);
		
		List<TiendaTO> tiendaTOsExpected = new ArrayList<TiendaTO>();
		tiendaTOsExpected.add(TiendaTypeConversor.toTiendaTO(tiendaTestCampus));
		tiendaTOsExpected.add(TiendaTypeConversor.toTiendaTO(tiendaTestFemenino));
		
		assertTrue(tiendaTOs.equals(tiendaTOsExpected));
		
		// punto intermedio pero rango de sólo 100m, ninguna tienda debería estar tan cerca
		tiendaTOs = tiendaService.getTiendasCercanas(43.367978, -8.410732, 100.0);
		
		assertTrue(tiendaTOs.isEmpty());
		
		// una tienda está en rango, la otra no por poco
		tiendaTOs = tiendaService.getTiendasCercanas(43.369204, -8.420125, 1000.0);
		
		tiendaTOsExpected.clear();
		tiendaTOsExpected.add(TiendaTypeConversor.toTiendaTO(tiendaTestCampus));
		
		assertTrue(tiendaTOs.equals(tiendaTOsExpected));
	}
	
	@Test
	public void testGetTiendasFavDaoHibernate() throws InstanceNotFoundException {
		
		logger.debug("testGetTiendasFavDaoHibernate");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		//clienteDao.save(cliente);
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda1);
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);
		
		List<Tienda> tiendasFavoritas = tiendaDao.getTiendasFav(new Long(cliente.getId()));
		logger.debug("tiendas favoritas: " + tiendasFavoritas.size());
		for (int i=0; i<tiendasFavoritas.size(); i++) {
			logger.debug("Tienda " + i + ": " + tiendasFavoritas.get(i).getNombre() + " " + tiendasFavoritas.get(i).getId());
		}
		
		assertTrue(tiendasFavoritas.size() == 2);
	}
	
	@Test
	public void testGetTiendasFav() throws InstanceNotFoundException {
		
		logger.debug("testGetTiendasFav");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		//clienteDao.save(cliente);
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda1);
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);
		
		List<TiendaTO> tiendasFavTO = tiendaService.getTiendasFav(cliente.getId());
		
		List<TiendaTO> tiendasFavTOExpected = new ArrayList<TiendaTO>();
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tienda1));
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tienda2));
		
		assertTrue(tiendasFavTO.equals(tiendasFavTOExpected));
	}
	
	@Test
	public void testFavTienda() throws InstanceNotFoundException {
		
		logger.debug("testFavTienda");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		clienteDao.save(cliente);
		
		Long clienteId = cliente.getId();
		
		Long tiendaId1 = DbUtil.getTestTiendaId1();
		Long tiendaId2 = DbUtil.getTestTiendaId2();
		
		tiendaService.favTienda(clienteId, tiendaId1);
		tiendaService.favTienda(clienteId, tiendaId2);
		
		List<TiendaTO> tiendasFavTO = tiendaService.getTiendasFav(clienteId);
		
		List<TiendaTO> tiendasFavTOExpected = new ArrayList<TiendaTO>();
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tiendaDao.find(tiendaId1)));
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tiendaDao.find(tiendaId2)));
		
		assertTrue(tiendasFavTO.equals(tiendasFavTOExpected));
		
		// probamos que no pasa nada si intentamos hacer favoritos
		// clientes o tiendas que no existen
		tiendaService.favTienda(NON_EXISTENT_ID, NON_EXISTENT_ID);
		tiendaService.favTienda(clienteId, NON_EXISTENT_ID);
		tiendaService.favTienda(NON_EXISTENT_ID, tiendaId1);
		
		tiendasFavTO = tiendaService.getTiendasFav(clienteId);
		
		assertTrue(tiendasFavTO.equals(tiendasFavTOExpected));
	}
	
	@Test
	public void testUnfavTienda() throws InstanceNotFoundException {
		
		logger.debug("testUnfavTienda");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		cliente.getFavoritas().add(tienda1);
		cliente.getFavoritas().add(tienda2);
		clienteDao.save(cliente);
		
		List<TiendaTO> tiendasFavTOExpected = new ArrayList<TiendaTO>();
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tienda1));
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tienda2));
		
		List<TiendaTO> tiendasFav = tiendaService.getTiendasFav(cliente.getId());
		
		assertTrue(tiendasFav.equals(tiendasFavTOExpected));
		
		tiendaService.unfavTienda(cliente.getId(), tienda1.getId());
		
		tiendasFavTOExpected.clear();
		tiendasFavTOExpected.add(TiendaTypeConversor.toTiendaTO(tienda2));
		
		tiendasFav = tiendaService.getTiendasFav(cliente.getId());
		
		assertTrue(tiendasFav.equals(tiendasFavTOExpected));
		
		tiendaService.unfavTienda(cliente.getId(), tienda2.getId());
		
		tiendasFavTOExpected.clear();
		
		tiendasFav = tiendaService.getTiendasFav(cliente.getId());
		
		assertTrue(tiendasFav.equals(tiendasFavTOExpected));
		
		// probamos que no pasa nada si intentamos borrar favoritos
		// clientes o tiendas que no existen
		tiendaService.unfavTienda(cliente.getId(), tienda1.getId());
		tiendaService.unfavTienda(NON_EXISTENT_ID, tienda1.getId());
		tiendaService.unfavTienda(cliente.getId(), NON_EXISTENT_ID);
		
		tiendasFav = tiendaService.getTiendasFav(cliente.getId());
		
		assertTrue(tiendasFav.isEmpty());
	}
	
	@Test
	public void testIsFavTienda() throws InstanceNotFoundException {
		
		logger.debug("testIsFavTienda");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		cliente.getFavoritas().add(tienda1);
		clienteDao.save(cliente);
		
		assertTrue(tiendaService.isFavTienda(cliente.getId(), tienda1.getId()));
		
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		assertTrue(!tiendaService.isFavTienda(cliente.getId(), tienda2.getId()));
	}
	
	@Test(expected=InstanceNotFoundException.class)
	public void testIsFavTiendaInstanceNotFound() throws InstanceNotFoundException {
		
		logger.debug("testIsFavTienda");
		
		Cliente cliente = new Cliente("cliente@correo.es", "pojo");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		cliente.getFavoritas().add(tienda1);
		clienteDao.save(cliente);
		
		assertTrue(tiendaService.isFavTienda(cliente.getId(), NON_EXISTENT_ID));
	}
	
	@Test
	public void testMetodosEnvioByTienda() throws InstanceNotFoundException {
		
		logger.debug("testMetodosEnvioByTienda");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		List<MetodoEnvioTO> metodoEnvioTOs = new ArrayList<MetodoEnvioTO>();
		
		metodoEnvioTOs = tiendaService.getMetodosEnvioByTienda(tienda1.getId());
		
		assertTrue(metodoEnvioTOs.isEmpty());
		
		
		MetodoEnvio metodoEnvio1 = new MetodoEnvio();
		metodoEnvio1.setDescripcion("metodoEnvio1");
		
		MetodoEnvio metodoEnvio2 = new MetodoEnvio();
		metodoEnvio2.setDescripcion("metodoEnvio2");
		
		MetodoEnvio metodoEnvio3 = new MetodoEnvio();
		metodoEnvio3.setDescripcion("metodoEnvio3");
		
		tienda1.getMetodosEnvio().add(metodoEnvio1);
		tienda1.getMetodosEnvio().add(metodoEnvio2);
		tienda1.getMetodosEnvio().add(metodoEnvio3);
		
		// los metodoEnvio se deberian guardar en cascada
		tiendaDao.save(tienda1);
		
		List<MetodoEnvioTO> metodoEnvioTOsExpected = new ArrayList<MetodoEnvioTO>();
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio1));
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio2));
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio3));
		
		metodoEnvioTOs = tiendaService.getMetodosEnvioByTienda(tienda1.getId());
		
		// dado que la lista de metodoEnvio no tiene un orden predecible, las comparamos así
		assertTrue(metodoEnvioTOs.size() == metodoEnvioTOsExpected.size());
		assertTrue(metodoEnvioTOs.containsAll(metodoEnvioTOsExpected));
	}
	
	@Test(expected=InstanceNotFoundException.class)
	public void testMetodosEnvioByTiendaInstanceNotFound() throws InstanceNotFoundException {
		
		logger.debug("testMetodosEnvioByTienda");
		
		Tienda tienda1 = tiendaDao.find(NON_EXISTENT_ID);
		
		List<MetodoEnvioTO> metodoEnvioTOs = new ArrayList<MetodoEnvioTO>();
		
		metodoEnvioTOs = tiendaService.getMetodosEnvioByTienda(tienda1.getId());
		
		assertTrue(metodoEnvioTOs.isEmpty());
		
		
		MetodoEnvio metodoEnvio1 = new MetodoEnvio();
		metodoEnvio1.setDescripcion("metodoEnvio1");
		
		MetodoEnvio metodoEnvio2 = new MetodoEnvio();
		metodoEnvio2.setDescripcion("metodoEnvio2");
		
		MetodoEnvio metodoEnvio3 = new MetodoEnvio();
		metodoEnvio3.setDescripcion("metodoEnvio3");
		
		tienda1.getMetodosEnvio().add(metodoEnvio1);
		tienda1.getMetodosEnvio().add(metodoEnvio2);
		tienda1.getMetodosEnvio().add(metodoEnvio3);
		
		// los metodoEnvio se deberian guardar en cascada
		tiendaDao.save(tienda1);
		
		List<MetodoEnvioTO> metodoEnvioTOsExpected = new ArrayList<MetodoEnvioTO>();
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio1));
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio2));
		metodoEnvioTOsExpected.add(MetodoEnvioTypeConversor.toMetodoEnvioTO(metodoEnvio3));
		
		metodoEnvioTOs = tiendaService.getMetodosEnvioByTienda(tienda1.getId());
		
		// dado que la lista de metodoEnvio no tiene un orden predecible, las comparamos así
		assertTrue(metodoEnvioTOs.size() == metodoEnvioTOsExpected.size());
		assertTrue(metodoEnvioTOs.containsAll(metodoEnvioTOsExpected));
	}
	
	// no he conseguido que funcione para propiedades, solo colecciones
	@Test
	public void testTiendaLazy() throws InstanceNotFoundException {
		
		logger.debug("testTiendaLazy");
		
		// puse esto porque pense que se estaba inicializando la propiedad localidad
		// de tienda en la sesion antes de tiempo, pero no parece tener que ver
		Session session = sessionFactory.getCurrentSession();
		session.clear();
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		//LAZY
		//assertTrue(!Hibernate.isPropertyInitialized(tienda1, "localidad"));
		//LAZY
		assertTrue(!Hibernate.isInitialized(tienda1.getCategorias()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(tienda1.getClientes()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(tienda1.getEmpleados()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(tienda1.getProductos()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(tienda1.getMetodosEnvio()));
		
		
		MetodoEnvio metodoEnvio1 = new MetodoEnvio();
		metodoEnvio1.setDescripcion("metodoEnvio1");
		
		tienda1.getMetodosEnvio().add(metodoEnvio1);
		
		// los metodoEnvio se deberian guardar en cascada
		tiendaDao.save(tienda1);
		
		//initialized
		assertTrue(Hibernate.isInitialized(tienda1.getMetodosEnvio()));
		//LAZY
		//assertTrue(!Hibernate.isPropertyInitialized(metodoEnvio1, "tienda"));
	}
	
	@Test
	public void testGetTiendasByEmpleado() throws InstanceNotFoundException {
		
		logger.debug("testGetTiendasByEmpleado");
		
		String testCorreo = "empleado@test.es";

		Empleado empleado = new Empleado(testCorreo, "pojo");
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		Tienda tienda2 = tiendaDao.find(DbUtil.getTestTiendaId2());
		
		empleado.getTrabaja().add(tienda1);
		empleado.getTrabaja().add(tienda2);
		
		empleadoDao.save(empleado);
		
		List<TiendaTO> expectedTiendaTOs = new ArrayList<TiendaTO>();
		expectedTiendaTOs.add(TiendaTypeConversor.toTiendaTO(tienda1));
		expectedTiendaTOs.add(TiendaTypeConversor.toTiendaTO(tienda2));

		List<TiendaTO> tiendaTOs = tiendaService.getTiendasByEmpleado(testCorreo);
		
		// dado que la lista de tiendaTOs no tiene un orden predecible, las comparamos así
		assertTrue(expectedTiendaTOs.size() == tiendaTOs.size());
		assertTrue(tiendaTOs.containsAll(expectedTiendaTOs));
	}
	
	@Test
	public void testGetCategoriasByTienda() throws InstanceNotFoundException {
		
		logger.debug("testGetCategoriasByTienda");
		
		List<CategoriaTO> categoriaTOs = new ArrayList<CategoriaTO>();
		
		// la tienda no existe (no deben saltar excepciones por ello)
		categoriaTOs = tiendaService.getCategoriasByTienda(NON_EXISTENT_ID);
		
		assertTrue(categoriaTOs.isEmpty());
		
		
		
		Tienda tienda1 = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		// la tienda no tiene categorias
		categoriaTOs = tiendaService.getCategoriasByTienda(tienda1.getId());
		
		assertTrue(categoriaTOs.isEmpty());
		
		Categoria categoria = new Categoria("Alpha");
		Categoria categoria2 = new Categoria("Beta");
		
		categoriaDao.save(categoria);
		categoriaDao.save(categoria2);
		
		// probamos con una categoria
		tienda1.getCategorias().add(categoria);
		tiendaDao.save(tienda1);
		
		List<CategoriaTO> expectedCategoriaTOs = new ArrayList<CategoriaTO>();
		expectedCategoriaTOs.add(CategoriaTypeConversor.toCategoriaTO(categoria));
		
		categoriaTOs = tiendaService.getCategoriasByTienda(tienda1.getId());
		
		assertTrue(categoriaTOs.size() == expectedCategoriaTOs.size());
		assertTrue(categoriaTOs.containsAll(expectedCategoriaTOs));
		
		//probamos con las dos
		tienda1.getCategorias().add(categoria2);
		tiendaDao.save(tienda1);
		
		expectedCategoriaTOs.add(CategoriaTypeConversor.toCategoriaTO(categoria2));

		categoriaTOs = tiendaService.getCategoriasByTienda(tienda1.getId());

		assertTrue(categoriaTOs.size() == expectedCategoriaTOs.size());
		assertTrue(categoriaTOs.containsAll(expectedCategoriaTOs));

	}
}
