package dev.renato.waiter.dao;

import dev.renato.waiter.entity.Order;

import java.util.List;

public interface OrderDAO {

    Order findById(int id);

    Order findByIdJoinFetch(int id);
    List<Order> findAll();
    Order save(Order order);

    void deleteById(int id);
}
