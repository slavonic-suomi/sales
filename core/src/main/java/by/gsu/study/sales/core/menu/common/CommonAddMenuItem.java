package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonAddMenuItem<E extends IEntity>
        implements IMenuItem<E> {

    private final IRepository<E> repository;
    private final IFactory<E> factory;

    @Override
    public String getTitle() {
        return "Add record";
    }

    @Override
    public int execute() {
        E product = factory.create();
        repository.save(product);
        return 0;
    }
}
