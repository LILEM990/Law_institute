
package com.sang.law.controller;

import com.sang.law.pojo.Order;
import com.sang.law.service.OrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrder") //查询所有
    public String findAllOrder() {
        return new JSONArray(orderService.findAllOrder()).toString();
    }

    @RequestMapping("/addOrder") //增加订单
    public boolean addOrder(@RequestBody Order order) {
        order.setOrder_id(UUID.randomUUID().toString());
        return orderService.addOrder(order);
    }

    @RequestMapping("/deleteOrderById") //删除订单
    public boolean deleteOrderById(String order_id) {
        return orderService.deleteOrderById(order_id);
    }

    @RequestMapping("/updateOrder") //修改
    public boolean updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }


}