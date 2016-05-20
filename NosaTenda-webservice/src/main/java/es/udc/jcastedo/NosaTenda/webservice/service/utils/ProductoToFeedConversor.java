package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.webservice.service.EntryTO;
import es.udc.jcastedo.NosaTenda.webservice.service.FeedTO;

public class ProductoToFeedConversor {
	
	//private static final Logger logger = LoggerFactory.getLogger(ProductoToFeedConversor.class);

	public ProductoToFeedConversor() {}
	
	public static FeedTO toFeedTO(List<ProductoTO> productoTOs) {
		
		FeedTO feedTO = new FeedTO();
		
		feedTO.setTitle("GetProductos");
		feedTO.setSubtitle("Lista de todos los productos");
		feedTO.setLink("http://www.acme.com/rest/service/GetProductosServlet");
		
		List<EntryTO> entries = new ArrayList<EntryTO>();
		
		for( ProductoTO p: productoTOs) {
			entries.add(toEntryTO(p));
		}
		
		feedTO.setEntries(entries);
		
		return feedTO;
	}
	
	public static EntryTO toEntryTO(ProductoTO productoTO) {
		
		EntryTO entryTO = new EntryTO();
		
		entryTO.setId(productoTO.getId());
		entryTO.setNombre(productoTO.getNombre());
		entryTO.setDescripcion(productoTO.getDescripcion());
		entryTO.setPrecio(productoTO.getPrecio());
		entryTO.setImagen(productoTO.getImagen());
		entryTO.setNombreTienda("");
		entryTO.setDireccionTienda("");
		
		return entryTO;
	}
}
