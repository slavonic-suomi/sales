package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.repository.AbstractDbRepository;
import by.gsu.study.sales.core.repository.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SaleRepository
        extends AbstractDbRepository<Sale> {

    @Autowired
    public SaleRepository(Parser<Sale> parser,
                          JdbcTemplate jdbcTemplate,
                          NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(parser, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected String getTableName() {
        return "sale";
    }

    @Override
    protected void create(Sale entity) {
        String sql = "insert into sale (product_id, user_id, date) " +
                " values (:product, :user, :date)";

        namedJdbcTemplate.update(sql, getParams(entity));

    }

    @Override
    protected void update(Sale entity) {

        String sql = "update sale set " +
                "product_id = :product, " +
                "user_id = :user, " +
                "date = :date " +
                "where id = :id";

        namedJdbcTemplate.update(sql, getParams(entity));

    }

    private SqlParameterSource getParams(Sale entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("product", entity.getProduct().getId());
        params.addValue("user", entity.getUser().getId());
        params.addValue("date", entity.getDate());
        if (entity.getId() != null) {
            params.addValue("id", entity.getId());
        }

        return params;
    }
}
