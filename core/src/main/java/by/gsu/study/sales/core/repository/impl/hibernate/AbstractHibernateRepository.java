package by.gsu.study.sales.core.repository.impl.hibernate;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.repository.IRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Transactional
public class AbstractHibernateRepository<E extends IEntity>
    implements IRepository<E> {

    protected final Class<E> entityClass;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(E entity) {
        em.persist(entity);
    }

    @Override
    public void deleteById(Integer id) {
        E reference = em.getReference(entityClass, id);
        em.remove(reference);

    }

    @Override
    public List<E> findAll() {
        TypedQuery<E> query = em.createQuery("from " + entityClass.getName(), entityClass);
        return query.getResultList();
    }

    @Override
    public E findById(Integer id) {
        return em.find(entityClass, id);
    }

    @Override
    public int count() {
        TypedQuery<Integer> query = em.createQuery("select count(e) from " + entityClass.getName() + " e", Integer.class);
        return query.getSingleResult();
    }
}
