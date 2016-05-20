package es.udc.jcastedo.NosaTenda.model.util;

public class Haversine {

	public Haversine() {}
	
	public static double distFrom(Double latDouble1, Double lngDouble1, Double latDouble2, Double lngDouble2) {
		
		
	    try {
	    	double lat1 = latDouble1.doubleValue();
	    	double lng1 = lngDouble1.doubleValue();
	    	
	    	double lat2 = latDouble2.doubleValue();
	    	double lng2 = lngDouble2.doubleValue();
	    	
			double earthRadius = 6371.0; // 3958.75 miles (or 6371.0 kilometers)
			double dLat = Math.toRadians(lat2-lat1);
			double dLng = Math.toRadians(lng2-lng1);
			double sindLat = Math.sin(dLat / 2);
			double sindLng = Math.sin(dLng / 2);
			double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
			        * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double dist = earthRadius * c;

			return dist*1000; //distance in meters
		} catch (NullPointerException e) {
			
			return -1;
		}
	}
	
	public static double todouble(Double object) {
		
		if (object == null) {
			return 0;
		} else {
			return object.doubleValue();
		}
	}
}
