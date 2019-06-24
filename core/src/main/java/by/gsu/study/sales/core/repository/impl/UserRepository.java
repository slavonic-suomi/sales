package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.context.ConnectionManager;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class UserRepository
        extends AbstractDbRepository<User>
        implements IRepository<User> {


    public UserRepository(Parser<User> parser) {
        super(parser);
    }

    @Override
    protected String getTableName() {
        return "user";
    }

    @SneakyThrows(java.sql.SQLException.class)
    protected void create(User entity) {
        Connection connection
                = ConnectionManager.getConnection();

        String insert = "insert into user (email) values (?)";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setString(1, entity.getEmail());
            statement.executeUpdate();
        }
    }

    @SneakyThrows(java.sql.SQLException.class)
    protected void update(User entity) {
        Connection connection
                = ConnectionManager.getConnection();

        String insert = "update user set email = ? where id = ?";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setString(1, entity.getEmail());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

}
