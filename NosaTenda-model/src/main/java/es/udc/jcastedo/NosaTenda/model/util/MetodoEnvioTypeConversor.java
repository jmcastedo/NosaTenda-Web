package es.udc.jcastedo.NosaTenda.model.util;

import java.util.HashSet;
import java.util.Set;

import es.udc.jcastedo.NosaTenda.model.MetodoEnvioTO;
import es.udc.jcastedo.NosaTenda.model.metodoEnvio.MetodoEnvio;

public class MetodoEnvioTypeConversor {

	public MetodoEnvioTypeConversor() {}
	
	public static MetodoEnvioTO toMetodoEnvioTO(MetodoEnvio metodoEnvio) {
		
		MetodoEnvioTO metodoEnvioTO = new MetodoEnvioTO();
		
		metodoEnvioTO.setId(metodoEnvio.getId());
		metodoEnvioTO.setCoste(metodoEnvio.getCoste());
		metodoEnvioTO.setDescripcion(metodoEnvio.getDescripcion());
		metodoEnvioTO.setPlazo(metodoEnvio.getPlazo());
		
		return metodoEnvioTO;
	}
	
	public static Set<MetodoEnvioTO> toMetodoEnvioTO(Set<MetodoEnvio> metodoEnvios) {
		
		Set<MetodoEnvioTO> metodoEnvioTOs = new HashSet<MetodoEnvioTO>();
		
		for (MetodoEnvio metodoEnvio: metodoEnvios) {
			metodoEnvioTOs.add(toMetodoEnvioTO(metodoEnvio));
		}
		
		return metodoEnvioTOs;
	}
}
