package by.gsu.study.sales.core.context;

import by.gsu.study.sales.core.context.qualifiers.TopMenu;
import by.gsu.study.sales.core.entity.Category;
import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.factory.ReflectionFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.RawMenuItem;
import by.gsu.study.sales.core.menu.common.TopLevelMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("by.gsu.study.sales.core")
@PropertySource("classpath:application.properties")
public class CommonConfig {

    private static final Logger log =
            LoggerFactory.getLogger(CommonConfig.class);

    @Bean
    public IFactory<User> userFactory() {
        return new ReflectionFactory<>(User.class);
    }

    @Bean
    public IFactory<Product> productFactory() {
        return new ReflectionFactory<>(Product.class);
    }

    @Bean
    public IFactory<Category> categoryFactory() {
        return new ReflectionFactory<>(Category.class);
    }

    @Bean
    public RawMenuItem rootMenu(
            @TopMenu List<IMenuItem<? extends IEntity>> items) {
        List<IMenuItem<?>> elements = new ArrayList<>(items);
        elements.add(() -> "Exit");

        return new TopLevelMenu(elements, "top menu");
    }

    @PreDestroy
    public void destroy() {
        log.error("BYE!");
    }
}
