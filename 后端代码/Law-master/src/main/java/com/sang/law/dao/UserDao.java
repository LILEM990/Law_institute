package com.sang.law.dao;

import com.sang.law.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Select("select * from users")
    List<User> findAllUser();

    @Insert("insert into users value(#{id},#{username},#{password},#{nickname},#{type},#{pic})")
    boolean addUser(User user);

    @Delete("delete from users where id=#{id}")
    boolean deleteUserById(String id);

    @Select("select * from users where username=#{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Update("update users set username=#{username},password=#{password},nickname=#{nickname},type=#{type},pic=#{pic} where id=#{id}")
    boolean updateUser(User user);

    @Select("select * from users where id=#{id}")
    User findUserById(String id);
}
