package by.gsu.study.sales.core.context.menu;

import by.gsu.study.sales.core.context.qualifiers.TopMenu;
import by.gsu.study.sales.core.entity.Category;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.impl.ICategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryMenuConfig {

    /**
     * top level menu bean for single entity,
     * marked with qualifier annotation @TopMenu
     * */
    @Bean
    @TopMenu
    public IMenuItem<Category> categoryTopLevelMenu(
            List<IMenuItem<Category>> items
    ) {
        return new CommonTopLevelMenu<>(
                items,
                "category management"
        );
    }

    /** sub items */

    @Bean
    public IMenuItem<Category> categoryExitMenuItem() {
        return () -> "Exit";
    }

    @Bean
    public IMenuItem<Category> categoryAddMenuItem(
            IFactory<Category> factory,
            ICategoryRepository repository
    ) {
        return new CommonAddMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Category> categoryUpdateMenuItem(
            IFactory<Category> factory,
            ICategoryRepository repository
    ) {
        return new CommonUpdateMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Category> categoryDeleteMenuItem(
            ICategoryRepository repository
    ) {
        return new CommonDeleteMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Category> categoryCountMenuItem(
            ICategoryRepository repository
    ) {
        return new CommonCountMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Category> categoryFindAllMenuItem(
            ICategoryRepository repository
    ) {
        return new CommonFindAllMenuItem<>(repository);
    }
}
