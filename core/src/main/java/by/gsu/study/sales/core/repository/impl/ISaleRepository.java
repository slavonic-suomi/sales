package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.repository.IRepository;

import java.util.List;

public interface ISaleRepository extends IRepository<Sale> {

    List<Sale> findAllEager();

}
