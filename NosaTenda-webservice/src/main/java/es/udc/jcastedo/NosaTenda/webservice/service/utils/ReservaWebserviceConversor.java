package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ReservaTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.webservice.service.ReservaInfoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ReservaInfoWTO.ReservaStateWTO;

public class ReservaWebserviceConversor {

	public ReservaWebserviceConversor() {}
	
	public static List<ReservaInfoWTO> toReservaInfoWTO(List<ReservaTO> reservaTOs) throws BadFormatRequestException {
		
		List<ReservaInfoWTO> reservaInfoWTOs = new ArrayList<ReservaInfoWTO>();
		
		for (ReservaTO reservaTO: reservaTOs) {
			reservaInfoWTOs.add(toReservaInfoWTO(reservaTO));
		}
		
		return reservaInfoWTOs;
	}

	public static ReservaInfoWTO toReservaInfoWTO(ReservaTO reservaTO) throws BadFormatRequestException {
		
		ReservaInfoWTO reservaInfoWTO = new ReservaInfoWTO();
		
		reservaInfoWTO.setId(reservaTO.getId());
		reservaInfoWTO.setUnidades(reservaTO.getUnidades());
		reservaInfoWTO.setEstado(toReservaState(reservaTO.getEstado()));
		reservaInfoWTO.setFecha(reservaTO.getFecha());
		reservaInfoWTO.setFecha_limite(reservaTO.getFecha_limite());
		reservaInfoWTO.setPrecio_noiva(reservaTO.getPrecio_noiva());
		reservaInfoWTO.setPrecio(reservaTO.getPrecio());
		reservaInfoWTO.setTotal(reservaTO.getTotal());
		reservaInfoWTO.setTax_amount(reservaTO.getTax_amount());
		reservaInfoWTO.setTax_percentage(reservaTO.getTax_percentage());
		reservaInfoWTO.setProducto(ProductoWebserviceConversor.toProductoWTO(reservaTO.getProductoTO()));
		
		return reservaInfoWTO;
	}
	
	public static ReservaStateWTO toReservaState(ReservaStateTO estado) throws BadFormatRequestException {
		
		for (ReservaStateWTO stateWTO: ReservaStateWTO.values()) {
			if (estado.toString().equals(stateWTO.toString())) {
				return stateWTO;
			}
		}
		throw new BadFormatRequestException(estado, ReservaWebserviceConversor.class.getName());
		
	}
}
