package by.gsu.study.sales.core.repository.impl.parser;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.Parser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class ProductParser implements Parser<Product> {

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public Product mapRow(ResultSet resultSet, int rowIndex) {
        int id      = resultSet.getInt("id");
        String name = resultSet.getString(2);

        return new Product(id, name);
    }
}
