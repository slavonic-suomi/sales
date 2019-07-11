package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.Category;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepository
        extends AbstractDbRepository<Category>
        implements IRepository<Category> {


    @Autowired
    public CategoryRepository(
            Parser<Category> parser,
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        super(parser, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected String getTableName() {
        return "category";
    }

    @Override
    protected void create(Category entity) {
        String sql = "insert into category (name) values (:name)";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }

    @Override
    protected void update(Category entity) {
        String sql = "update category set name = :name where id = :id";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }
}
