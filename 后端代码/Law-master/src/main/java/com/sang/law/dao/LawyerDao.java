package com.sang.law.dao;

import com.sang.law.pojo.Lawyer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LawyerDao {
    @Select("select * from lawyers")
    List<Lawyer> findAllLawyer();

    @Insert("insert into lawyers value(#{id},#{lawyer_username},#{lawyer_password},#{name},#{age},#{gender},#{worktime},#{cases},#{descrip},#{free},#{casetype},#{pic},#{company})")
    boolean addLawyer(Lawyer lawyer);

    @Delete("delete from lawyers where id=#{id}")
    boolean deleteLawyerById(String id);

    @Select("select * from lawyers where lawyer_username=#{lawyer_username} and lawyer_password=#{lawyer_password}")
    Lawyer loginLawyer(@Param("lawyer_username") String lawyer_username, @Param("lawyer_password") String lawyer_password);

    @Update("update lawyers set lawyer_username=#{lawyer_username},lawyer_password=#{lawyer_password},name=#{name},age=#{age},gender=#{gender},worktime=#{worktime},cases=#{cases},descrip=#{descrip},free=#{free},casetype=#{casetype},pic=#{pic},company=#{company} where id=#{id}")
    boolean updateLawyer(Lawyer lawyer);

    @Select("select * from lawyers where id=#{id}")
    Lawyer findLawyerById(String id);
}
