package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuHelper;

import java.util.Comparator;
import java.util.List;

public class CommonTopLevelMenu<E extends IEntity>
        extends TopLevelMenu
        implements IMenuItem<E> {

    public CommonTopLevelMenu(List<IMenuItem<E>> items, String title) {
        super(items, title);
    }

}
