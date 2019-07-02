package by.gsu.study.sales.core.context;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectionManager {

    private volatile Connection connection;

    /*
        all values will be populated by spring from "application.properties"
        because of "@PropertySource(..." annotation in CommonConfig
     */
    @Value("${jdbc.driver}")
    private String driverName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @SneakyThrows
    public Connection getConnection() {
        if (connection == null) {
           connection = init();
        }
        return connection;
    }

    @SneakyThrows //use to avoid liquibase connection corruption
    void reset() {
        if (connection != null) {
            connection.close();
        }
        connection = init();
    }

    @SneakyThrows
    private Connection init() {
        Class.forName(driverName);

        return DriverManager.getConnection(
                url,
                user,
                password);
    }
}
