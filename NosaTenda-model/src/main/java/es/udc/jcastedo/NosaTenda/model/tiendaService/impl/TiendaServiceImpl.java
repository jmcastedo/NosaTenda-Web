package es.udc.jcastedo.NosaTenda.model.tiendaService.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.MetodoEnvioTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.model.categoria.CategoriaDao;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.empleado.Empleado;
import es.udc.jcastedo.NosaTenda.model.empleado.EmpleadoDao;
import es.udc.jcastedo.NosaTenda.model.localidad.Localidad;
import es.udc.jcastedo.NosaTenda.model.localidad.LocalidadDao;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;
import es.udc.jcastedo.NosaTenda.model.tienda.TiendaDao;
import es.udc.jcastedo.NosaTenda.model.tiendaService.TiendaService;
import es.udc.jcastedo.NosaTenda.model.util.CategoriaTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.Haversine;
import es.udc.jcastedo.NosaTenda.model.util.MetodoEnvioTypeConversor;
import es.udc.jcastedo.NosaTenda.model.util.TiendaTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

@Service("tiendaService")
@Transactional
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	private TiendaDao tiendaDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private LocalidadDao localidadDao;
	
	@Transactional(readOnly = true)
	public TiendaTO getTiendaById(Long idTienda) throws InstanceNotFoundException {
		
		Tienda tienda = tiendaDao.find(idTienda);
		
		return TiendaTypeConversor.toTiendaTO(tienda);
	}
	
	@Transactional(readOnly = true)
	public List<TiendaTO> getTiendas() {
		
		List<Tienda> tiendas = tiendaDao.getTiendas();
		
		return TiendaTypeConversor.toTiendaTO(tiendas);
	}
	
	@Transactional(readOnly = true)
	public List<TiendaTO> getTiendasCercanas(Double lat, Double lon, double distancia) {
		
		List<Tienda> tiendas = tiendaDao.getTiendas();
		
		List<Tienda> tiendasCercanas = new ArrayList<Tienda>();
		
		for (Tienda tienda: tiendas) {
			double dist = Haversine.distFrom(lat, lon, tienda.getLat(), tienda.getLon());
			if ((dist <= distancia) && (dist >= 0))
				tiendasCercanas.add(tienda);
		}
		
		return TiendaTypeConversor.toTiendaTO(tiendasCercanas);
	}

	@Transactional(readOnly = true)
	public List<TiendaTO> getTiendasFav(Long clienteId) {
		
		List<Tienda> tiendas = tiendaDao.getTiendasFav(clienteId);
		
		return TiendaTypeConversor.toTiendaTO(tiendas);
	}

	public void favTienda(Long clienteId, Long tiendaId) {
		
		try {
			
			Cliente cliente = clienteDao.find(clienteId);
			
			Tienda tienda = tiendaDao.find(tiendaId);
			
			cliente.getFavoritas().add(tienda);
			
			clienteDao.save(cliente);
			
		} catch (InstanceNotFoundException e) {
			
			return;
		}
		
	}

	public void unfavTienda(Long clienteId, Long tiendaId) {
		
		try {
			
			Cliente cliente = clienteDao.find(clienteId);
			
			Tienda tienda = tiendaDao.find(tiendaId);
			
			cliente.getFavoritas().remove(tienda);
			
			clienteDao.save(cliente);
			
		} catch (InstanceNotFoundException e) {
			
			return;
		}
		
	}

	@Transactional(readOnly = true)
	public Boolean isFavTienda(Long clienteId, Long tiendaId)
			throws InstanceNotFoundException {
		
		Cliente cliente = clienteDao.find(clienteId);
		
		Tienda tienda = tiendaDao.find(tiendaId);
		
		return cliente.getFavoritas().contains(tienda);
	}

	@Transactional(readOnly = true)
	public List<MetodoEnvioTO> getMetodosEnvioByTienda(Long tiendaId)
			throws InstanceNotFoundException {
		
		Tienda tienda = tiendaDao.find(tiendaId);
		
		Set<MetodoEnvioTO> metodoEnvioTOSet = MetodoEnvioTypeConversor.toMetodoEnvioTO(tienda.getMetodosEnvio());
		
		List<MetodoEnvioTO> metodoEnvioTOs = new ArrayList<MetodoEnvioTO>(metodoEnvioTOSet);
		
		return metodoEnvioTOs;
	}

	@Transactional(readOnly = true)
	public List<TiendaTO> getTiendasByEmpleado(String empleadoCorreo) throws InstanceNotFoundException {
		
		Empleado empleado = empleadoDao.findByCorreo(empleadoCorreo);
		
		Set<TiendaTO> tiendaTOSet = TiendaTypeConversor.toTiendaTO(empleado.getTrabaja());
		
		List<TiendaTO> tiendaTOs = new ArrayList<TiendaTO>(tiendaTOSet);
		
		return tiendaTOs;
	}
	
	@Transactional(readOnly = true)
	public List<CategoriaTO> getCategoriasByTienda(Long tiendaId) {
		
		Set<CategoriaTO> categoriaTOSet = CategoriaTypeConversor.toCategoriaTO(categoriaDao.getCategoriasByTienda(tiendaId));
		
		List<CategoriaTO> categoriaTOs = new ArrayList<CategoriaTO>(categoriaTOSet);
		
		return categoriaTOs;
	}

	public TiendaTO editTienda(Long id, String nombre, String direccion,
			String provincia, String cp, String nif, String correo, String web,
			String phone1, String phone2, String fax, String imagen,
			Double lat, Double lon, Long localidadId) throws InstanceNotFoundException {
		
		Tienda tienda = tiendaDao.find(id);
		
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
		
		Localidad localidad = localidadDao.find(localidadId);
		tienda.setLocalidad(localidad);
		
		return TiendaTypeConversor.toTiendaTO(tienda);
	}

	public TiendaTO createTienda(String nombre, String direccion,
			String provincia, String cp, String nif, String correo, String web,
			String phone1, String phone2, String fax, String imagen,
			Double lat, Double lon, Long localidadId)
			throws InstanceNotFoundException {
		
		Tienda tienda = new Tienda();
		
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
		
		Localidad localidad = localidadDao.find(localidadId);
		tienda.setLocalidad(localidad);
		
		tiendaDao.save(tienda);
		
		return TiendaTypeConversor.toTiendaTO(tienda);
	}
}
