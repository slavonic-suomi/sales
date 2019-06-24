package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        CommonConfig.class
                );

        IRepository repository =
                context.getBean("productRepository", IRepository.class);
        repository.findAll().forEach(System.out::println);
    }
}
