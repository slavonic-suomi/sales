package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuHelper;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonUpdateMenuItem<E extends IEntity> implements IMenuItem<E> {

    private final IRepository<E> repository;
    private final IFactory<E> factory;

    @Override
    public String getTitle() {
        return "Update record";
    }

    @Override
    public int execute() {
        System.out.println("Input record id: ");
        int id = MenuHelper.getIndex();

        E entity = factory.create();
        entity.setId(id);

        repository.save(entity);

        return 0;
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
