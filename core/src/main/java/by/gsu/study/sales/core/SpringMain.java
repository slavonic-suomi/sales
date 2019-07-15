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
    }
}
