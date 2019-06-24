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
@RequiredArgsConstructor
public class SaleFactory implements IFactory<Sale> {

    private final IRepository<User> userRepository;
    private final IRepository<Product> productRepository;

//    @Autowired
//    private IMenuItem<User> someItem;
//
//    @Autowired(required = false)
//    public void setSomeItem(IMenuItem<User> someItem) {
//        this.someItem = someItem;
//    }

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
