package by.gsu.study.sales.core.context;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        if (connection == null) {
           connection = init();
        }
        return connection;
    }

    @SneakyThrows //use to avoid liquibase connection corruption
    static void reset() {
        if (connection != null) {
            connection.close();
        }
        connection = init();
    }

    @SneakyThrows
    private static Connection init() {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sales",
                "root",
                "root");
    }
}
