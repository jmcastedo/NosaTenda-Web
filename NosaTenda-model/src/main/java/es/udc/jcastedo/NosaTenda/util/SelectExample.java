package es.udc.jcastedo.NosaTenda.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SelectExample {

	public static void main (String[] args) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* Get a connection */
			connection = ConnectionManager.getConnection();
			
			/* Create "preparedStatement". */
			String queryString = "SELECT name, price FROM products";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* Execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* Iterate over matched rows. */
			while (resultSet.next()) {
				String productName = resultSet.getString(1);
				double price = resultSet.getDouble(2);
				System.out.println("productName = " + productName +
						" | price = " + price);
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		} // finally
	} // main
} // java
