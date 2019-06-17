package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.context.ConnectionManager;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;

import java.sql.Connection;

public class SaleRepository
    extends AbstractDbRepository<Sale> {

    public SaleRepository(Parser<Sale> parser) {
        super(parser);
    }

    @Override
    protected String getTableName() {
        return "sale";
    }

    @Override
    @SneakyThrows({java.sql.SQLException.class})
    protected void create(Sale entity) {
        Connection connection
                = ConnectionManager.getConnection();

        String insert = "insert into sale (product_id, user_id, date) values (?, ?, ?)";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setInt(1, entity.getProduct().getId());
            statement.setInt(2, entity.getUser().getId());
            statement.setDate(3, new java.sql.Date(entity.getDate().getTime()));
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows({java.sql.SQLException.class})
    protected void update(Sale entity) {
        Connection connection
                = ConnectionManager.getConnection();

        String insert = "update sale set product_id = ?, user_id = ?, date = ? where id = ?";
        try (var statement = connection.prepareStatement(insert)) {
            statement.setInt(1, entity.getProduct().getId());
            statement.setInt(2, entity.getUser().getId());
            statement.setDate(3, new java.sql.Date(entity.getDate().getTime()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        }

    }
}
