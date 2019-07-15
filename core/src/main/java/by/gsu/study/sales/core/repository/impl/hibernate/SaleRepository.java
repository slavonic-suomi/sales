package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Sale;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.ISaleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SaleRepository
        extends AbstractHibernateRepository<Sale>
        implements ISaleRepository {

    public SaleRepository() {
        super(Sale.class);
    }

    @Override
    public List<Sale> findAllEager() {
        TypedQuery<Sale> query = em.createQuery(
                "select s from Sale s " +
                        " inner join fetch s.product " +
                        " inner join fetch s.user",
                Sale.class);

        return query.getResultList();
    }
}
