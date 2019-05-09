package com.allaroundjava.dao;

import com.allaroundjava.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Optional;
import java.util.function.Consumer;

public class CarDaoImpl implements CarDao {
    private static final Logger log = LogManager.getLogger(CarDaoImpl.class);
    private final EntityManagerFactory emf;

    public CarDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }

    public Optional<Car> getById(Long id) {
        log.debug("Retrieving from database Car with id {}",id);
        EntityManager entityManager = emf.createEntityManager();
        return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    public void persist(Car car) {
        log.debug("Persisting Car with id {}",car.getId());
        executeInTransaction(entityManager -> entityManager.persist(car));
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        consumer.accept(entityManager);

        transaction.commit();
        entityManager.close();
    }

}
