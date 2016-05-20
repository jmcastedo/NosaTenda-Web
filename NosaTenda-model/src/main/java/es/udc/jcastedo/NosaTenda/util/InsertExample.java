package es.udc.jcastedo.NosaTenda.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class InsertExample {
	
	public static void main (String[] args) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* Get a connection */
			connection = ConnectionManager.getConnection();
			
			/* Create data for some products. */
			String[] productIdentifiers = new String[] {"product-1", "product-2",
					"product-3"};
			double[] prices = new double[] {100.0, 200.0, 300.0};
			
			/* Create "preparedStatement" */
			String queryString = "INSERT INTO products " +
					"(name, price) VALUES (?,?)";
			preparedStatement =
					connection.prepareStatement(queryString);
			
			/* Insert the products in database. */
			for (int i=0; i<productIdentifiers.length; i++) {
				/* Fill "preparedStatement". */
				preparedStatement.setString(1, productIdentifiers[i]);
				preparedStatement.setDouble(2, prices[i]);
				
				/* Execute query. */
				int insertedRows = preparedStatement.executeUpdate();
				
				if (insertedRows != 1) {
					throw new SQLException(productIdentifiers[i] + ": problemas when inserting !!!!");
				}
			}
			
			System.out.println("Products inserted");
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		
		
	}

}
