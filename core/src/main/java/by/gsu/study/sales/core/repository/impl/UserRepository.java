package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.User;
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
import org.springframework.transaction.annotation.Transactional;

@Component
@Primary
public class UserRepository
        extends AbstractDbRepository<User>
        implements IRepository<User> {

    @Autowired
    public UserRepository(
            Parser<User> parser,
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(parser, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected String getTableName() {
        return "user";
    }

    @Override
    protected void create(User entity) {
        String sql = "insert into user (email) values (:email)";
        KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(
                sql,
                new BeanPropertySqlParameterSource(entity),
                holder
        );
        entity.setId(holder.getKey().intValue());
    }

    @Override
    protected void update(User entity) {
        String sql = "update user set email = :email where id = :id";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }

}
