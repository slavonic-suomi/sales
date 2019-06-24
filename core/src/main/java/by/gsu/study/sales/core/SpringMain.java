package by.gsu.study.sales.core;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "classpath:beans.xml"
                );


        IRepository<Product> productRepository =
                context.getBean("productRepository", IRepository.class);

        productRepository.findAll().forEach(System.out::println);
    }
}
