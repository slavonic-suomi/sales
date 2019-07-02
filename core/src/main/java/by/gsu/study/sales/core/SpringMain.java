package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(
                        CommonConfig.class
                );

        //enable @PreDestroy processing
        context.registerShutdownHook();

        CommonTopLevelMenu topLevelMenu =
                context.getBean("topLevelMenu", CommonTopLevelMenu.class);

        topLevelMenu.execute();
    }
}
