package utility;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/9/2021 11:28 PM
    Description: 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {

  public static final int TIMEOUT = 30;

  private static final String CONNECTION = "jdbc:sqlite:movie.db"; // include the library and the name of your database
  private static final String DRIVER_NAME = "org.sqlite.JDBC"; // the driver program

  // Create the database connection method
  public static Connection createConnection() throws ClassNotFoundException, SQLException {

    // Register the driver
    Class.forName(DRIVER_NAME); // loads the driver into the class

    // Create the database connection
    return DriverManager.getConnection(CONNECTION);
  }

  // Close the database connection method
  public static void closeConnection(final Connection connection, final Statement statement) {

    try {
      if(null != connection) {
        connection.close();
      }
      if(null!= statement) {
        statement.close();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

  }
}
