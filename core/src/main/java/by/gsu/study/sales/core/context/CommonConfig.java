package by.gsu.study.sales.core.context;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.factory.ReflectionFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuBuilder;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ComponentScan("by.gsu.study.sales.core")
@PropertySource("classpath:application.properties")
public class CommonConfig {


//    @Bean
//    public IRepository<Product> productRepository(
//            Parser<Product> parser
//    ) {
//        return new ProductRepository(parser);
//    }
//
//    @Bean
//    public Parser<Product> productParser() {
//        return new ProductParser();
//    }

    @Bean
    public IFactory<User> userFactory() {
        return new ReflectionFactory<>(User.class);
    }

    @Bean
    public IFactory<Product> productFactory() {
        return new ReflectionFactory<>(Product.class);
    }


    @Bean
    public CommonTopLevelMenu<Product> productMenu(
            MenuBuilder builder,
            IRepository<Product> repository,
            IFactory<Product> factory
    ) {
        return builder.getCommonMenu(
                "product management",
                repository,
                factory);
    }

    @Bean
    public CommonTopLevelMenu<User> userMenu(
            MenuBuilder builder,
            IRepository<User> repository,
            IFactory<User> factory
    ) {
        return builder.getCommonMenu(
                "user management",
                repository,
                factory);
    }

    @Bean
    public CommonTopLevelMenu<Sale> saleMenu(
            MenuBuilder builder,
            IRepository<Sale> repository,
            IFactory<Sale> factory
    ) {
        return builder.getCommonMenu(
                "sale management",
                repository,
                factory);
    }

    @Bean
    public CommonTopLevelMenu topLevelMenu(
            MenuBuilder builder,
            List<IMenuItem<?>> menu) {
        return builder.combine(menu);
    }
}
