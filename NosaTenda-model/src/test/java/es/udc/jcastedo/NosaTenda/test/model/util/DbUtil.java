package es.udc.jcastedo.NosaTenda.test.model.util;

import static es.udc.jcastedo.NosaTenda.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.jcastedo.NosaTenda.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.localidad.LocalidadDao;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;



public class DbUtil {
	
	static {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE});
		
		//prueba de la base de datos de produccion
		/*ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {SPRING_CONFIG_FILE});*/
		
		transactionManager = (PlatformTransactionManager) context
				.getBean("transactionManager");
		
		productoDao = (ProductoDao) context.getBean("productoDao");
		tiendaDao = (TiendaDao) context.getBean("tiendaDao");
		localidadDao = (LocalidadDao) context.getBean("localidadDao");
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DbUtil.class);
	
	private static Long testLocalidadId;
	
	private static Long testTiendaId1;
	private static Long testTiendaId2;
	
	private static Long testProductoId1;
	private static Long testProductoId2;
	private static Long testProductoId3;
	
	private static ProductoDao productoDao;
	private static TiendaDao tiendaDao;
	private static LocalidadDao localidadDao;
	
	private static PlatformTransactionManager transactionManager;
	
	public static Long getTestLocalidadId() {
		return testLocalidadId;
	}
	
	public static Long getTestTiendaId1() {
		return testTiendaId1;
	}
	
	public static Long getTestTiendaId2() {
		return testTiendaId2;
	}
	
	public static Long getTestProductoId1() {
		return testProductoId1;
	}
	
	public static Long getTestProductoId2() {
		return testProductoId2;
	}
	
	public static Long getTestProductoId3() {
		return testProductoId3;
	}
	
	
	public static void populateDb() throws Throwable {
		
		
		/*	
		 * Since this method is supposed to be called from a @BeforeClass 
		 * method, it works directly with "TransactionManager", since 
		 * @BeforeClass methods with Spring TestContext do not run in the 
		 * context of a transaction (which is required for DAOs to work).
		 */

		TransactionStatus transactionStatus = transactionManager
				.getTransaction(null);
		
		try{
			
			logger.info("entrando en el try de populateDb()");
			
			// DO NOT MODIFY!! Tiendas and Productos are used in many tests, add new values if needed
			// NO MODIFICAR!! Tiendas y Productos son usados en muchos tests, crear nuevos si es necesario
			Localidad localidad = new Localidad("A Coruña");
			localidadDao.save(localidad);
			
			testLocalidadId = localidad.getId();
			
			Tienda testTienda1 = new Tienda("tienda1", "calle de los test, 1", "00000001", "tienda1@test.com", "539295939", localidad);
			Tienda testTienda2 = new Tienda("tienda2", "calle de las pruebas, 2", "00000002", "tienda2@test.com", "549959393", localidad);
			tiendaDao.save(testTienda1);
			tiendaDao.save(testTienda2);
			
			testTiendaId1 = testTienda1.getId();
			testTiendaId2 = testTienda2.getId();
			
			Producto testProducto1 = new Producto(new Double(10), new Long(0), testTienda1);
			Producto testProducto2 = new Producto(new Double(20), new Long(50), testTienda1);
			Producto testProducto3 = new Producto(new Double(30), new Long(100), testTienda2);
			
			// guardamos los productos en la BD para generar un id
			productoDao.save(testProducto1);
			productoDao.save(testProducto2);
			productoDao.save(testProducto3);
			
			// ahora que tenemos id, podemos crear los productoData de cada uno
			testProducto1.getProductoData().setNombre("producto1");
			testProducto1.getProductoData().setDescripcion("test de p1");
			testProducto1.getProductoData().setImagen("uri_imagen1");
			
			testProducto2.getProductoData().setNombre("producto2");
			testProducto2.getProductoData().setDescripcion("test de p2");
			testProducto2.getProductoData().setImagen("uri_imagen2");
			
			testProducto3.getProductoData().setNombre("producto3");
			testProducto3.getProductoData().setDescripcion("test de p3");
			testProducto3.getProductoData().setImagen("uri_imagen3");
			
			// guardamos de nuevo los productos para que los productoData se guarden en cascada
			productoDao.save(testProducto1);
			productoDao.save(testProducto2);
			productoDao.save(testProducto3);
			
			testProductoId1 = testProducto1.getId();
			testProductoId2 = testProducto2.getId();
			testProductoId3 = testProducto3.getId();
			
			logger.debug("testTienda1Id: {}, testTienda2Id: {} ", testTienda1.getId(), testTienda2.getId());
			logger.debug("testProducto1Id: {}, testProducto2Id: {}, testProducto3Id: {} ", 
					testProducto1.getId(), testProducto2.getId(), testProducto3.getId());
			
			transactionManager.commit(transactionStatus);
			
		} catch (Throwable e) {
			transactionManager.rollback(transactionStatus);
			throw e;
		}
		
		logger.info("saliendo populateDb()");
	}
	
	public static void cleanDb() throws Throwable {
		
		/*
		 * For the same reason as "populateDb" (with regard to @AfterClass 
		 * methods), this method works directly with "TransactionManager".
		 */

		TransactionStatus transactionStatus = transactionManager
				.getTransaction(null);
		
		try {
			
			logger.info("Entrada en el try de cleanDb");
			
			productoDao.remove(testProductoId1);
			productoDao.remove(testProductoId2);
			productoDao.remove(testProductoId3);
		
			tiendaDao.remove(testTiendaId1);
			tiendaDao.remove(testTiendaId2);
			
			localidadDao.remove(testLocalidadId);
			
			testLocalidadId = null;
			
			testTiendaId1 = null;
			testTiendaId2 = null;
			
			testProductoId1 = null;
			testProductoId2 = null;
			testProductoId3 = null;
			
			transactionManager.commit(transactionStatus);
		
		} catch (Throwable e) {
			transactionManager.rollback(transactionStatus);
			throw e;
		}	
			
		logger.info("saliendo cleanDb()");
	}

}
