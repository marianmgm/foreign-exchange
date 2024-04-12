package com.example.foreignexchange.repository;

import com.example.foreignexchange.exceptions.EntityNotFoundException;
import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.repository.ExchangeRateRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public ExchangeRateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ExchangeRate getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            ExchangeRate exchangeRate = session.get(ExchangeRate.class, id);
            if (exchangeRate == null) {
                throw new EntityNotFoundException("Exchange rate", id);
            }
            return exchangeRate;
        }
    }

    @Override
    public void create(ExchangeRate exchangeRate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(exchangeRate);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(ExchangeRate exchangeRate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(exchangeRate);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        ExchangeRate exchangeRate = getById(id);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(exchangeRate);
            session.getTransaction().commit();
        }


    }
}
