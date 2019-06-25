package by.gsu.study.sales.core.repository.impl.parser;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;

@RequiredArgsConstructor
@Component
public class SaleParser implements Parser<Sale> {

    private final IRepository<Product> productRepository;
    private final IRepository<User> userRepository;

    @Override
    @SneakyThrows(java.sql.SQLException.class)
    public Sale parseRow(ResultSet resultSet, int rowIndex) {
        int id        = resultSet.getInt("id");
        int productId = resultSet.getInt("product_id");
        int userId    = resultSet.getInt("user_id");
        Date date     = resultSet.getDate("date");

        Product product = productRepository.findById(productId);
        User user = userRepository.findById(userId);

        return new Sale(id, product, user, date);
    }
}
