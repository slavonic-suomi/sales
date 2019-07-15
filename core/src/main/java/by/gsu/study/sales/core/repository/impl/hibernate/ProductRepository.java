package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.IProductRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepository
        extends AbstractHibernateRepository<Product>
        implements IProductRepository {

    public ProductRepository() {
        super(Product.class);
    }

}
