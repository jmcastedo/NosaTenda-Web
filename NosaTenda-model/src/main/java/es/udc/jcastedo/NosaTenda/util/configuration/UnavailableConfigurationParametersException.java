package es.udc.jcastedo.NosaTenda.util.configuration;

public class UnavailableConfigurationParametersException extends
		RuntimeException {

	public UnavailableConfigurationParametersException(Exception e) {
		super("Cannot access to configuration parameters", e);
	}
}
