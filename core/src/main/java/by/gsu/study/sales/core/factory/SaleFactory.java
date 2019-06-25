package by.gsu.study.sales.core.factory;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuHelper;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SaleFactory implements IFactory<Sale> {

    @Autowired //for field injection example
    private IRepository<User> userRepository;

    private IRepository<Product> productRepository;

    @Autowired //for setter injection example
    public void setProductRepository(IRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Sale create() {
        System.out.println("Input product id:");
        int productId = MenuHelper.getIndex();

        System.out.println("Input user id:");
        int userId = MenuHelper.getIndex();

        Product product = productRepository.findById(productId);
        User    user    = userRepository.findById(userId);

        return new Sale(null, product, user, new Date());
    }
}
