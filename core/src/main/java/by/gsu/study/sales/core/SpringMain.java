package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(
                        CommonConfig.class
                );

        //enable @PreDestroy processing
//        context.registerShutdownHook();
//
//        CommonTopLevelMenu topLevelMenu =
//                context.getBean("topLevelMenu", CommonTopLevelMenu.class);
//
//        topLevelMenu.execute();

//        IMenuItem productMenu
//                = context.getBean("productTopLevelMenu", IMenuItem.class);
//
//        productMenu.execute();

        JdbcTemplate template = context.getBean(JdbcTemplate.class);

        ProductParser parser = context.getBean(ProductParser.class);

        template.update(
                "update User set email = 'test@test'" +
                        "where id = ?", new Object[] {18});

        List<User> users = template.query("select * from User",
                new BeanPropertyRowMapper<>(User.class));

        System.out.println(users);

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(
                "insert into Product (name) values (?)",
                new Object[] {"newproduct"}

                );

//        holder.getKey()

        List<Product> products = template
                .query("select * from Product", parser::parseRow);

        System.out.println(products);



    }
}
