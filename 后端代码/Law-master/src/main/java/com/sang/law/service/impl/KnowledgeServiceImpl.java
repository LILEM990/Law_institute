package com.sang.law.service.impl;

import com.sang.law.dao.KnowledgeDao;
import com.sang.law.pojo.Knowledge;
import com.sang.law.service.KnowledegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledegService {
    @Autowired
    private KnowledgeDao knowledgeDao;

    @Override
    public List<Knowledge> getAllKnowledge() {
        return knowledgeDao.getAllKnowledge();
    }
}
