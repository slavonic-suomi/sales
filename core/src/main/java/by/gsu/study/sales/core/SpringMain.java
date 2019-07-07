package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.menu.RawMenuItem;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(
                        CommonConfig.class
                );

//        enable @PreDestroy processing
        context.registerShutdownHook();
//
        RawMenuItem topLevelMenu =
                context.getBean("rootMenu", RawMenuItem.class);

        topLevelMenu.execute();

//        IMenuItem productMenu
//                = context.getBean("productTopLevelMenu", IMenuItem.class);
//
//        productMenu.execute();

      /*  JdbcTemplate template = context.getBean(JdbcTemplate.class);

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
                new Object[] {"newproduct2"}

                );

//        holder.getKey()

        List<Product> products = template
                .query("select * from Product", parser::mapRow);

        System.out.println(products);


*/
    }
}
