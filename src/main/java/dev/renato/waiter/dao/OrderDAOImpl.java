package dev.renato.waiter.dao;

import dev.renato.waiter.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order findById(int id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Order findByIdJoinFetch(int id) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o LEFT JOIN FETCH o.itemsOrdered WHERE o.id = :data", Order.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }


    @Override
    public List<Order> findAll() {
        TypedQuery<Order> query = entityManager.createQuery("FROM Order", Order.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return entityManager.merge(order);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Order order = findByIdJoinFetch(id);
        entityManager.remove(order);
    }
}
