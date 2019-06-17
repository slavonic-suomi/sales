package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.factory.ReflectionFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.CommonAddMenuItem;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.menu.product.ProductAddMenuItem;
import by.gsu.study.sales.core.menu.common.CommonFindAllMenuItem;
import by.gsu.study.sales.core.menu.product.ProductTopLevelMenu;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.SaleRepository;
import by.gsu.study.sales.core.repository.impl.UserRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;
import by.gsu.study.sales.core.repository.impl.parser.SaleParser;
import by.gsu.study.sales.core.repository.impl.parser.UserParser;

import java.util.Arrays;

public class ProductMenuMain {
    public static void main(String[] args) {
        LiquibaseManager.initDatabase();

        ProductRepository productRepository = new ProductRepository(new ProductParser());
        IFactory<Product> productIFactory = new ReflectionFactory<>(Product.class);

        CommonTopLevelMenu<Product> productMenu = new CommonTopLevelMenu<>(
                Arrays.asList(
                        new CommonAddMenuItem<>(productRepository, productIFactory),
                        new CommonFindAllMenuItem<>(productRepository),
                        new IMenuItem<>() {
                            @Override
                            public String getTitle() {
                                return "Exit";
                            }

                            @Override
                            public int execute() {
                                return -1;
                            }
                        }
                ),
                "Product management"
        );

        UserRepository userRepository = new UserRepository(new UserParser());
        IFactory<User> userIFactory = new ReflectionFactory<>(User.class);

        CommonTopLevelMenu<User> userMenu = new CommonTopLevelMenu<>(
                Arrays.asList(
                        new CommonAddMenuItem<>(userRepository, userIFactory),
                        new CommonFindAllMenuItem<>(userRepository),
                        new IMenuItem<>() {
                            @Override
                            public String getTitle() {
                                return "Exit";
                            }

                            @Override
                            public int execute() {
                                return -1;
                            }
                        }
                ),
                "User management"
        );

        SaleRepository saleRepository = new SaleRepository(new SaleParser(
                productRepository,
                userRepository
        ));

        CommonTopLevelMenu topMenu = new CommonTopLevelMenu(
                Arrays.asList(
                        userMenu,
                        productMenu,
                        new IMenuItem() {
                            @Override
                            public String getTitle() {
                                return "Exit";
                            }

                            @Override
                            public int execute() {
                                return -1;
                            }
                        }
                ),
                "All management"
        );

        topMenu.execute();
    }
}
