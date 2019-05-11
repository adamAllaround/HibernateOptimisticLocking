package com.allaroundjava.dao;

import com.allaroundjava.model.House;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HouseDaoImplTest {
    private EntityManagerFactory emf;
    private BaseDao<House> houseDao;

    public HouseDaoImplTest() {
        this.emf = Persistence.createEntityManagerFactory("hibernateOptimisticLocking");
        this.houseDao = new HouseDaoImpl(emf);
    }

    @Test
    public void whenModifyingHouse_thenDirtyPropertiesChecked() {
        House house = new House();
        house.setAddress("Summer Street");
        house.setOwnerName("John");
        houseDao.persist(house);

        houseDao.executeInTransaction(entityManager -> {
            House houseInTransaction = entityManager.find(House.class, house.getId());
            houseInTransaction.setAddress("Winter Street");
        });
    }
}