package by.gsu.study.sales.core.menu.product;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
public class ProductAddMenuItem implements IMenuItem {

    private final IRepository<Product> repository;
    private final IFactory<Product> factory;

    @Override
    public String getTitle() {
        return "Add product";
    }

    @Override
    public int execute() {
        Product product = factory.create();
        repository.save(product);
        return 0;
    }
}
