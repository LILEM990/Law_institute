package com.sang.law.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String admin_id;
    private String admin_username;
    private String admin_password;
    private String power;
}
