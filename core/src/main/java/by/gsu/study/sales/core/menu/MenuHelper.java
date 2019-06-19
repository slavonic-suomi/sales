package by.gsu.study.sales.core.menu;

import java.util.Scanner;

public class MenuHelper {

    private static Scanner sc = new Scanner(System.in);

    public static int getIndex() {
        return getIndex(Integer.MAX_VALUE);
    }

    public static int getIndex(int limit) {

        while (true) {
            String line = sc.nextLine();
            try {
                int result = Integer.parseInt(line);

                if (result <= 0 || result >= limit) {
                    System.out.println("Wrong index, try again");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Can't parse number, try again");
            }
        }

    }
}
