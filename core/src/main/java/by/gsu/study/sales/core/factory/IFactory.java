package by.gsu.study.sales.core.factory;

import by.gsu.study.sales.core.entity.IEntity;

public interface IFactory<E extends IEntity> {
    E create();
}
