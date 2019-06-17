package by.gsu.study.sales.core.menu;

import by.gsu.study.sales.core.entity.IEntity;

public interface IMenuItem<E extends IEntity> {

    String getTitle();

    int execute();

    default int getOrder() {
        return 0;
    }
}
