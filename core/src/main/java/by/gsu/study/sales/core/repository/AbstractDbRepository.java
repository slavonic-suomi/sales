package by.gsu.study.sales.core.repository;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.context.ConnectionManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractDbRepository<E extends IEntity>
    implements IRepository<E> {

    private final Parser<E> parser;

    protected final ConnectionManager manager;

    protected abstract String getTableName();
    protected abstract void create(E entity);
    protected abstract void update(E entity);

    @Override
    public void save(E entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public void deleteById(Integer id) {
        Connection connection
                = manager.getConnection();
        String tableName = getTableName();
        String delete = "delete from " + tableName +" where id = ?";

        try (var statement = connection.prepareStatement(delete)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public List<E> findAll() {
        Connection connection =
                manager.getConnection();

        String selectAll = "select * from " + getTableName();
        try (var statement = connection.prepareStatement(selectAll)) {
            try (var resultSet = statement.executeQuery()) {
                return parser.parseList(resultSet);
            }
        }

    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public E findById(Integer id) {
        Connection connection = manager.getConnection();
        String selectById = "select * from " + getTableName() + " where id = ?";

        try (var statement = connection.prepareStatement(selectById)) {
            statement.setInt(1, id);
            try (var resultSet = statement.executeQuery()) {
                return parser.parseSingleRow(resultSet);
            }
        }
    }

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public int count() {
        Connection connection = manager.getConnection();
        String count = "select count(*) from " + getTableName();
        try (var statement = connection.prepareStatement(count)) {
            try (var resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        }
    }
}
