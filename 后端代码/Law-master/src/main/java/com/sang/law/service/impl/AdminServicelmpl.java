package com.sang.law.service.impl;

import com.sang.law.dao.AdminDao;
import com.sang.law.pojo.Admin;
import com.sang.law.pojo.Lawyer;
import com.sang.law.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicelmpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin loginAdmin(String admin_username, String admin_password) {
        return adminDao.loginAdmin(admin_username, admin_password);
    }
}
