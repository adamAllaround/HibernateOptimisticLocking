package com.allaroundjava.dao;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.function.Consumer;

public interface Dao<T> {
    Optional<T> getById(Long id);

    void persist(T item);

    void executeInTransaction(Consumer<EntityManager> consumer);

}
