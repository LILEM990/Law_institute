package com.sang.law.service;

import com.sang.law.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrder();

    boolean addOrder(Order order);

    boolean deleteOrderById(String order_id);

    boolean updateOrder(Order order);

}