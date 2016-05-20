package es.udc.jcastedo.NosaTenda.modelutil.exceptions;

@SuppressWarnings("serial")
public class BadFormatRequestException extends InstanceException {

	public BadFormatRequestException(Object key, String className) {
		super("Badly formatted request, aborted", key, className);
	}
}
