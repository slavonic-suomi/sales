package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.menu.MenuHelper;
import by.gsu.study.sales.core.menu.RawMenuItem;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class TopLevelMenu implements RawMenuItem {

    private final List<? extends RawMenuItem> items;
    private final String title;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int execute() {
        items.sort(Comparator.comparingInt(RawMenuItem::getOrder));

        while (true) {
            int counter = 1;
            for (RawMenuItem item : items) {
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
