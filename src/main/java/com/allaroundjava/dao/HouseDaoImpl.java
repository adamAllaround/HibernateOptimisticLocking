package com.allaroundjava.dao;

import com.allaroundjava.model.House;

import javax.persistence.EntityManagerFactory;

public class HouseDaoImpl extends BaseDao<House> {
    HouseDaoImpl(EntityManagerFactory emf) {
        super(House.class, emf);
    }
}
