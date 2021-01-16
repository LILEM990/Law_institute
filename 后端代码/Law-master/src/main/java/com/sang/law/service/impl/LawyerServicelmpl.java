package com.sang.law.service.impl;

import com.sang.law.dao.LawyerDao;
import com.sang.law.pojo.Lawyer;
import com.sang.law.service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawyerServicelmpl implements LawyerService {
    @Autowired
    private LawyerDao lawyerDao;

    @Override
    public List<Lawyer> findAllLawyer() {
        return lawyerDao.findAllLawyer();
    }

    @Override
    public boolean addLawyer(Lawyer lawyer) {
        return lawyerDao.addLawyer(lawyer);
    }

    @Override
    public boolean deleteLawyerById(String id) {
        return lawyerDao.deleteLawyerById(id);
    }

    @Override
    public Lawyer getLawyer(String id) {
        return lawyerDao.findLawyerById(id);
    }

    @Override
    public Lawyer loginLawyer(String lawyer_username, String lawyer_password) {
        return lawyerDao.loginLawyer(lawyer_username, lawyer_password);
    }

    @Override
    public boolean updateLawyer(Lawyer lawyer) {
        return lawyerDao.updateLawyer(lawyer);
    }
}
