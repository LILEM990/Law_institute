package com.sang.law.dao;

import com.sang.law.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    @Select("select * from orders")
    List<Order> findAllOrder();

    @Insert("insert into orders value(#{order_id},#{user_id},#{user_name},#{lawyer_id},#{lawyer_name},#{order_descrip},#{order_type},#{done},#{start},#{end})")
    boolean addOrder(Order order);

    @Delete("delete from orders where order_id=#{order_id}")
    boolean deleteOrderById(String order_id);

    @Update("update orders set user_id=#{user_id},user_name=#{user_name},lawyer_id=#{lawyer_id},lawyer_name=#{lawyer_name},order_descrip=#{order_descrip},order_type=#{order_type},done=#{done},start=#{start},end=#{end}  where order_id=#{order_id}")
    boolean updateOrder(Order order);
}
