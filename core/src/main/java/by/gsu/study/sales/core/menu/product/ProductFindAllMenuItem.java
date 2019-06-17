package by.gsu.study.sales.core.menu.product;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
public class ProductFindAllMenuItem implements IMenuItem {

    private final IRepository<Product> productRepository;

    @Override
    public String getTitle() {
        return "Print all products";
    }

    @Override
    public int execute() {
        productRepository
                .findAll()
                .forEach(System.out::println);
        return 0;
    }
}
