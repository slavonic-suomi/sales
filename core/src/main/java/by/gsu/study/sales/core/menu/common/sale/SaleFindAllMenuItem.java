package by.gsu.study.sales.core.menu.common.sale;

import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.menu.common.CommonFindAllMenuItem;
import by.gsu.study.sales.core.repository.impl.ISaleRepository;

import java.util.List;

public class SaleFindAllMenuItem extends CommonFindAllMenuItem<Sale> {

    public SaleFindAllMenuItem(ISaleRepository repository) {
        super(repository);
    }

    @Override
    public int execute() {
        List<Sale> sales = ((ISaleRepository) repository).findAllEager();
        sales.forEach(System.out::println);
        return 0;
    }
}
