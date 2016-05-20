package es.udc.jcastedo.NosaTenda.modelutil.exceptions;

@SuppressWarnings("serial")
public class InsufficientStockException extends InstanceException {

	public InsufficientStockException(Object key, String className) {
		super("Insufficient stock", key, className);
	}
}
