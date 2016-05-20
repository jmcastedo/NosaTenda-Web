package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO;

public class CategoriaWebserviceConversor {

	public CategoriaWebserviceConversor() {}
	
	public static CategoriaWTO toCategoriaWTO(CategoriaTO categoriaTO) {
		
		CategoriaWTO categoriaWTO = new CategoriaWTO();
		
		categoriaWTO.setId(categoriaTO.getId());
		categoriaWTO.setNombre(categoriaTO.getNombre());
		
		return categoriaWTO;
	}
	
	public static List<CategoriaWTO> toCategoriaWTO(List<CategoriaTO> categoriaTOs) {
		
		List<CategoriaWTO> categoriaWTOs = new ArrayList<CategoriaWTO>();
		
		for (CategoriaTO categoriaTO: categoriaTOs) {
			categoriaWTOs.add(toCategoriaWTO(categoriaTO));
		}
		
		return categoriaWTOs;
	}
}
