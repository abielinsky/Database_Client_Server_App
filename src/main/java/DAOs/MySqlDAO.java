package DAOs;

import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAO {

    public Connection getConnection() throws DaoException{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/games_db";
        String username = "root";
        String password = "";
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Failed to find the driver class " + cnfe.getMessage());
            System.exit(1);
        } catch (SQLException sqe) {
            System.out.println("Connection failed " + sqe.getMessage());
            System.exit(2);
        }
        return connection;

    }

    public void freeConnection(Connection connection) throws DaoException {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException sqe) {
            System.out.println("Failed to  free the connection: " + sqe.getMessage());
            System.exit(1);
        }


    }


}