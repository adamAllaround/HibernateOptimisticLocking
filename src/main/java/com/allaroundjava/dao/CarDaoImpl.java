package com.allaroundjava.dao;

import com.allaroundjava.model.Car;

import javax.persistence.EntityManagerFactory;

public class CarDaoImpl extends BaseDao<Car> {
    CarDaoImpl(EntityManagerFactory emf) {
        super(Car.class, emf);
    }
}
