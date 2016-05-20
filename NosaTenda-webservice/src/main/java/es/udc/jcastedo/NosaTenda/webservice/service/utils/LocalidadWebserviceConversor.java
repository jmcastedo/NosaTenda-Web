package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.webservice.service.LocalidadWTO;

public class LocalidadWebserviceConversor {

	public LocalidadWebserviceConversor() {}
	
	public static LocalidadWTO toLocalidadWTO(LocalidadTO localidadTO) {
		
		LocalidadWTO localidadWTO = new LocalidadWTO();
		
		localidadWTO.setId(localidadTO.getId());
		localidadWTO.setNombre(localidadTO.getNombre());
		
		return localidadWTO;
	}
	
	public static List<LocalidadWTO> toLocalidadWTO(List<LocalidadTO> localidadesTO) {
		
		List<LocalidadWTO> localidadesWTO = new ArrayList<LocalidadWTO>();
		
		for (LocalidadTO localidadTO: localidadesTO) {
			localidadesWTO.add(toLocalidadWTO(localidadTO));
		}
		
		return localidadesWTO;
	}
	
	public static LocalidadTO toLocalidadTO(LocalidadWTO localidadWTO) {
		
		LocalidadTO localidadTO = new LocalidadTO();
		
		localidadTO.setId(localidadWTO.getId());
		localidadTO.setNombre(localidadWTO.getNombre());
		
		return localidadTO;
	}
}
