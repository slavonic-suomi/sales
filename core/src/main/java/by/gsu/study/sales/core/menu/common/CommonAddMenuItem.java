package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.context.CommonConfig;
import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class CommonAddMenuItem<E extends IEntity>
        implements IMenuItem<E> {

    private final IRepository<E> repository;
    private final IFactory<E> factory;

    @Override
    public String getTitle() {
        return "Add record";
    }

    @Override
    @Transactional
    public int execute() {
        E entity = factory.create();

        if (entity.getId() == null) {
            log.info("new item!");
        }

        repository.save(entity);
        return 0;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
