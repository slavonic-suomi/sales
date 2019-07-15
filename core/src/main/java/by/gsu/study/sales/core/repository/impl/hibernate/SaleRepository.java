package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.stereotype.Repository;

@Repository("saleHibernateRepository")
public class SaleRepository
        extends AbstractHibernateRepository<Sale>
        implements IRepository<Sale> {

    public SaleRepository() {
        super(Sale.class);
    }

}
