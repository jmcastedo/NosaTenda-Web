package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets.TestConfirmPaymentServlet;

@SuppressWarnings("serial")
public class JSONHttpPaypalServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(JSONHttpPaypalServlet.class);
	
	public void init(ServletConfig servletConfig) throws ServletException {
		
		// ##Load Configuration
		// Load SDK configuration for
		// the resource. This intialization code can be
		// done as Init Servlet.
		InputStream is = TestConfirmPaymentServlet.class
				.getResourceAsStream("/sdk_config.properties");

		try {
			PayPalResource.initConfig(is);
		} catch (PayPalRESTException e) {
			logger.error(e.getMessage());
		}
	}

}
