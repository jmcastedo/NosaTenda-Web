package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.LocalidadTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

public class TiendaTypeConversor {

	public TiendaTypeConversor() {}
	
	public static TiendaTO toTiendaTO(Tienda tienda) {
		
		Long id = tienda.getId();
		String nombre = tienda.getNombre();
		String direccion = tienda.getDireccion();
		String provincia = tienda.getProvincia();
		String cp = tienda.getCp();
		String nif = tienda.getNif();
		String correo = tienda.getCorreo();
		String web = tienda.getWeb();
		String phone1 = tienda.getPhone1();
		String phone2 = tienda.getPhone2();
		String fax = tienda.getFax();
		String imagen = tienda.getImagen();
		Double lat = tienda.getLat();
		Double lon = tienda.getLon();
		LocalidadTO localidadTO = LocalidadTypeConversor.toLocalidadTO(tienda.getLocalidad());
		Set<CategoriaTO> categoriaTOs = CategoriaTypeConversor.toCategoriaTO(tienda.getCategorias());
		
		TiendaTO tiendaTO = new TiendaTO();
		tiendaTO.setId(id);
		tiendaTO.setNombre(nombre);
		tiendaTO.setDireccion(direccion);
		tiendaTO.setLocalidad(localidadTO);
		tiendaTO.setProvincia(provincia);
		tiendaTO.setCp(cp);
		tiendaTO.setNif(nif);
		tiendaTO.setCorreo(correo);
		tiendaTO.setWeb(web);
		tiendaTO.setPhone1(phone1);
		tiendaTO.setPhone2(phone2);
		tiendaTO.setFax(fax);
		tiendaTO.setImagen(imagen);
		tiendaTO.setLat(lat);
		tiendaTO.setLon(lon);
		tiendaTO.setCategorias(categoriaTOs);
		
		return tiendaTO;
	}
	
	public static List<TiendaTO> toTiendaTO(List<Tienda> tiendas) {
		
		List<TiendaTO> tiendaTOs = new ArrayList<TiendaTO>();
		
		for (int i=0; i < tiendas.size(); i++) {
			tiendaTOs.add(toTiendaTO(tiendas.get(i)));
		}
		
		return tiendaTOs;
	}
	
	public static Set<TiendaTO> toTiendaTO(Set<Tienda> tiendas) {
		
		Set<TiendaTO> tiendaTOs = new HashSet<TiendaTO>();
		
		for (Tienda tienda: tiendas) {
			tiendaTOs.add(toTiendaTO(tienda));
		}
		
		return tiendaTOs;
	}
	
	public static Tienda toTienda(TiendaTO tiendaTO) {
		
		Long id = tiendaTO.getId();
		String nombre = tiendaTO.getNombre();
		String direccion = tiendaTO.getDireccion();
		String provincia = tiendaTO.getProvincia();
		String cp = tiendaTO.getCp();
		String nif = tiendaTO.getNif();
		String correo = tiendaTO.getCorreo();
		String web = tiendaTO.getWeb();
		String phone1 = tiendaTO.getPhone1();
		String phone2 = tiendaTO.getPhone2();
		String fax = tiendaTO.getFax();
		String imagen = tiendaTO.getImagen();
		Double lat = tiendaTO.getLat();
		Double lon = tiendaTO.getLon();
		Localidad localidad = LocalidadTypeConversor.toLocalidad(tiendaTO.getLocalidad());
		Set<Categoria> categorias = CategoriaTypeConversor.toCategoria(tiendaTO.getCategorias());
		
		Tienda tienda = new Tienda();
		tienda.setId(id);
		tienda.setNombre(nombre);
		tienda.setDireccion(direccion);
		tienda.setProvincia(provincia);
		tienda.setCp(cp);
		tienda.setNif(nif);
		tienda.setCorreo(correo);
		tienda.setWeb(web);
		tienda.setPhone1(phone1);
		tienda.setPhone2(phone2);
		tienda.setFax(fax);
		tienda.setImagen(imagen);
		tienda.setLat(lat);
		tienda.setLon(lon);
		tienda.setLocalidad(localidad);
		tienda.setCategorias(categorias);
		
		return tienda;
	}
}
