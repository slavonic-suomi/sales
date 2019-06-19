package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.factory.ReflectionFactory;
import by.gsu.study.sales.core.factory.SaleFactory;
import by.gsu.study.sales.core.menu.MenuBuilder;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.SaleRepository;
import by.gsu.study.sales.core.repository.impl.UserRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import by.gsu.study.sales.core.repository.impl.parser.SaleParser;
import by.gsu.study.sales.core.repository.impl.parser.UserParser;

import static java.util.Arrays.asList;

public class LordOfTheMains {
    public static void main(String[] args) {
        LiquibaseManager.initDatabase();

        ProductParser productParser = new ProductParser();
        UserParser userParser = new UserParser();

        IRepository<Product> productRepository = new ProductRepository(productParser);
        IRepository<User> userRepository = new UserRepository(userParser);

        SaleParser saleParser = new SaleParser(productRepository, userRepository);
        IRepository<Sale> saleRepository = new SaleRepository(saleParser);

        IFactory<Product> productFactory = new ReflectionFactory<>(Product.class);
        IFactory<User> userFactory = new ReflectionFactory<>(User.class);
        IFactory<Sale> saleFactory = new SaleFactory(userRepository, productRepository);


        MenuBuilder menuBuilder = new MenuBuilder();

        CommonTopLevelMenu<Product> productMenu = menuBuilder.getCommonMenu(
                "Product management",
                productRepository,
                productFactory
        );

        CommonTopLevelMenu<User> userMenu = menuBuilder.getCommonMenu(
                "User management",
                userRepository,
                userFactory
        );

        CommonTopLevelMenu<Sale> saleMenu = menuBuilder.getCommonMenu(
                "Sale management",
                saleRepository,
                saleFactory
        );

        CommonTopLevelMenu<?> veryMainMenu = menuBuilder.combine(
                asList(
                        productMenu,
                        userMenu,
                        saleMenu
                )
        );

        veryMainMenu.execute();
    }
}
