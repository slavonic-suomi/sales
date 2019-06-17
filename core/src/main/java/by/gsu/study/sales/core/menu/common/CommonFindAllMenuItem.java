package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonFindAllMenuItem<E extends IEntity>
        implements IMenuItem<E> {

    private final IRepository<E> repository;

    @Override
    public String getTitle() {
        return "Print all";
    }

    @Override
    public int execute() {
        repository
                .findAll()
                .forEach(System.out::println);
        return 0;
    }
}
