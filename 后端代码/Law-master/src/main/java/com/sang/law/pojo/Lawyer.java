package com.sang.law.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lawyer {
    private String id;
    private String lawyer_username;
    private String lawyer_password;
    private String name;
    private int age;
    private String gender;
    private int worktime;
    private int cases;
    private String descrip;
    private boolean free;
    private String casetype;
    private byte[] pic;
    private String company;
}
