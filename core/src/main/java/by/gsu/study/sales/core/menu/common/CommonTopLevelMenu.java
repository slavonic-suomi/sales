package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.menu.IMenuItem;
import by.gsu.study.sales.core.menu.MenuHelper;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CommonTopLevelMenu<E extends IEntity>
        implements IMenuItem<E> {

    private final List<IMenuItem<E>> items;
    private final String title;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int execute() {
        items.sort(Comparator.comparingInt(IMenuItem::getOrder));

        while (true) {
            int counter = 1;
            for (IMenuItem item : items) {
                System.out.println(counter + ": " + item.getTitle());
                counter++;
            }

            Integer index = MenuHelper.getIndex(items.size() + 1);
            int result = items.get(index - 1).execute();
            if (result == -1) {
                break;
            }
        }

        return 0;
    }
}
