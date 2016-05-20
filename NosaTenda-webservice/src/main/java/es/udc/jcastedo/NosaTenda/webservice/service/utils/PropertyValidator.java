package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * It contains common validation/conversion operations. Each operation takes the
 * following parameters:
 * 
 * <ul>
 * 
 * <li>An errors <code>Map</code>.</li>
 * <li>A property name: the name of a form field.</li>
 * <li>The property value: the value of the form field.</li>
 * <li>A <code>boolean</code> value specifying whether or not the property is
 * mandatory.</li>
 * <li>A specification of the range of valid values (maybe specified with
 * several parameters).</li>
 * <li>A <code>Locale</code> object if validation/conversion is
 * locale-dependent.</li>
 * 
 * </ul>
 * 
 * If the property value is not correct, an error message is
 * added to the errors <code>Map</code> using the property name. The error 
 * message maybe one of the following:
 * 
 * <ul>
 * 
 * <li><code>INCORRECT_LONG_VALUE</code>: incorrect integer value.</li>
 * <li><code>INCORRECT_DOUBLE_VALUE</code>: incorrect real value.</li>
 * <li><code>MANDATORY_FIELD</code>: mandatory field.</li>
 * </ul>
 * 
 * <p>
 * Some operations return the value resulting from the conversion. If the
 * conversion is not successful, the returned value is meaningless.
 * <p>
 */
public class PropertyValidator {

	private final static String INCORRECT_LONG_VALUE = "Valor incorrecto";
	private final static String INCORRECT_DOUBLE_VALUE = "Valor real incorrecto";
	private final static String MANDATORY_FIELD = "Campo obligatorio";
	private final static String INCORRECT_CONFIRM_PASSWORD = "Las contraseñas no coinciden";
	private final static String INCORRECT_CONFIRM_CORREO = "Los correos no coinciden";
	private final static String INCORRECT_DATE_VALUE = "Fecha incorrecta";
	
	private PropertyValidator() {}
	
	public final static Calendar validateCalendar(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory,
			SimpleDateFormat sdf,
			Calendar lowerValidLimit, Calendar upperValidLimit) {
		
		Calendar propertyValueAsCalendar = Calendar.getInstance();
		propertyValue = propertyValue == null ? "" : propertyValue.trim();
		
		if(validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				propertyValueAsCalendar.setTime(sdf.parse(propertyValue));
				if ((propertyValueAsCalendar.compareTo(lowerValidLimit) < 0)
					|| (propertyValueAsCalendar.compareTo(upperValidLimit) > 0)) {
						propertyValueIsCorrect = false;
					}
				
			} catch (ParseException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
				errors.put(propertyName, INCORRECT_DATE_VALUE);
			}
		}
		
		return propertyValueAsCalendar;
	}
	
	public final static long validatelong(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory,
			long lowerValidLimit, long upperValidLimit) {
		
		long propertyValueAsLong = 0;
		propertyValue = propertyValue == null ? "" : propertyValue.trim();
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				propertyValueAsLong = new Long(propertyValue).longValue();
				if ((propertyValueAsLong < lowerValidLimit)
						|| (propertyValueAsLong > upperValidLimit)) {
					propertyValueIsCorrect = false;
				}
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
				errors.put(propertyName, INCORRECT_LONG_VALUE);
			}
		}
		
		return propertyValueAsLong;
	}
	
	public final static Long validateLong(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory,
			long lowerValidLimit, long upperValidLimit) {
		
		long propertyValueAsLong = 0;
		boolean propertyValueIsNull = false;
		if (propertyValue == null || propertyValue== "") propertyValueIsNull = true;
		propertyValue = propertyValue == null ? "" : propertyValue.trim();
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				propertyValueAsLong = new Long(propertyValue).longValue();
				if ((propertyValueAsLong < lowerValidLimit)
						|| (propertyValueAsLong > upperValidLimit)) {
					propertyValueIsCorrect = false;
				}
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect && !propertyValueIsNull) {
				errors.put(propertyName, INCORRECT_LONG_VALUE);
			}
		}
		
		return new Long(propertyValueAsLong);
	}
	
	public final static double validatedouble(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory,
			double lowerValidLimit, double upperValidLimit) {
		
		double propertyValueAsDouble = 0;
		propertyValue = propertyValue == null ? "" : propertyValue.trim();
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				propertyValueAsDouble = Double.parseDouble(propertyValue);
				if ((propertyValueAsDouble < lowerValidLimit)
						|| (propertyValueAsDouble > upperValidLimit)) {
					propertyValueIsCorrect = false;
				}
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
				errors.put(propertyName, INCORRECT_DOUBLE_VALUE);
			}
		}
		
		return propertyValueAsDouble;
	}
	
	public final static Double validateDouble(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory,
			double lowerValidLimit, double upperValidLimit) {
		
		double propertyValueAsDouble = 0;
		propertyValue = propertyValue == null ? "" : propertyValue.trim();
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				propertyValueAsDouble = Double.parseDouble(propertyValue);
				if ((propertyValueAsDouble < lowerValidLimit)
						|| (propertyValueAsDouble > upperValidLimit)) {
					propertyValueIsCorrect = false;
				}
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
				errors.put(propertyName, INCORRECT_DOUBLE_VALUE);
			}
		}
		
		return new Double(propertyValueAsDouble);
	}
	
	/**
	 * Checks if a mandatory field is present.
	 * 
	 * @return <code>false</code> if <code>propertyValue</code> is
	 *         <code>null</code> or the empty string; <code>true</code>
	 *         otherwise
	 */
	public final static boolean validateMandatory(Map<String, String> errors,
			String propertyName, String propertyValue) {
		
		if ((propertyValue == null) || (propertyValue.length() == 0)) {
			errors.put(propertyName, MANDATORY_FIELD);
			return false;
		} else {
			return true;
		}
	}
	
	private final static boolean validateMandatory(Map<String, String> errors,
			String propertyName, String propertyValue, boolean mandatory) {
		
		if (mandatory) {
			return validateMandatory(errors,propertyName,propertyValue);
		} else {
			return true;
		}
	}
	
	public final static boolean validateConfirmPassword(Map<String, String> errors,
			String propertyName, String propertyValue, String propertyNameConfirm, String propertyValueConfirm) {
		
		if (propertyValue.equals(propertyValueConfirm)) {
			return true;
		} else {
			errors.put(propertyName, INCORRECT_CONFIRM_PASSWORD);
			errors.put(propertyNameConfirm, INCORRECT_CONFIRM_PASSWORD);
			return false;
		}	
	}
	
	public final static boolean validateConfirmCorreo(Map<String, String> errors,
			String propertyName, String propertyValue, String propertyNameConfirm, String propertyValueConfirm) {
		
		if (propertyValue.equals(propertyValueConfirm)) {
			return true;
		} else {
			errors.put(propertyName, INCORRECT_CONFIRM_CORREO);
			errors.put(propertyNameConfirm, INCORRECT_CONFIRM_CORREO);
			return false;
		}	
	}
}
