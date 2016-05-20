package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ClienteTO;
import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO;
import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva;
import es.udc.jcastedo.NosaTenda.model.reserva.Reserva.ReservaState;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;

public class ReservaTypeConversor {

	public ReservaTypeConversor() {}
	
	public static ReservaTO toReservaTO(Reserva reserva) throws BadFormatRequestException {
		
		Long id = reserva.getId();
		Long unidades = reserva.getUnidades();
		Reserva.ReservaState estado = reserva.getEstado();
		Calendar fecha = reserva.getFecha();
		Calendar fecha_limite = reserva.getFecha_limite();
		Double precio_noiva = reserva.getPrecio_noiva();
		Double precio = reserva.getPrecio();
		Double total = reserva.getTotal();
		Double tax_amount = reserva.getTax_amount();
		Double tax_percentage = reserva.getTax_percentage();
		Producto producto = reserva.getProducto();
		Cliente cliente = reserva.getCliente();
		
		ReservaTO reservaTO = new ReservaTO();
		reservaTO.setId(id);
		reservaTO.setUnidades(unidades);
		reservaTO.setEstado(toReservaStateTO(estado));
		reservaTO.setFecha(fecha);
		reservaTO.setFecha_limite(fecha_limite);
		reservaTO.setPrecio_noiva(precio_noiva);
		reservaTO.setPrecio(precio);
		reservaTO.setTotal(total);
		reservaTO.setTax_amount(tax_amount);
		reservaTO.setTax_percentage(tax_percentage);
		reservaTO.setProductoTO(ProductoTypeConversor.toProductoTO(producto));
		reservaTO.setClienteTO(ClienteTypeConversor.toClienteTO(cliente));
		
		return reservaTO;
		
	}
	
	public static Reserva toReserva(ReservaTO reservaTO) throws BadFormatRequestException {
		
		Long id = reservaTO.getId();
		Long unidades = reservaTO.getUnidades();
		ReservaTO.ReservaStateTO estado = reservaTO.getEstado();
		Calendar fecha = reservaTO.getFecha();
		Calendar fecha_limite = reservaTO.getFecha_limite();
		Double precio_noiva = reservaTO.getPrecio_noiva();
		Double precio = reservaTO.getPrecio();
		Double total = reservaTO.getTotal();
		Double tax_amount = reservaTO.getTax_amount();
		Double tax_percentage = reservaTO.getTax_percentage();
		ProductoTO productoTO = reservaTO.getProductoTO();
		ClienteTO clienteTO = reservaTO.getClienteTO();
		
		Reserva reserva = new Reserva();
		reserva.setId(id);
		reserva.setUnidades(unidades);
		reserva.setEstado(toReservaState(estado));
		reserva.setFecha(fecha);
		reserva.setFecha_limite(fecha_limite);
		reserva.setPrecio_noiva(precio_noiva);
		reserva.setPrecio(precio);
		reserva.setTotal(total);
		reserva.setTax_amount(tax_amount);
		reserva.setTax_percentage(tax_percentage);
		reserva.setProducto(ProductoTypeConversor.toProducto(productoTO));
		reserva.setCliente(ClienteTypeConversor.toCliente(clienteTO));
		
		return reserva;
	}
	
	public static ReservaState toReservaState(ReservaTO.ReservaStateTO estado) throws BadFormatRequestException {
		
		for (ReservaState state: ReservaState.values()) {
			if (estado.toString().equals(state.toString())) {
				return state;
			}
		}
		throw new BadFormatRequestException(estado, ReservaTypeConversor.class.getName());
		
	}
	
	public static ReservaStateTO toReservaStateTO(Reserva.ReservaState estado) throws BadFormatRequestException {
		
		for (ReservaStateTO stateTO: ReservaStateTO.values()) {
			if (estado.toString().equals(stateTO.toString())) {
				return stateTO;
			}
		}
		throw new BadFormatRequestException(estado, ReservaTypeConversor.class.getName());
		
	}
	
	public static ReservaStateTO toReservaStateTO(String estado) throws BadFormatRequestException {
		
		for (ReservaStateTO stateTO: ReservaStateTO.values()) {
			if (estado.equals(stateTO.toString())) {
				return stateTO;
			}
		}
		throw new BadFormatRequestException(estado, ReservaTypeConversor.class.getName());
		
	}

	public static List<ReservaTO> toReservaTO(List<Reserva> reservas) throws BadFormatRequestException {
		
		List<ReservaTO> reservaTOs = new ArrayList<ReservaTO>();
		
		for (int i=0; i < reservas.size(); i++) {
			reservaTOs.add(toReservaTO(reservas.get(i)));
		}
		
		return reservaTOs;
	}
}
