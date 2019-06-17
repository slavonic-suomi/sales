package by.gsu.study.sales.core;

import by.gsu.study.sales.core.context.LiquibaseManager;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.factory.ReflectionFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.common.CommonAddMenuItem;
import by.gsu.study.sales.core.menu.common.CommonFindAllMenuItem;
import by.gsu.study.sales.core.menu.common.CommonTopLevelMenu;
import by.gsu.study.sales.core.repository.impl.ProductRepository;
import by.gsu.study.sales.core.repository.impl.parser.ProductParser;

import java.util.Arrays;

public class LordOfTheMains {
    public static void main(String[] args) {
        LiquibaseManager.initDatabase();

        ProductRepository repository = new ProductRepository(new ProductParser());
        IFactory<Product> factory = new ReflectionFactory<>(Product.class);

        CommonTopLevelMenu<Product> menu = new CommonTopLevelMenu<>(
                Arrays.asList(
                        new CommonAddMenuItem<>(repository, factory),
                        new CommonFindAllMenuItem<>(repository),
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
    }
}
