package com.kirilo.sqlite.jdbc;

import java.sql.*;

public class Sample {
    private static final String SQL = "SELECT * from dir_Make";
    private static ResultSet resultSet;
    private static Statement statement;
    private static Connection connection;


    public static void main(String[] args) {
        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            connection = DriverManager.getConnection("jdbc:sqlite:D:/Downloads/Батиршинов/3.Базы данных и SQL в Java/CarShop.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name_en") + " - " + resultSet.getObject("name_ua"));
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
