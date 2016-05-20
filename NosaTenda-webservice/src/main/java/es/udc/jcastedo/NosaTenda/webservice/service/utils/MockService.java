package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ProductoTO;

public class MockService {

	public static List<ProductoTO> getProductos() {
		
		List<ProductoTO> productos = new ArrayList<ProductoTO>();
		
		ProductoTO producto1 = new ProductoTO();
		producto1.setId(new Long(1));
		producto1.setNombre("producto1");
		producto1.setDescripcion("mock producto1");
		producto1.setImagen("imag 1");
		producto1.setPrecio(new Double(1));
		producto1.setTienda(null);
		
		ProductoTO producto2 = new ProductoTO();
		producto2.setId(new Long(2));
		producto2.setNombre("producto2");
		producto2.setDescripcion("mock producto2");
		producto2.setImagen("imag 2");
		producto2.setPrecio(new Double(2));
		producto2.setTienda(null);
		
		ProductoTO producto3 = new ProductoTO();
		producto3.setId(new Long(3));
		producto3.setNombre("producto3");
		producto3.setDescripcion("mock producto3");
		producto3.setImagen("imag 3");
		producto3.setPrecio(new Double(3));
		producto3.setTienda(null);
		
		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		
		return productos;
	}
}
