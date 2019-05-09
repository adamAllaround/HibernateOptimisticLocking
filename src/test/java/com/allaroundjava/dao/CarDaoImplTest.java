package com.allaroundjava.dao;

import com.allaroundjava.model.Car;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class CarDaoImplTest {
    private EntityManagerFactory emf;
    private BaseDao<Car> carDao;

    public CarDaoImplTest() {
        this.emf = Persistence.createEntityManagerFactory("hibernateOptimisticLocking");
        carDao = new CarDaoImpl(emf);
    }

    @Test
    public void whenPersistingCar_thenVersionNumberIsZero() {
        Car car = createCar("Ford", "Galaxy");
        carDao.persist(car);
        Optional<Car> retrievedCarOptional = carDao.getById(car.getId());
        Assert.assertEquals(0, retrievedCarOptional.get().getVersion());
    }

    @Test
    public void whenModifyingCar_thenVersionNumberIncremented() {
        Car car = createCar("Fiat", "Bravo");
        carDao.persist(car);
        Optional<Car> retrievedCarOptional = carDao.getById(car.getId());
        Assert.assertEquals(0, retrievedCarOptional.get().getVersion());

        carDao.executeInTransaction(entityManager -> {
            Car carInTransaction = entityManager.find(Car.class, car.getId());
            carInTransaction.setModel("Ducato");
        });

        retrievedCarOptional = carDao.getById(car.getId());
        Assert.assertEquals(1, retrievedCarOptional.get().getVersion());
    }

    private Car createCar(String make, String model) {
        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        return car;
    }


}