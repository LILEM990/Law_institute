package com.sang.law.service.impl;

import com.sang.law.dao.OrderDao;
import com.sang.law.pojo.Order;
import com.sang.law.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServicelmpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAllOrder() {
        return orderDao.findAllOrder();
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public boolean deleteOrderById(String order_id) {
        return orderDao.deleteOrderById(order_id);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }


}