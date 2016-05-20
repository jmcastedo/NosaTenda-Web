package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;

public class LocalidadTypeConversor {

	public LocalidadTypeConversor() {}
	
	public static LocalidadTO toLocalidadTO(Localidad localidad) {
		
		LocalidadTO localidadTO = new LocalidadTO();
		
		localidadTO.setId(localidad.getId());
		localidadTO.setNombre(localidad.getNombre());
		
		return localidadTO;
	}
	
	public static Localidad toLocalidad(LocalidadTO localidadTO) {
		
		Localidad localidad = new Localidad();
		
		localidad.setId(localidadTO.getId());
		localidad.setNombre(localidadTO.getNombre());
		
		return localidad;
	}
	
	public static List<LocalidadTO> toLocalidadTO(List<Localidad> localidades) {
		
		List<LocalidadTO> localidadesTO = new ArrayList<LocalidadTO>();
		
		for (int i=0; i<localidades.size(); i++) {
			localidadesTO.add(toLocalidadTO(localidades.get(i)));
		}
		
		return localidadesTO;
	}
}
