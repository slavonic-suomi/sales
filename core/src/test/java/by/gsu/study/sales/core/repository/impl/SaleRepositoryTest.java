package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(CommonConfig.class)
//@DirtiesContext
public class SaleRepositoryTest {

    @Autowired
    private IRepository<Sale> saleRepository;

    @Autowired
    private IRepository<User> userRepository;

    @Autowired
    private IRepository<Product> productRepository;

    @Test
    public void create() {
        User user = new User(null, "test@test");
        userRepository.save(user);

        Product product = new Product(null, "test");
        productRepository.save(product);

        Sale sale = new Sale(null, product, user, new Date());

        saleRepository.save(sale);

        assertNotNull(sale.getId());
    }

    @After
    public void destroy() {
        saleRepository.findAll()
                .forEach(s -> saleRepository.deleteById(s.getId()));

        userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals("test@test"))
                .findFirst()
                .ifPresent(user -> {
                    userRepository.deleteById(user.getId());
                });
    }
}