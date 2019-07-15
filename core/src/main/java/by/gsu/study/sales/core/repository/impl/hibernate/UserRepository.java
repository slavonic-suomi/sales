package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
        extends AbstractHibernateRepository<User>
        implements IUserRepository {

    public UserRepository() {
        super(User.class);
    }

}
