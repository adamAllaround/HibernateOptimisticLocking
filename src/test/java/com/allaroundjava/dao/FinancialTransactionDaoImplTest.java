package com.allaroundjava.dao;

import com.allaroundjava.model.FinancialTransaction;
import com.allaroundjava.model.FinancialTransaction;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class FinancialTransactionDaoImplTest {
    private EntityManagerFactory emf;
    private BaseDao<FinancialTransaction> financialTransactionDao;

    public FinancialTransactionDaoImplTest() {
        this.emf = Persistence.createEntityManagerFactory("hibernateOptimisticLocking");
        this.financialTransactionDao = new FinancialTransactionDaoImpl(emf);
    }

    @Test
    public void whenModifyingFinancialTransaction_thenTimestampUpdated() {
        FinancialTransaction financialTransaction = createFinancialTransaction("123456789");
        financialTransactionDao.persist(financialTransaction);
        LocalDateTime originalDateCreated = financialTransactionDao.getById(financialTransaction.getId()).get().getDateCreated();

        financialTransactionDao.executeInTransaction(entityManager -> {
            FinancialTransaction financialTransactionInTransaction = entityManager.find(FinancialTransaction.class, financialTransaction.getId());
            financialTransactionInTransaction.setAccountNumber("0000001");
        });

        FinancialTransaction retrievedFinancialTransaction = financialTransactionDao.getById(financialTransaction.getId()).get();
        Assert.assertNotEquals(originalDateCreated, retrievedFinancialTransaction.getDateCreated());
    }

    private FinancialTransaction createFinancialTransaction(String accountNumber) {
        FinancialTransaction financialTransaction = new FinancialTransaction();
        financialTransaction.setAccountNumber(accountNumber);
        return financialTransaction;
    }
}