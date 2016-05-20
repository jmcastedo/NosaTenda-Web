package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ClienteInfoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ClienteResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ClienteWTO;

public class ClienteWebserviceConversor {

	public ClienteWebserviceConversor() {}
	
	public static ClienteWTO toClienteWTO(ClienteTO clienteTO) {
		
		ClienteWTO clienteWTO = new ClienteWTO();
		
		clienteWTO.setNombre(clienteTO.getNombre());
		clienteWTO.setCorreo(clienteTO.getCorreo());
		clienteWTO.setPassword(clienteTO.getPassword());
		
		return clienteWTO;
	}
	
	public static ClienteResponseWTO toClienteResponseWTO(ClienteTO clienteTO) {
		
		ClienteResponseWTO clienteResponseWTO = new ClienteResponseWTO();
		
		clienteResponseWTO.setClienteId(clienteTO.getId());
		
		return clienteResponseWTO;
	}
	
	public static ClienteInfoWTO toClienteInfoWTO(ClienteTO clienteTO) {
		
		ClienteInfoWTO clienteInfoWTO = new ClienteInfoWTO();
		
		clienteInfoWTO.setId(clienteTO.getId());
		clienteInfoWTO.setNombre(clienteTO.getNombre());
		clienteInfoWTO.setApellidos(clienteTO.getApellidos());
		clienteInfoWTO.setCorreo(clienteTO.getCorreo());
		clienteInfoWTO.setPassword(clienteTO.getPassword());
		clienteInfoWTO.setDireccion(clienteTO.getDireccion());
		clienteInfoWTO.setLocalidad(clienteTO.getLocalidad());
		clienteInfoWTO.setProvincia(clienteTO.getProvincia());
		clienteInfoWTO.setCp(clienteTO.getCp());
		clienteInfoWTO.setNif(clienteTO.getNif());
		clienteInfoWTO.setPhone1(clienteTO.getPhone1());
		clienteInfoWTO.setPhone2(clienteTO.getPhone2());
		
		return clienteInfoWTO;
	}
}
