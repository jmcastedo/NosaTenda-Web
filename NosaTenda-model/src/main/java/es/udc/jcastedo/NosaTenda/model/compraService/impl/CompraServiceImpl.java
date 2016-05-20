package es.udc.jcastedo.NosaTenda.model.compraService.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.jcastedo.NosaTenda.model.CompraTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.model.cliente.ClienteDao;
import es.udc.jcastedo.NosaTenda.model.compra.Compra;
import es.udc.jcastedo.NosaTenda.model.compra.CompraDao;
import es.udc.jcastedo.NosaTenda.model.compraService.CompraService;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoDao;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.util.CompraTypeConversor;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

@Service("compraService")
@Transactional
public class CompraServiceImpl implements CompraService {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private CompraDao compraDao;
	
	
	
	
	public Long guardarCompra(Long unidades, RecogidaStateTO estadoRecogida, Calendar fecha, Double precio, Double total, String idPaypal,
			String estadoPaypal, String formaPago, String currency, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		Compra compra = new Compra(unidades, CompraTypeConversor.toRecogidaState(estadoRecogida), fecha, precio, total, idPaypal, estadoPaypal, formaPago, currency,
				productoDao.find(productoId), clienteDao.find(clienteId));
		
		compraDao.save(compra);
		
		productoService.actualizarStock(productoId, unidades, "down");
		
		return compra.getId();
	}
	
	public Long guardarCompra(Long unidades, RecogidaStateTO estadoRecogida, Calendar fecha, Double precio, Double total, String idPaypal,
			String estadoPaypal, String formaPago, String currency, Double tax_amount, Long productoId, Long clienteId)
					throws InstanceNotFoundException, InsufficientStockException, BadFormatRequestException {
		
		Compra compra = new Compra(unidades, CompraTypeConversor.toRecogidaState(estadoRecogida), fecha, precio, total, idPaypal, estadoPaypal, formaPago, currency,
				productoDao.find(productoId), clienteDao.find(clienteId));
		compra.setTax_amount(tax_amount);
		
		compraDao.save(compra);
		
		productoService.actualizarStock(productoId, unidades, "down");
		
		return compra.getId();
	}
	
	@Transactional(readOnly = true)
	public List<CompraTO> getComprasByCliente(Long clienteId) throws BadFormatRequestException {
		
		List<Compra> compras = compraDao.getComprasByClienteId(clienteId);
		
		List<CompraTO> compraTOs = CompraTypeConversor.toCompraTO(compras);
		
		return compraTOs;
	}
	
	@Transactional(readOnly = true)
	public List<CompraTO> getCompras() throws BadFormatRequestException {
		
		List<Compra> compras = compraDao.getCompras();
		
		List<CompraTO> compraTOs = CompraTypeConversor.toCompraTO(compras);
		
		return compraTOs;
	}
	
	@Transactional(readOnly = true)
	public List<CompraTO> getComprasByTienda(Long tiendaId) throws BadFormatRequestException {
		
		List<Compra> compras = compraDao.getComprasByTiendaId(tiendaId);
		
		List<CompraTO> compraTOs = CompraTypeConversor.toCompraTO(compras);
		
		return compraTOs;
	}
	
	@Transactional(readOnly = true)
	public CompraTO getCompraById(Long compraId) throws InstanceNotFoundException, BadFormatRequestException {
		
		CompraTO compraTO = CompraTypeConversor.toCompraTO(compraDao.find(compraId));
		
		return compraTO;
	}
}
