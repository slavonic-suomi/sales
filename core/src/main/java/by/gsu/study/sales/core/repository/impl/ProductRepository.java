package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProductRepository
        extends AbstractDbRepository<Product>
        implements IRepository<Product> {


    @Autowired
    public ProductRepository(
            Parser<Product> parser,
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        super(parser, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected String getTableName() {
        return "product";
    }

    @Override
    protected void create(Product entity) {
        String sql = "insert into product (name) values (:name)";
        KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity), holder);
        entity.setId(holder.getKey().intValue());
    }

    @Override
    protected void update(Product entity) {
        String sql = "update product set name = :name where id = :id";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }
}
