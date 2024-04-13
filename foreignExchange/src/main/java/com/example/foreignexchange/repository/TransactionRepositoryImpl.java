package com.example.foreignexchange.repository;

import com.example.foreignexchange.exceptions.EntityNotFoundException;
import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public TransactionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Transaction getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.get(Transaction.class, id);
            if (transaction == null) {
                throw new EntityNotFoundException("Transaction", id);
            }
            return transaction;
        }
    }

    @Override
    public void create(Transaction transaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(transaction);
            session.getTransaction().commit();
        }

    }
    @Override
    public List<Transaction> getFilteredTransactions(TransactionFilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
            Root<Transaction> root = query.from(Transaction.class);
            List<Predicate> predicates = new ArrayList<>();

            if (filterOptions.getDay() != null) {
                LocalDateTime startOfDay = filterOptions.getDay().atStartOfDay();
                LocalDateTime endOfDay = filterOptions.getDay().plusDays(1).atStartOfDay();
                predicates.add(builder.between(root.get("timestamp"), startOfDay, endOfDay));
            }

            if (filterOptions.getStartDate() != null && filterOptions.getEndDate() != null) {
                LocalDateTime startDateTime = filterOptions.getStartDate().atStartOfDay();
                LocalDateTime endDateTime = filterOptions.getEndDate().plusDays(1).atStartOfDay();
                predicates.add(builder.between(root.get("timestamp"), startDateTime, endDateTime));
            }

            query.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(query).getResultList();
        }
    }
}
