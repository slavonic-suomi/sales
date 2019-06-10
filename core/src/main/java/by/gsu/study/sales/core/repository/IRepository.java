package by.gsu.study.sales.core.repository;

import by.gsu.study.sales.core.entity.IEntity;

import java.util.List;

public interface IRepository<E extends IEntity> {
    void save(E entity);

    void deleteById(Integer id);

    List<E> findAll();

    E findById(Integer id);
}
