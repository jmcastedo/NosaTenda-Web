package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.CompraTO;
import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.model.cliente.Cliente;
import es.udc.jcastedo.NosaTenda.model.compra.Compra;
import es.udc.jcastedo.NosaTenda.model.compra.Compra.RecogidaState;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;

public class CompraTypeConversor {

	public CompraTypeConversor() {}
	
	public static CompraTO toCompraTO(Compra compra) throws BadFormatRequestException {
		
		Long id = compra.getId();
		Long unidades = compra.getUnidades();
		RecogidaState estadoRecogida = compra.getEstadoRecogida();
		Calendar fecha = compra.getFecha();
		Double precio_noiva = compra.getPrecio_noiva();
		Double precio = compra.getPrecio();
		Double total = compra.getTotal();
		String idPaypal = compra.getIdPaypal();
		String estadoPaypal = compra.getEstadoPaypal();
		String formaPago = compra.getFormaPago();
		String currency = compra.getCurrency();
		Double tax_amount = compra.getTax_amount();
		Double tax_percentage = compra.getTax_percentage();
		Long num_factura = compra.getNum_factura();
		Producto producto = compra.getProducto();
		Cliente cliente = compra.getCliente();
		
		CompraTO compraTO = new CompraTO();
		compraTO.setId(id);
		compraTO.setUnidades(unidades);
		compraTO.setEstadoRecogida(toRecogidaStateTO(estadoRecogida));
		compraTO.setFecha(fecha);
		compraTO.setPrecio_noiva(precio_noiva);
		compraTO.setPrecio(precio);
		compraTO.setTotal(total);
		compraTO.setIdPaypal(idPaypal);
		compraTO.setEstadoPaypal(estadoPaypal);
		compraTO.setFormaPago(formaPago);
		compraTO.setCurrency(currency);
		compraTO.setTax_amount(tax_amount);
		compraTO.setTax_percentage(tax_percentage);
		compraTO.setNum_factura(num_factura);
		compraTO.setProducto(ProductoTypeConversor.toProductoTO(producto));
		compraTO.setCliente(ClienteTypeConversor.toClienteTO(cliente));
		
		return compraTO;
	}
	
	public static RecogidaStateTO toRecogidaStateTO(RecogidaState estado) throws BadFormatRequestException {
		
		for (RecogidaStateTO estadoTO: RecogidaStateTO.values()) {
			if (estado.toString().equals(estadoTO.toString())) {
				return estadoTO;
			}
		}
		throw new BadFormatRequestException(estado, CompraTypeConversor.class.getName());
		
	}
	
	public static RecogidaState toRecogidaState(RecogidaStateTO estado) throws BadFormatRequestException {
		
		for (RecogidaState state: RecogidaState.values()) {
			if (estado.toString().equals(state.toString())) {
				return state;
			}
		}
		throw new BadFormatRequestException(estado, CompraTypeConversor.class.getName());
		
	}
	
	public static List<CompraTO> toCompraTO(List<Compra> compras) throws BadFormatRequestException {
		
		List<CompraTO> compraTOs = new ArrayList<CompraTO>();
		
		for (int i=0; i < compras.size(); i++) {
			compraTOs.add(toCompraTO(compras.get(i)));
		}
		
		return compraTOs;
	}
}
