package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommonCountMenuItem<E extends IEntity> implements IMenuItem<E> {

    private final IRepository<E> repository;

    @Override
    public String getTitle() {
        return "Print records count";
    }

    @Override
    @Transactional
    public int execute() {
        int count = repository.count();
        System.out.println(count);
        return 0;
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
