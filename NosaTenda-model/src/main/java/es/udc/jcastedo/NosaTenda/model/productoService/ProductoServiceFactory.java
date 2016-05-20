package es.udc.jcastedo.NosaTenda.model.productoService;

import es.udc.jcastedo.NosaTenda.util.configuration.ConfigurationParametersManager;
import es.udc.jcastedo.NosaTenda.util.exceptions.ServiceException;

public class ProductoServiceFactory {

	private final static String CLASS_NAME_PARAMETER =
			"ProductoServiceFactory/className";
	
	private static ProductoService instance;
	
	static {
		try {
			String implClassName = ConfigurationParametersManager.getParameter(
					CLASS_NAME_PARAMETER);
			Class implClass = Class.forName(implClassName);
			instance = (ProductoService) implClass.newInstance();
		} catch (Exception e) {
			
		}
	}
	
	private ProductoServiceFactory() {}
	
	public static ProductoService getProductoService()
		throws ServiceException {
		
		if(instance == null) {
			throw new ServiceException("Cannot create instance of " +
					ProductoService.class);
		} else {
			return instance;
		}
	}
	
	
}
