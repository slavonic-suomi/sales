package by.gsu.study.sales.core.repository;

import by.gsu.study.sales.core.entity.IEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@RequiredArgsConstructor
public abstract class AbstractDbRepository<E extends IEntity>
    implements IRepository<E> {

    private final Parser<E> parser;

    /** keep both of templates for showing examples */
    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedJdbcTemplate;

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
    public void deleteById(Integer id) {
        String tableName = getTableName();
        String delete = "delete from " + tableName +" where id = ?";

        jdbcTemplate.update(delete, new Object[]{id}, new int[]{Types.INTEGER});
    }

    @Override
    public List<E> findAll() {
        String selectAll = "select * from " + getTableName();
        return jdbcTemplate.query(selectAll, parser);
    }

    @Override
    public E findById(Integer id) {
        String selectById = "select * from " + getTableName() + " where id = :id";

        Map<String, Integer> params = Collections.singletonMap("id", id);

        return namedJdbcTemplate.queryForObject(selectById, params, parser);
    }

    @Override
    public int count() {
        String count = "select count(*) from " + getTableName();
        Integer result = jdbcTemplate.queryForObject(count, (rs, rowNum) -> rs.getInt(1));
        return Optional.ofNullable(result).orElse(0);
    }
}
