package com.sang.law.controller;

import com.sang.law.pojo.User;
import com.sang.law.service.UserService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser") //查询所有
    public String findAllUser() {
        return new JSONArray(userService.findAllUser()).toString();

    }

    @RequestMapping("/addUser") //增加用户
    public boolean addUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        return userService.addUser(user);
    }

    @RequestMapping("/deleteUserById") //删除用户
    public boolean deleteUserById(String id) {
        return userService.deleteUserById(id);
    }

    @RequestMapping("/login")  //登录
    public User login(String username, String password) {
        return userService.login(username, password);
    }

    @RequestMapping("/updateUser") //修改
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/getpic")
    public void getPicById(final HttpServletResponse response, String id) throws IOException {
        User user = userService.getUser(id);
        byte[] data = user.getPic();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream os = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            os.write(buf, 0, len);
        }
        os.close();
    }

    @RequestMapping("/savepic")
    public String savePic(@RequestBody User user, @RequestParam("file") MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            is.read(pic);
            user.setPic(pic);
            userService.updateUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
