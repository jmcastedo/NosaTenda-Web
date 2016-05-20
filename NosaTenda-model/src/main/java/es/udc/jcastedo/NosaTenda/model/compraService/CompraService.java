package es.udc.jcastedo.NosaTenda.model.compraService;

import java.util.Calendar;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CompraTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

public interface CompraService {

	public Long guardarCompra(Long unidades, RecogidaStateTO estadoRecogida, Calendar fecha, Double precio, Double total, String idPaypal,
			String estadoPaypal, String formaPago, String currency, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException;
	
	public Long guardarCompra(Long unidades, RecogidaStateTO estadoRecogida, Calendar fecha, Double precio, Double total, String idPaypal,
			String estadoPaypal, String formaPago, String currency, Double tax_amount, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException;
	
	public List<CompraTO> getComprasByCliente(Long clienteId) throws BadFormatRequestException;
	
	public List<CompraTO> getCompras() throws BadFormatRequestException;
	
	public List<CompraTO> getComprasByTienda(Long tiendaId) throws BadFormatRequestException;
	
	public CompraTO getCompraById(Long compraId) throws InstanceNotFoundException, BadFormatRequestException;
}
