package by.gsu.study.sales.core.menu;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.menu.common.*;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class MenuBuilder {

    /* get common "exit" menu item with generic-safety */
    private static <E extends IEntity> IMenuItem<E> getExitMenuItem() {
        return new IMenuItem<>() {
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
    }

    /* get all CRUD menu items for single entity */
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
                getExitMenuItem()
        );
    }

    /* get titled top level menu for entity with all common items */
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
        list.add(getExitMenuItem());
        return new CommonTopLevelMenu(
                list,
                "Combined"
        );
    }
}
