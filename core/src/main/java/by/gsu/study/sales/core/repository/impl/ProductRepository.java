package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.context.ConnectionManager;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class ProductRepository
        extends AbstractDbRepository<Product>
        implements IRepository<Product> {

    @Autowired
    public ProductRepository(
            Parser<Product> parser,
            ConnectionManager manager) {
        super(parser, manager);
    }

    @Override
    protected String getTableName() {
        return "product";
    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    protected void create(Product entity) {
        Connection connection
                = manager.getConnection();

        String insert = "insert into product (name) values (?)";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    protected void update(Product entity) {
        Connection connection
                = manager.getConnection();

        String insert = "update product set name = ? where id = ?";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }
}
