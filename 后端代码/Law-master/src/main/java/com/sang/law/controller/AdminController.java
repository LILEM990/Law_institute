package com.sang.law.controller;

import com.sang.law.pojo.Admin;
import com.sang.law.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/loginAdmin")  //登录
    public Admin loginAdmin(String admin_username, String admin_password) {
        return adminService.loginAdmin(admin_username, admin_password);
    }
}
