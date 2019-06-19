package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.SaleRepository;
import by.gsu.study.sales.core.repository.impl.UserRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import by.gsu.study.sales.core.repository.impl.parser.SaleParser;
import by.gsu.study.sales.core.repository.impl.parser.UserParser;

import java.util.Date;

@Deprecated
public class Main {

    public static void main(String[] args) {
        LiquibaseManager.initDatabase();

        ProductRepository productRepository =
                new ProductRepository(
                new ProductParser()
        );
        UserRepository userIRepository = new UserRepository(
                new UserParser()
        );
        SaleRepository saleRepository =
                new SaleRepository(
                        new SaleParser(productRepository, userIRepository)
                );


        Product product = productRepository.findById(1);

        User user = userIRepository
                .findAll()
                .stream()
                .findFirst()
                .orElse(null);

        Sale sale = new Sale(null, product, user, new Date());

        saleRepository.save(sale);

        System.out.println(saleRepository.findAll());
    }

}
