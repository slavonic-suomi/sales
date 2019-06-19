package by.gsu.study.sales.core.repository.impl;

import by.gsu.study.sales.core.entity.IEntity;
import by.gsu.study.sales.core.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryRepository<E extends IEntity>
    implements IRepository<E> {

    private List<E> list = new ArrayList<>();
    private AtomicInteger ai = new AtomicInteger(1);

    @Override
    public void save(E entity) {
        if (entity.getId() != null) {
           update(entity);
        } else {
           create(entity);
        }
    }

    private void create(E entity) {
        entity.setId(ai.incrementAndGet());
        list.add(entity);
    }

    private void update(E entity) {
        boolean updated = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(entity.getId())) {
                list.set(i, entity);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new IllegalArgumentException(
                "Can't find entity with id = " + entity.getId()
            );
        }
    }

    @Override
    public void deleteById(Integer id) {
        list.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public E findById(Integer id) {
        return list
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {
        return list.size();
    }
}
