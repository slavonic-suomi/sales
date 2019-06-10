package by.gsu.study.sales.core.context;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import lombok.SneakyThrows;

import java.sql.Connection;


public class LiquibaseManager {

    @SneakyThrows(liquibase.exception.LiquibaseException.class)
    public static void initDatabase() {
        Connection conn = ConnectionManager.getConnection();

        Database database = DatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(
                        new JdbcConnection(conn)
                );

        //TODO move to classpath resource
        String path = LiquibaseManager.class
                .getClassLoader()
                .getResource("db/initial.sql")
                .getFile();

        Liquibase liquibase = new Liquibase(
                path,
                new FileSystemResourceAccessor(),
                database
        );

        liquibase.update("");

        ConnectionManager.reset();
    }
}
