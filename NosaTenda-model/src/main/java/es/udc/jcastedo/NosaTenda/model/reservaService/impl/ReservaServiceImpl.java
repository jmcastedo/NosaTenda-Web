package es.udc.jcastedo.NosaTenda.model.reservaService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.ReservaTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva.ReservaState;
import es.udc.jcastedo.NosaTenda.model.reserva.ReservaDao;
import es.udc.jcastedo.NosaTenda.model.reservaService.ReservaService;
import es.udc.jcastedo.NosaTenda.model.util.ReservaTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectStateException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

@Service("reservaService")
@Transactional
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private ReservaDao reservaDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	
	
	public Long reserveProducto(Long unidades, ReservaStateTO estado, Double precio,
			Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		Reserva reserva = new Reserva(unidades, ReservaTypeConversor.toReservaState(estado), precio,
				productoDao.find(productoId), clienteDao.find(clienteId));
		
		reservaDao.save(reserva);
		
		productoService.actualizarStock(productoId, unidades, "down");
		
		return reserva.getId();
	}
	
	public Long reserveProducto(Long unidades, ReservaStateTO estado, Double precio,
			Double tax_amount, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		Reserva reserva = new Reserva(unidades, ReservaTypeConversor.toReservaState(estado), precio,
				productoDao.find(productoId), clienteDao.find(clienteId));
		reserva.setTax_amount(tax_amount);
		
		reservaDao.save(reserva);
		
		productoService.actualizarStock(productoId, unidades, "down");
		
		return reserva.getId();
	}
	
	@Transactional(readOnly = true)
	public List<ReservaTO> getReservasById(Long clienteId) throws BadFormatRequestException, InstanceNotFoundException {
		
		// simple chequeo para ver si el cliente existe, y si no lanzar InstanceNotFoundException
		clienteDao.find(clienteId);
		
		List<Reserva> reservas = reservaDao.getReservas(clienteId);
		
		List<ReservaTO> reservaTOs = ReservaTypeConversor.toReservaTO(reservas);
		
		return reservaTOs;
	}

	public void cancelarReserva(Long reservaId) throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, IncorrectStateException {
		
		Reserva reserva = reservaDao.find(reservaId);
		
		if (!reserva.getEstado().toString().equals("PENDIENTE")) {
			throw new IncorrectStateException(reserva.getEstado().toString());
		}

		// cancelamos la reserva cambiando el estado
		reserva.setEstado(ReservaState.CANCELADA);
		reservaDao.save(reserva);
		
		// actualizamos el stock devolviendo las unidades reservadas
		productoService.actualizarStock(reserva.getProducto().getId(),
				reserva.getUnidades(),
				"up");
	}
	
	@Transactional(readOnly = true)
	public List<ReservaTO> getReservas() throws BadFormatRequestException {
		
		List<Reserva> reservas = reservaDao.getReservas();
		
		List<ReservaTO> reservaTOs = ReservaTypeConversor.toReservaTO(reservas);
		
		return reservaTOs;
	}

	public void entregarReserva(Long reservaId)
			throws InstanceNotFoundException {
		
		Reserva reserva = reservaDao.find(reservaId);
		
		if (reserva == null) throw new InstanceNotFoundException(reservaId, Reserva.class.getName());
		
		// entregamos la reserva cambiando el estado
		reserva.setEstado(ReservaState.ENTREGADA);
		reservaDao.save(reserva);
		
	}
}
