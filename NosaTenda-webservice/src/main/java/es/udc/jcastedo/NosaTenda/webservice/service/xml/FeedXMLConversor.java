package es.udc.jcastedo.NosaTenda.webservice.service.xml;

import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.util.exceptions.ParsingException;
import es.udc.jcastedo.NosaTenda.webservice.service.EntryTO;
import es.udc.jcastedo.NosaTenda.webservice.service.FeedTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoToFeedConversor;

public class FeedXMLConversor {
	
	//private static final Logger logger = LoggerFactory.getLogger(FeedXMLConversor.class);

	//mirar que Namespace usamos
	public final static Namespace XML_NS =
			Namespace.getNamespace("http://www.w3.org/2005/Atom");
	
	private FeedXMLConversor() {}
	
	public static Element toXML(List<ProductoTO> productoTOs)
		throws ParsingException {
		
		try {
			FeedTO feedTO = ProductoToFeedConversor.toFeedTO(productoTOs);
			
			Element feedElement = toXML(feedTO);
			
			return feedElement;
		} catch (Exception e) {
			throw new ParsingException("Error serializing instand of List <" 
					+ Element.class + '>', e);
		}
	}
	
	public static Element toXML(FeedTO feedTO) {
		
		Element feedElement = new Element("feed", XML_NS);
		
		Element titleElement = new Element("title", XML_NS);
		titleElement.setText(feedTO.getTitle());
		feedElement.addContent(titleElement);
		
		Element subtitleElement = new Element("subtitle", XML_NS);
		subtitleElement.setText(feedTO.getSubtitle());
		feedElement.addContent(subtitleElement);
		
		Element linkElement = new Element("link", XML_NS);
		linkElement.setText(feedTO.getLink());
		feedElement.addContent(linkElement);
		
		for(EntryTO e: feedTO.getEntries()) {
			feedElement.addContent(toXML(e));			
		}
		
		return feedElement;
	}
	
	private static Element toXML(EntryTO entryTO) {
		
		Element entryElement = new Element("entry", XML_NS);
		
		Element idElement = new Element("id", XML_NS);
		idElement.setText(entryTO.getId().toString());
		entryElement.addContent(idElement);
		
		Element nombreElement = new Element("nombre", XML_NS);
		nombreElement.setText(entryTO.getNombre());
		entryElement.addContent(nombreElement);
		
		Element descripcionElement = new Element("descripcion", XML_NS);
		descripcionElement.setText(entryTO.getDescripcion());
		entryElement.addContent(descripcionElement);
		
		Element imagenElement = new Element("imagen", XML_NS);
		imagenElement.setText(entryTO.getImagen());
		entryElement.addContent(imagenElement);
		
		Element precioElement = new Element("precio", XML_NS);
		precioElement.setText(entryTO.getPrecio().toString());
		entryElement.addContent(precioElement);
		
		Element nombreTiendaElement = new Element("nombreTienda", XML_NS);
		nombreTiendaElement.setText(entryTO.getNombreTienda());
		entryElement.addContent(nombreTiendaElement);
		
		Element direccionTiendaElement = new Element("direccionTienda", XML_NS);
		direccionTiendaElement.setText(entryTO.getDireccionTienda());
		entryElement.addContent(direccionTiendaElement);
		
		return entryElement;
	}
}
