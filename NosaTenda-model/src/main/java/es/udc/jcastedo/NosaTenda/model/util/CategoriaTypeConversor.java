package es.udc.jcastedo.NosaTenda.model.util;

import java.util.HashSet;
import java.util.Set;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;

public class CategoriaTypeConversor {

	public CategoriaTypeConversor() {}
	
	public static CategoriaTO toCategoriaTO(Categoria categoria) {
		
		CategoriaTO categoriaTO = new CategoriaTO();
		
		categoriaTO.setId(categoria.getId());
		categoriaTO.setNombre(categoria.getNombre());
		
		return categoriaTO;
	}
	
	public static Set<CategoriaTO> toCategoriaTO(Set<Categoria> categorias) {
		
		Set<CategoriaTO> categoriaTOs = new HashSet<CategoriaTO>();
		
		for (Categoria categoria: categorias) {
			categoriaTOs.add(toCategoriaTO(categoria));
		}
		
		return categoriaTOs;
	}
	
	public static Categoria toCategoria(CategoriaTO categoriaTO) {
		
		Categoria categoria = new Categoria();
		
		categoria.setId(categoriaTO.getId());
		categoria.setNombre(categoriaTO.getNombre());
		
		return categoria;
	}
	
	public static Set<Categoria> toCategoria(Set<CategoriaTO> categoriaTOs) {
		
		Set<Categoria> categorias = new HashSet<Categoria>();
		
		for (CategoriaTO categoriaTO: categoriaTOs) {
			categorias.add(toCategoria(categoriaTO));
		}
		
		return categorias;
	}
}
