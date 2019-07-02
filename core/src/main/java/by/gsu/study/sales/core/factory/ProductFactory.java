package by.gsu.study.sales.core.factory;

import by.gsu.study.sales.core.entity.Product;
import org.springframework.stereotype.Component;

//@Component
//just a reminder, we can make subclasses for each entity and annotate it with @Component
public class ProductFactory
    extends ReflectionFactory<Product> {

    public ProductFactory() {
        super(Product.class);
    }
}
