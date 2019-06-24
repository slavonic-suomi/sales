package by.gsu.study.sales.core.context;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.Parser;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("by.gsu.study.sales.core")
public class CommonConfig {

//    @Bean
//    public IRepository<Product> productRepository(
//            Parser<Product> parser
//    ) {
//        return new ProductRepository(parser);
//    }
//
//    @Bean
//    public Parser<Product> productParser() {
//        return new ProductParser();
//    }

}
