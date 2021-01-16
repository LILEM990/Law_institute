package com.sang.law.controller;


import com.sang.law.pojo.Lawyer;

import com.sang.law.service.LawyerService;
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
@RequestMapping("/lawyer")
public class LawyerController {
    @Autowired
    private LawyerService lawyerService;

    @RequestMapping("/findAllLawyer")
    public String findALLLawyer() {
        return new JSONArray(lawyerService.findAllLawyer()).toString();
    }

    @RequestMapping("/addLawyer")
    public boolean addLawyer(@RequestBody Lawyer lawyer) {
        lawyer.setId(UUID.randomUUID().toString());
        return lawyerService.addLawyer(lawyer);
    }

    @RequestMapping("/deleteLawyerById")
    public boolean deleteLawyerById(String id) {
        return lawyerService.deleteLawyerById(id);
    }

    @RequestMapping("/loginLawyer")  //登录
    public Lawyer loginLawyer(String lawyer_username, String lawyer_password) {
        return lawyerService.loginLawyer(lawyer_username, lawyer_password);
    }

    @RequestMapping("/updateLawyer") //修改
    public boolean updateLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.updateLawyer(lawyer);
    }

    @RequestMapping("/getpiclawyer")
    public void getPicById(final HttpServletResponse response, String id) throws IOException {
        Lawyer lawyer = lawyerService.getLawyer(id);
        byte[] data2 = lawyer.getPic();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream os2 = response.getOutputStream();
        InputStream in2 = new ByteArrayInputStream(data2);
        int len2 = 0;
        byte[] buf2 = new byte[1024];
        while ((len2 = in2.read(buf2, 0, 1024)) != -1) {
            os2.write(buf2, 0, len2);
        }
        os2.close();
    }

    @RequestMapping("/savepiclawyer")
    public String savePic(@RequestBody Lawyer lawyer, @RequestParam("file") MultipartFile file) {
        try {
            InputStream is2 = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            is2.read(pic);
            lawyer.setPic(pic);
            lawyerService.updateLawyer(lawyer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
