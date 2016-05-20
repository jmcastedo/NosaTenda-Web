package es.udc.jcastedo.NosaTenda.model.reservaService;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ReservaTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectStateException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

public interface ReservaService {

	public Long reserveProducto(Long unidades, ReservaStateTO estado, Double precio,
			Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException;
	
	public Long reserveProducto(Long unidades, ReservaStateTO estado, Double precio,
			Double tax_amount, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException;
	
	public List<ReservaTO> getReservasById(Long clienteId) throws BadFormatRequestException, InstanceNotFoundException;
	
	public void cancelarReserva(Long reservaId) throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException, IncorrectStateException;
	
	public List<ReservaTO> getReservas() throws BadFormatRequestException;
	
	public void entregarReserva(Long reservaId)
			throws InstanceNotFoundException;
}
