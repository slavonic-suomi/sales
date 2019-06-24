package by.gsu.study.sales.core.repository.impl.parser;

import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class UserParser implements Parser<User> {

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public User parseRow(ResultSet resultSet, int rowIndex) {
        int id       = resultSet.getInt("id");
        String email = resultSet.getString(2);

        return new User(id, email);
    }
}
