package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.udc.jcastedo.NosaTenda.model.ProductoHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.producto.ProductoHistorico;

public class ProductoHistoricoTypeConversor {

	public ProductoHistoricoTypeConversor() {}
	
	public static ProductoHistoricoTO toProductoHistoricoTO(ProductoHistorico productoHistorico) {
		
		ProductoHistoricoTO productoHistoricoTO = new ProductoHistoricoTO();
		
		productoHistoricoTO.setId(productoHistorico.getId());
		productoHistoricoTO.setVersion(productoHistorico.getVersion());
		productoHistoricoTO.setFecha_puesta_venta(productoHistorico.getFecha_puesta_venta());
		productoHistoricoTO.setFecha_retirada(productoHistorico.getFecha_retirada());
		productoHistoricoTO.setPrecio(productoHistorico.getPrecio());
		productoHistoricoTO.setPrecio_noiva(productoHistorico.getPrecio_noiva());
		productoHistoricoTO.setStock_inicial(productoHistorico.getStock_inicial());
		productoHistoricoTO.setTax_amount(productoHistorico.getTax_amount());
		productoHistoricoTO.setTax_percentage(productoHistorico.getTax_percentage());
		
		return productoHistoricoTO;
	}
	
	public static Set<ProductoHistoricoTO> toProductoHistoricoTO(Set<ProductoHistorico> productoHistoricos) {
		
		Set<ProductoHistoricoTO> productoHistoricoTOs = new HashSet<ProductoHistoricoTO>();
		
		for (ProductoHistorico productoHistorico: productoHistoricos) {
			productoHistoricoTOs.add(toProductoHistoricoTO(productoHistorico));
		}
		
		return productoHistoricoTOs;
	}
	
	public static List<ProductoHistoricoTO> toProductoHistoricoTO(List<ProductoHistorico> productoHistoricos) {
		
		List<ProductoHistoricoTO> productoHistoricoTOs = new ArrayList<ProductoHistoricoTO>();
		
		for (ProductoHistorico productoHistorico: productoHistoricos) {
			productoHistoricoTOs.add(toProductoHistoricoTO(productoHistorico));
		}
		
		return productoHistoricoTOs;
	}
}
