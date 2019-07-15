package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("productHibernateRepository")
public class ProductRepository
        extends AbstractHibernateRepository<Product>
        implements IRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

//    @Override
//    public List<Product> findAll() {
//        String hql = "select p from Product p";
//        TypedQuery<Product> query
//                = em.createQuery(hql, Product.class);
//
//        return query.getResultList();
//
//    }


//    @Override
//    public List<Product> findAll() {
//        List<Product> products = super.findAll();
//        for (Product product : products) {
//            System.out.println(product.getName()
//                    + " " + product.getSales());
//        }
//        return products;
//    }
}
