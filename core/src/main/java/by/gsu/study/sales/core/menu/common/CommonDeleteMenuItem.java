package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuHelper;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonDeleteMenuItem<E extends IEntity> implements IMenuItem<E> {

    private final IRepository<E> repository;

    @Override
    public String getTitle() {
        return "Delete record";
    }

    @Override
    public int execute() {
        System.out.println("Input record id: ");
        int id = MenuHelper.getIndex();

        repository.deleteById(id);

        return 0;
    }

    @Override
    public int getOrder() {
        return 30;
    }
}
