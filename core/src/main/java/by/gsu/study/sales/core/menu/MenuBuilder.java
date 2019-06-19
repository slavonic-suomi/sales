package by.gsu.study.sales.core.menu;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.IRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MenuBuilder {

    private static final IMenuItem exitMenuItem = new IMenuItem<>() {
        @Override
        public String getTitle() {
            return "Exit";
        }

        @Override
        public int execute() {
            return -1;
        }

        @Override
        public int getOrder() {
            return Integer.MAX_VALUE;
        }
    };


    @SuppressWarnings("unchecked")
    public <E extends IEntity> List<IMenuItem<E>> getCommonItems(
            IRepository<E> repository,
            IFactory<E> factory
    ) {
        return asList(
                new CommonAddMenuItem<>(repository, factory),
                new CommonFindAllMenuItem<>(repository),
                new CommonUpdateMenuItem<>(repository, factory),
                new CommonDeleteMenuItem<>(repository),
                new CommonCountMenuItem<>(repository),
                exitMenuItem
        );
    }

    public <E extends IEntity> CommonTopLevelMenu<E> getCommonMenu(
            String title,
            IRepository<E> repository,
            IFactory<E> factory
    ) {
        return new CommonTopLevelMenu<>(
                getCommonItems(repository, factory),
                title
        );
    }

    @SuppressWarnings("unchecked")
    public CommonTopLevelMenu<?> combine(List<IMenuItem<?>> menus) {
        List<IMenuItem<?>> list = new ArrayList<>(menus);
        list.add(exitMenuItem);
        return new CommonTopLevelMenu(
                list,
                "Combined"
        );
    }
}
