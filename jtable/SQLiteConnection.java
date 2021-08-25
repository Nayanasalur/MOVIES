package com.kirilo.sqlite.jtable;

import com.kirilo.sqlite.jdbc.Start;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    private static Connection connection;
    private static Driver driver;
    private static final String URL = "jdbc:sqlite:D:/Downloads/SQL в Java/MOVIES.db";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                if (driver == null) {
                    driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
                }
                connection = DriverManager.getConnection(URL);

            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, URL, e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
/*            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }*/
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, "Can't close", e);
        }
    }
}
