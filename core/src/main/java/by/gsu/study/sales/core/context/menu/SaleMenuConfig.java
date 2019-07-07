package by.gsu.study.sales.core.context.menu;

import by.gsu.study.sales.core.context.qualifiers.TopMenu;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SaleMenuConfig {

    /**
     * top level menu bean for single entity,
     * marked with qualifier annotation @TopMenu
     * */
    @Bean
    @TopMenu
    public IMenuItem<Sale> saleTopLevelMenu(
            List<IMenuItem<Sale>> items
    ) {
        return new CommonTopLevelMenu<>(
                items,
                "sale management"
        );
    }

    /** sub items */

    @Bean
    public IMenuItem<Sale> saleExitMenuItem() {
        return () -> "Exit";
    }

    @Bean
    public IMenuItem<Sale> saleAddMenuItem(
            IFactory<Sale> factory,
            IRepository<Sale> repository
    ) {
        return new CommonAddMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Sale> saleUpdateMenuItem(
            IFactory<Sale> factory,
            IRepository<Sale> repository
    ) {
        return new CommonUpdateMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<Sale> saleDeleteMenuItem(
            IRepository<Sale> repository
    ) {
        return new CommonDeleteMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Sale> saleCountMenuItem(
            IRepository<Sale> repository
    ) {
        return new CommonCountMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<Sale> saleFindAllMenuItem(
            IRepository<Sale> repository
    ) {
        return new CommonFindAllMenuItem<>(repository);
    }
}
