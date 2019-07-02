package by.gsu.study.sales.core.context;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;


@Component
@RequiredArgsConstructor
public class LiquibaseManager {

    private static final Logger log =
            LoggerFactory.getLogger(LiquibaseManager.class);

    private final ConnectionManager manager;

    @PostConstruct
    @SneakyThrows(liquibase.exception.LiquibaseException.class)
    public void initDatabase() {
        Connection conn = manager.getConnection();

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

        manager.reset();
    }

    /* don't forget to applicationContext.registerShutdownHook()! */
    @PreDestroy
    public void destroy() {
//        System.out.println("STOP!");
        log.error("OOOPS!");
    }
}
