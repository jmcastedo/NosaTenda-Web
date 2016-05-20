package es.udc.jcastedo.NosaTenda.model.tiendaService;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.MetodoEnvioTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

public interface TiendaService {

	public TiendaTO getTiendaById(Long idTienda) throws InstanceNotFoundException;
	
	public List<TiendaTO> getTiendas();
	
	public List<TiendaTO> getTiendasCercanas(Double lat, Double lon, double distancia);
	
	public List<TiendaTO> getTiendasFav(Long clienteId);
	
	public void favTienda(Long clienteId, Long tiendaId);
	
	public void unfavTienda(Long clienteId, Long tiendaId);
	
	public Boolean isFavTienda(Long clienteId, Long tiendaId) throws InstanceNotFoundException;
	
	public List<MetodoEnvioTO> getMetodosEnvioByTienda(Long tiendaId) throws InstanceNotFoundException;
	
	public List<TiendaTO> getTiendasByEmpleado(String empleadoCorreo) throws InstanceNotFoundException;
	
	public List<CategoriaTO> getCategoriasByTienda(Long tiendaId);
	
	public TiendaTO editTienda(Long id, String nombre, String direccion, String provincia, String cp,
			String nif, String correo, String web, String phone1, String phone2, String fax,
			String imagen, Double lat, Double lon, Long localidadId) throws InstanceNotFoundException;
	
	public TiendaTO createTienda(String nombre, String direccion, String provincia, String cp,
			String nif, String correo, String web, String phone1, String phone2, String fax,
			String imagen, Double lat, Double lon, Long localidadId) throws InstanceNotFoundException;
}
