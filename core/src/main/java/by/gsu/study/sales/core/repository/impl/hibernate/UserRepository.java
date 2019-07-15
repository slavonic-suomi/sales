package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import org.springframework.stereotype.Repository;

@Repository("userHibernateRepository")
public class UserRepository
        extends AbstractHibernateRepository<User>
        implements IRepository<User> {

    public UserRepository() {
        super(User.class);
    }

}
