package by.gsu.study.sales.core.context.menu;

import by.gsu.study.sales.core.context.qualifiers.TopMenu;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserMenuConfig {

    /**
     * top level menu bean for single entity,
     * marked with qualifier annotation @TopMenu
     * */
    @Bean
    @TopMenu
    public IMenuItem<User> userTopLevelMenu(
            List<IMenuItem<User>> items
    ) {
        return new CommonTopLevelMenu<>(
                items,
                "user management"
        );
    }

    /** sub items */

    @Bean
    public IMenuItem<User> userExitMenuItem() {
        return () -> "Exit";
    }

    @Bean
    public IMenuItem<User> userAddMenuItem(
            IFactory<User> factory,
            IUserRepository repository
    ) {
        return new CommonAddMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<User> userUpdateMenuItem(
            IFactory<User> factory,
            IUserRepository repository
    ) {
        return new CommonUpdateMenuItem<>(repository, factory);
    }

    @Bean
    public IMenuItem<User> userDeleteMenuItem(
            IUserRepository repository
    ) {
        return new CommonDeleteMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<User> userCountMenuItem(
            IUserRepository repository
    ) {
        return new CommonCountMenuItem<>(repository);
    }

    @Bean
    public IMenuItem<User> userFindAllMenuItem(
            IUserRepository repository
    ) {
        return new CommonFindAllMenuItem<>(repository);
    }
}