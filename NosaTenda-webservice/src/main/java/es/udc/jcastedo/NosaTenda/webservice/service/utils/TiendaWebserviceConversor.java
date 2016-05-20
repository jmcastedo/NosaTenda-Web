package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;

public class TiendaWebserviceConversor {

	public TiendaWebserviceConversor() {}
	
	public static TiendaWTO toTiendaWTO(TiendaTO tiendaTO) {
		
		TiendaWTO tiendaWTO = new TiendaWTO();
		
		tiendaWTO.setId(tiendaTO.getId());
		tiendaWTO.setNombre(tiendaTO.getNombre());
		tiendaWTO.setDireccion(tiendaTO.getDireccion());
		tiendaWTO.setLocalidad(LocalidadWebserviceConversor.toLocalidadWTO(tiendaTO.getLocalidad()));
		tiendaWTO.setProvincia(tiendaTO.getProvincia());
		tiendaWTO.setCp(tiendaTO.getCp());
		tiendaWTO.setNif(tiendaTO.getNif());
		tiendaWTO.setCorreo(tiendaTO.getCorreo());
		tiendaWTO.setWeb(tiendaTO.getWeb());
		tiendaWTO.setPhone1(tiendaTO.getPhone1());
		tiendaWTO.setPhone2(tiendaTO.getPhone2());
		tiendaWTO.setFax(tiendaTO.getFax());
		tiendaWTO.setImagen(tiendaTO.getImagen());
		tiendaWTO.setLat(tiendaTO.getLat());
		tiendaWTO.setLon(tiendaTO.getLon());
		
		return tiendaWTO;
	}
	
	public static List<TiendaWTO> toTiendaWTO(List<TiendaTO> tiendaTOs) {
		
		List<TiendaWTO> tiendaWTOs = new ArrayList<TiendaWTO>();
		
		for(TiendaTO tiendaTO: tiendaTOs) {
			tiendaWTOs.add(toTiendaWTO(tiendaTO));
		}
		
		return tiendaWTOs;
	}
	
	public static TiendaTO toTiendaTO(TiendaWTO tiendaWTO) {
		
		TiendaTO tiendaTO = new TiendaTO();
		
		tiendaTO.setId(tiendaWTO.getId());
		tiendaTO.setNombre(tiendaWTO.getNombre());
		tiendaTO.setDireccion(tiendaWTO.getDireccion());
		tiendaTO.setLocalidad(LocalidadWebserviceConversor.toLocalidadTO(tiendaWTO.getLocalidad()));
		tiendaTO.setProvincia(tiendaWTO.getProvincia());
		tiendaTO.setCp(tiendaWTO.getCp());
		tiendaTO.setNif(tiendaWTO.getNif());
		tiendaTO.setCorreo(tiendaWTO.getCorreo());
		tiendaTO.setWeb(tiendaWTO.getWeb());
		tiendaTO.setPhone1(tiendaWTO.getPhone1());
		tiendaTO.setPhone2(tiendaWTO.getPhone2());
		tiendaTO.setFax(tiendaWTO.getFax());
		tiendaTO.setImagen(tiendaWTO.getImagen());
		tiendaTO.setLat(tiendaWTO.getLat());
		tiendaTO.setLon(tiendaWTO.getLon());
		
		return tiendaTO;
	}
	
}
