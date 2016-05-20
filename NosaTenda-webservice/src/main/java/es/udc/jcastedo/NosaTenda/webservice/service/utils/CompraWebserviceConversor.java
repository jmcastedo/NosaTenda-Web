package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CompraTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO.RecogidaStateWTO;

public class CompraWebserviceConversor {

	public CompraWebserviceConversor() {}
	
	public static List<CompraWTO> toCompraWTO(List<CompraTO> compraTOs) throws BadFormatRequestException {
		
		List<CompraWTO> compraWTOs = new ArrayList<CompraWTO>();
		
		for (CompraTO compraTO: compraTOs) {
			compraWTOs.add(toCompraWTO(compraTO));
		}
		
		return compraWTOs;
	}
	
	public static CompraWTO toCompraWTO(CompraTO compraTO) throws BadFormatRequestException {
		
		CompraWTO compraWTO = new CompraWTO();
		
		compraWTO.setId(compraTO.getId());
		compraWTO.setUnidades(compraTO.getUnidades());
		compraWTO.setEstadoRecogida(toRecogidaStateWTO(compraTO.getEstadoRecogida()));
		compraWTO.setFecha(compraTO.getFecha());
		compraWTO.setPrecio_noiva(compraTO.getPrecio_noiva());
		compraWTO.setPrecio(compraTO.getPrecio());
		compraWTO.setTotal(compraTO.getTotal());
		compraWTO.setIdPaypal(compraTO.getIdPaypal());
		compraWTO.setEstadoPaypal(compraTO.getEstadoPaypal());
		compraWTO.setFormaPago(compraTO.getFormaPago());
		compraWTO.setCurrency(compraTO.getCurrency());
		compraWTO.setTax_amount(compraTO.getTax_amount());
		compraWTO.setTax_percentage(compraTO.getTax_percentage());
		compraWTO.setNum_factura(compraTO.getNum_factura());
		compraWTO.setProducto(ProductoWebserviceConversor.toProductoWTO(compraTO.getProducto()));
		compraWTO.setCliente(ClienteWebserviceConversor.toClienteInfoWTO(compraTO.getCliente()));
		
		return compraWTO;
	}
	
	public static RecogidaStateWTO toRecogidaStateWTO(RecogidaStateTO estado) throws BadFormatRequestException {
		
		for (RecogidaStateWTO stateWTO: RecogidaStateWTO.values()) {
			if (estado.toString().equals(stateWTO.toString())) {
				return stateWTO;
			}
		}
		throw new BadFormatRequestException(estado, CompraWebserviceConversor.class.getName());
		
	}
}
