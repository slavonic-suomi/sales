package by.gsu.study.sales.core.context.menu;

import by.gsu.study.sales.core.context.qualifiers.TopLevelMenu;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.CommonAddMenuItem;
import by.gsu.study.sales.core.menu.common.CommonFindAllMenuItem;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductMenuConfig {

    @Bean
    @Qualifier("product")
    public CommonAddMenuItem<Product> addProductMenuItem(
            IFactory<Product> factory,
            IRepository<Product> repository
    ) {
        return new CommonAddMenuItem<>(repository, factory);
    }

    @Bean
    @Qualifier("product")
    public CommonFindAllMenuItem<Product> findAllProductMenuItem(
            IRepository<Product> repository
    ) {
        return new CommonFindAllMenuItem<>(repository);
    }

    @Bean
    @TopLevelMenu
    public CommonTopLevelMenu<Product> productTopLevelMenu(
            @Qualifier("product") List<IMenuItem<Product>> items
    ) {
        return new CommonTopLevelMenu<>(
                items,
                "product management"
        );
    }
}
