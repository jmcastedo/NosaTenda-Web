package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.VentaHistoricoTO;
import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.VentasBlockWTO;

public class ProductoWebserviceConversor {

	public ProductoWebserviceConversor() {}
	
	public static List<ProductoWTO> toProductoWTO(List<ProductoTO> productoTOs) {
		
		List<ProductoWTO> productoWTOs = new ArrayList<ProductoWTO>();
		
		for(ProductoTO productoTO: productoTOs) {
			productoWTOs.add(toProductoWTO(productoTO));
		}
		
		return productoWTOs;
	}

	public static ProductoWTO toProductoWTO(ProductoTO productoTO) {
		
		ProductoWTO productoWTO = new ProductoWTO();
		
		productoWTO.setId(productoTO.getId());
		productoWTO.setNombre(productoTO.getNombre());
		productoWTO.setDescripcion(productoTO.getDescripcion());
		productoWTO.setImagen(productoTO.getImagen());
		productoWTO.setPrecio_noiva(productoTO.getPrecio_noiva());
		productoWTO.setPrecio(productoTO.getPrecio());
		productoWTO.setStock(productoTO.getStock());
		productoWTO.setTax_amount(productoTO.getTax_amount());
		productoWTO.setTax_percentage(productoTO.getTax_percentage());
		productoWTO.setFecha_puesta_venta(productoTO.getFecha_puesta_venta());
		productoWTO.setFecha_retirada(productoTO.getFecha_retirada());
		productoWTO.setTienda(TiendaWebserviceConversor.toTiendaWTO(productoTO.getTienda()));
		
		return productoWTO;
	}
	
	public static ProductoTO toProductoTO(ProductoWTO productoWTO) {
		
		ProductoTO productoTO = new ProductoTO();
		
		productoTO.setId(productoWTO.getId());
		productoTO.setNombre(productoWTO.getNombre());
		productoTO.setDescripcion(productoWTO.getDescripcion());
		productoTO.setImagen(productoWTO.getImagen());
		productoTO.setPrecio_noiva(productoWTO.getPrecio_noiva());
		productoTO.setPrecio(productoWTO.getPrecio());
		productoTO.setStock(productoWTO.getStock());
		productoTO.setTax_amount(productoWTO.getTax_amount());
		productoTO.setTax_percentage(productoWTO.getTax_percentage());
		productoTO.setFecha_puesta_venta(productoWTO.getFecha_puesta_venta());
		productoTO.setFecha_retirada(productoWTO.getFecha_retirada());
		productoTO.setTienda(TiendaWebserviceConversor.toTiendaTO(productoWTO.getTienda()));
		
		return productoTO;
	}
	
	public static List<ProductoTO> toProductoTO(List<ProductoWTO> productoWTOs) {
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		
		for (ProductoWTO productoWTO: productoWTOs) {
			productoTOs.add(toProductoTO(productoWTO));
		}
		
		return productoTOs;
	}
	
	public static VentasBlockWTO toVentasBlockWTO(UnidadesBlock unidadesBlock) {
		
		VentasBlockWTO ventasblockWTO = new VentasBlockWTO();
		
		ventasblockWTO.setId(unidadesBlock.getProductoId());
		ventasblockWTO.setVentas(unidadesBlock.getUnidadesVendidas());
		
		return ventasblockWTO;
	}
	
	public static List<VentasBlockWTO> toVentasBlockWTO(List<UnidadesBlock> unidadesBlock) {
		
		List<VentasBlockWTO> ventasblockWTO = new ArrayList<VentasBlockWTO>();
		
		for (UnidadesBlock unidadBlock: unidadesBlock) {
			ventasblockWTO.add(toVentasBlockWTO(unidadBlock));
		}
		
		return ventasblockWTO;
	}
	
	public static ProductoVentasBlockWTO toProductoVentasBlockWTO(VentaHistoricoTO ventaHistoricoTO) {
		
		ProductoVentasBlockWTO pvbWTO = new ProductoVentasBlockWTO();
		
		pvbWTO.setId(ventaHistoricoTO.getId());
		pvbWTO.setVersion(ventaHistoricoTO.getVersion());
		pvbWTO.setFecha_puesta_venta(ventaHistoricoTO.getFecha_puesta_venta());
		pvbWTO.setFecha_retirada(ventaHistoricoTO.getFecha_retirada());
		pvbWTO.setPrecio(ventaHistoricoTO.getPrecio());
		pvbWTO.setStock_inicial(ventaHistoricoTO.getStock_inicial());
		pvbWTO.setVentas(ventaHistoricoTO.getVentas());
		
		return pvbWTO;
	}
	
	public static List<ProductoVentasBlockWTO> toProductoVentasBlockWTO(List<VentaHistoricoTO> ventaHistoricoTOs) {
		
		List<ProductoVentasBlockWTO> pvbWTOs = new ArrayList<ProductoVentasBlockWTO>();
		
		for (VentaHistoricoTO ventaHistoricoTO: ventaHistoricoTOs) {
			pvbWTOs.add(toProductoVentasBlockWTO(ventaHistoricoTO));
		}
		
		return pvbWTOs;
	}
}
