package by.gsu.study.sales.core.context.menu;

import by.gsu.study.sales.core.context.qualifiers.TopMenu;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.IProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductMenuConfig {

    /**
     * top level menu bean for single entity,
     * marked with qualifier annotation @TopMenu
     * */
    @Bean
    @TopMenu
    public IMenuItem<Product> productTopLevelMenu(
            List<IMenuItem<Product>> items
    ) {
        return new CommonTopLevelMenu<>(
                items,
                "product management"
        );
    }

    /** sub items */

    @Bean
    public IMenuItem<Product> productExitMenuItem() {
        return () -> "Exit";
    }

    @Bean
    public IMenuItem<Product> productAddMenuItem(
            IFactory<Product> factory,
            IProductRepository repository
    ) {
        return new CommonAddMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Product> productUpdateMenuItem(
            IFactory<Product> factory,
            IProductRepository repository
    ) {
        return new CommonUpdateMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Product> productDeleteMenuItem(
            IProductRepository repository
    ) {
        return new CommonDeleteMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Product> productCountMenuItem(
            IProductRepository repository
    ) {
        return new CommonCountMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Product> productFindAllMenuItem(
            IProductRepository repository
    ) {
        return new CommonFindAllMenuItem<>(repository);
    }
}
