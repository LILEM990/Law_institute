package com.sang.law.dao;

import com.sang.law.pojo.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KnowledgeDao {
    @Select("select * from knowledge")
    List<Knowledge> getAllKnowledge();
}
