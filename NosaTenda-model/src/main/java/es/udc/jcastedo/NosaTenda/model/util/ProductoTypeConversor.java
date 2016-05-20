package es.udc.jcastedo.NosaTenda.model.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import es.udc.jcastedo.NosaTenda.model.CategoriaTO;
import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.TiendaTO;
import es.udc.jcastedo.NosaTenda.model.categoria.Categoria;
import es.udc.jcastedo.NosaTenda.model.producto.Producto;
import es.udc.jcastedo.NosaTenda.model.tienda.Tienda;

public class ProductoTypeConversor {

	public ProductoTypeConversor() {}
	
	public static List<ProductoTO> toProductoTO(List<Producto> productos) {
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		
		for (int i=0; i < productos.size(); i++) {
			productoTOs.add(toProductoTO(productos.get(i)));
		}
		
		return productoTOs;
	}
	
	public static ProductoTO toProductoTO(Producto producto) {
		
		Long id = producto.getId();
		String nombre = producto.getProductoData().getNombre();
		String descripcion = producto.getProductoData().getDescripcion();
		String imagen = producto.getProductoData().getImagen();
		Double precio = producto.getPrecio();
		Double precio_noiva = producto.getPrecio_noiva();
		Long stock = producto.getStock();
		Double tax_amount = producto.getTax_amount();
		Double tax_percentage = producto.getTax_percentage();
		Calendar fecha_puesta_venta = producto.getFecha_puesta_venta();
		Calendar fecha_retirada = producto.getFecha_retirada();
		TiendaTO tienda = TiendaTypeConversor.toTiendaTO(producto.getTienda());
		Set<CategoriaTO> categoriaTOs = CategoriaTypeConversor.toCategoriaTO(producto.getCategorias());
		
		ProductoTO productoTO = new ProductoTO();
		productoTO.setId(id);
		productoTO.setNombre(nombre);
		productoTO.setDescripcion(descripcion);
		productoTO.setImagen(imagen);
		productoTO.setPrecio(precio);
		productoTO.setPrecio_noiva(precio_noiva);
		productoTO.setStock(stock);
		productoTO.setTax_amount(tax_amount);
		productoTO.setTax_percentage(tax_percentage);
		productoTO.setFecha_puesta_venta(fecha_puesta_venta);
		productoTO.setFecha_retirada(fecha_retirada);
		productoTO.setTienda(tienda);
		productoTO.setCategorias(categoriaTOs);
		
		return productoTO;
	}
	
	public static List<Producto> toProducto(List<ProductoTO> productoTOs) {
		
		List<Producto> productos = new ArrayList<Producto>();
		
		for (int i= 0; i< productoTOs.size(); i++) {
			productos.add(toProducto(productoTOs.get(i)));
		}
		
		return productos;
	}
	
	public static Producto toProducto(ProductoTO productoTO) {
		
		Long id = productoTO.getId();
		String nombre = productoTO.getNombre();
		String descripcion = productoTO.getDescripcion();
		String imagen = productoTO.getImagen();
		Double precio = productoTO.getPrecio();
		Double precio_noiva = productoTO.getPrecio_noiva();
		Long stock = productoTO.getStock();
		Double tax_amount = productoTO.getTax_amount();
		Double tax_percentage = productoTO.getTax_percentage();
		Calendar fecha_puesta_venta = productoTO.getFecha_puesta_venta();
		Calendar fecha_retirada = productoTO.getFecha_retirada();
		Tienda tienda = TiendaTypeConversor.toTienda(productoTO.getTienda());
		Set<Categoria> categorias = CategoriaTypeConversor.toCategoria(productoTO.getCategorias());
		
		Producto producto = new Producto();
		producto.setId(id);
		producto.getProductoData().setNombre(nombre);
		producto.getProductoData().setDescripcion(descripcion);
		producto.getProductoData().setImagen(imagen);
		producto.setPrecio(precio);
		producto.setPrecio_noiva(precio_noiva);
		producto.setStock(stock);
		producto.setTax_amount(tax_amount);
		producto.setTax_percentage(tax_percentage);
		producto.setFecha_puesta_venta(fecha_puesta_venta);
		producto.setFecha_retirada(fecha_retirada);
		producto.setTienda(tienda);
		producto.setCategorias(categorias);
		
		return producto;
	}
}
