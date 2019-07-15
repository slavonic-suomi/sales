package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.menu.RawMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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

//        topLevelMenu.execute();

        IRepository<Product> repository =
                context.getBean("productHibernateRepository", IRepository.class);

//        Product product = repository.findById(6);

//        System.out.println(product);

//        Product newProduct = new Product(null, "test_prd");

//        repository.save(newProduct);

//        System.out.println(newProduct);

//        repository.deleteById(6);

        List<Product> products = repository.findAll();

        System.out.println(products);
        for (Product product : products) {
            System.out.println(product.getName()
                    + " " + product.getSales());
        }
//        return products;

//        IRepository<Sale> saleRepo = context.getBean("saleHibernateRepository", IRepository.class);
//
//        List<Sale> sales = saleRepo.findAll();
//
//        sales.forEach(System.out::println);
    }
}
