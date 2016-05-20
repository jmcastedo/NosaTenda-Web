package es.udc.jcastedo.NosaTenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private final static String DRIVER_URL = "jdbc:postgresql://localhost:5433/pojo";
	private final static String USER = "pojo";
	private final static String PASSWORD = "pojo";
	
	static {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private ConnectionManager() {}
	
	public final static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
	}
	
	
}
