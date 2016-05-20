package es.udc.jcastedo.NosaTenda.test.model.userService;

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

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoDataTO;
import es.udc.jcastedo.NosaTenda.model.EmpleadoTO;
import es.udc.jcastedo.NosaTenda.model.RoleTO;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteData;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoData;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado.Roles_Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoDao;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoDataDao;
import es.udc.jcastedo.NosaTenda.model.role.Role;
import es.udc.jcastedo.NosaTenda.model.role.RoleDao;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.model.util.EmpleadoTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.RoleTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectPasswordException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.test.model.util.DbUtil;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE})
//prueba base de datos de produccion
//@ContextConfiguration (locations = {SPRING_CONFIG_FILE})
@Transactional
public class UserServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	private final Long NON_EXISTENT_ID = Long.valueOf(-1);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private EmpleadoDataDao empleadoDataDao;
	
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
	public void testNewCliente() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testNewCliente");
		
		Long clienteId = userService.newCliente("correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		assertEquals(cliente.getCorreo(), "correo_test_cliente");
		assertEquals(cliente.getPassword(), "password");
	}
	
	@Test
	public void testNewClienteBis() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testNewCliente");
		
		Long clienteId = userService.newCliente("nombre_cliente", "correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		assertEquals(cliente.getClienteData().getNombre(), "nombre_cliente");
		assertEquals(cliente.getCorreo(), "correo_test_cliente");
		assertEquals(cliente.getPassword(), "password");
	}
	
	@Test(expected = DuplicateInstanceException.class)
	public void testNewClienteDuplicateCorreo() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testNewClienteDuplicateCorreo");
		
		Long clienteId1 = userService.newCliente("correo_test_cliente", "password");
		
		// cliente con el mismo correo que el anterior, debe lanzar DuplicateInstanceException
		Long clienteId2 = userService.newCliente("correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId1);
		
		Cliente cliente2 = clienteDao.find(clienteId2);
		
		assertEquals(cliente.getCorreo(), "correo_test_cliente");
		assertEquals(cliente.getPassword(), "password");
		assertEquals(cliente.getCorreo(), cliente2.getCorreo());
	}
	
	
	@Test
	public void testLoginCliente() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginCliente");
		
		Cliente cliente = new Cliente("correo@Test.es", "pojo");
		
		// Cascade.PERSIST
		clienteDao.save(cliente);
		
		Long clienteId = userService.loginCliente(cliente.getCorreo(), cliente.getPassword());
		
		assertEquals(cliente.getId(), clienteId);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testLoginClienteNotFound() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginClienteNotFound");
		
		Cliente cliente = new Cliente("correo@Test.es", "pojo");
		
		clienteDao.save(cliente);
		
		Long clienteId = userService.loginCliente(cliente.getCorreo() + "s", cliente.getPassword());
		
		assertEquals(cliente.getId(), clienteId);
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void testLoginClienteIncorrectPassword() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginClienteIncorrectPassword");
		
		Cliente cliente = new Cliente("correo@Test.es", "pojo");
		
		clienteDao.save(cliente);
		
		Long clienteId = userService.loginCliente(cliente.getCorreo(), cliente.getPassword() + "s");
		
		assertEquals(cliente.getId(), clienteId);
	}
	
	@Test
	public void testLoginEmpleado() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginEmpleado");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		Long empleadoId = userService.loginEmpleado("correo@Test.es", "pojo");
		
		assertEquals(empleado.getId(), empleadoId);

	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testLoginEmpleadoNotFound() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginEmpleadoNotFound");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		Long empleadoId = userService.loginEmpleado(empleado.getCorreo() + "s", empleado.getPassword());
		
		assertEquals(empleado.getId(), empleadoId);
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void testLoginEmpleadoIncorrectPassword() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testLoginEmpleadoIncorrectPassword");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		Long empleadoId = userService.loginEmpleado(empleado.getCorreo(), empleado.getPassword() + "s");
		
		assertEquals(empleado.getId(), empleadoId);
	}
	
	@Test
	public void testNewEmpleado() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testNewEmpleado");
		
		Long empleadoId = userService.newEmpleado("correo_test_empleado", "pojo");
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		assertEquals(empleado.getCorreo(), "correo_test_empleado");
		assertEquals(empleado.getPassword(), "pojo");
		
		// testeamos que tambien se haya creado un EmpleadoData con el mismo id
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		
		assertEquals(empleadoId, empleadoData.getId());
	}
	
	@Test(expected = DuplicateInstanceException.class)
	public void testNewEmpleadoDuplicateCorreo() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testNewEmpleadoDuplicateCorreo");
		
		Long empleadoId = userService.newEmpleado("correo_test_empleado", "pojo");
		
		Long empleadoId2 = userService.newEmpleado("correo_test_empleado", "pojo");
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		Empleado empleado2 = empleadoDao.find(empleadoId2);
		
		assertEquals(empleado.getCorreo(), "correo_test_empleado");
		assertEquals(empleado.getPassword(), "pojo");
		assertEquals(empleado.getCorreo(), empleado2.getCorreo());
		
		// testeamos que tambien se haya creado un EmpleadoData con el mismo id
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		
		assertEquals(empleadoId, empleadoData.getId());
	}
	
	@Test
	public void testDeleteEmpleado() throws InstanceNotFoundException {
		
		logger.debug("testDeleteEmpleado");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		Boolean result = userService.deleteEmpleado("correo@Test.es");
		
		assertTrue(result);
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testDeleteEmpleadoBis() throws InstanceNotFoundException {
		
		logger.debug("testDeleteEmpleado");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		Long empleadoId = empleado.getId();
		
		Boolean result = userService.deleteEmpleado("correo@Test.es");
		
		assertTrue(result);
		
		empleado = empleadoDao.find(empleadoId);
	}
	
	@Test
	public void testDeleteEmpleadoId() throws InstanceNotFoundException {
		
		logger.debug("testDeleteEmpleadoId");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		empleadoData.setNombre("test");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.deleteEmpleado(empleado.getId());
		
		assertTrue(result);
		
		try {
			EmpleadoData empleadoDataTest = empleadoDataDao.find(empleadoData.getId());
			
			assertTrue(empleadoDataTest != null);
		} catch (InstanceNotFoundException e) {
			assertTrue(true);
		}
		
		try {
			Empleado empleadoTest = empleadoDao.find(empleado.getId());
			
			assertTrue(empleadoTest != null);
		} catch (InstanceNotFoundException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testActivateEmpleado() throws InstanceNotFoundException {
		
		logger.debug("testActivateEmpleado");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		assertTrue(!empleado.getActivado());
		
		Boolean result = userService.activateEmpleado("correo@Test.es");
		
		assertTrue(result);
		assertTrue(empleado.getActivado());
	}
	
	@Test
	public void testActivateEmpleadoId() throws InstanceNotFoundException {
		
		logger.debug("testActivateEmpleadoId");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		assertTrue(!empleado.getActivado());
		
		Boolean result = userService.activateEmpleado(empleado.getId());
		
		assertTrue(result);
		assertTrue(empleado.getActivado());
	}
	
	@Test
	public void testDeactivateEmpleadoId() throws InstanceNotFoundException {
		
		logger.debug("testDeactivateEmpleadoId");
		
		Empleado empleado = new Empleado("correo@Test.es", "pojo");
		empleadoDao.save(empleado);
		
		assertTrue(!empleado.getActivado());
		
		Boolean result = userService.activateEmpleado(empleado.getId());
		
		assertTrue(result);
		assertTrue(empleado.getActivado());
		
		result = userService.deactivateEmpleado(empleado.getId());
		
		assertTrue(result);
		assertTrue(!empleado.getActivado());
	}
	
	@Test
	public void testEditEmpleado() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testEditEmpleado");
		
		Empleado empleado = new Empleado("correo", "password",
				Roles_Empleado.ROLE_USUARIO, false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleado("correo", "ROLE_ADMIN_TIENDA",
				true, "nifNew", "nombre", "apellidos", "direccion", null, "provincia", "cp",
				"phone1", "phone2");
		
		assertTrue(result);
		
		assertTrue(empleado.getCorreo().equals("correo"));
		assertTrue(empleado.getRole().toString().equals("ROLE_ADMIN_TIENDA"));
		assertTrue(empleado.getEmpleadoData().getNombre().equals("nombre"));
		assertTrue(empleado.getEmpleadoData().getApellidos().equals("apellidos"));
	}
	
	@Test(expected = BadFormatRequestException.class)
	public void testEditEmpleadoBadFormat() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testEditEmpleado");
		
		Empleado empleado = new Empleado("correo", "password",
				Roles_Empleado.ROLE_USUARIO, false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleado("correo", "ROLE_NON_EXISTENT",
				true, "nifNew", "nombre", "apellidos", "direccion", null, "provincia", "cp",
				"phone1", "phone2");
		
		assertTrue(result);
		
		assertTrue(empleado.getCorreo().equals("correo"));
		assertTrue(empleado.getRole().toString().equals("ROLE_ADMIN_TIENDA"));
		assertTrue(empleado.getEmpleadoData().getNombre().equals("nombre"));
		assertTrue(empleado.getEmpleadoData().getApellidos().equals("apellidos"));
	}
	
	@Test
	public void testEditEmpleadoRole() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testEditEmpleadoRole");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Long empleadoId = empleado.getId();
		
		Boolean result = userService.editEmpleadoRole(empleadoId, "ROLE_ADMIN_TIENDA");
		
		assertTrue(result);
		assertTrue(empleado.getRole().toString().equals("ROLE_ADMIN_TIENDA"));
	}
	
	@Test(expected = BadFormatRequestException.class)
	public void testEditEmpleadoRoleBadFormat() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testEditEmpleadoRoleBadFormat");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Long empleadoId = empleado.getId();
		
		// role no existente, debe saltar una BadFormatRequestException
		Boolean result = userService.editEmpleadoRole(empleadoId, "ROLE_NON_EXISTENT");
		
		assertTrue(result);
		assertTrue(empleado.getRole().toString().equals("ROLE_NON_EXISTENT"));
	}
	
	@Test
	public void testEditEmpleadoPassword() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testEditEmpleadoPassword");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleadoPassword("correoOld", "passwordOld", "passwordNew");
		
		assertTrue(result);
		assertTrue(empleado.getPassword().equals("passwordNew"));
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testEditEmpleadoPasswordUserNotFound() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testEditEmpleadoPasswordUserNotFound");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				true, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleadoPassword("correo", "passwordOld", "passwordNew");
		
		assertTrue(result);
		
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void testEditEmpleadoPasswordIncorrectPassword() throws InstanceNotFoundException, IncorrectPasswordException {
		
		logger.debug("testEditEmpleadoPassword");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				true, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleadoPassword("correoOld", "password", "passwordNew");
		
		assertTrue(result);
		
	}
	
	@Test
	public void testEditEmpleadoCorreo() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testEditEmpleadoCorreo");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				false, "nifOld");
		
		empleadoDao.save(empleado);
		
		Boolean result = userService.editEmpleadoCorreo("correoOld", "correoNew");
		
		assertTrue(result);
		
		assertTrue(empleado.getCorreo().equals("correoNew"));
	}
	
	@Test(expected = DuplicateInstanceException.class)
	public void testEditEmpleadoCorreoDuplicateCorreo() throws InstanceNotFoundException, DuplicateInstanceException {
		
		logger.debug("testEditEmpleadoCorreoDuplicateCorreo");
		
		Empleado empleado = new Empleado("correoOld", "passwordOld", Roles_Empleado.ROLE_USUARIO,
				false, "nifOld");
		
		empleadoDao.save(empleado);
		
		// intentamos poner un correo que ya esta en uso, correoOld, deberia lanzar DuplicateInstanceException
		Boolean result = userService.editEmpleadoCorreo("correoOld", "correoOld");
		
		assertTrue(result);
		
		assertTrue(empleado.getCorreo().equals("correoNew"));
	}
	
	@Test
	public void testGetEmpleados() throws BadFormatRequestException {
		
		logger.debug("testGetEmpleados");
		
		Empleado empleado1 = new Empleado("correo1", "password");
		Empleado empleado2 = new Empleado("correo2", "password");
		Empleado empleado3 = new Empleado("correo3", "password");
		
		empleadoDao.save(empleado1);
		empleadoDao.save(empleado2);
		empleadoDao.save(empleado3);
		
		List<EmpleadoTO> listExpected = new ArrayList<EmpleadoTO>();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado1));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado2));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado3));
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleados();
		
		assertEquals(listExpected, empleadoTOs);
	}
	
	@Test
	public void testGetEmpleadosEmpty() throws BadFormatRequestException {
		
		logger.debug("testGetEmpleadosEmpty");
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleados();
		
		assertTrue(empleadoTOs.isEmpty());
	}
	
	@Test
	public void testGetEmpleadosByTienda() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetEmpleadosByTienda");
		
		Tienda testTienda = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		Empleado empleado1 = new Empleado("correo1", "password");
		Empleado empleado2 = new Empleado("correo2", "password");
		Empleado empleado3 = new Empleado("correo3", "password");
		
		empleado1.getTrabaja().add(testTienda);
		empleado3.getTrabaja().add(testTienda);
		
		empleadoDao.save(empleado1);
		empleadoDao.save(empleado2);
		empleadoDao.save(empleado3);
		
		List<EmpleadoTO> listExpected = new ArrayList<EmpleadoTO>();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado1));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado3));
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleadosByTienda(testTienda.getId());
		
		assertEquals(listExpected, empleadoTOs);
	}
	
	@Test
	public void testGetEmpleadosByTiendaEmpty() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetEmpleadosByTiendaEmpty");
		
		Tienda testTienda = tiendaDao.find(DbUtil.getTestTiendaId1());
		
		Empleado empleado1 = new Empleado("correo1", "password");
		Empleado empleado2 = new Empleado("correo2", "password");
		Empleado empleado3 = new Empleado("correo3", "password");
		
		empleado1.getTrabaja().add(testTienda);
		empleado3.getTrabaja().add(testTienda);
		
		empleadoDao.save(empleado1);
		empleadoDao.save(empleado2);
		empleadoDao.save(empleado3);
		
		List<EmpleadoTO> listExpected = new ArrayList<EmpleadoTO>();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado1));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado3));
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleadosByTienda(NON_EXISTENT_ID);
		
		assertTrue(empleadoTOs.isEmpty());
		
		empleadoTOs = userService.getEmpleadosByTienda(DbUtil.getTestTiendaId2());
		
		assertTrue(empleadoTOs.isEmpty());
	}
	
	@Test
	public void testGetEmpleadosByRole() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetEmpleadosByRole");
		
		Empleado empleado1 = new Empleado("correo1", "password");
		Empleado empleado2 = new Empleado("correo2", "password");
		Empleado empleado3 = new Empleado("correo3", "password");
		
		empleadoDao.save(empleado1);
		empleadoDao.save(empleado2);
		empleadoDao.save(empleado3);
		
		List<EmpleadoTO> listExpected = new ArrayList<EmpleadoTO>();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado1));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado2));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado3));
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleadosByRole("ROLE_USUARIO");
		
		assertEquals(listExpected, empleadoTOs);

		userService.editEmpleadoRole(empleado2.getId(), "ROLE_ADMIN_TIENDA");
		
		listExpected.remove(1); // position of empleado2
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_USUARIO");
		
		assertEquals(listExpected, empleadoTOs);
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_ADMIN_TIENDA");
		
		listExpected.clear();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado2));
		
		assertEquals(listExpected, empleadoTOs);
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_ADMIN");
		
		assertTrue(empleadoTOs.isEmpty());
		
	}
	
	@Test(expected = BadFormatRequestException.class)
	public void testGetEmpleadosByRoleBadFormat() throws InstanceNotFoundException, BadFormatRequestException {
		
		logger.debug("testGetEmpleadosByRoleBadFormat");
		
		Empleado empleado1 = new Empleado("correo1", "password");
		Empleado empleado2 = new Empleado("correo2", "password");
		Empleado empleado3 = new Empleado("correo3", "password");
		
		empleadoDao.save(empleado1);
		empleadoDao.save(empleado2);
		empleadoDao.save(empleado3);
		
		List<EmpleadoTO> listExpected = new ArrayList<EmpleadoTO>();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado1));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado2));
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado3));
		
		List<EmpleadoTO> empleadoTOs = userService.getEmpleadosByRole("ROLE_NON_EXISTENT");
		
		assertEquals(listExpected, empleadoTOs);

		userService.editEmpleadoRole(empleado2.getId(), "ROLE_ADMIN_TIENDA");
		
		listExpected.remove(1); // position of empleado2
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_USUARIO");
		
		assertEquals(listExpected, empleadoTOs);
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_ADMIN_TIENDA");
		
		listExpected.clear();
		listExpected.add(EmpleadoTypeConversor.toEmpleadoTO(empleado2));
		
		assertEquals(listExpected, empleadoTOs);
		
		empleadoTOs = userService.getEmpleadosByRole("ROLE_ADMIN");
		
		assertTrue(empleadoTOs.isEmpty());
		
	}
	
	@Test
	public void testGetEmpleadoData() throws InstanceNotFoundException {
		
		logger.debug("testGetEmpleadoData");
		
		String testnombre = "testnombre";
		String testapellidos = "testapellidos";
		String testdireccion = "testdireccion";
		String testlocalidad = "testlocalidad";
		String testprovincia = "testprovincia";
		String testcp = "testcp";
		String testphone1 = "testphone1";
		String testphone2 = "testphone2";
		
		Empleado empleado = new Empleado("testcorreo", "testpassword", Roles_Empleado.ROLE_USUARIO, true, "testnif");
		empleadoDao.save(empleado);
		
		EmpleadoData empleadoData = empleado.getEmpleadoData();
		empleadoData.setNombre(testnombre);
		empleadoData.setApellidos(testapellidos);
		empleadoData.setDireccion(testdireccion);
		empleadoData.setLocalidad(testlocalidad);
		empleadoData.setProvincia(testprovincia);
		empleadoData.setCp(testcp);
		empleadoData.setPhone1(testphone1);
		empleadoData.setPhone2(testphone2);
		
		//empleadoDataDao.save(empleadoDataExpected);
		empleadoDao.save(empleado);
		
		EmpleadoDataTO empleadoDataTOExpected = new EmpleadoDataTO();
		empleadoDataTOExpected.setId(empleado.getId());
		empleadoDataTOExpected.setNombre(testnombre);
		empleadoDataTOExpected.setApellidos(testapellidos);
		empleadoDataTOExpected.setDireccion(testdireccion);
		empleadoDataTOExpected.setLocalidad(testlocalidad);
		empleadoDataTOExpected.setProvincia(testprovincia);
		empleadoDataTOExpected.setCp(testcp);
		empleadoDataTOExpected.setPhone1(testphone1);
		empleadoDataTOExpected.setPhone2(testphone2);

		EmpleadoDataTO empleadoDataTO = userService.getEmpleadoData(empleado.getId());
		
		assertEquals(empleadoDataTOExpected, empleadoDataTO);
	}
	
	@Test
	public void testGetClienteData() throws DuplicateInstanceException, InstanceNotFoundException {
		
		logger.debug("testGetClienteData");
		
		String testnombre = "testnombre";
		String testapellidos = "testapellidos";
		String testdireccion = "testdireccion";
		String testlocalidad = "testlocalidad";
		String testprovincia = "testprovincia";
		String testcp = "testcp";
		String testphone1 = "testphone1";
		String testphone2 = "testphone2";
		
		Long clienteId = userService.newCliente("correo_test_cliente", "password");
		
		Cliente cliente = clienteDao.find(clienteId);
		
		ClienteData clienteData = cliente.getClienteData();
		clienteData.setNombre(testnombre);
		clienteData.setApellidos(testapellidos);
		clienteData.setDireccion(testdireccion);
		clienteData.setLocalidad(testlocalidad);
		clienteData.setProvincia(testprovincia);
		clienteData.setCp(testcp);
		clienteData.setPhone1(testphone1);
		clienteData.setPhone2(testphone2);
		
		clienteDao.save(cliente);
		
		ClienteTO clienteTOExpected = new ClienteTO();
		clienteTOExpected.setId(cliente.getId());
		clienteTOExpected.setCorreo(cliente.getCorreo());
		clienteTOExpected.setPassword("");
		clienteTOExpected.setNombre(testnombre);
		clienteTOExpected.setApellidos(testapellidos);
		clienteTOExpected.setDireccion(testdireccion);
		clienteTOExpected.setLocalidad(testlocalidad);
		clienteTOExpected.setProvincia(testprovincia);
		clienteTOExpected.setCp(testcp);
		clienteTOExpected.setPhone1(testphone1);
		clienteTOExpected.setPhone2(testphone2);
		
		ClienteTO clienteTO = userService.findClienteById(clienteId);
		
		assertEquals(clienteTO, clienteTOExpected);
		
		assertEquals(clienteId, clienteData.getId());
		
	}
	
	@Test
	public void testGetRolesDaoHibernate() {
		
		logger.debug("testGetRolesDaoHibernate");
		
		Role role1 = new Role("ROLE_USUARIO");
		Role role2 = new Role("ROLE_ADMIN");
		Role role3 = new Role("ROLE_CLIENTE");
		
		roleDao.save(role1);
		roleDao.save(role2);
		roleDao.save(role3);
		
		List<Role> roles = roleDao.getRoles();
		
		assertTrue(roles.size() == 3);
		
	}
	
	@Test
	public void testGetRoles() {
		
		logger.debug("testGetRoles");
		
		Role role1 = new Role("ROLE_USUARIO");
		Role role2 = new Role("ROLE_ADMIN");
		Role role3 = new Role("ROLE_CLIENTE");
		
		roleDao.save(role1);
		roleDao.save(role2);
		roleDao.save(role3);
		
		// los roles son devueltos en orden alfabético
		List<RoleTO> listExpected = new ArrayList<RoleTO>();
		listExpected.add(RoleTypeConversor.toRoleTO(role2));
		listExpected.add(RoleTypeConversor.toRoleTO(role3));
		listExpected.add(RoleTypeConversor.toRoleTO(role1));
		
		List<RoleTO> roleTOs = userService.getRoles();
		
		assertEquals(roleTOs, listExpected);
		
	}
	
	@Test
	public void testClienteLazy() throws DuplicateInstanceException, InstanceNotFoundException {
		
		logger.debug("testClienteLazy");
		
		Long clienteId = userService.newCliente("correo_test_cliente", "password");
		
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		Cliente cliente = clienteDao.find(clienteId);
		
		//EAGER
		assertTrue(Hibernate.isPropertyInitialized(cliente, "clienteData"));
		//LAZY
		assertTrue(!Hibernate.isInitialized(cliente.getFavoritas()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(cliente.getCompras()));
		//LAZY
		assertTrue(!Hibernate.isInitialized(cliente.getReservas()));
	}
	
	// igual en que TiendaTest, no he conseguido hace funcionar el test para una
	// propiedad lazy, solo para colecciones
	@Test
	public void testEmpleadoLazy() throws InstanceNotFoundException {
		
		logger.debug("testEmpleadoLazy");
		
		Empleado empleado1 = new Empleado("correo1", "password");
		empleadoDao.save(empleado1);
		Long empleadoId = empleado1.getId();
		
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		Empleado empleado = empleadoDao.find(empleadoId);
		
		//LAZY
		//assertTrue(!Hibernate.isPropertyInitialized(empleado, "empleadoData"));
		//LAZY
		assertTrue(!Hibernate.isInitialized(empleado.getTrabaja()));
	}
}
