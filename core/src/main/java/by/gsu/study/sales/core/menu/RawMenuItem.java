package by.gsu.study.sales.core.menu;

public interface RawMenuItem {
    String getTitle();

    default int execute() {
        return -1;
    }

    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
