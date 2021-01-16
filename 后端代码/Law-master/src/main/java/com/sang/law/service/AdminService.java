package com.sang.law.service;

import com.sang.law.pojo.Admin;


public interface AdminService {
    Admin loginAdmin(String admin_username, String admin_password);
}
