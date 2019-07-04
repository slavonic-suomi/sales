package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.context.ConnectionManager;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
@Transactional
public class UserRepository
        extends AbstractDbRepository<User>
        implements IRepository<User> {

    @Autowired
    public UserRepository(Parser<User> parser, ConnectionManager manager) {
        super(parser, manager);
    }

    @Override
    protected String getTableName() {
        return "user";
    }

    @SneakyThrows(java.sql.SQLException.class)
    protected void create(User entity) {
        Connection connection
                = manager.getConnection();

        String insert = "insert into user (email) values (?)";
        try (var statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getEmail());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            /// parse rs
        }
    }

    @SneakyThrows(java.sql.SQLException.class)
    protected void update(User entity) {
        Connection connection
                = manager.getConnection();

        String insert = "update user set email = ? where id = ?";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setString(1, entity.getEmail());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

}
