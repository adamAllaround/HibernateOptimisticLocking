package com.allaroundjava.dao;

import com.allaroundjava.model.Car;

import javax.persistence.EntityManagerFactory;

public class CarDaoImpl extends BaseDao<Car> {
    public CarDaoImpl(EntityManagerFactory emf) {
        super(Car.class, emf);
    }
}
