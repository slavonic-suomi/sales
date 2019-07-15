package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.Category;
import by.gsu.study.sales.core.entity.User;
import by.gsu.study.sales.core.repository.IRepository;
import by.gsu.study.sales.core.repository.impl.ICategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository
        extends AbstractHibernateRepository<Category>
        implements ICategoryRepository {

    public CategoryRepository() {
        super(Category.class);
    }

}
