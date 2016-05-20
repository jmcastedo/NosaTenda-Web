package es.udc.jcastedo.NosaTenda.model.util;

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;

public class ClienteTypeConversor {

	public ClienteTypeConversor () {}
	
	public static ClienteTO toClienteTO(Cliente cliente) {
		
		Long id = cliente.getId();
		
		String nombre = cliente.getClienteData().getNombre();
		String apellidos = cliente.getClienteData().getApellidos();
		String direccion = cliente.getClienteData().getDireccion();
		String localidad = cliente.getClienteData().getLocalidad();
		String provincia = cliente.getClienteData().getProvincia();
		String cp = cliente.getClienteData().getCp();
		String phone1 = cliente.getClienteData().getPhone1();
		String phone2 = cliente.getClienteData().getPhone2();
		
		String correo = cliente.getCorreo();
		String password = cliente.getPassword();
		String nif = cliente.getNif();
		
		
		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setId(id);
		clienteTO.setNombre(nombre);
		clienteTO.setApellidos(apellidos);
		clienteTO.setCorreo(correo);
		clienteTO.setPassword(password);
		clienteTO.setDireccion(direccion);
		clienteTO.setLocalidad(localidad);
		clienteTO.setProvincia(provincia);
		clienteTO.setCp(cp);
		clienteTO.setNif(nif);
		clienteTO.setPhone1(phone1);
		clienteTO.setPhone2(phone2);
		
		return clienteTO;
	}
	
	public static Cliente toCliente(ClienteTO clienteTO) {
		
		Long id = clienteTO.getId();
		String nombre = clienteTO.getNombre();
		String apellidos = clienteTO.getApellidos();
		String correo = clienteTO.getCorreo();
		String password = clienteTO.getPassword();
		String direccion = clienteTO.getDireccion();
		String localidad = clienteTO.getLocalidad();
		String provincia = clienteTO.getProvincia();
		String cp = clienteTO.getCp();
		String nif = clienteTO.getNif();
		String phone1 = clienteTO.getPhone1();
		String phone2= clienteTO.getPhone2();
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.getClienteData().setNombre(nombre);
		cliente.getClienteData().setApellidos(apellidos);
		cliente.setCorreo(correo);
		cliente.setPassword(password);
		cliente.getClienteData().setDireccion(direccion);
		cliente.getClienteData().setLocalidad(localidad);
		cliente.getClienteData().setProvincia(provincia);
		cliente.getClienteData().setCp(cp);
		cliente.setNif(nif);
		cliente.getClienteData().setPhone1(phone1);
		cliente.getClienteData().setPhone2(phone2);
		
		return cliente;
	}
}
