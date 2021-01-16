package com.sang.law.dao;


import com.sang.law.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminDao {
    @Select("select * from admin where admin_username=#{admin_username} and admin_password=#{admin_password}")
    Admin loginAdmin(@Param("admin_username") String admin_username, @Param("admin_password") String admin_password);
}
